package de.sharknoon.siedlervoncatan.view;

import javafx.scene.Node;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.view.controller.RootLayoutController;

public interface Controller {
    void setSpiel(Spiel spiel);

    void setLayoutController(RootLayoutController layoutController);

    void setNode(Node self);
}

