public class BlackJack {
    private Player[] players;
    private CardSet cardOnTable = new CardSet(GameConfig.getSetOfCard());

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
        printGameResult();
        resetAllPlayers();
    }

    /**
     * 詢問每位玩家下注的金額
     */
    private void askChipValue() {
        for (Player player : players) {
            if (!player.isBookmaker()) {
                System.out.println("玩家" + player.getPlayerNo());
                System.out.print("請輸入你的籌碼 $");
                String tmp = Utils.sc.nextLine();
                int amount = Integer.parseInt(tmp);
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
                            System.out.println("#玩家" + player.getPlayerNo() + " 爆牌輸了");
                            player.setStatus(PlayerStatus.LOSE);
                            break;
                        }
                    } else {
                        player.setStopGetCard(true);
                        break;
                    }
                    if (i == 4) {
                        System.out.println("#玩家" + player.getPlayerNo() + " 過五關未爆牌贏了");
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
                System.out.println("#莊家爆牌輸了");
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
            if (player.isBookmaker() || !(player.getStatus() == PlayerStatus.PLAYING)) {
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
        }
    }

    /**
     * 印出遊戲結果
     */
    private void printGameResult() {
        System.out.println("\n\n==========遊戲結果==========");
        System.out.println("莊家的牌為:");
        players[0].getSet().print();
        for (Player player : players) {
            if (player.getStatus() == PlayerStatus.WIN && !player.isBookmaker()) {
                System.out.print("玩家" + player.getPlayerNo() + "贏了, ");
                Account.transfer(players[0].getAccount(), player.getAccount(), player.getChipValue());
            } else if (player.getStatus() == PlayerStatus.LOSE && !player.isBookmaker()) {
                System.out.print("玩家" + player.getPlayerNo() + "輸了, ");
                Account.transfer(player.getAccount(), players[0].getAccount(), player.getChipValue());
            } else if (player.getStatus() == PlayerStatus.TIE && !player.isBookmaker()) {
                System.out.print("玩家" + player.getPlayerNo() + "與莊家平手, ");
            }
            if (!player.isBookmaker()) {
                player.printAccountBalance();
            }
        }
        System.out.println("============================\n\n");
    }

    /**
     * 將所有player重置成遊戲開始前的樣子，除了account
     */
    private void resetAllPlayers() {
        for (Player player : players) {
            player.startANewGame();
        }
    }

}
