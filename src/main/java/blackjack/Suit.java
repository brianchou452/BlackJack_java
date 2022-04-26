package blackjack;

public enum Suit {
    CLUBS("梅花"),
    DIAMONDS("方塊"),
    SPADES("黑桃"),
    HEARTS("紅心");


    private String suit;

    Suit(String suit) {
        this.suit = suit;
    }
    
    public String getString() {
        return suit;
    }
}
