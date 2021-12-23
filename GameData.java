public class GameData {
    private static Player[] players;
    private static CardSet cardOnTable = new CardSet(GameConfig.getSetOfCard());
    private static boolean reShuffle = false;

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
