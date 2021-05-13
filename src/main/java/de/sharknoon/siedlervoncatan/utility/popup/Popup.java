package de.sharknoon.siedlervoncatan.utility.popup;

import javafx.animation.ScaleTransition;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class Popup {
    public void animatedShowAndWait(Stage stage) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500.0), stage.getScene().getRoot());
        scaleTransition.setFromX(0.0);
        scaleTransition.setFromY(0.0);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.play();
        stage.showAndWait();
    }
}

