public class Player {
    private CardSet set = new CardSet();
    private boolean stopGetCard = false;
    private boolean isBookmaker = false;
    private PlayerStatus Status = PlayerStatus.PLAYING;
    private int playerNo;
    private Account account = new Account();
    private int chipValue = 0;// 籌碼
    private boolean buyInsurance = false;
    private boolean doubleBet = false;


    public Player() {

    }

    /**
     * 
     * @param playerNo 設定此玩家為第幾號玩家
     */
    public Player(int playerNo) {
        this.playerNo = playerNo;
    }

    

    /**
     * 玩家於遊戲中取得一張牌並放入手中的牌
     * 
     * @param card 拿到的牌
     */
    public void getCard(Card card) {
        set.add(card);
    }

    /**
     * 判斷player是否已經說停止拿牌 如果未說停止拿牌 回傳false
     * 
     * @return
     */
    public boolean isStopGetCard() {
        return stopGetCard;
    }

    /**
     * 設定玩家是否聲明已停止拿牌
     * 
     * @param stopGetCard true 表玩家已聲明停止拿牌
     */
    public void setStopGetCard(boolean stopGetCard) {
        this.stopGetCard = stopGetCard;
    }

    /**
     * 判斷玩家是否為莊家
     * 
     * @return true 表莊家
     */
    public boolean isBookmaker() {
        return isBookmaker;
    }

    /**
     * 設定此玩家為遊戲中的莊家
     * 
     * @param isBookmaker true 表莊家
     */
    public void setBookmaker(boolean isBookmaker) {
        this.isBookmaker = isBookmaker;
    }

    /**
     * 取得玩家手上的整副牌
     * 
     * @return CardSet 物件
     */
    public CardSet getSet() {
        return set;
    }

    /**
     * 取得玩家目前的遊戲狀態如win,lose,tie,playing
     * 
     * @return 目前的遊戲狀態
     */
    public PlayerStatus getStatus() {
        return Status;
    }

    /**
     * 設定玩家目前遊戲狀態
     * 
     * @param status
     */
    public void setStatus(PlayerStatus status) {
        this.Status = status;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public Account getAccount() {
        return account;
    }

    /**
     * 取得玩家下注的籌碼
     * 
     * @return 多少籌碼
     */
    public int getChipValue() {
        return chipValue;
    }

    /**
     * 設定玩家下注多少籌碼
     * 
     * @param chipValue
     */
    public void setChipValue(int chipValue) {
        this.chipValue = chipValue;
    }

    /**
     * 除了acount裡的金額，將player狀態設定回遊戲開始前的樣子
     */
    public void startANewGame() {
        setStatus(PlayerStatus.PLAYING);
        setStopGetCard(false);
        setBuyInsurance(false);
        setDoubleBet(false);
        set.clear();
    }

    public boolean isBuyInsurance() {
        return buyInsurance;
    }

    public void setBuyInsurance(boolean buyInsurance) {
        this.buyInsurance = buyInsurance;
    }

    public boolean isDoubleBet() {
        return doubleBet;
    }

    public void setDoubleBet(boolean doubleBet) {
        this.doubleBet = doubleBet;
    }

    

    

}
