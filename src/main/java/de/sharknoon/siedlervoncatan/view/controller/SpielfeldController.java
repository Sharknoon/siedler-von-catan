package de.sharknoon.siedlervoncatan.view.controller;

import de.sharknoon.siedlervoncatan.sound.Sound;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.spielfeld.Landschaftsfeld;
import de.sharknoon.siedlervoncatan.spielfeld.Ortschaft;
import de.sharknoon.siedlervoncatan.spielfeld.Strasse;
import de.sharknoon.siedlervoncatan.utility.Pfade;
import de.sharknoon.siedlervoncatan.utility.Position;
import de.sharknoon.siedlervoncatan.view.Controller;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SpielfeldController implements MapChangeListener, PropertyChangeListener, Controller {
    private Spiel spiel;
    private final Map<Position, ImageView> imageViewLandschaften = new HashMap<>();
    private final Map<Position, Label> labelLandschaftsfelder = new HashMap<>();
    private final Map<Position, ImageView> Ecken = new HashMap<>();
    private final Map<Set<Position>, ImageView> kanten = new HashMap<>();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @FXML
    private Group groupInsel;
    @FXML
    private StackPane stackPaneInsel;
    @FXML
    private ImageView wuerfel1IV;
    @FXML
    private ImageView wuerfel2IV;
    @FXML
    private ImageView landschaft_3_2;
    @FXML
    private ImageView landschaft_5_2;
    @FXML
    private ImageView landschaft_7_2;
    @FXML
    private ImageView landschaft_2_5;
    @FXML
    private ImageView landschaft_4_5;
    @FXML
    private ImageView landschaft_6_5;
    @FXML
    private ImageView landschaft_8_5;
    @FXML
    private ImageView landschaft_1_8;
    @FXML
    private ImageView landschaft_3_8;
    @FXML
    private ImageView landschaft_5_8;
    @FXML
    private ImageView landschaft_7_8;
    @FXML
    private ImageView landschaft_9_8;
    @FXML
    private ImageView landschaft_2_11;
    @FXML
    private ImageView landschaft_4_11;
    @FXML
    private ImageView landschaft_6_11;
    @FXML
    private ImageView landschaft_8_11;
    @FXML
    private ImageView landschaft_3_14;
    @FXML
    private ImageView landschaft_5_14;
    @FXML
    private ImageView landschaft_7_14;
    @FXML
    private Label label_3_2;
    @FXML
    private Label label_5_2;
    @FXML
    private Label label_7_2;
    @FXML
    private Label label_2_5;
    @FXML
    private Label label_4_5;
    @FXML
    private Label label_6_5;
    @FXML
    private Label label_8_5;
    @FXML
    private Label label_1_8;
    @FXML
    private Label label_3_8;
    @FXML
    private Label label_5_8;
    @FXML
    private Label label_7_8;
    @FXML
    private Label label_9_8;
    @FXML
    private Label label_2_11;
    @FXML
    private Label label_4_11;
    @FXML
    private Label label_6_11;
    @FXML
    private Label label_8_11;
    @FXML
    private Label label_3_14;
    @FXML
    private Label label_5_14;
    @FXML
    private Label label_7_14;
    @FXML
    private Label messages;
    @FXML
    private Label runde;
    @FXML
    private ImageView ecke_3_0;
    @FXML
    private ImageView ecke_5_0;
    @FXML
    private ImageView ecke_7_0;
    @FXML
    private ImageView ecke_2_1;
    @FXML
    private ImageView ecke_4_1;
    @FXML
    private ImageView ecke_6_1;
    @FXML
    private ImageView ecke_8_1;
    @FXML
    private ImageView ecke_2_3;
    @FXML
    private ImageView ecke_4_3;
    @FXML
    private ImageView ecke_6_3;
    @FXML
    private ImageView ecke_8_3;
    @FXML
    private ImageView ecke_1_4;
    @FXML
    private ImageView ecke_3_4;
    @FXML
    private ImageView ecke_5_4;
    @FXML
    private ImageView ecke_7_4;
    @FXML
    private ImageView ecke_9_4;
    @FXML
    private ImageView ecke_1_6;
    @FXML
    private ImageView ecke_3_6;
    @FXML
    private ImageView ecke_5_6;
    @FXML
    private ImageView ecke_7_6;
    @FXML
    private ImageView ecke_9_6;
    @FXML
    private ImageView ecke_0_7;
    @FXML
    private ImageView ecke_2_7;
    @FXML
    private ImageView ecke_4_7;
    @FXML
    private ImageView ecke_6_7;
    @FXML
    private ImageView ecke_8_7;
    @FXML
    private ImageView ecke_10_7;
    @FXML
    private ImageView ecke_3_16;
    @FXML
    private ImageView ecke_5_16;
    @FXML
    private ImageView ecke_7_16;
    @FXML
    private ImageView ecke_2_15;
    @FXML
    private ImageView ecke_4_15;
    @FXML
    private ImageView ecke_6_15;
    @FXML
    private ImageView ecke_8_15;
    @FXML
    private ImageView ecke_2_13;
    @FXML
    private ImageView ecke_4_13;
    @FXML
    private ImageView ecke_6_13;
    @FXML
    private ImageView ecke_8_13;
    @FXML
    private ImageView ecke_1_12;
    @FXML
    private ImageView ecke_3_12;
    @FXML
    private ImageView ecke_5_12;
    @FXML
    private ImageView ecke_7_12;
    @FXML
    private ImageView ecke_9_12;
    @FXML
    private ImageView ecke_1_10;
    @FXML
    private ImageView ecke_3_10;
    @FXML
    private ImageView ecke_5_10;
    @FXML
    private ImageView ecke_7_10;
    @FXML
    private ImageView ecke_9_10;
    @FXML
    private ImageView ecke_0_9;
    @FXML
    private ImageView ecke_2_9;
    @FXML
    private ImageView ecke_4_9;
    @FXML
    private ImageView ecke_6_9;
    @FXML
    private ImageView ecke_8_9;
    @FXML
    private ImageView ecke_10_9;
    @FXML
    private ImageView kante_3_0_2_1;
    @FXML
    private ImageView kante_3_0_4_1;
    @FXML
    private ImageView kante_5_0_4_1;
    @FXML
    private ImageView kante_5_0_6_1;
    @FXML
    private ImageView kante_7_0_6_1;
    @FXML
    private ImageView kante_7_0_8_1;
    @FXML
    private ImageView kante_2_1_2_3;
    @FXML
    private ImageView kante_4_1_4_3;
    @FXML
    private ImageView kante_6_1_6_3;
    @FXML
    private ImageView kante_8_1_8_3;
    @FXML
    private ImageView kante_2_3_1_4;
    @FXML
    private ImageView kante_2_3_3_4;
    @FXML
    private ImageView kante_4_3_3_4;
    @FXML
    private ImageView kante_4_3_5_4;
    @FXML
    private ImageView kante_6_3_5_4;
    @FXML
    private ImageView kante_6_3_7_4;
    @FXML
    private ImageView kante_8_3_7_4;
    @FXML
    private ImageView kante_8_3_9_4;
    @FXML
    private ImageView kante_1_4_1_6;
    @FXML
    private ImageView kante_3_4_3_6;
    @FXML
    private ImageView kante_5_4_5_6;
    @FXML
    private ImageView kante_7_4_7_6;
    @FXML
    private ImageView kante_9_4_9_6;
    @FXML
    private ImageView kante_1_6_0_7;
    @FXML
    private ImageView kante_1_6_2_7;
    @FXML
    private ImageView kante_3_6_2_7;
    @FXML
    private ImageView kante_3_6_4_7;
    @FXML
    private ImageView kante_5_6_4_7;
    @FXML
    private ImageView kante_5_6_6_7;
    @FXML
    private ImageView kante_7_6_6_7;
    @FXML
    private ImageView kante_7_6_8_7;
    @FXML
    private ImageView kante_9_6_8_7;
    @FXML
    private ImageView kante_9_6_10_7;
    @FXML
    private ImageView kante_0_7_0_9;
    @FXML
    private ImageView kante_2_7_2_9;
    @FXML
    private ImageView kante_4_7_4_9;
    @FXML
    private ImageView kante_6_7_6_9;
    @FXML
    private ImageView kante_8_7_8_9;
    @FXML
    private ImageView kante_10_7_10_9;
    @FXML
    private ImageView kante_0_9_1_10;
    @FXML
    private ImageView kante_2_9_1_10;
    @FXML
    private ImageView kante_2_9_3_10;
    @FXML
    private ImageView kante_4_9_3_10;
    @FXML
    private ImageView kante_4_9_5_10;
    @FXML
    private ImageView kante_6_9_5_10;
    @FXML
    private ImageView kante_6_9_7_10;
    @FXML
    private ImageView kante_8_9_7_10;
    @FXML
    private ImageView kante_8_9_9_10;
    @FXML
    private ImageView kante_10_9_9_10;
    @FXML
    private ImageView kante_1_10_1_12;
    @FXML
    private ImageView kante_3_10_3_12;
    @FXML
    private ImageView kante_5_10_5_12;
    @FXML
    private ImageView kante_7_10_7_12;
    @FXML
    private ImageView kante_9_10_9_12;
    @FXML
    private ImageView kante_1_12_2_13;
    @FXML
    private ImageView kante_3_12_2_13;
    @FXML
    private ImageView kante_3_12_4_13;
    @FXML
    private ImageView kante_5_12_4_13;
    @FXML
    private ImageView kante_5_12_6_13;
    @FXML
    private ImageView kante_7_12_6_13;
    @FXML
    private ImageView kante_7_12_8_13;
    @FXML
    private ImageView kante_9_12_8_13;
    @FXML
    private ImageView kante_2_13_2_15;
    @FXML
    private ImageView kante_4_13_4_15;
    @FXML
    private ImageView kante_6_13_6_15;
    @FXML
    private ImageView kante_8_13_8_15;
    @FXML
    private ImageView kante_2_15_3_16;
    @FXML
    private ImageView kante_4_15_3_16;
    @FXML
    private ImageView kante_4_15_5_16;
    @FXML
    private ImageView kante_6_15_5_16;
    @FXML
    private ImageView kante_6_15_7_16;
    @FXML
    private ImageView kante_8_15_7_16;

    private void setKante(ImageView kante, int x1, int y1, int x2, int y2) {
        Position pos1 = new Position(x1, y1);
        Position pos2 = new Position(x2, y2);
        HashSet<Position> pos = new HashSet<>();
        pos.add(pos1);
        pos.add(pos2);
        kante.setUserData(pos);
        this.kanten.put(pos, kante);
    }

    private void erzeugeKanten() {
        this.setKante(this.kante_0_7_0_9, 0, 7, 0, 9);
        this.setKante(this.kante_0_9_1_10, 0, 9, 1, 10);
        this.setKante(this.kante_10_7_10_9, 10, 7, 10, 9);
        this.setKante(this.kante_10_9_9_10, 10, 9, 9, 10);
        this.setKante(this.kante_1_10_1_12, 1, 10, 1, 12);
        this.setKante(this.kante_1_12_2_13, 1, 12, 2, 13);
        this.setKante(this.kante_1_4_1_6, 1, 4, 1, 6);
        this.setKante(this.kante_1_6_0_7, 1, 6, 0, 7);
        this.setKante(this.kante_1_6_2_7, 1, 6, 2, 7);
        this.setKante(this.kante_2_13_2_15, 2, 13, 2, 15);
        this.setKante(this.kante_2_15_3_16, 2, 15, 3, 16);
        this.setKante(this.kante_2_1_2_3, 2, 1, 2, 3);
        this.setKante(this.kante_2_3_1_4, 2, 3, 1, 4);
        this.setKante(this.kante_2_3_3_4, 2, 3, 3, 4);
        this.setKante(this.kante_2_7_2_9, 2, 7, 2, 9);
        this.setKante(this.kante_2_9_1_10, 2, 9, 1, 10);
        this.setKante(this.kante_2_9_3_10, 2, 9, 3, 10);
        this.setKante(this.kante_3_0_2_1, 3, 0, 2, 1);
        this.setKante(this.kante_3_0_4_1, 3, 0, 4, 1);
        this.setKante(this.kante_3_10_3_12, 3, 10, 3, 12);
        this.setKante(this.kante_3_12_2_13, 3, 12, 2, 13);
        this.setKante(this.kante_3_12_4_13, 3, 12, 4, 13);
        this.setKante(this.kante_3_4_3_6, 3, 4, 3, 6);
        this.setKante(this.kante_3_6_2_7, 3, 6, 2, 7);
        this.setKante(this.kante_3_6_4_7, 3, 6, 4, 7);
        this.setKante(this.kante_4_13_4_15, 4, 13, 4, 15);
        this.setKante(this.kante_4_15_3_16, 4, 15, 3, 16);
        this.setKante(this.kante_4_15_5_16, 4, 15, 5, 16);
        this.setKante(this.kante_4_1_4_3, 4, 1, 4, 3);
        this.setKante(this.kante_4_3_3_4, 4, 3, 3, 4);
        this.setKante(this.kante_4_3_5_4, 4, 3, 5, 4);
        this.setKante(this.kante_4_7_4_9, 4, 7, 4, 9);
        this.setKante(this.kante_4_9_3_10, 4, 9, 3, 10);
        this.setKante(this.kante_4_9_5_10, 4, 9, 5, 10);
        this.setKante(this.kante_5_0_4_1, 5, 0, 4, 1);
        this.setKante(this.kante_5_0_6_1, 5, 0, 6, 1);
        this.setKante(this.kante_5_10_5_12, 5, 10, 5, 12);
        this.setKante(this.kante_5_12_4_13, 5, 12, 4, 13);
        this.setKante(this.kante_5_12_6_13, 5, 12, 6, 13);
        this.setKante(this.kante_5_4_5_6, 5, 4, 5, 6);
        this.setKante(this.kante_5_6_4_7, 5, 6, 4, 7);
        this.setKante(this.kante_5_6_6_7, 5, 6, 6, 7);
        this.setKante(this.kante_6_13_6_15, 6, 13, 6, 15);
        this.setKante(this.kante_6_15_5_16, 6, 15, 5, 16);
        this.setKante(this.kante_6_15_7_16, 6, 15, 7, 16);
        this.setKante(this.kante_6_1_6_3, 6, 1, 6, 3);
        this.setKante(this.kante_6_3_5_4, 6, 3, 5, 4);
        this.setKante(this.kante_6_3_7_4, 6, 3, 7, 4);
        this.setKante(this.kante_6_7_6_9, 6, 7, 6, 9);
        this.setKante(this.kante_6_9_5_10, 6, 9, 5, 10);
        this.setKante(this.kante_6_9_7_10, 6, 9, 7, 10);
        this.setKante(this.kante_7_0_6_1, 7, 0, 6, 1);
        this.setKante(this.kante_7_0_8_1, 7, 0, 8, 1);
        this.setKante(this.kante_7_10_7_12, 7, 10, 7, 12);
        this.setKante(this.kante_7_12_6_13, 7, 12, 6, 13);
        this.setKante(this.kante_7_12_8_13, 7, 12, 8, 13);
        this.setKante(this.kante_7_4_7_6, 7, 4, 7, 6);
        this.setKante(this.kante_7_6_6_7, 7, 6, 6, 7);
        this.setKante(this.kante_7_6_8_7, 7, 6, 8, 7);
        this.setKante(this.kante_8_13_8_15, 8, 13, 8, 15);
        this.setKante(this.kante_8_15_7_16, 8, 15, 7, 16);
        this.setKante(this.kante_8_1_8_3, 8, 1, 8, 3);
        this.setKante(this.kante_8_3_7_4, 8, 3, 7, 4);
        this.setKante(this.kante_8_3_9_4, 8, 3, 9, 4);
        this.setKante(this.kante_8_7_8_9, 8, 7, 8, 9);
        this.setKante(this.kante_8_9_7_10, 8, 9, 7, 10);
        this.setKante(this.kante_8_9_9_10, 8, 9, 9, 10);
        this.setKante(this.kante_9_10_9_12, 9, 10, 9, 12);
        this.setKante(this.kante_9_12_8_13, 9, 12, 8, 13);
        this.setKante(this.kante_9_4_9_6, 9, 4, 9, 6);
        this.setKante(this.kante_9_6_10_7, 9, 6, 10, 7);
        this.setKante(this.kante_9_6_8_7, 9, 6, 8, 7);
    }

    private void setEcke(ImageView ecke, Position position) {
        ecke.setUserData(position);
        this.Ecken.put(position, ecke);
    }

    private void erzeugeEcken() {
        Position position = new Position(3, 0);
        this.setEcke(this.ecke_3_0, position);
        position = new Position(5, 0);
        this.setEcke(this.ecke_5_0, position);
        position = new Position(7, 0);
        this.setEcke(this.ecke_7_0, position);
        position = new Position(2, 1);
        this.setEcke(this.ecke_2_1, position);
        position = new Position(4, 1);
        this.setEcke(this.ecke_4_1, position);
        position = new Position(6, 1);
        this.setEcke(this.ecke_6_1, position);
        position = new Position(8, 1);
        this.setEcke(this.ecke_8_1, position);
        position = new Position(2, 3);
        this.setEcke(this.ecke_2_3, position);
        position = new Position(4, 3);
        this.setEcke(this.ecke_4_3, position);
        position = new Position(6, 3);
        this.setEcke(this.ecke_6_3, position);
        position = new Position(8, 3);
        this.setEcke(this.ecke_8_3, position);
        position = new Position(1, 4);
        this.setEcke(this.ecke_1_4, position);
        position = new Position(3, 4);
        this.setEcke(this.ecke_3_4, position);
        position = new Position(5, 4);
        this.setEcke(this.ecke_5_4, position);
        position = new Position(7, 4);
        this.setEcke(this.ecke_7_4, position);
        position = new Position(9, 4);
        this.setEcke(this.ecke_9_4, position);
        position = new Position(1, 6);
        this.setEcke(this.ecke_1_6, position);
        position = new Position(3, 6);
        this.setEcke(this.ecke_3_6, position);
        position = new Position(5, 6);
        this.setEcke(this.ecke_5_6, position);
        position = new Position(7, 6);
        this.setEcke(this.ecke_7_6, position);
        position = new Position(9, 6);
        this.setEcke(this.ecke_9_6, position);
        position = new Position(0, 7);
        this.setEcke(this.ecke_0_7, position);
        position = new Position(2, 7);
        this.setEcke(this.ecke_2_7, position);
        position = new Position(4, 7);
        this.setEcke(this.ecke_4_7, position);
        position = new Position(6, 7);
        this.setEcke(this.ecke_6_7, position);
        position = new Position(8, 7);
        this.setEcke(this.ecke_8_7, position);
        position = new Position(10, 7);
        this.setEcke(this.ecke_10_7, position);
        position = new Position(3, 16);
        this.setEcke(this.ecke_3_16, position);
        position = new Position(5, 16);
        this.setEcke(this.ecke_5_16, position);
        position = new Position(7, 16);
        this.setEcke(this.ecke_7_16, position);
        position = new Position(2, 15);
        this.setEcke(this.ecke_2_15, position);
        position = new Position(4, 15);
        this.setEcke(this.ecke_4_15, position);
        position = new Position(6, 15);
        this.setEcke(this.ecke_6_15, position);
        position = new Position(8, 15);
        this.setEcke(this.ecke_8_15, position);
        position = new Position(2, 13);
        this.setEcke(this.ecke_2_13, position);
        position = new Position(4, 13);
        this.setEcke(this.ecke_4_13, position);
        position = new Position(6, 13);
        this.setEcke(this.ecke_6_13, position);
        position = new Position(8, 13);
        this.setEcke(this.ecke_8_13, position);
        position = new Position(1, 12);
        this.setEcke(this.ecke_1_12, position);
        position = new Position(3, 12);
        this.setEcke(this.ecke_3_12, position);
        position = new Position(5, 12);
        this.setEcke(this.ecke_5_12, position);
        position = new Position(7, 12);
        this.setEcke(this.ecke_7_12, position);
        position = new Position(9, 12);
        this.setEcke(this.ecke_9_12, position);
        position = new Position(1, 10);
        this.setEcke(this.ecke_1_10, position);
        position = new Position(3, 10);
        this.setEcke(this.ecke_3_10, position);
        position = new Position(5, 10);
        this.setEcke(this.ecke_5_10, position);
        position = new Position(7, 10);
        this.setEcke(this.ecke_7_10, position);
        position = new Position(9, 10);
        this.setEcke(this.ecke_9_10, position);
        position = new Position(0, 9);
        this.setEcke(this.ecke_0_9, position);
        position = new Position(2, 9);
        this.setEcke(this.ecke_2_9, position);
        position = new Position(4, 9);
        this.setEcke(this.ecke_4_9, position);
        position = new Position(6, 9);
        this.setEcke(this.ecke_6_9, position);
        position = new Position(8, 9);
        this.setEcke(this.ecke_8_9, position);
        position = new Position(10, 9);
        this.setEcke(this.ecke_10_9, position);
    }

    private void setLabelZahlenchips(Position position, Label label) {
        label.setUserData(position);
        this.labelLandschaftsfelder.put(position, label);
    }

    private void erzeugeLabelZahlenchips() {
        Position key = new Position(3, 2);
        this.setLabelZahlenchips(key, this.label_3_2);
        key = new Position(5, 2);
        this.setLabelZahlenchips(key, this.label_5_2);
        key = new Position(7, 2);
        this.setLabelZahlenchips(key, this.label_7_2);
        key = new Position(2, 5);
        this.setLabelZahlenchips(key, this.label_2_5);
        key = new Position(4, 5);
        this.setLabelZahlenchips(key, this.label_4_5);
        key = new Position(6, 5);
        this.setLabelZahlenchips(key, this.label_6_5);
        key = new Position(8, 5);
        this.setLabelZahlenchips(key, this.label_8_5);
        key = new Position(1, 8);
        this.setLabelZahlenchips(key, this.label_1_8);
        key = new Position(3, 8);
        this.setLabelZahlenchips(key, this.label_3_8);
        key = new Position(5, 8);
        this.setLabelZahlenchips(key, this.label_5_8);
        key = new Position(7, 8);
        this.setLabelZahlenchips(key, this.label_7_8);
        key = new Position(9, 8);
        this.setLabelZahlenchips(key, this.label_9_8);
        key = new Position(2, 11);
        this.setLabelZahlenchips(key, this.label_2_11);
        key = new Position(4, 11);
        this.setLabelZahlenchips(key, this.label_4_11);
        key = new Position(6, 11);
        this.setLabelZahlenchips(key, this.label_6_11);
        key = new Position(8, 11);
        this.setLabelZahlenchips(key, this.label_8_11);
        key = new Position(3, 14);
        this.setLabelZahlenchips(key, this.label_3_14);
        key = new Position(5, 14);
        this.setLabelZahlenchips(key, this.label_5_14);
        key = new Position(7, 14);
        this.setLabelZahlenchips(key, this.label_7_14);
    }

    private void setImageViewLandschaften(Position position, ImageView landschaft) {
        landschaft.setUserData(position);
        this.imageViewLandschaften.put(position, landschaft);
    }

    private void erzeugeImageViewLandschaften() {
        Position key = new Position(3, 2);
        this.setImageViewLandschaften(key, this.landschaft_3_2);
        key = new Position(5, 2);
        this.setImageViewLandschaften(key, this.landschaft_5_2);
        key = new Position(7, 2);
        this.setImageViewLandschaften(key, this.landschaft_7_2);
        key = new Position(2, 5);
        this.setImageViewLandschaften(key, this.landschaft_2_5);
        key = new Position(4, 5);
        this.setImageViewLandschaften(key, this.landschaft_4_5);
        key = new Position(6, 5);
        this.setImageViewLandschaften(key, this.landschaft_6_5);
        key = new Position(8, 5);
        this.setImageViewLandschaften(key, this.landschaft_8_5);
        key = new Position(1, 8);
        this.setImageViewLandschaften(key, this.landschaft_1_8);
        key = new Position(3, 8);
        this.setImageViewLandschaften(key, this.landschaft_3_8);
        key = new Position(5, 8);
        this.setImageViewLandschaften(key, this.landschaft_5_8);
        key = new Position(7, 8);
        this.setImageViewLandschaften(key, this.landschaft_7_8);
        key = new Position(9, 8);
        this.setImageViewLandschaften(key, this.landschaft_9_8);
        key = new Position(2, 11);
        this.setImageViewLandschaften(key, this.landschaft_2_11);
        key = new Position(4, 11);
        this.setImageViewLandschaften(key, this.landschaft_4_11);
        key = new Position(6, 11);
        this.setImageViewLandschaften(key, this.landschaft_6_11);
        key = new Position(8, 11);
        this.setImageViewLandschaften(key, this.landschaft_8_11);
        key = new Position(3, 14);
        this.setImageViewLandschaften(key, this.landschaft_3_14);
        key = new Position(5, 14);
        this.setImageViewLandschaften(key, this.landschaft_5_14);
        key = new Position(7, 14);
        this.setImageViewLandschaften(key, this.landschaft_7_14);
    }

    @FXML
    private void initialize() {
        this.erzeugeImageViewLandschaften();
        this.erzeugeLabelZahlenchips();
        this.erzeugeEcken();
        this.erzeugeKanten();
    }

    public void erzeugeSpielfeld() {
        this.messages.setText("");
        this.runde.textProperty().bind(this.spiel.getAnzahlRunden().asString());
        Map<Position, Landschaftsfeld> spielfeldLandschaftsfelder = this.spiel.getSpielfeld().getLandschaftsfelder();
        for (Position position : this.imageViewLandschaften.keySet()) {
            Image image = spielfeldLandschaftsfelder.get(position).getImage();
            this.imageViewLandschaften.get(position).setImage(image);
            this.labelLandschaftsfelder.get(position).setTextFill(Color.BLACK);
            this.labelLandschaftsfelder.get(position).setScaleX(1.5);
            this.labelLandschaftsfelder.get(position).setScaleY(1.5);
            int zahl = spielfeldLandschaftsfelder.get(position).getZahl();
            String ausgabe = Integer.toString(zahl);
            if (zahl == 6 || zahl == 8) {
                this.labelLandschaftsfelder.get(position).setTextFill(Color.DARKRED);
                this.labelLandschaftsfelder.get(position).setScaleX(1.8);
                this.labelLandschaftsfelder.get(position).setScaleY(1.8);
            }
            if (zahl == 2 || zahl == 12) {
                this.labelLandschaftsfelder.get(position).setScaleX(1.2);
                this.labelLandschaftsfelder.get(position).setScaleY(1.2);
            }
            if (zahl == 0) {
                ausgabe = "";
            }
            if (position.equals(this.spiel.getRaeuber().getPosition().get())) {
                ausgabe = "R";
                this.labelLandschaftsfelder.get(position).setScaleX(2.0);
                this.labelLandschaftsfelder.get(position).setScaleY(2.0);
            }
            this.labelLandschaftsfelder.get(position).setText(ausgabe);
        }
        ObservableMap<Position, Ortschaft> spielfeldBauplaetze = this.spiel.getSpielfeld().getBauplaetze();
        for (Position position : spielfeldBauplaetze.keySet()) {
            Image image = spielfeldBauplaetze.get(position).getImage();
            this.Ecken.get(position).setImage(image);
        }
        ObservableMap<Set<Position>, Strasse> spielfeldStrassen = this.spiel.getSpielfeld().getStrassen();
        for (Set<Position> positionen : spielfeldStrassen.keySet()) {
            Image image = spielfeldStrassen.get(positionen).getImage();
            this.kanten.get(positionen).setImage(image);
        }
    }

    @FXML
    private void handleEckeClicked(Event event) {
        ImageView ecke = (ImageView)event.getSource();
        Position position = (Position)ecke.getUserData();
        this.support.firePropertyChange("Ecke", null, position);
    }

    @FXML
    private void handleKanteClicked(Event event) {
        ImageView kante = (ImageView)event.getSource();
        Set<Position> positionen = (Set<Position>)kante.getUserData();
        this.support.firePropertyChange("Kante", null, positionen);
    }

    @FXML
    private void handleLabelZahlenchipsClicked(Event event) {
        Label zahlenchip = (Label)event.getSource();
        Position position = (Position)zahlenchip.getUserData();
        this.support.firePropertyChange("Landschaftsfeld", null, position);
    }

    public void addListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    public void removeListener(PropertyChangeListener listener) {
        this.support.removePropertyChangeListener(listener);
    }

    public void setMessage(String message) {
        this.messages.setText(message);
    }

    @Override
    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
        spiel.getSpielfeld().addOrtschaftenListener(this);
        spiel.getSpielfeld().addStrassenListener(this);
        spiel.getWuerfel().addListener(this);
        this.spiel.getSpielstart().getRootLayout().boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
            double height = newValue.getHeight() - 100.0;
            double width = newValue.getWidth() - 300.0;
            double scale = Math.min(height, width) / 600.0;
            if (Math.abs(SpielfeldController.this.groupInsel.getScaleX() - scale) > 0.01) {
                SpielfeldController.this.groupInsel.setScaleX(scale);
                SpielfeldController.this.groupInsel.setScaleY(scale);
            }
        });
        this.erzeugeSpielfeld();
    }

    public void onChanged(Change change) {
        Image image;
        Object key = change.getKey();
        if (key instanceof Position) {
            image = this.spiel.getSpielfeld().getBauplaetze().get(key).getImage();
            this.Ecken.get(key).setImage(image);
            Sound.getInstanz().playSoundeffekt(Sound.BAU_CLIP);
        }
        if (key instanceof Set) {
            image = this.spiel.getSpielfeld().getStrassen().get(key).getImage();
            this.kanten.get(key).setImage(image);
            Sound.getInstanz().playSoundeffekt(Sound.BAU_CLIP);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int zahl;
        if (evt.getPropertyName().equals("wuerfel1")) {
            zahl = (Integer)evt.getNewValue();
            this.wuerfel1IV.setImage(this.getWuerfelImage(zahl));
        }
        if (evt.getPropertyName().equals("wuerfel2")) {
            zahl = (Integer)evt.getNewValue();
            this.wuerfel2IV.setImage(this.getWuerfelImage(zahl));
        }
    }

    private Image getWuerfelImage(int zahl) {
        String path = switch (zahl) {
            case 1 -> Pfade.WUERFEL_EINS;
            case 2 -> Pfade.WUERFEL_ZWEI;
            case 3 -> Pfade.WUERFEL_DREI;
            case 4 -> Pfade.WUERFEL_VIER;
            case 5 -> Pfade.WUERFEL_FUENF;
            case 6 -> Pfade.WUERFEL_SECHS;
            default -> Pfade.WUERFEL_EINS;
        };
        return new Image(path);
    }

    @Override
    public void setLayoutController(RootLayoutController layoutController) {
    }

    @Override
    public void setNode(Node self) {
    }
}

