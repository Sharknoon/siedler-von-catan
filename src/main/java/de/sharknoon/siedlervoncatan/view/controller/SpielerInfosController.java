package de.sharknoon.siedlervoncatan.view.controller;

import de.sharknoon.siedlervoncatan.enums.Rohstoff;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.view.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.util.Collections;

public class SpielerInfosController
implements Controller {
    @FXML
    private Label spielerL;
    @FXML
    private Label siegpunkteL;
    @FXML
    private Label strasseL;
    @FXML
    private Label ritterL;
    @FXML
    private Label holzL;
    @FXML
    private Label lehmL;
    @FXML
    private Label wolleL;
    @FXML
    private Label kornL;
    @FXML
    private Label erzL;
    private Node self;
    private RootLayoutController layoutController;

    public void setSpieler(Spieler spieler) {
        this.spielerL.setText(spieler.getName());
        this.siegpunkteL.setText(Integer.toString(spieler.getSiegpunkte().get()));
        this.ritterL.setText(Integer.toString(spieler.getRitter().get()));
        this.strasseL.setText(Integer.toString(spieler.getLaengsteHandelsstrasse()));
        ObservableList<Rohstoff> karten = spieler.getKarten();
        this.holzL.setText(Integer.toString(Collections.frequency(karten, Rohstoff.HOLZ)));
        this.lehmL.setText(Integer.toString(Collections.frequency(karten, Rohstoff.LEHM)));
        this.wolleL.setText(Integer.toString(Collections.frequency(karten, Rohstoff.WOLLE)));
        this.kornL.setText(Integer.toString(Collections.frequency(karten, Rohstoff.KORN)));
        this.erzL.setText(Integer.toString(Collections.frequency(karten, Rohstoff.ERZ)));
    }

    @FXML
    private void handleClose() {
        this.layoutController.removeFromCenterAnimatedH(this.self);
    }

    @Override
    public void setSpiel(Spiel spiel) {
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

