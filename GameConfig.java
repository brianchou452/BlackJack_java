public class GameConfig {
    private static int defaultBalance = 5000;
    private static int playerNumber = 2;
    private static int SetOfCard = 4;//TODO 有幾副牌 

    public static void init() {
        System.out.println("輸入玩家人數: ");
        GameConfig.playerNumber = 1 + Utils.sc.nextInt();
        System.out.println("輸入每位玩家的預設帳戶金額: ");
        GameConfig.defaultBalance = Utils.sc.nextInt();
        System.out.println("輸入要以幾組撲克牌來進行遊戲: ");
        GameConfig.SetOfCard = Utils.sc.nextInt();
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
