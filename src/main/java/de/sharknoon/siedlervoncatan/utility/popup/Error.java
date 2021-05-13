package de.sharknoon.siedlervoncatan.utility.popup;

import de.sharknoon.siedlervoncatan.utility.Pfade;
import de.sharknoon.siedlervoncatan.view.ViewController;
import javafx.stage.Stage;

import java.io.IOException;

public class Error
extends Popup {
    private Stage stage;

    public Error(String text) {
        try {
            ViewController viewController = new ViewController(null, null);
            this.stage = viewController.createStage(Pfade.ERROR, text);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAndWait() {
        super.animatedShowAndWait(this.stage);
    }
}

