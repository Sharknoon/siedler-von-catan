package de.sharknoon.siedlervoncatan.view.controller;

import de.sharknoon.siedlervoncatan.sound.Sound;
import de.sharknoon.siedlervoncatan.view.PopupController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorController
implements PopupController {
    @FXML
    private Label textL;
    private Stage stage;

    @FXML
    private void handleClose() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.stage.close();
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void setText(String text) {
        this.textL.setText(text);
        Sound.getInstanz().playSoundeffekt(Sound.ERROR_CLIP);
    }
}

