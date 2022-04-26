module blackjack {
    requires javafx.controls;
    requires javafx.fxml;

    opens blackjack to javafx.fxml;
    exports blackjack;
}
