public class Card {
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        //assert rank != null && suit != null;// TODO 或許可以移除？？
        this.rank = rank;
        this.suit = suit;
    }

    public int getRankAsInt() {
        return rank.getRank();
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }
}
