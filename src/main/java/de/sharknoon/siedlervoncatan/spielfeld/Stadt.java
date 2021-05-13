package de.sharknoon.siedlervoncatan.spielfeld;

import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.utility.Pfade;
import de.sharknoon.siedlervoncatan.utility.Position;
import javafx.scene.image.Image;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

public class Stadt
implements Ortschaft,
Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Spieler besitzer;
    private final Position position;
    private transient Image image;

    public Stadt(Spieler besitzer, Position position) throws IllegalArgumentException {
        if (!this.bauErlaubt(position, besitzer)) {
            throw new IllegalArgumentException("Kann an dieser Position nicht gebaut werden.");
        }
        this.besitzer = besitzer;
        this.position = position;
        this.besitzer.getSpiel().getSpielfeld().putOrtschaft(this);
    }

    private boolean bauErlaubt(Position position, Spieler besitzer) {
        Map<Position, Ortschaft> ortschaften = besitzer.getOrtschaften();
        return ortschaften.containsKey(position) && ortschaften.get(position) instanceof Siedlung;
    }

    @Override
    public Spieler getBesitzer() {
        return this.besitzer;
    }

    @Override
    public int getErtrag() {
        return 2;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Image getImage() {
        if (this.image == null) {
            String path = switch (this.besitzer.getFarbe()) {
                case ROT -> Pfade.STADT_ROT;
                case BLAU -> Pfade.STADT_BLAU;
                case GELB -> Pfade.STADT_GELB;
                case BRAUN -> Pfade.STADT_BRAUN;
                case GRUEN -> Pfade.STADT_GRUEN;
                case WEISS -> Pfade.STADT_WEISS;
            };
            this.image = new Image(path);
        }
        return this.image;
    }

    public String toString() {
        return String.format("Stadt %s", this.besitzer.getFarbe());
    }
}

