package de.sharknoon.siedlervoncatan.view.controller;

import de.sharknoon.siedlervoncatan.sound.Sound;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.view.Controller;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class SiegerController
implements Controller {
    @FXML
    private Label labelSpieler;
    private Spiel spiel;
    private Node self;
    private RootLayoutController layoutController;

    @Override
    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
        this.labelSpieler.setText(spiel.getSieger().getName());
    }

    @FXML
    private void handleNeu() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.layoutController.removeFromCenterAnimatedH(this.self);
        this.spiel.getSpielstart().neuesSpiel();
    }

    @FXML
    private void handleEnde() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        System.exit(0);
    }

    @Override
    public void setLayoutController(RootLayoutController layoutController) {
        this.layoutController = layoutController;
    }

    @Override
    public void setNode(Node self) {
        this.self = self;
    }
}

