package de.sharknoon.siedlervoncatan.view.controller;

import de.sharknoon.siedlervoncatan.enums.Farbe;
import de.sharknoon.siedlervoncatan.sound.Sound;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.view.Controller;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.Objects;

public class SpielerAnlegenController
implements Controller {
    private Spiel spiel;
    private Node self;
    private RootLayoutController layoutController;
    @FXML
    private TextField name;
    @FXML
    private ComboBox<Farbe> farbe;

    @FXML
    private void initialize() {
        this.farbe.getItems().addAll(Spiel.farben);
        this.name.requestFocus();
    }

    @FXML
    private void handleOK() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        Farbe farbe = this.farbe.getValue();
        Spiel.farben.remove(farbe);
        String name = this.name.getText();
        if (!Objects.equals(name, "") && farbe != null) {
            Spieler spieler = new Spieler(name, farbe, this.spiel);
            this.spiel.addSpieler(spieler);
            this.layoutController.removeFromCenterAnimatedH(this.self);
        } else {
            this.spiel.getUserInterface().zeigeError("Ungültige Eingaben!");
        }
    }

    @FXML
    private void handleAbbrechen() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.layoutController.removeFromCenterAnimatedH(this.self);
    }

    @Override
    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
        this.name.requestFocus();
    }

    @Override
    public void setLayoutController(RootLayoutController layoutController) {
        this.layoutController = layoutController;
        this.name.requestFocus();
    }

    @Override
    public void setNode(Node self) {
        this.self = self;
        this.name.requestFocus();
    }
}

