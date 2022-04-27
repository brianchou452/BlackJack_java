package blackjack;

public class GameConfig {
    private static int defaultBalance = 5000;
    private static int playerNumber = 2;
    private static int SetOfCard = 4;// 有幾副牌
    private static boolean playWithMoney;
    private static GameMode gameMode = GameMode.GUI;//TODO change it later
    private static boolean debugMode = false;
    private static boolean debugMessage = false;
    

    public enum GameMode {
        COMMAD_LINE,GUI
    }

    /**
     * 初始化遊戲設定
     */
    public static void init() {
        System.out.print("輸入玩家人數: ");
        String tmp = Utils.sc.nextLine();
        playerNumber = 1 + Integer.parseInt(tmp);
        playWithMoney = Utils.askYesNoQuestion("是否開啟賭錢功能(y/n)");
        debugMode = Utils.askYesNoQuestion("debug mode ? (n)");
        debugMessage = Utils.askYesNoQuestion("debug message ? (n)");
        if (playWithMoney) {
            System.out.print("輸入每位玩家的預設帳戶金額: $");
            tmp = Utils.sc.nextLine();
            defaultBalance = Integer.parseInt(tmp);
        }
        System.out.print("輸入要以幾組撲克牌來進行遊戲: ");
        tmp = Utils.sc.nextLine();
        SetOfCard = Integer.parseInt(tmp);
    }
    
    /**
     * 初始化遊戲設定
     */
    public static void initGUI(String playerNum) {
        
        playerNumber = 1 + Integer.parseInt(playerNum);
        playWithMoney = false;
        debugMode = false;
        debugMessage = true;
        SetOfCard = 2;
    }

    /**
     * 取得每位玩家帳戶的初始金額
     * 
     * @return
     */
    public static int getDefaultBalance() {
        return defaultBalance;
    }

    /**
     * 取得有多少位玩家
     * 
     * @return
     */
    public static int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * 取得要用幾副牌來進行遊戲
     * 
     * @return
     */
    public static int getSetOfCard() {
        return SetOfCard;
    }

    public static GameMode getGameMode() {
        return gameMode;
    }

    public static boolean isPlayWithMoney() {
        return playWithMoney;
    }

    public static boolean isDebugMode() {
        return debugMode;
    }

    public static boolean isPrintDebugMessage() {
        return debugMessage;
    }
    

}
