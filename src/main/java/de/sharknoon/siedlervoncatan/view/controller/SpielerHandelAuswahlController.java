package de.sharknoon.siedlervoncatan.view.controller;

import de.sharknoon.siedlervoncatan.enums.Rohstoff;
import de.sharknoon.siedlervoncatan.sound.Sound;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.utility.Handel;
import de.sharknoon.siedlervoncatan.view.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.beans.PropertyChangeSupport;
import java.util.List;

public class SpielerHandelAuswahlController
implements Controller {
    @FXML
    private ChoiceBox<Spieler> spielerCB;
    @FXML
    private ListView<HBox> angebotLV;
    @FXML
    private ListView<HBox> nachfrageLV;
    @FXML
    private Label textL;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Handel handel;
    private Spiel spiel;
    private Node self;
    private RootLayoutController layoutController;

    @Override
    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
        this.support.addPropertyChangeListener(spiel);
        ObservableList<Spieler> andereSpieler = FXCollections.observableArrayList(spiel.getAlleSpieler());
        andereSpieler.remove(spiel.getAktiverSpieler());
        this.spielerCB.setItems(andereSpieler);
        this.textL.setText("Wähle den Mitspieler der mit " + spiel.getAktiverSpieler() + " handeln möchte.");
    }

    public void setAngebotNachfrage(Handel handel) {
        this.angebotLV.setItems(this.createListViewInlay(handel.getAngebot()));
        this.nachfrageLV.setItems(this.createListViewInlay(handel.getNachfrage()));
        this.handel = handel;
    }

    private ObservableList<HBox> createListViewInlay(List<Rohstoff> rohstoffe) {
        ObservableList<HBox> listHBox = FXCollections.observableArrayList();
        for (Rohstoff rohstoff : rohstoffe) {
            Label label = new Label(rohstoff.toString());
            ImageView imageView = new ImageView(rohstoff.getImage());
            imageView.setFitHeight(35.0);
            imageView.setFitWidth(35.0);
            HBox hBox = new HBox(5.0);
            hBox.getChildren().addAll(imageView, label);
            listHBox.add(hBox);
        }
        return listHBox;
    }

    @FXML
    private void handleOK() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        Spieler andererSpieler = this.spielerCB.getSelectionModel().getSelectedItem();
        if (andererSpieler != null) {
            if (andererSpieler.getKarten().containsAll(this.handel.getNachfrage())) {
                this.handel.setNachfrager(andererSpieler);
                this.handel.handeln();
                Sound.getInstanz().playMusik(Sound.MUSIK_MEER);
                this.layoutController.removeFromCenterAnimatedH(this.self);
                this.spiel.getUserInterface().zeigeZug();
            } else {
                this.spiel.getUserInterface().zeigeError(andererSpieler + " hat nicht genügend Rohstoffe in seinem Besitz.");
            }
        } else {
            this.spiel.getUserInterface().zeigeError("Ungültige Auswahl.");
        }
    }

    @FXML
    private void handleAbbrechen() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        Sound.getInstanz().playMusik(Sound.MUSIK_MEER);
        this.layoutController.removeFromCenterAnimatedH(this.self);
        this.spiel.getUserInterface().zeigeZug();
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

