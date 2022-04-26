package blackjack;

public class GameData {
    private static Player[] players;
    private static CardSet cardOnTable = new CardSet(GameConfig.getSetOfCard());
    private static boolean reShuffle = false;
    private static String nowPlayer;
    private static int nowPlayerInt = 1;
    private static int numberOfCardNowPlayerGet = 0;
    private static String bookmakerCardSet;
    private static String nowPlayerCardSet;
    private static String singlePlayerGameResult;
    private static boolean playerFinishAddCard = false;
    public static BlackJack game;

    
    
    public static boolean isPlayerFinishAddCard() {
        return playerFinishAddCard;
    }

    public static void setPlayerFinishAddCard(boolean playerFinishAddCard) {
        GameData.playerFinishAddCard = playerFinishAddCard;
    }

    public static String getSinglePlayerGameResult() {
        return singlePlayerGameResult;
    }

    public static void setSinglePlayerGameResult(String singlePlayerGameResult) {
        GameData.singlePlayerGameResult = singlePlayerGameResult;
    }

    public static String getBookmakerCardSet() {
        return bookmakerCardSet;
    }

    public static void setBookmakerCardSet(String bookmakerCardSet) {
        GameData.bookmakerCardSet = bookmakerCardSet;
    }

    public static int getNumberOfCardNowPlayerGet() {
        return numberOfCardNowPlayerGet;
    }

    public static void setNumberOfCardNowPlayerGet(int numberOfCardNowPlayerGet) {
        GameData.numberOfCardNowPlayerGet = numberOfCardNowPlayerGet;
    }

    public static int getNowPlayerInt() {
        return nowPlayerInt;
    }

    public static void setNowPlayerInt(int nowPlayerInt) {
        GameData.nowPlayerInt = nowPlayerInt;
    }

    public static void initGame() {
        game = new BlackJack();
    }

    public static String getNowPlayerCardSet() {
        return nowPlayerCardSet;
    }
    public static void setNowPlayerCardSet(String nowPlayerCardSet) {
        GameData.nowPlayerCardSet = nowPlayerCardSet;
    }
    public static String getNowPlayer() {
        return nowPlayer;
    }
    public static void setNowPlayer(String nowPlayer) {
        GameData.nowPlayer = nowPlayer;
    }
    public static Player[] getPlayers() {
        return players;
    }
    public static void setPlayers(Player[] players) {
        GameData.players = players;
    }
    public static CardSet getCardOnTable() {
        return cardOnTable;
    }

    public static void setCardOnTable(CardSet cardOnTable) {
        GameData.cardOnTable = cardOnTable;
    }
    public static boolean isReShuffle() {
        return reShuffle;
    }
    public static void setReShuffle(boolean reShuffle) {
        GameData.reShuffle = reShuffle;
    }
    
    
    

    
}
