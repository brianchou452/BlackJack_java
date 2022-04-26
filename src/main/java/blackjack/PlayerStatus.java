package blackjack;

public enum PlayerStatus {
    WIN("贏了"),
    LOSE("輸了"),
    TIE("與莊家平手"),
    PLAYING("遊戲中");
    
    private String status;

    PlayerStatus(String status) {
        this.status = status;
    }
    
    public String toString(){
        return status;
    }
}
