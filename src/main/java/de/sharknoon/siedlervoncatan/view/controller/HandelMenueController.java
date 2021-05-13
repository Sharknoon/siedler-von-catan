package de.sharknoon.siedlervoncatan.view.controller;

import de.sharknoon.siedlervoncatan.enums.Rohstoff;
import de.sharknoon.siedlervoncatan.sound.Sound;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.utility.Handel;
import de.sharknoon.siedlervoncatan.view.Controller;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javax.swing.*;
import java.beans.PropertyChangeSupport;

public class HandelMenueController
implements Controller {
    @FXML
    private TableView<Rohstoff> angebotTable;
    @FXML
    private TableView<Rohstoff> nachfrageTable;
    @FXML
    private TableColumn<Rohstoff, HBox> angebotColumn;
    @FXML
    private TableColumn<Rohstoff, HBox> nachfrageColumn;
    private final ObservableList<Rohstoff> angebot;
    private final ObservableList<Rohstoff> nachfrage;
    private Spiel spiel;
    private Node self;
    private RootLayoutController layoutController;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public HandelMenueController() {
        this.angebot = FXCollections.observableArrayList();
        this.nachfrage = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        this.angebotTable.setItems(this.angebot);
        this.nachfrageTable.setItems(this.nachfrage);
        this.angebotColumn.setCellValueFactory(c -> new SimpleObjectProperty<>(this.createColumnInlay(c.getValue())));
        this.nachfrageColumn.setCellValueFactory(c -> new SimpleObjectProperty<>(this.createColumnInlay(c.getValue())));
        this.angebot.addListener((ListChangeListener<? super Rohstoff>) c -> {
            while (c.next()) {
                if (!c.wasAdded()) continue;
                this.angebot.sort(null);
            }
        });
        this.nachfrage.addListener((ListChangeListener<? super Rohstoff>) c -> {
            while (c.next()) {
                if (!c.wasAdded()) continue;
                this.nachfrage.sort(null);
            }
        });
    }

    private HBox createColumnInlay(Rohstoff rohstoff) {
        Image image = rohstoff.getImage();
        Label rohstoffL = new Label(rohstoff.toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(35.0);
        imageView.setFitWidth(35.0);
        HBox hBox = new HBox(5.0);
        hBox.getChildren().add(imageView);
        hBox.getChildren().add(rohstoffL);
        return hBox;
    }

    @Override
    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
        this.support.addPropertyChangeListener(spiel);
        Sound.getInstanz().playMusik(Sound.MUSIK_HANDEL);
    }

    @FXML
    private void handleOK() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        if (this.angebot.isEmpty() || this.nachfrage.isEmpty() || !this.spiel.getAktiverSpieler().getKarten().containsAll(this.angebot)) {
            this.spiel.getUserInterface().zeigeError("Ungültige Eingaben.");
        } else {
            Handel handel = new Handel(this.angebot, this.nachfrage);
            handel.setAnbieter(this.spiel.getAktiverSpieler());
            this.layoutController.removeFromCenterAnimatedH(this.self);
            Timer timer = new Timer(500, e -> Platform.runLater(() -> this.spiel.getUserInterface().zeigeSpielerHandel(handel)));
            timer.setRepeats(false);
            timer.start();
        }
    }

    @FXML
    private void handleEntfernen() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        int selectedIndexAngebot = this.angebotTable.getSelectionModel().getSelectedIndex();
        int selectedIndexNachfrage = this.nachfrageTable.getSelectionModel().getSelectedIndex();
        if (selectedIndexAngebot >= 0) {
            this.angebotTable.getItems().remove(selectedIndexAngebot);
        }
        if (selectedIndexNachfrage >= 0) {
            this.nachfrageTable.getItems().remove(selectedIndexNachfrage);
        }
        if (selectedIndexAngebot < 0 && selectedIndexNachfrage < 0) {
            this.spiel.getUserInterface().zeigeError("Kein Rohstoff ausgewählt.");
        }
        this.angebotTable.getSelectionModel().clearSelection();
        this.nachfrageTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleAbbrechen() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        Sound.getInstanz().playMusik(Sound.MUSIK_MEER);
        this.layoutController.removeFromCenterAnimatedH(this.self);
        this.spiel.getUserInterface().zeigeZug();
    }

    @FXML
    private void handleHolzA() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.angebot.add(Rohstoff.HOLZ);
    }

    @FXML
    private void handleLehmA() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.angebot.add(Rohstoff.LEHM);
    }

    @FXML
    private void handleWolleA() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.angebot.add(Rohstoff.WOLLE);
    }

    @FXML
    private void handleKornA() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.angebot.add(Rohstoff.KORN);
    }

    @FXML
    private void handleErzA() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.angebot.add(Rohstoff.ERZ);
    }

    @FXML
    private void handleHolzN() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.nachfrage.add(Rohstoff.HOLZ);
    }

    @FXML
    private void handleLehmN() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.nachfrage.add(Rohstoff.LEHM);
    }

    @FXML
    private void handleWolleN() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.nachfrage.add(Rohstoff.WOLLE);
    }

    @FXML
    private void handleKornN() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.nachfrage.add(Rohstoff.KORN);
    }

    @FXML
    private void handleErzN() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.nachfrage.add(Rohstoff.ERZ);
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

