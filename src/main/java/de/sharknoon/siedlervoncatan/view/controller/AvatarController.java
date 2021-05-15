package de.sharknoon.siedlervoncatan.view.controller;

import de.sharknoon.siedlervoncatan.enums.Zustand;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.view.Controller;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.beans.PropertyChangeSupport;

public class AvatarController
        implements Controller {
    @FXML
    private Label spielerL;
    @FXML
    private ImageView avatarIV;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Spieler spieler;
    private Spiel spiel;

    public void setSpieler(Spieler spieler) {
        this.spieler = spieler;
        this.spielerL.setText(spieler.getName());
        Image image = spieler.getAvatar();
        this.avatarIV.setImage(image);
    }

    @FXML
    private void initialize() {
        this.spielerL.setMaxWidth(this.avatarIV.getFitWidth());
    }

    @FXML
    private void handleAvatarClicked() {
        if (this.spiel.getZustand() != Zustand.SPIELER) {
            this.spiel.getUserInterface().zeigeSpielerInfos(this.spieler);
        } else {
            this.support.firePropertyChange("Spieler", null, this.spieler);
        }
    }

    @Override
    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
        this.support.addPropertyChangeListener(spiel);
    }

    @Override
    public void setLayoutController(RootLayoutController layoutController) {
    }

    @Override
    public void setNode(Node self) {
    }
}

