package blackjack;

public class View {
    private static GameConfig.GameMode gameMode = GameConfig.getGameMode();

    public View() {

    }

    private static boolean isUsingGUI() {
        if (gameMode == GameConfig.GameMode.COMMAD_LINE) {
            return false;
        } else {
            return true;
        }
    }

    public static void printCurrentPlayer(int playerNo) {
        System.out.println("#玩家" + playerNo);
    }

    public static int askChipValue() {
        if (isUsingGUI()) {
            // TODO call GUI function
        }
        return Utils.askQuestionAnswerWithInt("請輸入你的籌碼 $");
    }

    public static void printSinglePlayerGameResult(String playerName, String reason, PlayerStatus status) {
        if (isUsingGUI()) {
            GameData.setSinglePlayerGameResult("#" + playerName + reason + status);
            // TODO 原因改用enum傳遞
        }
        System.out.println("#" + playerName + reason + status);
    }

    /**
     * 印出遊戲結果
     */
    public static void printGameResult() {
        Player[] players = GameData.getPlayers();
        if (isUsingGUI()) {
            // TODO call GUI function
            String tmp = "";
            tmp += "\n\n==========遊戲結果==========";
            tmp +="\n莊家的牌為:";
            for (Card card : players[0].getSet().getCards()) {
                tmp += card.getSuit().getString() + card.getRank().getRankStr() + " ";
            }
            tmp += "\n總和為:" + players[0].getSet().calculateRank()+"\n";
            for (Player player : players) {
                if (player.isBookmaker()) {
                    continue;
                }
                tmp +="玩家" + player.getPlayerNo() + player.getStatus();

                // 印出玩家的帳戶餘額，格式為 “帳戶餘額: $xxxx”
                if (GameConfig.isPlayWithMoney()) {
                    tmp +=", " + "帳戶餘額: $" + player.getAccount().getBalance();
                }
                tmp +="\n";
            }
            tmp += "\n============================\n\n";
            GameData.setSinglePlayerGameResult(tmp);
        }
        System.out.println("\n\n==========遊戲結果==========");
        System.out.println("莊家的牌為:");
        // players[0].getSet().print();
        View.printCardset(players[0].getSet());
        for (Player player : players) {
            if (player.isBookmaker()) {
                continue;
            }
            System.out.print("玩家" + player.getPlayerNo() + player.getStatus());

            // 印出玩家的帳戶餘額，格式為 “帳戶餘額: $xxxx”
            if (GameConfig.isPlayWithMoney()) {
                System.out.print(", " + "帳戶餘額: $" + player.getAccount().getBalance());
            }
            System.out.println("");
        }
        System.out.println("============================\n\n");
    }

    public static void changePlayer(String playerName) {
        if (isUsingGUI()) {
            GameData.setNowPlayer(playerName);
        }
        System.out.println("\n\n----------------------");
        System.out.println("#" + playerName);
    }

    public static void noMoreMoney() {
        System.out.println("嗚嗚嗚 沒有那麼多錢拉!");
    }

    /**
     * 印出所有牌和點數總和
     */
    public static void printCardset(CardSet set) {// TODO override toString??
        if (isUsingGUI()) {
            // TODO call GUI function
            String tmp = "";
            for (Card card : set.getCards()) {
                tmp += card.getSuit().getString() + card.getRank().getRankStr() + " ";
            }
            tmp += "\n總和為:" + set.calculateRank();
            GameData.setNowPlayerCardSet(tmp);
        }
        for (Card card : set.getCards()) {
            System.out.print(card.getSuit().getString() + card.getRank().getRankStr() + " ");
        }
        System.out.println("");
        System.out.println("總和為:" + set.calculateRank());
    }

    /**
     * 印出除了第一張牌以外的所有牌以及點數和(用於印出莊家手中的牌)
     */
    public static void printCardsetWithHiding1stCard(CardSet set) {
        if (isUsingGUI()) {
            String tmp = "";
            int i = 0;
            for (Card card : set.getCards()) {
                if (i == 0) {
                    tmp += "*** ";
                    i++;
                    continue;
                }
                tmp += card.getSuit().getString() + card.getRank().getRankStr() + " ";
            }
            tmp += "\n總和為: n + " + set.calculateRankWithout1stCard();
            GameData.setBookmakerCardSet(tmp);
        }
        int i = 0;
        for (Card card : set.getCards()) {
            if (i == 0) {
                System.out.print("*** ");
                i++;
                continue;
            }
            System.out.print(card.getSuit().getString() + card.getRank().getRankStr() + " ");
        }
        System.out.println("");
        System.out.println("總和為: n + " + set.calculateRankWithout1stCard());
    }

    public static void askPlayerToBuyInsurance() {
        System.out.println("莊家明牌為A 請玩家決定是否購買保險");
    }

    public static void debugMessage(String print) {
        if (GameConfig.isPrintDebugMessage()) {
            System.out.println("*debug* " + print);
        }
    }
}
