package de.sharknoon.siedlervoncatan.io;

import de.sharknoon.siedlervoncatan.Spielstart;
import de.sharknoon.siedlervoncatan.enums.Rohstoff;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.utility.Handel;
import de.sharknoon.siedlervoncatan.view.controller.SpielfeldController;
import javafx.scene.layout.Pane;

public interface UserInterface {

    void setSpielstart(Spielstart spielstart);

    void setSpiel(Spiel spiel);

    void zeigeHauptmenue();

    void zeigeAudiomenue();

    void zeigeSpielfeld();

    void zeigeNeuesspielMenue();

    void spielerAnlegen();

    void zeigeWuerfel();

    void zeigeZug();

    void zeigeEntwicklungskarten();

    void zeigeBau();

    void zeigeSpielInfos();

    void zeigeHandel();

    void zeigeSpielerHandel(Handel handel);

    void zeigeKartenAbgeben(Spieler spieler, int anzahl);

    void zeigeSieger();

    void zeigeInfo(String info);

    void zeigeError(String error);

    Rohstoff zeigeRohstoffauswahl(String text);

    boolean zeigeConfirmation(String text);

    void zeigeSpielerInfos(Spieler spieler);

    Pane zeigeAvatar(Spieler spieler);

    SpielfeldController getSpielfeldController();

    void zeigeMessage(String message);

    void zeigeBaukosten();

    void zeigeAnleitung();
}

