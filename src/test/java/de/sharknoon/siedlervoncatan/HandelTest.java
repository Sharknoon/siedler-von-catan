package de.sharknoon.siedlervoncatan;

import de.sharknoon.siedlervoncatan.enums.Farbe;
import de.sharknoon.siedlervoncatan.enums.Rohstoff;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.utility.Handel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;


@ExtendWith(ApplicationExtension.class)
public class HandelTest {

    @Test
    public void test() {
        ObservableList<Rohstoff> kartenSpieler1 = FXCollections.observableArrayList(Rohstoff.ERZ, Rohstoff.ERZ, Rohstoff.KORN, Rohstoff.KORN, Rohstoff.KORN);
        ObservableList<Rohstoff> kartenSpieler2 = FXCollections.observableArrayList(Rohstoff.HOLZ, Rohstoff.HOLZ, Rohstoff.HOLZ, Rohstoff.HOLZ);
        Spiel spiel = new Spiel();
        spiel.setUserInterface(new MenueTest());
        Spieler spieler1 = new Spieler("Marcel", Farbe.BRAUN, spiel);
        spieler1.addKarten(kartenSpieler1);
        Spieler spieler2 = new Spieler("Artem", Farbe.BLAU, spiel);
        spieler2.addKarten(kartenSpieler2);
        Handel handel = new Handel();
        handel.setAnbieter(spieler1);
        handel.setNachfrager(spieler2);
        handel.addAngebot(Rohstoff.ERZ);
        handel.addNachfrage(Rohstoff.HOLZ);
        handel.handeln();
        ObservableList<Rohstoff> karten1 = spieler1.getKarten();
        ObservableList<Rohstoff> karten2 = spieler2.getKarten();
        kartenSpieler1 = FXCollections.observableArrayList(Rohstoff.HOLZ, Rohstoff.ERZ, Rohstoff.KORN, Rohstoff.KORN, Rohstoff.KORN);
        kartenSpieler2 = FXCollections.observableArrayList(Rohstoff.ERZ, Rohstoff.HOLZ, Rohstoff.HOLZ, Rohstoff.HOLZ);
        Assertions.assertTrue(karten1.containsAll(kartenSpieler1));
        Assertions.assertTrue(karten2.containsAll(kartenSpieler2));
    }
}

