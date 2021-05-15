package de.sharknoon.siedlervoncatan.spielfeld;

import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.utility.Pfade;
import de.sharknoon.siedlervoncatan.utility.Position;
import javafx.collections.ObservableMap;
import javafx.scene.image.Image;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

public class Siedlung implements Ortschaft, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Spieler besitzer;
    private final Position position;
    private transient Image image;

    public Siedlung(Spieler besitzer, Position position, boolean ignoriereStrassenanbindung) throws IllegalArgumentException {
        if (!this.bauplatzFrei(position, besitzer) || !ignoriereStrassenanbindung && !this.hatStrassenanbindung(position, besitzer)) {
            throw new IllegalArgumentException("Kann an dieser Position nicht gebaut werden.");
        }
        this.besitzer = besitzer;
        this.position = position;
        besitzer.getSpiel().getSpielfeld().putOrtschaft(this);
    }

    private boolean bauplatzFrei(Position position, Spieler besitzer) {
        ObservableMap<Position, Ortschaft> bauplaetze = besitzer.getSpiel().getSpielfeld().getBauplaetze();
        if (bauplaetze.get(position) != null) {
            return false;
        }
        for (Position posNachbar : position.getNachbarn()) {
            if (bauplaetze.get(posNachbar) == null) continue;
            return false;
        }
        return true;
    }

    private boolean hatStrassenanbindung(Position position, Spieler besitzer) {
        for (Set<Position> posStrasse : besitzer.getStrassen().keySet()) {
            if (!posStrasse.contains(position)) continue;
            return true;
        }
        return false;
    }

    @Override
    public int getErtrag() {
        return 1;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Image getImage() {
        if (this.image == null) {
            String path = switch (this.besitzer.getFarbe()) {
                case ROT -> Pfade.SIEDLUNG_ROT;
                case BLAU -> Pfade.SIEDLUNG_BLAU;
                case GELB -> Pfade.SIEDLUNG_GELB;
                case BRAUN -> Pfade.SIEDLUNG_BRAUN;
                case GRUEN -> Pfade.SIEDLUNG_GRUEN;
                case WEISS -> Pfade.SIEDLUNG_WEISS;
            };
            this.image = new Image(path);
        }
        return this.image;
    }

    public String toString() {
        return String.format("Siedlung %s", this.besitzer.getFarbe());
    }
}

