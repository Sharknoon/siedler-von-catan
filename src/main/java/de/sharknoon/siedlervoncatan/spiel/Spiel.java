package de.sharknoon.siedlervoncatan.spiel;

import de.sharknoon.siedlervoncatan.Spielstart;
import de.sharknoon.siedlervoncatan.enums.Farbe;
import de.sharknoon.siedlervoncatan.enums.Rohstoff;
import de.sharknoon.siedlervoncatan.enums.Zustand;
import de.sharknoon.siedlervoncatan.io.UserInterface;
import de.sharknoon.siedlervoncatan.spielfeld.Entwicklungskarte;
import de.sharknoon.siedlervoncatan.spielfeld.Landschaftsfeld;
import de.sharknoon.siedlervoncatan.spielfeld.Raeuber;
import de.sharknoon.siedlervoncatan.spielfeld.Spielfeld;
import de.sharknoon.siedlervoncatan.utility.Pfade;
import de.sharknoon.siedlervoncatan.utility.Position;
import de.sharknoon.siedlervoncatan.utility.Wuerfel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Spiel implements Serializable, PropertyChangeListener {
    @Serial
    private static final long serialVersionUID = 1L;
    public static List<Farbe> farben;
    private Position posSiedlung;
    private int anzahlRundenSave;
    private transient IntegerProperty anzahlRunden;
    private List<Spieler> alleSpielerSave;
    private transient ObservableList<Spieler> alleSpieler;
    private final Spielfeld spielfeld;
    private final Wuerfel wuerfel;
    private final Raeuber raeuber;
    private Spieler sieger;
    private Spieler aktiverSpieler;
    private transient Spielstart spielstart;
    private transient UserInterface ui;
    private Zustand zustand;
    private boolean hatGewuerfelt;
    private boolean saveable;

    public Spiel() {
        farben = new ArrayList<>(Arrays.asList(Farbe.values()));
        this.alleSpieler = FXCollections.observableArrayList();
        this.spielfeld = new Spielfeld();
        this.wuerfel = new Wuerfel();
        this.wuerfel.addListener(this);
        this.raeuber = new Raeuber(this);
        this.sieger = null;
        this.anzahlRunden = new SimpleIntegerProperty(1);
        this.hatGewuerfelt = false;
        this.saveable = false;
    }

    public void setSpielstart(Spielstart spielstart) {
        this.spielstart = spielstart;
        this.setUserInterface(spielstart.getUserInterface());
    }

    public void setUserInterface(UserInterface ui) {
        this.ui = ui;
        ui.setSpiel(this);
    }

    public void starten() {
        this.ui.getSpielfeldController().addListener(this);
        this.ui.zeigeNeuesspielMenue();
    }

    private void autosave() {
        Path path = Pfade.SAVES.resolve("autosave.svc");
        this.speichern(path);
    }

    public void speichern(Path path) {
        if (this.saveable) {
            try {
                this.preSave();
                OutputStream os = Files.newOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(os));
                oos.writeObject(this);
                oos.flush();
                oos.close();
                os.close();
            } catch (Exception e) {
                this.ui.zeigeError("Spielstand konnte nicht gespeichert werden in der Datei:\n" + path);
                e.printStackTrace();
            }
        } else {
            this.ui.zeigeError("Spielstand kann jetzt nicht gespeichert werden.");
        }
    }

    public void spielerAnlegen() {
        this.ui.spielerAnlegen();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Ecke") && this.zustand != null) {
            switch (this.zustand) {
                case ERSTE_SIEDLUNG -> this.setzeSiedlung((Position) evt.getNewValue());
                case SIEDLUNG_BAUEN -> this.siedlungBauen((Position) evt.getNewValue());
                case STADT_BAUEN -> this.stadtBauen((Position) evt.getNewValue());
            }
        }
        if (evt.getPropertyName().equals("Kante") && this.zustand != null) {
            switch (this.zustand) {
                case ERSTE_STRASSE -> this.setzeStrasse((Set<Position>) evt.getNewValue());
                case STARSSE_BAUEN -> this.strasseBauen((Set<Position>) evt.getNewValue());
            }
        }
        if (evt.getPropertyName().equals("Landschaftsfeld") && this.zustand == Zustand.LANDSCHAFTSFELD) {
            this.versetzeRauber((Position) evt.getNewValue());
        }
        if (evt.getPropertyName().equals("Spieler") && this.zustand == Zustand.SPIELER) {
            this.angrenzenderSpieler((Spieler) evt.getNewValue());
        }
        if (evt.getPropertyName().equals("wuerfeln")) {
            String ergebnis = evt.getNewValue().toString();
            this.ui.zeigeMessage(this.aktiverSpieler + " hat eine " + ergebnis + " gewürfelt.");
        }
    }

    public void spielen() {
        Collections.shuffle(this.alleSpieler);
        this.ui.zeigeSpielInfos();
        this.ersteRunde();
    }

    public void weiterspielen() {
        this.ui.getSpielfeldController().addListener(this);
        if (this.hatGewuerfelt) {
            this.zeigeZug();
        } else {
            this.naechsteRunde();
        }
    }

    private void ersteRunde() {
        this.aktiverSpieler = this.naechsterSpieler();
        if (this.aktiverSpieler != null) {
            this.setzeErsteRunde(false);
        } else {
            Collections.reverse(this.alleSpieler);
            for (Spieler spieler : this.alleSpieler) {
                spieler.hatGesetzt(false);
            }
            if (this.alleSpieler.get(0).getOrtschaften().size() < 2) {
                this.ersteRunde();
            } else {
                this.zustand = null;
                this.setSaveable();
                this.naechsteRunde();
            }
        }
    }

    private void setzeErsteRunde(boolean siedlungGebaut) {
        if (!siedlungGebaut) {
            this.zustand = Zustand.ERSTE_SIEDLUNG;
            this.ui.zeigeMessage(this.aktiverSpieler + " wählen Sie einen Bauplatz für Ihre Siedlung.");
        } else {
            this.zustand = Zustand.ERSTE_STRASSE;
            this.ui.zeigeMessage(this.aktiverSpieler + " wählen Sie einen Bauplatz für Ihre Strasse.");
        }
    }

    private void setzeSiedlung(Position position) {
        this.posSiedlung = position;
        boolean gebaut = this.aktiverSpieler.baueSiedlung(position, true);
        if (gebaut) {
            if (this.aktiverSpieler.getOrtschaften().size() == 2) {
                for (Landschaftsfeld landschaftsfeld : this.spielfeld.getLandschaftsfelder().values()) {
                    if (!landschaftsfeld.getZentrum().isNachbar(position) || landschaftsfeld.getLandschaft().getRohstoff() == null)
                        continue;
                    Rohstoff rohstoff = landschaftsfeld.getLandschaft().getRohstoff();
                    this.aktiverSpieler.addKarte(rohstoff);
                }
            }
            this.setzeErsteRunde(true);
        }
    }

    private void setzeStrasse(Set<Position> positionen) {
        if (positionen.contains(this.posSiedlung)) {
            boolean gebaut = this.aktiverSpieler.baueStrasse(positionen, true, true);
            if (gebaut) {
                this.aktiverSpieler.hatGesetzt(true);
                this.ersteRunde();
            }
        } else {
            this.ui.zeigeError("Strasse muss an die zuletzt gebaute Siedlung angrenzen.");
        }
    }

    private Spieler naechsterSpieler() {
        for (Spieler spieler : this.alleSpieler) {
            if (spieler.getGespielteRunden() == this.anzahlRunden.get() || spieler.hatGesetzt()) continue;
            return spieler;
        }
        return null;
    }

    public void naechsteRunde() {
        this.hatGewuerfelt = false;
        this.ui.zeigeMessage("");
        if (this.sieger == null) {
            this.aktiverSpieler = this.naechsterSpieler();
            if (this.aktiverSpieler == null) {
                this.anzahlRunden.set(this.anzahlRunden.get() + 1);
                this.setSaveable();
                this.autosave();
                this.naechsteRunde();
            } else {
                this.ui.zeigeMessage(this.aktiverSpieler + " ist am Zug.");
                this.aktiverSpieler.setAktiv();
                this.aktiverSpieler.setEntwicklungskarteNichtGespielt();
                for (Entwicklungskarte entwicklungskarte : this.aktiverSpieler.getEntwickulungskarten()) {
                    entwicklungskarte.darfGespieltWerden();
                }
                this.setSaveable();
                this.ui.zeigeWuerfel();
            }
        } else {
            this.ui.zeigeSieger();
        }
    }

    public void wuerfeln() {
        this.wuerfel.wuerfeln();
        if (this.aktiverSpieler.getRaeuberVersetzen()) {
            this.aktiverSpieler.versetzeRauber();
        } else {
            this.zeigeZug();
        }
    }

    public void entwicklungKaufen() {
        boolean gekauft = this.aktiverSpieler.kaufeEntwicklungskarte();
        if (gekauft) {
            this.ui.zeigeInfo(String.format("%s hat eine Entwicklungskarte gekauft.", this.aktiverSpieler));
        }
    }

    public void stadtBauen(Position position) {
        boolean gebaut = this.aktiverSpieler.baueStadt(position);
        if (gebaut) {
            this.zustand = null;
            this.ui.zeigeMessage("");
        }
    }

    public void siedlungBauen(Position position) {
        boolean gebaut = this.aktiverSpieler.baueSiedlung(position, false);
        if (gebaut) {
            this.zustand = null;
            this.ui.zeigeMessage("");
        }
    }

    public void strasseBauen(Set<Position> positionen) {
        boolean gebaut = this.aktiverSpieler.baueStrasse(positionen, false, false);
        if (gebaut) {
            this.zustand = null;
            this.ui.zeigeMessage("");
        }
    }

    private void versetzeRauber(Position position) {
        boolean versetzt = this.raeuber.versetze(position);
        if (versetzt) {
            this.aktiverSpieler.rauberVersetzt();
            if (this.raeuber.getAngrenzendeSpieler().isEmpty()) {
                this.zustand = null;
                this.ui.zeigeMessage("");
                this.zeigeZug();
            } else {
                this.zustand = Zustand.SPIELER;
                this.ui.zeigeMessage(this.aktiverSpieler + " wählen Sie den Spieler bei dem Sie ziehen möchten.");
            }
        }
    }

    private void angrenzenderSpieler(Spieler spieler) {
        if (this.raeuber.getAngrenzendeSpieler().contains(spieler) && !this.aktiverSpieler.equals(spieler)) {
            this.aktiverSpieler.zieheKarte(spieler);
            this.zustand = null;
            this.ui.zeigeMessage("");
            this.zeigeZug();
        } else {
            this.ui.zeigeError("Sie können nicht bei " + spieler + " ziehen.");
        }
    }

    private void zeigeZug() {
        this.zustand = null;
        this.hatGewuerfelt = true;
        this.saveable = true;
        this.ui.zeigeZug();
    }

    public void setSieger(Spieler spieler) {
        this.sieger = spieler;
    }

    public Spieler getSieger() {
        return this.sieger;
    }

    public void addSpieler(Spieler spieler) {
        this.alleSpieler.add(spieler);
        this.wuerfel.addListener(spieler);
    }

    public ObservableList<Spieler> getAlleSpieler() {
        return this.alleSpieler;
    }

    public Raeuber getRaeuber() {
        return this.raeuber;
    }

    public Spielfeld getSpielfeld() {
        return this.spielfeld;
    }

    public UserInterface getUserInterface() {
        return this.ui;
    }

    public IntegerProperty getAnzahlRunden() {
        return this.anzahlRunden;
    }

    public Spieler getAktiverSpieler() {
        return this.aktiverSpieler;
    }

    public Spielstart getSpielstart() {
        return this.spielstart;
    }

    public void setZustand(Zustand zustand) {
        this.zustand = zustand;
    }

    public Zustand getZustand() {
        return this.zustand;
    }

    public boolean isSaveable() {
        return this.saveable;
    }

    public void setSaveable() {
        this.saveable = true;
    }

    public void setNotSaveable() {
        this.saveable = false;
    }

    public boolean hatGewuerfelt() {
        return this.hatGewuerfelt;
    }

    public Wuerfel getWuerfel() {
        return this.wuerfel;
    }

    public void preSave() {
        this.alleSpielerSave = new ArrayList<>(this.alleSpieler);
        for (Spieler spieler : this.alleSpieler) {
            spieler.preSave();
        }
        this.spielfeld.preSave();
        this.raeuber.preSave();
        this.anzahlRundenSave = this.anzahlRunden.get();
    }

    public void postLoad() {
        this.alleSpieler = FXCollections.observableArrayList(this.alleSpielerSave);
        for (Spieler spieler : this.alleSpieler) {
            spieler.postLoad();
        }
        this.spielfeld.postLoad();
        this.raeuber.postLoad();
        this.anzahlRunden = new SimpleIntegerProperty(this.anzahlRundenSave);
    }
}

