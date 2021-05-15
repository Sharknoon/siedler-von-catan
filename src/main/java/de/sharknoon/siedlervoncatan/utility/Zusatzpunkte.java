package de.sharknoon.siedlervoncatan.utility;

import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.spielfeld.Strasse;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Zusatzpunkte implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static void pruefeGroessteRittermacht(Spieler spieler) {
        if (spieler.hatGroessteRittermacht()) {
            return;
        }
        boolean groessteRittermachtVergeben = false;
        for (Spieler andererSpieler : spieler.getSpiel().getAlleSpieler()) {
            if (!andererSpieler.hatGroessteRittermacht()) continue;
            groessteRittermachtVergeben = true;
            if (spieler.getRitter().get() <= andererSpieler.getRitter().get()) continue;
            Zusatzpunkte.changeGroessteRittermacht(spieler, andererSpieler);
        }
        if (!groessteRittermachtVergeben) {
            Zusatzpunkte.setGroessteRittermacht(spieler);
        }
    }

    private static void setGroessteRittermacht(Spieler spieler) {
        spieler.bekommtGroessteRittermacht();
        spieler.erhoeheSiegpunkte();
        spieler.erhoeheSiegpunkte();
        spieler.getSpiel().getUserInterface().zeigeInfo(spieler + " hat nun die größte Rittermacht.");
    }

    private static void changeGroessteRittermacht(Spieler spieler, Spieler andererSpieler) {
        spieler.bekommtGroessteRittermacht();
        spieler.erhoeheSiegpunkte();
        spieler.erhoeheSiegpunkte();
        andererSpieler.verliertGroessteRittermacht();
        andererSpieler.erniedrigeSiegpunkte();
        andererSpieler.erniedrigeSiegpunkte();
        spieler.getSpiel().getUserInterface().zeigeInfo(spieler + " hat nun die größte Rittermacht.");
    }

    public static int laengsteHandelsstrasse(Spieler spieler, Strasse strasse) {
        Collection<Strasse> strassen = spieler.getStrassen().values();
        int laenge = 1;
        ArrayList<Strasse> kopieStrassen = new ArrayList<>(strassen);
        kopieStrassen.remove(strasse);
        for (Position position : strasse.getPositionen()) {
            laenge += Zusatzpunkte.laengsteHandelsstrasse(position, kopieStrassen);
        }
        return laenge;
    }

    private static int laengsteHandelsstrasse(Position startposition, List<Strasse> strassen) {
        int maxlaenge = 0;
        ArrayList<Strasse> kopiestrassen = new ArrayList<>(strassen);
        for (Strasse strasse : kopiestrassen) {
            HashSet<Position> positionen;
            if (!strassen.contains(strasse) || !(positionen = new HashSet<>(strasse.getPositionen())).contains(startposition))
                continue;
            positionen.remove(startposition);
            strassen.remove(strasse);
            int teillaenge = 1;
            for (Position endposition : positionen) {
                maxlaenge = Math.max(maxlaenge, teillaenge += Zusatzpunkte.laengsteHandelsstrasse(endposition, strassen));
            }
        }
        return maxlaenge;
    }

    public static void pruefeLaengsteHandelsstrasse(Spieler spieler) {
        if (spieler.hatLaengsteHandelsstrasse()) {
            return;
        }
        boolean laengsteHandelsstrasseVergeben = false;
        for (Spieler andererSpieler : spieler.getSpiel().getAlleSpieler()) {
            if (!andererSpieler.hatLaengsteHandelsstrasse()) continue;
            laengsteHandelsstrasseVergeben = true;
            if (spieler.getLaengsteHandelsstrasse() <= andererSpieler.getLaengsteHandelsstrasse()) continue;
            Zusatzpunkte.changeLaengsteHandelsstrasse(spieler, andererSpieler);
        }
        if (!laengsteHandelsstrasseVergeben) {
            Zusatzpunkte.setLaengsteHandelsstrasse(spieler);
        }
    }

    private static void setLaengsteHandelsstrasse(Spieler spieler) {
        spieler.bekommtLaengsteHandelsstrasse();
        spieler.erhoeheSiegpunkte();
        spieler.erhoeheSiegpunkte();
        spieler.getSpiel().getUserInterface().zeigeInfo(spieler + " hat die längste Handelsstraße.");
    }

    private static void changeLaengsteHandelsstrasse(Spieler spieler, Spieler andererSpieler) {
        spieler.bekommtLaengsteHandelsstrasse();
        spieler.erhoeheSiegpunkte();
        spieler.erhoeheSiegpunkte();
        andererSpieler.verliertLaengsteHandelsstrasse();
        andererSpieler.erniedrigeSiegpunkte();
        andererSpieler.erniedrigeSiegpunkte();
        spieler.getSpiel().getUserInterface().zeigeInfo(spieler + " hat nun die längste Handelsstraße.");
    }
}

