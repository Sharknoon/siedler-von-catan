package de.sharknoon.siedlervoncatan.spielfeld;

import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.utility.Pfade;
import de.sharknoon.siedlervoncatan.utility.Position;
import javafx.scene.image.Image;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Strasse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Spieler besitzer;
    private final Set<Position> positionen;
    private transient Image image;

    public Strasse(Spieler besitzer, Position position1, Position position2, boolean nurOrtsanbindung) throws IllegalArgumentException {
        boolean hatAnbindung = this.hatOrtschaftsanbindung(position1, besitzer) || this.hatOrtschaftsanbindung(position2, besitzer) || this.hatStrassenanbindung(position1, besitzer) || this.hatStrassenanbindung(position2, besitzer);
        if (nurOrtsanbindung) {
            hatAnbindung = this.hatOrtschaftsanbindung(position1, besitzer) || this.hatOrtschaftsanbindung(position2, besitzer);
        }
        if (!(position1.isNachbar(position2) && this.bauplatzFrei(position1, position2, besitzer) && hatAnbindung)) {
            throw new IllegalArgumentException("Kann zwischen diesen Positionen nicht gebaut werden.");
        }
        this.besitzer = besitzer;
        this.positionen = new HashSet<>();
        this.positionen.add(position1);
        this.positionen.add(position2);
        this.besitzer.getSpiel().getSpielfeld().putStrasse(this);
    }

    private boolean bauplatzFrei(Position position1, Position position2, Spieler besitzer) {
        for (Set<Position> posStrasse : besitzer.getSpiel().getSpielfeld().getStrassen().keySet()) {
            if (!posStrasse.contains(position1) || !posStrasse.contains(position2)) continue;
            return false;
        }
        return true;
    }

    private boolean hatStrassenanbindung(Position position, Spieler besitzer) {
        for (Set<Position> posStrasse : besitzer.getStrassen().keySet()) {
            if (posStrasse == null || !posStrasse.contains(position)) continue;
            return true;
        }
        return false;
    }

    private boolean hatOrtschaftsanbindung(Position position, Spieler besitzer) {
        for (Position posOrtschaft : besitzer.getOrtschaften().keySet()) {
            if (!position.equals(posOrtschaft)) continue;
            return true;
        }
        return false;
    }

    public Spieler getBesitzer() {
        return this.besitzer;
    }

    public Set<Position> getPositionen() {
        return this.positionen;
    }

    public Image getImage() {
        if (this.image == null) {
            String path = switch (this.besitzer.getFarbe()) {
                case ROT -> Pfade.STRASSE_ROT;
                case BLAU -> Pfade.STRASSE_BLAU;
                case GELB -> Pfade.STRASSE_GELB;
                case BRAUN -> Pfade.STRASSE_BRAUN;
                case GRUEN -> Pfade.STRASSE_GRUEN;
                case WEISS -> Pfade.STRASSE_WEISS;
            };
            this.image = new Image(path);
        }
        return this.image;
    }

    public String toString() {
        return String.format("Strasse %s", this.besitzer.getFarbe());
    }
}

