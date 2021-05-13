package de.sharknoon.siedlervoncatan.view.controller;

import de.sharknoon.siedlervoncatan.sound.Sound;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.spielfeld.Entwicklungskarte;
import de.sharknoon.siedlervoncatan.view.Controller;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class EntwicklungskartenController
implements Controller {
    @FXML
    private ListView<Entwicklungskarte> entwicklungskarten;
    @FXML
    private Text text;
    @FXML
    private Label entwicklung;
    private Node self;
    private RootLayoutController layoutController;
    private Spiel spiel;

    @Override
    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
        this.entwicklungskarten.setItems(spiel.getAktiverSpieler().getEntwickulungskarten());
    }

    @FXML
    private void initialize() {
        this.entwicklungskarten.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> this.zeigeText(newValue));
        this.entwicklung.setText("");
        this.text.setText("");
    }

    private void zeigeText(Entwicklungskarte karte) {
        if (karte != null) {
            this.entwicklung.setText(karte.toString());
            this.text.setText(karte.getEntwicklung().getText());
        }
    }

    @FXML
    private void handleSpielen() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        Entwicklungskarte selectedItem = this.entwicklungskarten.getSelectionModel().getSelectedItem();
        if (selectedItem != null && selectedItem.getDarfGespieltWerden()) {
            this.layoutController.removeFromCenterAnimatedH(this.self);
            selectedItem.ausspielen();
        } else {
            this.spiel.getUserInterface().zeigeError("Ungültige Auswahl.");
        }
    }

    @FXML
    private void handleAbbrechen() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.layoutController.removeFromCenterAnimatedH(this.self);
        if (this.spiel.hatGewuerfelt()) {
            this.spiel.getUserInterface().zeigeZug();
        } else {
            this.spiel.getUserInterface().zeigeWuerfel();
        }
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

