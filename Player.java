public class Player {
    private CardSet set = new CardSet();
    private boolean stopGetCard = false;
    private boolean isBookmaker = false;
    private boolean isLose = false;
    private int playerNo;

    

    

    public Player() {

    }
    
    public Player(int playerNo) {
        this.playerNo = playerNo;
    }

    public void getCard(Card card) {
        set.add(card);
    }

    /**
     * 判斷player是否已經說停止拿牌 如果未說停止拿牌 回傳false
     * @return 
     */
    public boolean isStopGetCard() {
        return stopGetCard;
    }

    public void setStopGetCard(boolean stopGetCard) {
        this.stopGetCard = stopGetCard;
    }

    public boolean isBookmaker() {
        return isBookmaker;
    }

    public void setBookmaker(boolean isBookmaker) {
        this.isBookmaker = isBookmaker;
    }

    public CardSet getSet() {
        return set;
    }

    public boolean isLose() {
        return isLose;
    }

    public void setLose(boolean isLose) {
        this.isLose = isLose;
    }

    public int getPlayerNo() {
        return playerNo;
    }
    
}
