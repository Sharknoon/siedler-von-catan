package de.sharknoon.siedlervoncatan.utility;

import de.sharknoon.siedlervoncatan.enums.Rohstoff;
import de.sharknoon.siedlervoncatan.spiel.Spieler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Handel {
    private final ObservableList<Rohstoff> angebot;
    private final ObservableList<Rohstoff> nachfrage;
    private Spieler nachfrager;
    private Spieler anbieter;

    public Handel() {
        this(FXCollections.observableArrayList(), FXCollections.observableArrayList());
    }

    public Handel(ObservableList<Rohstoff> angebot, ObservableList<Rohstoff> nachfrage) {
        this.angebot = angebot;
        this.nachfrage = nachfrage;
    }

    public void handeln() {
        boolean antwort = this.anbieter.getSpiel().getUserInterface().zeigeConfirmation(String.format("%s wollen Sie %s gegen %s mit %s tauschen?", this.anbieter, this.angebot, this.nachfrage, this.nachfrager));
        if (antwort) {
            this.anbieter.removeKarten(this.angebot);
            this.anbieter.addKarten(this.nachfrage);
            this.nachfrager.removeKarten(this.nachfrage);
            this.nachfrager.addKarten(this.angebot);
        }
    }

    public void addAngebot(Rohstoff rohstoff) {
        this.angebot.add(rohstoff);
    }

    public boolean removeAngebot(Rohstoff rohstoff) {
        if (this.angebot.contains(rohstoff)) {
            this.angebot.remove(rohstoff);
            return true;
        }
        return false;
    }

    public void addNachfrage(Rohstoff rohstoff) {
        this.nachfrage.add(rohstoff);
    }

    public boolean removeNachfrage(Rohstoff rohstoff) {
        if (this.nachfrage.contains(rohstoff)) {
            this.nachfrage.remove(rohstoff);
            return true;
        }
        return false;
    }

    public ObservableList<Rohstoff> getAngebot() {
        return this.angebot;
    }

    public ObservableList<Rohstoff> getNachfrage() {
        return this.nachfrage;
    }

    public Spieler getNachfrager() {
        return this.nachfrager;
    }

    public void setNachfrager(Spieler nachfrager) {
        this.nachfrager = nachfrager;
    }

    public Spieler getAnbieter() {
        return this.anbieter;
    }

    public void setAnbieter(Spieler anbieter) {
        this.anbieter = anbieter;
    }
}

