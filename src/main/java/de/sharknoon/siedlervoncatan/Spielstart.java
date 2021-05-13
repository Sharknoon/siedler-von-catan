package de.sharknoon.siedlervoncatan;

import de.sharknoon.siedlervoncatan.io.Menuefx;
import de.sharknoon.siedlervoncatan.io.UserInterface;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.utility.Pfade;
import de.sharknoon.siedlervoncatan.view.controller.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class Spielstart extends Application {
    private static Stage PRIMARYSTAGE;
    private BorderPane rootLayout;
    private RootLayoutController layoutController;
    private Spiel spiel;
    private UserInterface ui;

    public void start(Stage primaryStage) {
        PRIMARYSTAGE = primaryStage;
        primaryStage.setTitle("Siedler von Catan");
        primaryStage.getIcons().add(new Image(Pfade.LOGO));
        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.setMinHeight(730.0);
        primaryStage.setMinWidth(920.0);
        this.initRootLayout();
        this.ui = new Menuefx();
        this.ui.setSpielstart(this);
        this.ui.zeigeHauptmenue();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL(Pfade.ROOTLAYOUT));
            this.rootLayout = loader.load();
            Scene scene = new Scene(this.rootLayout);
            PRIMARYSTAGE.setScene(scene);
            this.layoutController = loader.getController();
            this.layoutController.setSpielstart(this);
            PRIMARYSTAGE.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void spielLaden(Path path) {
        try {
            InputStream is = Files.newInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(is));
            this.spiel = (Spiel)ois.readObject();
            ois.close();
            is.close();
            this.spiel.postLoad();
            this.spiel.setSpielstart(this);
            this.ui.zeigeSpielfeld();
            this.ui.zeigeSpielInfos();
            this.spiel.weiterspielen();
        }
        catch (Exception e) {
            this.ui.zeigeError("Spielstand konnte nicht geladen werden aus der Datei:\n" + path);
            e.printStackTrace();
        }
    }

    public void spielSpeichern(Path path) {
        if (this.spiel != null) {
            this.spiel.speichern(path);
        }
    }

    public void neuesSpiel() {
        this.spiel = new Spiel();
        this.spiel.setSpielstart(this);
        this.ui.zeigeSpielfeld();
        this.spiel.starten();
    }

    public void beenden() {
        boolean antwort = this.ui.zeigeConfirmation("Möchten Sie das Spiel wirklich beenden?");
        if (antwort) {
            System.exit(0);
        }
    }

    public static Stage getPrimaryStage() {
        return PRIMARYSTAGE;
    }

    public BorderPane getRootLayout() {
        return this.rootLayout;
    }

    public Spiel getSpiel() {
        return this.spiel;
    }

    public UserInterface getUserInterface() {
        return this.ui;
    }

    public RootLayoutController getLayoutController() {
        return this.layoutController;
    }
}

