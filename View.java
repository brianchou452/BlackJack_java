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
        if (isUsingGUI()) {
            // TODO call GUI function
        }
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
            // TODO call GUI function
            // TODO 原因改用enum傳遞
        }
        System.out.println("#"+playerName+reason+status);
    }

    /**
     * 印出遊戲結果
     */
    public static void printGameResult() {
        Player[] players = GameData.getPlayers();
        if (isUsingGUI()) {
            // TODO call GUI function
            return;
        }
        System.out.println("\n\n==========遊戲結果==========");
        System.out.println("莊家的牌為:");
        players[0].getSet().print();
        for (Player player : players) {
            if (player.isBookmaker()) {
                continue;
            }
            System.out.print("玩家" + player.getPlayerNo() + player.getStatus() );

            // 印出玩家的帳戶餘額，格式為 “帳戶餘額: $xxxx”
            if (GameConfig.isPlayWithMoney()) {
                System.out.print(", "+ "帳戶餘額: $" + player.getAccount().getBalance());
            }
            System.out.println("");
        }
        System.out.println("============================\n\n");
    }
    

    
    public static void changePlayer(String playerName) {
        System.out.println("\n\n----------------------");
        System.out.println("#" + playerName);
    }
    
    public static void noMoreMoney() {
        System.out.println("嗚嗚嗚 沒有那麼多錢拉!");
    }
}
