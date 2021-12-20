public class View {
    private GameConfig.GameMode gameMode = GameConfig.getGameMode();

    public View() {

    }

    private boolean isUsingGUI() {
        if (gameMode == GameConfig.GameMode.COMMAD_LINE) {
            return false;
        } else {
            return true;
        }
    }

    public void printCurrentPlayer(int playerNo) {
        if (isUsingGUI()) {
            // TODO call GUI function
        }
        System.out.println("#玩家" + playerNo);
    }

    public int askChipValue() {
        if (isUsingGUI()) {
            // TODO call GUI function
        }
        return Utils.askQuestionAnswerWithInt("請輸入你的籌碼 $");
    }

    public void printSinglePlayerGameResult(String playerName, String reason, PlayerStatus status) {
        if (isUsingGUI()) {
            // TODO call GUI function
            // TODO 原因改用enum傳遞
        }
        System.out.println("#"+playerName+reason+status);
    }

    /**
     * 印出遊戲結果
     */
    public void printGameResult(Player[] players) {
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
            System.out.print("玩家" + player.getPlayerNo() + player.getStatus() +", ");
            
            player.printAccountBalance();
        }
        System.out.println("============================\n\n");
    }
}
