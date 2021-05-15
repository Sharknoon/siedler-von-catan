package de.sharknoon.siedlervoncatan.spielfeld;

import de.sharknoon.siedlervoncatan.enums.Hafen;
import de.sharknoon.siedlervoncatan.enums.Landschaft;
import de.sharknoon.siedlervoncatan.utility.Position;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Spielfeld
implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final List<Position> LANDSCHAFTS_POSITIONEN;
    private static final List<Landschaft> LANDSCHAFTEN;
    private static final List<Integer> ZAHLENCHIPS;
    private static final Map<Position, Hafen> HAEFEN;
    private final Map<Position, Landschaftsfeld> landschaftsfelder;
    private Map<Position, Ortschaft> ortschaftenSave;
    private transient ObservableMap<Position, Ortschaft> ortschaften;
    private Map<Set<Position>, Strasse> strassenSave;
    private transient ObservableMap<Set<Position>, Strasse> strassen = FXCollections.observableHashMap();
    private Landschaftsfeld wueste;

    static {
        int i;
        LANDSCHAFTS_POSITIONEN = new ArrayList<>();
        LANDSCHAFTEN = new ArrayList<>();
        ZAHLENCHIPS = new ArrayList<>();
        HAEFEN = new HashMap<>();
        int[][] xAchsen = new int[][]{{3, 5, 7}, {2, 4, 6, 8}, {1, 3, 5, 7, 9}, {2, 4, 6, 8}, {3, 5, 7}};
        for (i = 1; i < 6; ++i) {
            for (int xAchse : xAchsen[i - 1]) {
                Position posNeu = new Position(xAchse, 3 * i - 1);
                LANDSCHAFTS_POSITIONEN.add(posNeu);
            }
        }
        for (i = 0; i < 3; ++i) {
            LANDSCHAFTEN.add(Landschaft.GEBIRGE);
            LANDSCHAFTEN.add(Landschaft.HUEGELLAND);
        }
        for (i = 0; i < 4; ++i) {
            LANDSCHAFTEN.add(Landschaft.WALD);
            LANDSCHAFTEN.add(Landschaft.ACKERLAND);
            LANDSCHAFTEN.add(Landschaft.WEIDELAND);
        }
        LANDSCHAFTEN.add(Landschaft.WUESTE);
        for (i = 3; i < 12; ++i) {
            if (i == 7) continue;
            ZAHLENCHIPS.add(i);
            ZAHLENCHIPS.add(i);
        }
        ZAHLENCHIPS.add(2);
        ZAHLENCHIPS.add(12);
        Position key = new Position(1, 4);
        HAEFEN.put(key, Hafen.WOLLE_HAFEN);
        key = new Position(2, 3);
        HAEFEN.put(key, Hafen.WOLLE_HAFEN);
        key = new Position(4, 1);
        HAEFEN.put(key, Hafen.ERZ_HAFEN);
        key = new Position(5, 0);
        HAEFEN.put(key, Hafen.ERZ_HAFEN);
        key = new Position(9, 4);
        HAEFEN.put(key, Hafen.KORN_HAFEN);
        key = new Position(9, 6);
        HAEFEN.put(key, Hafen.KORN_HAFEN);
        key = new Position(4, 15);
        HAEFEN.put(key, Hafen.LEHM_HAFEN);
        key = new Position(5, 16);
        HAEFEN.put(key, Hafen.LEHM_HAFEN);
        key = new Position(1, 12);
        HAEFEN.put(key, Hafen.HOLZ_HAFEN);
        key = new Position(2, 13);
        HAEFEN.put(key, Hafen.HOLZ_HAFEN);
        key = new Position(0, 7);
        HAEFEN.put(key, Hafen.DREI_ZU_EINS);
        key = new Position(0, 9);
        HAEFEN.put(key, Hafen.DREI_ZU_EINS);
        key = new Position(7, 0);
        HAEFEN.put(key, Hafen.DREI_ZU_EINS);
        key = new Position(8, 1);
        HAEFEN.put(key, Hafen.DREI_ZU_EINS);
        key = new Position(9, 10);
        HAEFEN.put(key, Hafen.DREI_ZU_EINS);
        key = new Position(9, 12);
        HAEFEN.put(key, Hafen.DREI_ZU_EINS);
        key = new Position(7, 16);
        HAEFEN.put(key, Hafen.DREI_ZU_EINS);
        key = new Position(8, 15);
        HAEFEN.put(key, Hafen.DREI_ZU_EINS);
    }

    public Spielfeld() {
        this.ortschaften = FXCollections.observableHashMap();
        this.landschaftsfelder = new HashMap<>();
        do {
            this.erzeugeSpielfeld();
        } while (this.roteZahlenNebeneinander());
    }

    private void erzeugeSpielfeld() {
        this.landschaftsfelder.clear();
        Collections.shuffle(LANDSCHAFTEN);
        Collections.shuffle(ZAHLENCHIPS);
        int i = 0;
        int j = 0;
        while (i < LANDSCHAFTS_POSITIONEN.size()) {
            Landschaftsfeld landschaftsfeld;
            Position position = LANDSCHAFTS_POSITIONEN.get(i);
            Landschaft landschaft = LANDSCHAFTEN.get(i);
            if (landschaft.equals(Landschaft.WUESTE)) {
                this.wueste = landschaftsfeld = new Landschaftsfeld(position, landschaft, 0);
                --j;
            } else {
                landschaftsfeld = new Landschaftsfeld(position, landschaft, ZAHLENCHIPS.get(j));
            }
            this.landschaftsfelder.put(position, landschaftsfeld);
            ++i;
            ++j;
        }
    }

    private boolean roteZahlenNebeneinander() {
        for (Landschaftsfeld landschaftsfeld : this.landschaftsfelder.values()) {
            int zahl = landschaftsfeld.getZahl();
            if (zahl != 6 && zahl != 8) continue;
            for (Landschaftsfeld andereLandschaft : this.landschaftsfelder.values()) {
                int andereZahl = andereLandschaft.getZahl();
                if (!andereLandschaft.getZentrum().isNachbarlandschaft(landschaftsfeld.getZentrum()) || andereZahl != 6 && andereZahl != 8) continue;
                return true;
            }
        }
        return false;
    }

    public void putOrtschaft(Ortschaft ortschaft) {
        this.ortschaften.put(ortschaft.getPosition(), ortschaft);
    }

    public void addOrtschaftenListener(MapChangeListener<Position, Ortschaft> listener) {
        this.ortschaften.addListener(listener);
    }

    public void putStrasse(Strasse strasse) {
        this.strassen.put(strasse.getPositionen(), strasse);
    }

    public void addStrassenListener(MapChangeListener<Set<Position>, Strasse> listener) {
        this.strassen.addListener(listener);
    }

    public ObservableMap<Set<Position>, Strasse> getStrassen() {
        return this.strassen;
    }

    public Map<Position, Landschaftsfeld> getLandschaftsfelder() {
        return this.landschaftsfelder;
    }

    public ObservableMap<Position, Ortschaft> getBauplaetze() {
        return this.ortschaften;
    }

    public static Map<Position, Hafen> getHaefen() {
        return HAEFEN;
    }

    public Landschaftsfeld getWueste() {
        return this.wueste;
    }

    public void preSave() {
        this.ortschaftenSave = new HashMap<>(this.ortschaften);
        this.strassenSave = new HashMap<>(this.strassen);
    }

    public void postLoad() {
        this.ortschaften = FXCollections.observableMap(this.ortschaftenSave);
        this.strassen = FXCollections.observableMap(this.strassenSave);
    }
}

