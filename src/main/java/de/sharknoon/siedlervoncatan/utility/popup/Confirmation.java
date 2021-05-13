package de.sharknoon.siedlervoncatan.utility.popup;

import de.sharknoon.siedlervoncatan.utility.Pfade;
import de.sharknoon.siedlervoncatan.view.ViewController;
import de.sharknoon.siedlervoncatan.view.controller.ConfirmationController;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class Confirmation
extends Popup
implements PropertyChangeListener {
    private Stage stage;
    private boolean antwort;

    public Confirmation(String text) {
        try {
            ViewController viewController = new ViewController(null, null);
            this.stage = viewController.createStage(Pfade.CONFIRMATION, text);
            ConfirmationController controller = viewController.getLoader().getController();
            controller.setConfirmation(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showAndWait() {
        super.animatedShowAndWait(this.stage);
        return this.antwort;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.antwort = (Boolean)evt.getNewValue();
    }
}

