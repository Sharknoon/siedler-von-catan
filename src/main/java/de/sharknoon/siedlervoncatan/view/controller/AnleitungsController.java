package de.sharknoon.siedlervoncatan.view.controller;

import de.sharknoon.siedlervoncatan.utility.Pfade;
import de.sharknoon.siedlervoncatan.view.PopupController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.stream.Stream;

public class AnleitungsController implements PopupController {

    @FXML
    VBox vBoxImages;

    @FXML
    Label labelClose;

    @Override
    public void setStage(Stage stage) {
        labelClose.setOnMouseClicked(event -> stage.close());
        Stream.of(
                Pfade.ANLEITUNG_1,
                Pfade.ANLEITUNG_2,
                Pfade.ANLEITUNG_3,
                Pfade.ANLEITUNG_4,
                Pfade.ANLEITUNG_5,
                Pfade.ANLEITUNG_6,
                Pfade.ANLEITUNG_7,
                Pfade.ANLEITUNG_8,
                Pfade.ANLEITUNG_9,
                Pfade.ANLEITUNG_10,
                Pfade.ANLEITUNG_11,
                Pfade.ANLEITUNG_12
        )
                .map(Image::new)
                .map(ImageView::new)
                .forEach(i -> {
                    i.setFitWidth(785);
                    i.setPreserveRatio(true);
                    vBoxImages.getChildren().add(i);
                });
    }

    @Override
    public void setText(String text) {
    }
}
