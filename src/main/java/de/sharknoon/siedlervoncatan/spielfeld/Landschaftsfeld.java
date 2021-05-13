package de.sharknoon.siedlervoncatan.spielfeld;

import de.sharknoon.siedlervoncatan.Spielstart;
import de.sharknoon.siedlervoncatan.enums.Landschaft;
import de.sharknoon.siedlervoncatan.utility.Pfade;
import de.sharknoon.siedlervoncatan.utility.Position;
import javafx.scene.image.Image;

import java.io.Serial;
import java.io.Serializable;

public class Landschaftsfeld
implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Position zentrum;
    private final Landschaft landschaft;
    private final int zahl;
    private transient Image image;

    public Landschaftsfeld(Position zentrum, Landschaft landschaft, int zahl) {
        this.zentrum = zentrum;
        this.landschaft = landschaft;
        this.zahl = zahl;
    }

    public Landschaft getLandschaft() {
        return this.landschaft;
    }

    public int getZahl() {
        return this.zahl;
    }

    public Position getZentrum() {
        return this.zentrum;
    }

    public Image getImage() {
        if (this.image == null) {
            String art = this.landschaft.toString().toLowerCase();
            this.image = new Image(String.valueOf(Spielstart.class.getResource(Pfade.LANDSCHAFT.replace("{art}", art))));
        }
        return this.image;
    }

    public String toString() {
        return String.format("%s %s %d.", this.zentrum, this.landschaft, this.zahl);
    }
}

