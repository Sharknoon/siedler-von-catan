package de.sharknoon.siedlervoncatan.spielfeld;

import de.sharknoon.siedlervoncatan.enums.Entwicklung;
import de.sharknoon.siedlervoncatan.enums.Rohstoff;
import de.sharknoon.siedlervoncatan.io.UserInterface;
import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.utility.Position;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class Entwicklungskarte implements Serializable, PropertyChangeListener {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Entwicklung entwicklung;
    private boolean darfGespieltWerden;
    private final Spieler besitzer;
    private boolean strasse1;

    public Entwicklungskarte(Spieler besitzer) {
        this.besitzer = besitzer;
        this.entwicklung = this.getZufaelligeEntwicklung();
        this.darfGespieltWerden = this.entwicklung.equals(Entwicklung.SIEGPUNKT);
    }

    public boolean ausspielen() {
        if (this.entwicklung == Entwicklung.SIEGPUNKT) {
            this.besitzer.getSpiel().getUserInterface().zeigeInfo(this.besitzer + " spielt die karte Siegpunkt.");
            this.besitzer.erhoeheSiegpunkte();
            this.besitzer.getEntwickulungskarten().remove(this);
            this.zeigeMenue();
            return true;
        }
        if (!this.besitzer.entwicklungskarteGespielt()) {
            UserInterface userInterface = this.besitzer.getSpiel().getUserInterface();
            switch (this.entwicklung) {
                case RITTER -> {
                    this.besitzer.getSpiel().getUserInterface().zeigeInfo(this.besitzer + " spielt die Karte Ritter.");
                    this.besitzer.versetzeRauber();
                    this.besitzer.addRitter();
                }
                case ROHSTOFFMONOPOL -> {
                    Rohstoff rohstoff = userInterface.zeigeRohstoffauswahl(this.besitzer + " wählen Sie einen Rohstoff.");
                    this.besitzer.getSpiel().getUserInterface().zeigeInfo(this.besitzer + " spielt die Karte Rohstoffmonopol und bekommt alles " + rohstoff + ".");
                    for (Spieler spieler : this.besitzer.getSpiel().getAlleSpieler()) {
                        if (this.besitzer.equals(spieler)) continue;
                        ArrayList<Rohstoff> kopieKarten = new ArrayList<>(spieler.getKarten());
                        for (Rohstoff rohstoffKarte : kopieKarten) {
                            if (!rohstoff.equals(rohstoffKarte)) continue;
                            spieler.removeKarte(rohstoff);
                            this.besitzer.addKarte(rohstoff);
                        }
                    }
                    Entwicklung.addEntwicklung(this.entwicklung);
                    this.zeigeMenue();
                }
                case STRASSENBAU -> {
                    this.strasse1 = true;
                    this.besitzer.getSpiel().getUserInterface().zeigeInfo(this.besitzer + " spielt die Karte Strassenbau.");
                    this.besitzer.getSpiel().setNotSaveable();
                    userInterface.zeigeMessage(this.besitzer + " wählen Sie einen Bauplatz für Ihre erste Strasse.");
                    userInterface.getSpielfeldController().addListener(this);
                    Entwicklung.addEntwicklung(this.entwicklung);
                }
                case ERFINDUNG -> {
                    this.besitzer.getSpiel().setNotSaveable();
                    this.besitzer.getSpiel().getUserInterface().zeigeInfo(this.besitzer + " spielt die Karte Erfindung.");
                    Rohstoff rohstoff = userInterface.zeigeRohstoffauswahl(this.besitzer + " wählen Sie einen Rohstoff.");
                    this.besitzer.addKarte(rohstoff);
                    rohstoff = userInterface.zeigeRohstoffauswahl(this.besitzer + " wählen Sie einen Rohstoff.");
                    this.besitzer.addKarte(rohstoff);
                    Entwicklung.addEntwicklung(this.entwicklung);
                    this.zeigeMenue();
                }
            }
            this.besitzer.setEntwicklungskarteGespielt();
            this.besitzer.getEntwickulungskarten().remove(this);
            return true;
        }
        this.besitzer.getSpiel().getUserInterface().zeigeError("Sie können keine weitere Entwicklung mehr spielen.");
        return false;
    }

    private void strassenbau(Set<Position> positionen) {
        boolean gebaut = this.besitzer.baueStrasse(positionen, false, true);
        if (gebaut) {
            UserInterface userInterface = this.besitzer.getSpiel().getUserInterface();
            if (this.strasse1) {
                this.strasse1 = false;
                userInterface.zeigeMessage(this.besitzer + " wählen Sie einen Bauplatz für Ihre zweite Strasse.");
            } else {
                userInterface.getSpielfeldController().removeListener(this);
                userInterface.zeigeMessage("");
                this.zeigeMenue();
            }
        }
    }

    private void zeigeMenue() {
        this.besitzer.getSpiel().setSaveable();
        if (this.besitzer.getSpiel().hatGewuerfelt()) {
            this.besitzer.getSpiel().getUserInterface().zeigeZug();
        } else {
            this.besitzer.getSpiel().getUserInterface().zeigeWuerfel();
        }
    }

    private Entwicklung getZufaelligeEntwicklung() {
        return Entwicklung.removeEntwicklung();
    }

    public boolean getDarfGespieltWerden() {
        return this.darfGespieltWerden;
    }

    public void darfGespieltWerden() {
        this.darfGespieltWerden = true;
    }

    public String toString() {
        return this.entwicklung.toString();
    }

    public Entwicklung getEntwicklung() {
        return this.entwicklung;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "Kante")) {
            this.strassenbau((Set<Position>)evt.getNewValue());
        }
    }
}

