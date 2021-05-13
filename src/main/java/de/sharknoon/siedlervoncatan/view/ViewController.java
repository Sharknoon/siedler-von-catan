package de.sharknoon.siedlervoncatan.view;

import de.sharknoon.siedlervoncatan.Spielstart;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.view.controller.RootLayoutController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ViewController {
    private final Spiel spiel;
    private final RootLayoutController layoutController;
    private FXMLLoader loader;
    private final Stage primaryStage;

    public ViewController(Spiel spiel, RootLayoutController layoutController) {
        this.layoutController = layoutController;
        this.spiel = spiel;
        this.primaryStage = Spielstart.getPrimaryStage();
    }

    public Pane initPane(String view) throws IOException {
        this.loader = new FXMLLoader();
        this.loader.setLocation(Spielstart.class.getResource(view));
        Pane pane = this.loader.load();
        Controller controller = this.loader.getController();
        if (controller != null) {
            controller.setSpiel(this.spiel);
            controller.setNode(pane);
            controller.setLayoutController(this.layoutController);
        }
        return pane;
    }

    public Stage createStage(String view, String text) throws IOException {
        this.loader = new FXMLLoader();
        this.loader.setLocation(Spielstart.class.getResource(view));
        Pane pane = this.loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.primaryStage);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        PopupController controller = this.loader.getController();
        if (controller != null) {
            controller.setStage(stage);
            controller.setText(text);
        }
        return stage;
    }

    public FXMLLoader getLoader() {
        return this.loader;
    }
}

