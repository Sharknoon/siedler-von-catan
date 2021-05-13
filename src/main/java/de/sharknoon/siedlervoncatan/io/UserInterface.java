package de.sharknoon.siedlervoncatan.io;

import de.sharknoon.siedlervoncatan.Spielstart;
import de.sharknoon.siedlervoncatan.enums.Rohstoff;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.utility.Handel;
import de.sharknoon.siedlervoncatan.view.controller.SpielfeldController;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public interface UserInterface {

    void setSpielstart(Spielstart var1);

    void setSpiel(Spiel var1);

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

    void zeigeSpielerHandel(Handel var1);

    void zeigeKartenAbgeben(Spieler var1, int var2);

    void zeigeSieger();

    void zeigeInfo(String var1);

    void zeigeError(String var1);

    Rohstoff zeigeRohstoffauswahl(String var1);

    boolean zeigeConfirmation(String var1);

    Pane zeigeSpielerInfos(Spieler var1);

    Pane zeigeAvatar(Spieler var1);

    void removeFromCenterAnimatedH(Node var1);

    SpielfeldController getSpielfeldController();

    void zeigeMessage(String var1);

    void zeigeBaukosten();
}

