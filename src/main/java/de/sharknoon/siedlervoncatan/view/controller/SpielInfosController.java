package de.sharknoon.siedlervoncatan.view.controller;

import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.view.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SpielInfosController
implements Controller {
    @FXML
    private VBox spielerVB;
    @FXML
    private Group groupG;
    @FXML
    private AnchorPane anchorP;

    @Override
    public void setSpiel(Spiel spiel) {
        ObservableList<Spieler> alleSpieler = spiel.getAlleSpieler();
        for (Spieler spieler : alleSpieler) {
            Pane pane = spiel.getUserInterface().zeigeAvatar(spieler);
            this.spielerVB.getChildren().add(pane);
        }
    }

    @Override
    public void setLayoutController(RootLayoutController layoutController) {
    }

    @Override
    public void setNode(Node self) {
    }
}

