package de.sharknoon.siedlervoncatan.view.controller;

import de.sharknoon.siedlervoncatan.sound.Sound;
import de.sharknoon.siedlervoncatan.utility.popup.Confirmation;
import de.sharknoon.siedlervoncatan.view.PopupController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.beans.PropertyChangeSupport;

public class ConfirmationController
implements PopupController {
    @FXML
    private Label textL;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Stage stage;

    @FXML
    private void handleOK() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.support.firePropertyChange("Confirmation", null, true);
        this.stage.close();
    }

    @FXML
    private void handleAbbrechen() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.support.firePropertyChange("Confirmation", null, false);
        this.stage.close();
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void setText(String text) {
        this.textL.setText(text);
    }

    public void setConfirmation(Confirmation confirmation) {
        this.support.addPropertyChangeListener(confirmation);
    }
}

