public class GameConfig {
    private static int defaultBalance = 5000;
    private static int playerNumber = 2;
    private static int SetOfCard = 4;//TODO 有幾副牌 

    public static void init() {
        System.out.print("輸入玩家人數: ");
        String tmp = Utils.sc.nextLine();
        GameConfig.playerNumber = 1 + Integer.parseInt(tmp);
        System.out.print("輸入每位玩家的預設帳戶金額: $");
        tmp = Utils.sc.nextLine();
        GameConfig.defaultBalance = Integer.parseInt(tmp);
        System.out.print("輸入要以幾組撲克牌來進行遊戲: ");
        tmp = Utils.sc.nextLine();
        GameConfig.SetOfCard = Integer.parseInt(tmp);
    }

    public static int getDefaultBalance() {
        return defaultBalance;
    }

    public static int getPlayerNumber() {
        return playerNumber;
    }

    public static int getSetOfCard() {
        return SetOfCard;
    }
    

}
