package de.sharknoon.siedlervoncatan.view.controller;

import de.sharknoon.siedlervoncatan.Spielstart;
import de.sharknoon.siedlervoncatan.sound.Sound;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.utility.Pfade;
import de.sharknoon.siedlervoncatan.view.Controller;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class RootLayoutController implements Controller {
    @FXML
    private StackPane centerSP;
    private Spielstart spielstart;
    private ScaleTransition appearH;
    private ScaleTransition disappearH;
    private ScaleTransition appearV;
    private ScaleTransition disappearV;
    private Sound sound;

    public void setSpielstart(Spielstart spielstart) {
        this.spielstart = spielstart;
        this.appearH = new ScaleTransition(Duration.millis(500.0));
        this.appearH.setFromX(0.0);
        this.appearH.setToX(1.0);
        this.disappearH = new ScaleTransition(Duration.millis(500.0));
        this.disappearH.setFromX(1.0);
        this.disappearH.setToX(0.0);
        this.appearV = new ScaleTransition(Duration.millis(500.0));
        this.appearV.setFromY(0.0);
        this.appearV.setToY(1.0);
        this.disappearV = new ScaleTransition(Duration.millis(500.0));
        this.disappearV.setFromY(1.0);
        this.disappearV.setToY(0.0);
        this.sound = Sound.getInstanz();
    }

    public void clearCenter() {
        this.centerSP.getChildren().clear();
    }

    public void addToCenter(Node node) {
        if (!this.centerSP.getChildren().contains(node)) {
            this.centerSP.getChildren().add(node);
        }
    }

    public void addToCenterAnimatedH(Node node) {
        this.addToCenter(node);
        this.appearH.setNode(node);
        this.appearH.play();
        this.sound.playSoundeffekt(Sound.PAPER);
    }

    public void addToCenterAnimatedV(Node node) {
        this.addToCenter(node);
        this.appearV.setNode(node);
        this.appearV.play();
        this.sound.playSoundeffekt(Sound.PAPER);
    }

    public void removeFromCenter(Node node) {
        this.centerSP.getChildren().remove(node);
    }

    public void removeFromCenterAnimatedH(Node node) {
        this.disappearH.setNode(node);
        this.disappearH.play();
        this.sound.playSoundeffekt(Sound.PAPER);
        this.disappearH.setOnFinished(e -> this.removeFromCenter(node));
    }

    public void removeFromCenterAnimatedV(Node node) {
        this.disappearV.setNode(node);
        this.disappearV.play();
        this.sound.playSoundeffekt(Sound.PAPER);
        this.disappearV.setOnFinished(e -> this.removeFromCenter(node));
    }

    @FXML
    private void handleLaden() {
        try {
            Files.createDirectories(Pfade.SAVES);
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("SVC files", "*svc");
            fileChooser.getExtensionFilters().add(extensionFilter);
            fileChooser.setInitialDirectory(Pfade.SAVES.toFile());
            File file = fileChooser.showOpenDialog(Spielstart.getPrimaryStage());
            if (file != null) {
                this.spielstart.spielLaden(file.toPath());
            }
        } catch (Exception e) {
            this.spielstart.getUserInterface().zeigeError("Spielstand kann jetzt nicht geladen werden.");
        }
    }

    @FXML
    private void handleNeu() {
        this.spielstart.neuesSpiel();
    }

    @FXML
    private void handleSpeichern() {
        Spiel spiel = this.spielstart.getSpiel();
        try {
            if (spiel == null || !spiel.isSaveable()) {
                throw new Exception();
            }
            Files.createDirectories(Pfade.SAVES);
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("SVC files (*.svc)", "*.svc");
            fileChooser.getExtensionFilters().add(extensionFilter);
            fileChooser.setInitialDirectory(Pfade.SAVES.toFile());
            File file = fileChooser.showSaveDialog(Spielstart.getPrimaryStage());
            if (file != null) {
                Path path = file.toPath();
                if (!path.endsWith(".svc")) {
                    path = path.resolveSibling(path.getFileName() + ".svc");
                }
                this.spielstart.spielSpeichern(path);
            }
        } catch (Exception e) {
            this.spielstart.getUserInterface().zeigeError("Spielstand kann jetzt nicht gespeichert werden.");
        }
    }

    @FXML
    private void handleBeenden() {
        this.spielstart.beenden();
    }

    @FXML
    private void handleAudio() {
        this.spielstart.getUserInterface().zeigeAudiomenue();
    }

    @FXML
    private void handleVollbild() {
        Stage primaryStage = Spielstart.getPrimaryStage();
        primaryStage.setFullScreen(!primaryStage.isFullScreen());
    }

    @FXML
    private void handleAnleitung() {
        this.spielstart.getUserInterface().zeigeAnleitung();
    }

    @FXML
    private void handleBaukosten() {
        this.spielstart.getUserInterface().zeigeBaukosten();
    }

    @Override
    public void setSpiel(Spiel spiel) {
    }

    @Override
    public void setLayoutController(RootLayoutController layoutController) {
    }

    @Override
    public void setNode(Node self) {
    }
}

