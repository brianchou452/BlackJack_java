public class PlayGame {
    public static void main(String[] args) {
        GameConfig.init();
        BlackJack gmae1 = new BlackJack();
        gmae1.start();
    }
}