public class BlackJack {
    // private CardSet cardOnTable = GameData.getCardOnTable();

    public BlackJack() {
        GameData.setPlayers(new Player[GameConfig.getPlayerNumber()]);
        Player[] players = GameData.getPlayers();
        for (int i = 0; i < GameConfig.getPlayerNumber(); i++) {
            players[i] = new Player(i);
        }
        players[0].setBookmaker(true);
    }

    /**
     * 開始遊戲
     */
    public void start() {
        CardSet cardOnTable = GameData.getCardOnTable();
        cardOnTable.shuffle();
        if (GameConfig.isPlayWithMoney()) {
            askChipValue();
        }
        dealCard();
        dealCard();
        printBookmakerCard();
        askPlayerToAddCard();
        bookmakerAddCard();
        compareRank();
        if (GameConfig.isPlayWithMoney()) {
            transferMoney();
        }
        View.printGameResult();
        resetAllPlayers();
    }

    /**
     * 詢問每位玩家下注的金額
     */
    private void askChipValue() {
        Player[] players = GameData.getPlayers();
        for (int i = 1; i < GameConfig.getPlayerNumber(); i++) {
            View.printCurrentPlayer(players[i].getPlayerNo());
            int amount = View.askChipValue();
            if (amount <= players[i].getAccount().getBalance()) {
                players[i].setChipValue(amount);
            } else {
                View.noMoreMoney();
                i--;
            }

        }
    }

    /**
     * 發一張牌給所有player
     */
    private void dealCard() {
        CardSet cardOnTable = GameData.getCardOnTable();
        Player[] players = GameData.getPlayers();
        for (Player player : players) {
            View.debugMessage("player" + player.getPlayerNo());
            player.getCard(cardOnTable.dealACard());
        }
    }

    /**
     * 詢問每位玩家是否繼續加牌
     */
    private void askPlayerToAddCard() {
        CardSet cardOnTable = GameData.getCardOnTable();
        Player[] players = GameData.getPlayers();
        for (Player player : players) {
            if (player.isBookmaker()) {
                continue;
            }
            if (!player.isStopGetCard() && player.getStatus() == PlayerStatus.PLAYING) {
                View.changePlayer("#玩家" + player.getPlayerNo());
                View.printCardset(player.getSet());

                /**
                 * 雙倍下注，若閒家首兩張牌點數之和為11點，
                 * 可以選擇加倍投注，但加注後僅獲發1張牌。
                 */
                if (player.getSet().calculateRank() == 11) {
                    if (Utils.askYesNoQuestion("首兩張牌點數之和為11點，是否加倍投注(y/n)")) {
                        player.getCard(cardOnTable.dealACard());
                        player.setStopGetCard(true);
                        player.setDoubleBet(true);
                        continue;
                    }
                }
                for (int i = 0; i < 5; i++) {
                    if (Utils.askYesNoQuestion("是否要加牌(y/n)")) {
                        View.debugMessage("player" + player.getPlayerNo());
                        player.getCard(cardOnTable.dealACard());
                        View.printCardset(player.getSet());
                        if (player.getSet().calculateRank() > 21) {
                            View.printSinglePlayerGameResult("玩家" + player.getPlayerNo(),
                                    "爆牌", PlayerStatus.LOSE);
                            player.setStatus(PlayerStatus.LOSE);
                            break;
                        }
                    } else {
                        player.setStopGetCard(true);
                        break;
                    }
                    if (i == 4) {
                        View.printSinglePlayerGameResult("玩家" + player.getPlayerNo(),
                                "過五關未爆牌", PlayerStatus.WIN);
                        player.setStatus(PlayerStatus.WIN);
                    }
                }

            }

        }
    }

    /**
     * 依序詢問每位玩家是否買保險
     */
    private void buyInsurance() {
        for (int i = 1; i < GameConfig.getPlayerNumber(); i++) {
            boolean buyInsuranceAns = Utils
                    .askYesNoQuestion("玩家" + GameData.getPlayers()[i].getPlayerNo() +
                            " 是否買保險(y/n)");
            GameData.getPlayers()[i].setBuyInsurance(buyInsuranceAns);
        }
    }

    private void printBookmakerCard() {
        Player[] players = GameData.getPlayers();
        View.changePlayer("#莊家");
        View.printCardsetWithHiding1stCard(players[0].getSet());
    }

    /**
     * 莊家自己加牌
     */
    private void bookmakerAddCard() {
        Player[] players = GameData.getPlayers();
        CardSet cardOnTable = GameData.getCardOnTable();
        printBookmakerCard();
        /**
         * 莊家第二張牌為A時 詢問是否買保險
         */
        if (players[0].getSet().getCards().get(1).getRank().equals(Rank.ACE)) {
            if (GameConfig.isPlayWithMoney()) {
                View.askPlayerToBuyInsurance();
                buyInsurance();
            }
        }
        while (players[0].getSet().calculateRank() < 17) {
            View.debugMessage("player"+ players[0].getPlayerNo());
            players[0].getCard(cardOnTable.dealACard());
            View.printCardsetWithHiding1stCard(players[0].getSet());
            if (players[0].getSet().calculateRank() > 21) {
                View.printSinglePlayerGameResult("莊家", "爆牌", PlayerStatus.LOSE);
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
        Player[] players = GameData.getPlayers();
        int bookmakerRank = players[0].getSet().calculateRank();
        for (Player player : players) {
            /**
             * 如果是莊家或以給定輸贏者則跳過
             */
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
     * 根據遊戲結果，依序將錢轉入或轉出
     */
    private void transferMoney() {
        Player[] players = GameData.getPlayers();
        for (Player player : players) {
            if (player.getStatus() == PlayerStatus.WIN && !player.isBookmaker()) {
                Account.transfer(players[0].getAccount(), player.getAccount(),
                        player.getChipValue());
                if (player.isDoubleBet()) {// 雙倍下注
                    Account.transfer(players[0].getAccount(), player.getAccount(),
                            player.getChipValue());
                }
            } else if (player.getStatus() == PlayerStatus.LOSE && !player.isBookmaker()) {
                Account.transfer(player.getAccount(), players[0].getAccount(),
                        player.getChipValue());
                if (player.isDoubleBet()) {// 雙倍下注
                    Account.transfer(player.getAccount(), players[0].getAccount(),
                            player.getChipValue());
                }
                /**
                 * 如果玩家有買保險則將錢歸還
                 */
                if (player.isBuyInsurance()) {
                    Account.transfer(players[0].getAccount(), player.getAccount(),
                            player.getChipValue());
                }
            }
        }
    }

    /**
     * 將所有player重置成遊戲開始前的樣子，除了account
     */
    private void resetAllPlayers() {
        Player[] players = GameData.getPlayers();
        for (Player player : players) {
            player.startANewGame();
        }
        if (GameData.isReShuffle()) {
            GameData.getCardOnTable().clear();
            GameData.setCardOnTable(new CardSet(GameConfig.getSetOfCard()));
            GameData.setReShuffle(false);
            View.debugMessage("重新洗牌完成");
        }
    }
    /*
     * public Player[] getPlayers() {
     * Player[] players = GameData.getPlayers();
     * return players;
     * }
     */

}
