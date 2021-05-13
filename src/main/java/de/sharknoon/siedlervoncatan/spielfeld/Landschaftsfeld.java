package de.sharknoon.siedlervoncatan.spielfeld;

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
            this.image = switch (this.landschaft) {
                case ACKERLAND -> new Image(Pfade.LANDSCHAFT_ACKERLAND);
                case GEBIRGE -> new Image(Pfade.LANDSCHAFT_GEBIRGE);
                case HUEGELLAND -> new Image(Pfade.LANDSCHAFT_HUEGELLAND);
                case WALD -> new Image(Pfade.LANDSCHAFT_WALD);
                case WUESTE -> new Image(Pfade.LANDSCHAFT_WUESTE);
                case WEIDELAND -> new Image(Pfade.LANDSCHAFT_WEIDELAND);
            };
        }
        return this.image;
    }

    public String toString() {
        return String.format("%s %s %d.", this.zentrum, this.landschaft, this.zahl);
    }
}

