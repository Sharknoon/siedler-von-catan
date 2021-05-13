package de.sharknoon.siedlervoncatan.spielfeld;

import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.utility.Position;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Raeuber
implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Position positionSave;
    private transient ObjectProperty<Position> position;
    private final Set<Spieler> angrenzendeSpieler;
    private final Spiel spiel;

    public Raeuber(Spiel spiel) {
        Position zentrum = spiel.getSpielfeld().getWueste().getZentrum();
        this.position = new SimpleObjectProperty<>(zentrum);
        this.angrenzendeSpieler = new HashSet<>();
        this.spiel = spiel;
        this.position.addListener(e -> spiel.getUserInterface().getSpielfeldController().erzeugeSpielfeld());
    }

    public Set<Spieler> getAngrenzendeSpieler() {
        return this.angrenzendeSpieler;
    }

    public boolean versetze(Position neuePosition) {
        if (this.position.get().equals(neuePosition)) {
            this.spiel.getUserInterface().zeigeError("Der Räuber muss auf eine neue Position gesetzt werden.");
            return false;
        }
        this.position.set(neuePosition);
        this.setAngrenzendeSpieler(neuePosition);
        return true;
    }

    private void setAngrenzendeSpieler(Position neuePosition) {
        this.angrenzendeSpieler.clear();
        for (Spieler spieler : this.spiel.getAlleSpieler()) {
            if (spieler.isAktiv()) continue;
            for (Position positionOrtschaft : spieler.getOrtschaften().keySet()) {
                if (!positionOrtschaft.isNachbar(neuePosition)) continue;
                this.angrenzendeSpieler.add(spieler);
            }
        }
    }

    public ObjectProperty<Position> getPosition() {
        return this.position;
    }

    public void preSave() {
        this.positionSave = this.position.get();
    }

    public void postLoad() {
        this.position = new SimpleObjectProperty<>(this.positionSave);
        this.position.addListener(e -> this.spiel.getUserInterface().getSpielfeldController().erzeugeSpielfeld());
    }
}

