package blackjack;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StartController {

    @FXML
    private TextField peopleNum;
    @FXML
    private Button startGame_btn;

    /**
     * 開始遊戲並開啟新的頁面
     * @throws IOException
     */
    @FXML
    public void startGame() throws IOException {
        String playerNumber = peopleNum.getText();
        GameConfig.initGUI(playerNumber);
        GameData.initGame();
        App.setRoot("inGame");
    }
    
}
