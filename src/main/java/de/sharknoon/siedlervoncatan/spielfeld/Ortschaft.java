package de.sharknoon.siedlervoncatan.spielfeld;

import javafx.scene.image.Image;
import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.utility.Position;

public interface Ortschaft {
    Spieler getBesitzer();

    int getErtrag();

    Position getPosition();

    Image getImage();
}

