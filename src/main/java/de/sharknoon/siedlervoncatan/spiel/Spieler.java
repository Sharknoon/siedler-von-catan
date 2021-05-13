package de.sharknoon.siedlervoncatan.spiel;

import de.sharknoon.siedlervoncatan.enums.*;
import de.sharknoon.siedlervoncatan.sound.Sound;
import de.sharknoon.siedlervoncatan.spielfeld.*;
import de.sharknoon.siedlervoncatan.utility.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Spieler
implements PropertyChangeListener,
Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private final Farbe farbe;
    private List<Rohstoff> kartenSave;
    private transient ObservableList<Rohstoff> karten;
    private List<Entwicklungskarte> entwicklungskartenSave;
    private transient ObservableList<Entwicklungskarte> entwicklungskarten;
    private final Map<Position, Ortschaft> ortschaften;
    private final Map<Set<Position>, Strasse> strassen;
    private int anzahlSiedlungen;
    private int anzahlStaedte;
    private int anzahlStrassen;
    private final Set<Hafen> haefen;
    private final Spiel spiel;
    private boolean aktiv;
    private int siegpunkteSave;
    private transient IntegerProperty siegpunkte;
    private int ritterSave;
    private transient IntegerProperty ritter;
    private int anzahlKartenSave;
    private transient IntegerProperty anzahlKarten;
    private int laengsteHandelsstrasse;
    private boolean hatGroessteRittermacht;
    private boolean hatLaengsteHandelsstrasse;
    private boolean hatEntwicklungskarteGespielt;
    private boolean raeuberVersetzen;
    private int gespielteRunden;
    private boolean hatGesetzt;

    public Spieler(String name, Farbe farbe, Spiel spiel) {
        this.name = name;
        this.farbe = farbe;
        this.spiel = spiel;
        this.karten = FXCollections.observableArrayList();
        this.entwicklungskarten = FXCollections.observableArrayList();
        this.ortschaften = new HashMap<>();
        this.strassen = new HashMap<>();
        this.anzahlSiedlungen = 0;
        this.anzahlStaedte = 0;
        this.anzahlStrassen = 0;
        this.haefen = new HashSet<>();
        this.aktiv = false;
        this.siegpunkte = new SimpleIntegerProperty(0);
        this.ritter = new SimpleIntegerProperty(0);
        this.anzahlKarten = new SimpleIntegerProperty(0);
        this.laengsteHandelsstrasse = 0;
        this.hatGroessteRittermacht = false;
        this.hatLaengsteHandelsstrasse = false;
        this.hatEntwicklungskarteGespielt = false;
        this.raeuberVersetzen = false;
        this.gespielteRunden = 0;
        this.hatGesetzt = false;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("wuerfeln")) {
            if (evt.getNewValue().equals(7)) {
                if (this.karten.size() > 7) {
                    int anzahlAbzugebendeKarten = this.karten.size() / 2;
                    this.spiel.getUserInterface().zeigeKartenAbgeben(this, anzahlAbzugebendeKarten);
                }
                if (this.isAktiv()) {
                    this.raeuberVersetzen = true;
                }
            } else {
                for (Ortschaft ortschaft : this.ortschaften.values()) {
                    for (Landschaftsfeld landschaftsfeld : this.spiel.getSpielfeld().getLandschaftsfelder().values()) {
                        if (landschaftsfeld.getLandschaft().getRohstoff() == null || !evt.getNewValue().equals(landschaftsfeld.getZahl()) || landschaftsfeld.getZentrum().equals(this.spiel.getRaeuber().getPosition().get()) || !landschaftsfeld.getZentrum().isNachbar(ortschaft.getPosition())) continue;
                        int ertrag = ortschaft.getErtrag();
                        Rohstoff rohstoff = landschaftsfeld.getLandschaft().getRohstoff();
                        for (int i = 0; i < ertrag; ++i) {
                            this.addKarte(rohstoff);
                        }
                    }
                }
            }
        }
    }

    public boolean baueStrasse(Set<Position> positionen, boolean nurOrtsanbindung, boolean kostenlos) {
        if (kostenlos || this.decktKosten(Baukosten.STRASSE) && this.anzahlStrassen < 15 && positionen.size() == 2) {
            try {
                ArrayList<Position> pos = new ArrayList<>(positionen);
                Strasse strasse = new Strasse(this, pos.get(0), pos.get(1), nurOrtsanbindung);
                this.strassen.put(strasse.getPositionen(), strasse);
                int laengsteHandelsstrasse = Zusatzpunkte.laengsteHandelsstrasse(this, strasse);
                if (this.laengsteHandelsstrasse < laengsteHandelsstrasse && laengsteHandelsstrasse >= 5) {
                    this.laengsteHandelsstrasse = laengsteHandelsstrasse;
                    Zusatzpunkte.pruefeLaengsteHandelsstrasse(this);
                }
                if (!kostenlos) {
                    this.removeKarten(Baukosten.STRASSE);
                }
                ++this.anzahlStrassen;
                return true;
            }
            catch (IllegalArgumentException e) {
                this.spiel.getUserInterface().zeigeError("Strasse konnte nicht gebaut werden.");
            }
        } else {
            this.spiel.getUserInterface().zeigeError("Nicht genügend Rohstoffe vorhanden.");
        }
        return false;
    }

    public boolean baueSiedlung(Position position, boolean kostenlos) {
        if (kostenlos || this.decktKosten(Baukosten.SIEDLUNG) && this.anzahlSiedlungen < 5) {
            try {
                Siedlung siedlung = new Siedlung(this, position, kostenlos);
                this.ortschaften.put(position, siedlung);
                this.erhoeheSiegpunkte();
                if (Spielfeld.getHaefen().containsKey(position)) {
                    this.haefen.add(Spielfeld.getHaefen().get(position));
                }
                if (!kostenlos) {
                    this.removeKarten(Baukosten.SIEDLUNG);
                }
                ++this.anzahlSiedlungen;
                return true;
            }
            catch (IllegalArgumentException e) {
                this.spiel.getUserInterface().zeigeError("Siedlung konnte nicht gebaut werden.");
            }
        } else {
            this.spiel.getUserInterface().zeigeError("Nicht genügend Rohstoffe vorhanden.");
        }
        return false;
    }

    public boolean baueStadt(Position position) {
        if (this.decktKosten(Baukosten.STADT) && this.anzahlStaedte < 4) {
            try {
                Stadt stadt = new Stadt(this, position);
                this.ortschaften.put(position, stadt);
                this.erhoeheSiegpunkte();
                this.removeKarten(Baukosten.STADT);
                ++this.anzahlStaedte;
                --this.anzahlSiedlungen;
                return true;
            }
            catch (IllegalArgumentException e) {
                this.spiel.getUserInterface().zeigeError("Stadt konnte nicht gebaut werden.");
            }
        } else {
            this.spiel.getUserInterface().zeigeError("Nicht genügend Rohstoffe vorhanden.");
        }
        return false;
    }

    public void removeKarten(Collection<Rohstoff> rohstoffe) {
        for (Rohstoff rohstoff : rohstoffe) {
            this.karten.remove(rohstoff);
        }
        this.anzahlKarten.set(this.karten.size());
    }

    public void removeKarte(Rohstoff rohstoff) {
        this.karten.remove(rohstoff);
        this.anzahlKarten.set(this.karten.size());
    }

    public void addKarten(Collection<Rohstoff> rohstoffe) {
        this.karten.addAll(rohstoffe);
        this.anzahlKarten.set(this.karten.size());
    }

    public void addKarte(Rohstoff rohstoff) {
        this.karten.add(rohstoff);
        this.anzahlKarten.set(this.karten.size());
    }

    private boolean decktKosten(Collection<Rohstoff> kosten) {
        for (Rohstoff rohstoff : kosten) {
            if (Collections.frequency(this.karten, rohstoff) >= Collections.frequency(kosten, rohstoff)) continue;
            return false;
        }
        return true;
    }

    public boolean kaufeEntwicklungskarte() {
        if (this.decktKosten(Baukosten.ENTWICKLUNGSKARTE) && Entwicklung.istNichtLeer()) {
            Entwicklungskarte entwicklungskarte = new Entwicklungskarte(this);
            this.entwicklungskarten.add(entwicklungskarte);
            this.removeKarten(Baukosten.ENTWICKLUNGSKARTE);
            return true;
        }
        this.spiel.getUserInterface().zeigeError("Nicht genügend Rohstoffe vorhanden.");
        return false;
    }

    public Rohstoff removeZufaelligeKarte() {
        int maxWert = this.karten.size();
        int zufallsZahl = Wuerfel.generiereZufallsZahl(maxWert) - 1;
        Rohstoff karte = this.karten.remove(zufallsZahl);
        this.anzahlKarten.set(this.karten.size());
        return karte;
    }

    public void spieleEntwicklungskarte(Entwicklungskarte entwicklungskarte) {
        boolean ausgespielt = entwicklungskarte.ausspielen();
        if (this.entwicklungskarten.contains(entwicklungskarte) && ausgespielt) {
            this.entwicklungskarten.remove(entwicklungskarte);
        }
    }

    public void versetzeRauber() {
        this.spiel.setNotSaveable();
        this.spiel.getUserInterface().zeigeMessage(this + " bitte versetzen Sie den Räuber.");
        this.spiel.setZustand(Zustand.LANDSCHAFTSFELD);
    }

    public void zieheKarte(Spieler spieler) {
        if (spieler.getAnzahlKarten().get() > 0) {
            Sound.getInstanz().playSoundeffekt(Sound.EVIL_LAUGH_CLIP);
            this.spiel.getUserInterface().zeigeInfo(this + " zieht eine Karte von " + spieler);
            Rohstoff karte = spieler.removeZufaelligeKarte();
            this.addKarte(karte);
        } else {
            this.spiel.getUserInterface().zeigeInfo(spieler + " hat keine Karten.");
        }
    }

    public void seehandel() {
        Rohstoff abzugeben = this.spiel.getUserInterface().zeigeRohstoffauswahl(this + "\nwählen Sie den Rohstoff, den Sie abgeben möchten.");
        Rohstoff erhalten = this.spiel.getUserInterface().zeigeRohstoffauswahl(this + "\nwählen Sie den Rohstoff, gegen den Sie tauschen möchten.");
        this.tauscheRohstoffe(abzugeben, erhalten);
    }

    public void tauscheRohstoffe(Rohstoff abzugeben, Rohstoff erhalten) {
        int vorhandenerRohstoff = Collections.frequency(this.karten, abzugeben);
        int umtauschkurs = 4;
        if (this.haefen.contains(Hafen.DREI_ZU_EINS)) {
            umtauschkurs = 3;
        }
        for (Hafen hafen : this.haefen) {
            if (!abzugeben.equals(hafen.getRohstoff())) continue;
            umtauschkurs = 2;
        }
        if (umtauschkurs <= vorhandenerRohstoff) {
            boolean antwort = this.spiel.getUserInterface().zeigeConfirmation(String.format("Wolen Sie %s gegen %s im Verhältnis %d:1 tauschen?", abzugeben, erhalten, umtauschkurs));
            if (antwort) {
                for (int i = 0; i < umtauschkurs; ++i) {
                    this.removeKarte(abzugeben);
                }
                this.addKarte(erhalten);
            }
        } else {
            this.spiel.getUserInterface().zeigeError("Nicht genügend Rohstoffe vorhanden.");
        }
    }

    public ObservableList<Entwicklungskarte> getEntwickulungskarten() {
        return this.entwicklungskarten;
    }

    public boolean hatGroessteRittermacht() {
        return this.hatGroessteRittermacht;
    }

    public void bekommtGroessteRittermacht() {
        this.hatGroessteRittermacht = true;
    }

    public void verliertGroessteRittermacht() {
        this.hatGroessteRittermacht = false;
    }

    public boolean hatLaengsteHandelsstrasse() {
        return this.hatLaengsteHandelsstrasse;
    }

    public void bekommtLaengsteHandelsstrasse() {
        this.hatLaengsteHandelsstrasse = true;
    }

    public void verliertLaengsteHandelsstrasse() {
        this.hatLaengsteHandelsstrasse = false;
    }

    public void erhoeheSiegpunkte() {
        this.siegpunkte.set(this.siegpunkte.get() + 1);
        if (this.siegpunkte.get() >= 10 && this.spiel.getSieger() == null) {
            this.spiel.setSieger(this);
        }
    }

    public void erniedrigeSiegpunkte() {
        this.siegpunkte.set(this.siegpunkte.get() - 1);
    }

    public Farbe getFarbe() {
        return this.farbe;
    }

    public Set<Hafen> getHaefen() {
        return this.haefen;
    }

    public ObservableList<Rohstoff> getKarten() {
        Collections.sort(this.karten);
        return this.karten;
    }

    public String getName() {
        return this.name;
    }

    public Map<Position, Ortschaft> getOrtschaften() {
        return this.ortschaften;
    }

    public Map<Set<Position>, Strasse> getStrassen() {
        return this.strassen;
    }

    public boolean isAktiv() {
        return this.aktiv;
    }

    public void setAktiv() {
        this.aktiv = true;
    }

    public void setNichtAktiv() {
        this.aktiv = false;
    }

    public IntegerProperty getSiegpunkte() {
        return this.siegpunkte;
    }

    public int getLaengsteHandelsstrasse() {
        return this.laengsteHandelsstrasse;
    }

    public IntegerProperty getRitter() {
        return this.ritter;
    }

    public void addRitter() {
        this.ritter.set(this.ritter.get() + 1);
        if (this.ritter.get() >= 3) {
            Zusatzpunkte.pruefeGroessteRittermacht(this);
        }
    }

    public boolean entwicklungskarteGespielt() {
        return this.hatEntwicklungskarteGespielt;
    }

    public void setEntwicklungskarteGespielt() {
        this.hatEntwicklungskarteGespielt = true;
    }

    public void setEntwicklungskarteNichtGespielt() {
        this.hatEntwicklungskarteGespielt = false;
    }

    public Spiel getSpiel() {
        return this.spiel;
    }

    public boolean getRaeuberVersetzen() {
        return this.raeuberVersetzen;
    }

    public void rauberVersetzt() {
        this.raeuberVersetzen = false;
    }

    public void erhoeheGespielteRunden() {
        ++this.gespielteRunden;
    }

    public int getGespielteRunden() {
        return this.gespielteRunden;
    }

    public boolean hatGesetzt() {
        return this.hatGesetzt;
    }

    public void hatGesetzt(boolean hatGesetzt) {
        this.hatGesetzt = hatGesetzt;
    }

    public IntegerProperty getAnzahlKarten() {
        return this.anzahlKarten;
    }

    public Image getAvatar() {
        String path = switch (getFarbe()) {
            case BLAU -> Pfade.AVATAR_BLAU;
            case ROT -> Pfade.AVATAR_ROT;
            case BRAUN -> Pfade.AVATAR_BRAUN;
            case GELB -> Pfade.AVATAR_GELB;
            case GRUEN -> Pfade.AVATAR_GRUEN;
            case WEISS -> Pfade.AVATAR_WEISS;
        };
        return new Image(path);
    }

    public void preSave() {
        this.entwicklungskartenSave = new ArrayList<>(this.entwicklungskarten);
        this.kartenSave = new ArrayList<>(this.karten);
        this.anzahlKartenSave = this.anzahlKarten.get();
        this.ritterSave = this.ritter.get();
        this.siegpunkteSave = this.siegpunkte.get();
    }

    public void postLoad() {
        this.entwicklungskarten = FXCollections.observableArrayList(this.entwicklungskartenSave);
        this.karten = FXCollections.observableArrayList(this.kartenSave);
        this.anzahlKarten = new SimpleIntegerProperty(this.anzahlKartenSave);
        this.ritter = new SimpleIntegerProperty(this.ritterSave);
        this.siegpunkte = new SimpleIntegerProperty(this.siegpunkteSave);
    }

    public int hashCode() {
        int result = 1;
        result = 31 * result + (this.farbe == null ? 0 : this.farbe.hashCode());
        result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Spieler other)) {
            return false;
        }
        if (this.farbe != other.farbe) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    public String toString() {
        return this.name;
    }
}

