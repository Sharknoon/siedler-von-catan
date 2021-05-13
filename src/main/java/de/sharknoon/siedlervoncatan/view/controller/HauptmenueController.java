package de.sharknoon.siedlervoncatan.view.controller;

import de.sharknoon.siedlervoncatan.Spielstart;
import de.sharknoon.siedlervoncatan.sound.Sound;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.utility.Pfade;
import de.sharknoon.siedlervoncatan.view.Controller;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Files;

public class HauptmenueController
        implements Controller {
    private Spielstart spielstart;
    private Node self;
    private RootLayoutController layoutController;

    public void setSpielstart(Spielstart spielstart) {
        this.spielstart = spielstart;
    }

    @FXML
    private void handleNeu() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        this.layoutController.removeFromCenter(this.self);
        this.spielstart.neuesSpiel();
    }

    @FXML
    private void handleLaden() {
        try {
            Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
            Files.createDirectories(Pfade.SAVES);
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("SVC files", "*svc");
            fileChooser.getExtensionFilters().add(extensionFilter);
            fileChooser.setInitialDirectory(Pfade.SAVES.toFile());
            File file = fileChooser.showOpenDialog(Spielstart.getPrimaryStage());
            if (file != null) {
                this.layoutController.removeFromCenter(this.self);
                this.spielstart.spielLaden(file.toPath());
            }
        } catch (Exception e) {
            this.spielstart.getUserInterface().zeigeError("Spielstand kann jetzt nicht geladen werden.");
        }
    }

    @FXML
    private void handleBeenden() {
        Sound.getInstanz().playSoundeffekt(Sound.BUTTON_CLIP);
        System.exit(0);
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

