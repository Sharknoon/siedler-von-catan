package de.sharknoon.siedlervoncatan.view.controller;

import de.sharknoon.siedlervoncatan.enums.Rohstoff;
import de.sharknoon.siedlervoncatan.sound.Sound;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.view.Controller;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.util.Collections;

public class WuerfelMenueController
implements Controller {
    @FXML
    private Label spieler;
    @FXML
    private Label anzahlHolzL;
    @FXML
    private Label anzahlLehmL;
    @FXML
    private Label anzahlWolleL;
    @FXML
    private Label anzahlKornL;
    @FXML
    private Label anzahlErzL;
    @FXML
    private ImageView avatarIV;
    private Spiel spiel;
    private Node self;
    private RootLayoutController layoutController;

    @Override
    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
        Spieler aktiverSpieler = this.spiel.getAktiverSpieler();
        this.spieler.setText(aktiverSpieler.toString());
        Image image = aktiverSpieler.getAvatar();
        this.avatarIV.setImage(image);
        this.setAnzahlRohstoffe(aktiverSpieler);
    }

    private void setAnzahlRohstoffe(Spieler spieler) {
        this.anzahlHolzL.setText(this.getAnzahlAsString(spieler.getKarten(), Rohstoff.HOLZ));
        this.anzahlLehmL.setText(this.getAnzahlAsString(spieler.getKarten(), Rohstoff.LEHM));
        this.anzahlWolleL.setText(this.getAnzahlAsString(spieler.getKarten(), Rohstoff.WOLLE));
        this.anzahlKornL.setText(this.getAnzahlAsString(spieler.getKarten(), Rohstoff.KORN));
        this.anzahlErzL.setText(this.getAnzahlAsString(spieler.getKarten(), Rohstoff.ERZ));
        spieler.getKarten().addListener((ListChangeListener<? super Rohstoff>) c -> {
            while (c.next()) {
                if (c.wasPermutated()) continue;
                this.anzahlHolzL.setText(this.getAnzahlAsString(c.getList(), Rohstoff.HOLZ));
                this.anzahlLehmL.setText(this.getAnzahlAsString(c.getList(), Rohstoff.LEHM));
                this.anzahlWolleL.setText(this.getAnzahlAsString(c.getList(), Rohstoff.WOLLE));
                this.anzahlKornL.setText(this.getAnzahlAsString(c.getList(), Rohstoff.KORN));
                this.anzahlErzL.setText(this.getAnzahlAsString(c.getList(), Rohstoff.ERZ));
            }
        });
    }

    private String getAnzahlAsString(ObservableList<? extends Rohstoff> observableList, Rohstoff rohstoff) {
        return Integer.toString(Collections.frequency(observableList, rohstoff));
    }

    @FXML
    private void handleEntwicklungskarte() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.layoutController.removeFromCenterAnimatedV(this.self);
        this.spiel.getUserInterface().zeigeEntwicklungskarten();
    }

    @FXML
    private void handleWuerfeln() {
        Sound sound = Sound.getInstanz();
        sound.playSoundeffekt(Sound.WUERFEL_CLIP);
        this.layoutController.removeFromCenterAnimatedV(this.self);
        Timer timer = new Timer(1000, e -> Platform.runLater(() -> this.spiel.wuerfeln()));
        timer.setRepeats(false);
        timer.start();
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

