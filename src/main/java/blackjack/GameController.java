package blackjack;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameController {
    @FXML
    private Label nowPlayer;
    @FXML
    private Label bookmakerCard;
    @FXML
    private Label bookmakerTotal;
    @FXML
    private Label playerCard;
    @FXML
    private Label playerTotal;
    @FXML
    private Label message;
    @FXML
    private Button addCard_btn;
    @FXML
    private Button buyInsurance_btn;
    @FXML
    private Button doubleBet_btn;
    @FXML
    private Button nextPlayer;

    public void initialize() {
        GameData.game.start();
        updateBookmakerCard();
        updatePlayerCard();
        updatePlayerNo();
    }

    public void updatePlayerNo() {
        nowPlayer.setText("玩家" + GameData.getNowPlayerInt());
    }

    public void updateBookmakerCard() {
        bookmakerCard.setText(GameData.getBookmakerCardSet());
    }

    public void updatePlayerCard() {
        playerCard.setText(GameData.getNowPlayerCardSet());
    }

    public void updateMessage() {
        message.setText(GameData.getSinglePlayerGameResult());
    }

    
    private void updateButtonStatus() {
        if (GameData.isPlayerFinishAddCard()) {
            addCard_btn.setDisable(true);
        } else {
            addCard_btn.setDisable(false);
        }
    }

    public void playerAddCard() {
        if (!GameData.isPlayerFinishAddCard()) {
            GameData.game.playerAddCard();
            updatePlayerCard();
            updatePlayerNo();
            updateMessage();
            updateButtonStatus();
        }

    }
    
    public void nextPlayer() {
        if (GameData.getNowPlayerInt() + 1 < GameConfig.getPlayerNumber()) {
            GameData.getPlayers()[GameData.getNowPlayerInt()].setStopGetCard(true);
            GameData.setNowPlayerInt(GameData.getNowPlayerInt() + 1);
            GameData.setPlayerFinishAddCard(false);
            GameData.game.updateScreen();
            updatePlayerCard();
            updatePlayerNo();
            updateMessage();
            updateButtonStatus();
        } else {
            nextPlayer.setDisable(true);
            GameData.game.bookmakerAddCard();
            updateBookmakerCard();
            GameData.game.compareRank();
            View.printGameResult();
            updateMessage();

        }
        
    }

}
