package de.sharknoon.siedlervoncatan.utility.popup;

import de.sharknoon.siedlervoncatan.enums.Rohstoff;
import de.sharknoon.siedlervoncatan.utility.Pfade;
import de.sharknoon.siedlervoncatan.view.ViewController;
import de.sharknoon.siedlervoncatan.view.controller.RohstoffauswahlController;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class Rohstoffauswahl
extends Popup
implements PropertyChangeListener {
    private Rohstoff rohstoff;
    private Stage stage;

    public Rohstoffauswahl() {
        this("Wähle einen Rohstoff.");
    }

    public Rohstoffauswahl(String text) {
        try {
            ViewController viewController = new ViewController(null, null);
            this.stage = viewController.createStage(Pfade.ROHSTOFFAUSWAHL, text);
            RohstoffauswahlController controller = viewController.getLoader().getController();
            controller.setRohstoffauswahl(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Rohstoff showAndWait() {
        super.animatedShowAndWait(this.stage);
        return this.rohstoff;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.rohstoff = (Rohstoff) evt.getNewValue();
    }
}

