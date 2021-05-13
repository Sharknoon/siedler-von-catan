package de.sharknoon.siedlervoncatan.enums;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public enum Entwicklung {
    RITTER("Wenn Sie diese Karte ausspielen, versetzen Sie den Räuber und ziehen bei einem der betroffenen Spieler eine Karte."),
    STRASSENBAU("Wenn Sie diese Karte ausspielen, dürfen Sie kostenlos zwei Strassen bauen."),
    ROHSTOFFMONOPOL("Wenn Sie diese Karte ausspielen, wählen Sie einen Rohstoff. Alle Spieler müssen Ihnen von diesem Rohstoff alle Karten geben, die sie besitzen."),
    ERFINDUNG("Wenn Sie diese Karte ausspielen, nehmen Sie sofort zwei Rohstoffkarten Ihrer Wahl vom Vorrat."),
    SIEGPUNKT("1 Siegpunkt");

    private static final List<Entwicklung> stapel;
    private final String text;

    static {
        stapel = new LinkedList<>();
        for (int i = 0; i < 14; ++i) {
            stapel.add(RITTER);
        }
        stapel.add(ERFINDUNG);
        stapel.add(ERFINDUNG);
        stapel.add(ROHSTOFFMONOPOL);
        stapel.add(ROHSTOFFMONOPOL);
        stapel.add(STRASSENBAU);
        stapel.add(STRASSENBAU);
        for (int i = 0; i < 5; ++i) {
            stapel.add(SIEGPUNKT);
        }
        Collections.shuffle(stapel);
    }

    Entwicklung(String text) {
        this.text = text;
    }

    public static void addEntwicklung(Entwicklung entwicklung) {
        stapel.add(entwicklung);
    }

    public static boolean istNichtLeer() {
        return stapel.size() > 0;
    }

    public static Entwicklung removeEntwicklung() {
        return stapel.remove(0);
    }

    public String getText() {
        return this.text;
    }
}

