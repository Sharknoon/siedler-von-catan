package de.sharknoon.siedlervoncatan;

import de.sharknoon.siedlervoncatan.enums.Rohstoff;
import de.sharknoon.siedlervoncatan.io.UserInterface;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.utility.Handel;
import de.sharknoon.siedlervoncatan.view.controller.SpielfeldController;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class MenueTest implements UserInterface {

    @Override
    public void setSpielstart(Spielstart spielstart) {
    }

    @Override
    public void setSpiel(Spiel spiel) {
    }

    @Override
    public void zeigeHauptmenue() {
    }

    @Override
    public void zeigeAudiomenue() {
    }

    @Override
    public void zeigeSpielfeld() {
    }

    @Override
    public void zeigeNeuesspielMenue() {
    }

    @Override
    public void spielerAnlegen() {
    }

    @Override
    public void zeigeWuerfel() {
    }

    @Override
    public void zeigeZug() {
    }

    @Override
    public void zeigeEntwicklungskarten() {
    }

    @Override
    public void zeigeBau() {
    }

    @Override
    public void zeigeSpielInfos() {
    }

    @Override
    public void zeigeHandel() {
    }

    @Override
    public void zeigeSpielerHandel(Handel handel) {
    }

    @Override
    public void zeigeKartenAbgeben(Spieler spieler, int anzahl) {
    }

    @Override
    public void zeigeSieger() {
    }

    @Override
    public void zeigeInfo(String text) {
    }

    @Override
    public void zeigeError(String text) {
    }

    @Override
    public Rohstoff zeigeRohstoffauswahl(String text) {
        return Rohstoff.HOLZ;
    }

    @Override
    public boolean zeigeConfirmation(String text) {
        return true;
    }

    @Override
    public Pane zeigeSpielerInfos(Spieler spieler) {
        return null;
    }

    @Override
    public Pane zeigeAvatar(Spieler spieler) {
        return null;
    }

    @Override
    public void removeFromCenterAnimatedH(Node node) {
    }

    @Override
    public SpielfeldController getSpielfeldController() {
        return null;
    }

    @Override
    public void zeigeMessage(String message) {
    }

    @Override
    public void zeigeBaukosten() {
    }

    @Override
    public void zeigeAnleitung() {
    }
}

