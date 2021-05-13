package de.sharknoon.siedlervoncatan.view.controller;

import de.sharknoon.siedlervoncatan.enums.Rohstoff;
import de.sharknoon.siedlervoncatan.sound.Sound;
import de.sharknoon.siedlervoncatan.utility.popup.Rohstoffauswahl;
import de.sharknoon.siedlervoncatan.view.PopupController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.beans.PropertyChangeSupport;

public class RohstoffauswahlController
implements PopupController {
    @FXML
    private Label textL;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Stage stage;

    @FXML
    private void handleRohstoffClicked(Event event) {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        Button button = (Button)event.getSource();
        String text = button.getText();
        Rohstoff rohstoff = Rohstoff.getRohstoff(text);
        this.support.firePropertyChange("Rohstoff", null, rohstoff);
        this.stage.close();
    }

    @Override
    public void setText(String text) {
        this.textL.setText(text);
    }

    public void setRohstoffauswahl(Rohstoffauswahl rohstoffauswahl) {
        this.support.addPropertyChangeListener(rohstoffauswahl);
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

