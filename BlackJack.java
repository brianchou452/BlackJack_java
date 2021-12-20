public class BlackJack {
    private Player[] players;
    private CardSet cardOnTable = new CardSet(GameConfig.getSetOfCard());
    private View view = new View();

    public BlackJack() {
        players = new Player[GameConfig.getPlayerNumber()];
        for (int i = 0; i < GameConfig.getPlayerNumber(); i++) {
            players[i] = new Player(i);
        }
        players[0].setBookmaker(true);
    }

    /**
     * 開始遊戲
     */
    public void start() {
        cardOnTable.shuffle();
        askChipValue();
        dealCard();
        dealCard();
        bookmakerAddCard();
        askPlayerToAddCard();
        compareRank();
        view.printGameResult(players);
        resetAllPlayers();
    }

    /**
     * 詢問每位玩家下注的金額
     */
    private void askChipValue() {
        for (Player player : players) {
            if (!player.isBookmaker()) {
                view.printCurrentPlayer(player.getPlayerNo());
                int amount = view.askChipValue();
                player.setChipValue(amount);
            }
        }
    }

    /**
     * 發一張牌給所有player
     */
    private void dealCard() {
        for (Player player : players) {
            player.getCard(cardOnTable.dealACard());
        }
    }

    /**
     * 詢問每位玩家是否繼續加牌
     */
    private void askPlayerToAddCard() {
        for (Player player : players) {
            if (player.isBookmaker()) {
                continue;
            }
            if (!player.isStopGetCard() && player.getStatus() == PlayerStatus.PLAYING) {
                System.out.println("\n\n----------------------");
                System.out.println("#玩家" + player.getPlayerNo());
                player.getSet().print();
                for (int i = 0; i < 5; i++) {
                    if (Utils.askYesNoQuestion("是否要加牌(y/n)")) {
                        player.getCard(cardOnTable.dealACard());
                        player.getSet().print();
                        if (player.getSet().calculateRank() > 21) {
                            view.printSinglePlayerGameResult("#玩家" + player.getPlayerNo(), "爆牌", PlayerStatus.LOSE);
                            player.setStatus(PlayerStatus.LOSE);
                            break;
                        }
                    } else {
                        player.setStopGetCard(true);
                        break;
                    }
                    if (i == 4) {
                        view.printSinglePlayerGameResult("#玩家" + player.getPlayerNo(), "過五關未爆牌", PlayerStatus.WIN);
                        player.setStatus(PlayerStatus.WIN);
                    }
                }

            }

        }
    }

    /**
     * 莊家自己加牌
     */
    private void bookmakerAddCard() {
        System.out.println("\n\n----------------------");
        System.out.println("#莊家");
        players[0].getSet().printWithHiding1stCard();
        while (players[0].getSet().calculateRank() < 17) {
            players[0].getCard(cardOnTable.dealACard());
            players[0].getSet().printWithHiding1stCard();
            if (players[0].getSet().calculateRank() > 21) {
                view.printSinglePlayerGameResult("莊家", "爆牌", PlayerStatus.LOSE);
                players[0].setStatus(PlayerStatus.LOSE);
                for (Player player : players) {
                    if (player.isBookmaker()) {
                        continue;
                    }
                    player.setStatus(PlayerStatus.WIN);
                }
            }
        }
    }

    /**
     * 所有玩家加牌完成和莊家比較點數看誰大
     */
    private void compareRank() {

        int bookmakerRank = players[0].getSet().calculateRank();
        for (Player player : players) {
            if (player.isBookmaker() || (player.getStatus() == PlayerStatus.PLAYING)) {
                continue;
            }
            int currentPlayerRank = player.getSet().calculateRank();
            if (currentPlayerRank < bookmakerRank) {
                player.setStatus(PlayerStatus.LOSE);
            } else if (currentPlayerRank == bookmakerRank) {
                player.setStatus(PlayerStatus.TIE);
            } else {
                player.setStatus(PlayerStatus.WIN);
            }
            
            if (player.getStatus() == PlayerStatus.WIN && !player.isBookmaker()) {
                Account.transfer(players[0].getAccount(), player.getAccount(), player.getChipValue());
            } else if (player.getStatus() == PlayerStatus.LOSE && !player.isBookmaker()) {
                Account.transfer(player.getAccount(), players[0].getAccount(), player.getChipValue());
            }
            
        }
    }

    /**
     * 將所有player重置成遊戲開始前的樣子，除了account
     */
    private void resetAllPlayers() {
        for (Player player : players) {
            player.startANewGame();
        }
    }

    public Player[] getPlayers() {
        return players;
    }

}
