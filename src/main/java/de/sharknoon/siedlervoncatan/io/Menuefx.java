package de.sharknoon.siedlervoncatan.io;

import de.sharknoon.siedlervoncatan.Spielstart;
import de.sharknoon.siedlervoncatan.enums.Rohstoff;
import de.sharknoon.siedlervoncatan.sound.Sound;
import de.sharknoon.siedlervoncatan.spiel.Spiel;
import de.sharknoon.siedlervoncatan.spiel.Spieler;
import de.sharknoon.siedlervoncatan.utility.Handel;
import de.sharknoon.siedlervoncatan.utility.Pfade;
import de.sharknoon.siedlervoncatan.utility.popup.Error;
import de.sharknoon.siedlervoncatan.utility.popup.*;
import de.sharknoon.siedlervoncatan.view.ViewController;
import de.sharknoon.siedlervoncatan.view.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class Menuefx implements UserInterface {
    private Spielstart spielstart;
    private ViewController viewController;
    private RootLayoutController layoutController;
    private SpielfeldController spielfeldController;

    @Override
    public void setSpielstart(Spielstart spielstart) {
        this.spielstart = spielstart;
        this.layoutController = spielstart.getLayoutController();
        this.viewController = new ViewController(null, spielstart.getLayoutController());
    }

    @Override
    public void setSpiel(Spiel spiel) {
        this.viewController = new ViewController(spiel, this.layoutController);
    }

    @Override
    public void zeigeHauptmenue() {
        try {
            Pane pane = this.viewController.initPane(Pfade.HAUPT_MENUE);
            this.layoutController.addToCenterAnimatedV(pane);
            HauptmenueController controller = this.viewController.getLoader().getController();
            controller.setSpielstart(this.spielstart);
            Sound.getInstanz().playMusik(Sound.MUSIK_MENUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void zeigeAudiomenue() {
        try {
            Pane pane = this.viewController.initPane(Pfade.AUDIO_MENUE);
            this.layoutController.addToCenterAnimatedH(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void zeigeBaukosten() {
        try {
            Pane pane = this.viewController.initPane(Pfade.BAUKOSTEN);
            this.layoutController.addToCenterAnimatedH(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void zeigeAnleitung() {
        new Anleitung("Anleitung").showAndWait();
    }

    @Override
    public void zeigeSpielfeld() {
        this.layoutController.clearCenter();
        try {
            Pane pane = this.viewController.initPane(Pfade.SPIELFELD);
            this.layoutController.addToCenter(pane);
            this.spielfeldController = this.viewController.getLoader().getController();
            Sound.getInstanz().playMusik(Sound.MUSIK_MEER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void zeigeNeuesspielMenue() {
        try {
            Pane pane = this.viewController.initPane(Pfade.NEUES_SPIEL_MENUE);
            StackPane.setAlignment(pane, Pos.CENTER_RIGHT);
            this.layoutController.addToCenterAnimatedV(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void spielerAnlegen() {
        try {
            Pane pane = this.viewController.initPane(Pfade.SPIELER_ANLEGEN);
            this.layoutController.addToCenterAnimatedH(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void zeigeWuerfel() {
        try {
            Pane pane = this.viewController.initPane(Pfade.WUERFEL_MENUE);
            StackPane.setAlignment(pane, Pos.CENTER_RIGHT);
            this.layoutController.addToCenterAnimatedV(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void zeigeZug() {
        try {
            Pane pane = this.viewController.initPane(Pfade.ZUG_MENUE);
            StackPane.setAlignment(pane, Pos.CENTER_RIGHT);
            this.layoutController.addToCenterAnimatedV(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void zeigeEntwicklungskarten() {
        try {
            Pane pane = this.viewController.initPane(Pfade.ENTWICKLUNGSKARTEN);
            this.layoutController.addToCenterAnimatedH(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void zeigeBau() {
        try {
            Pane pane = this.viewController.initPane(Pfade.BAU_MENUE);
            StackPane.setAlignment(pane, Pos.CENTER_RIGHT);
            this.layoutController.addToCenterAnimatedV(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void zeigeSpielInfos() {
        try {
            Pane pane = this.viewController.initPane(Pfade.SPIEL_INFOS);
            StackPane.setAlignment(pane, Pos.TOP_LEFT);
            this.layoutController.addToCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void zeigeHandel() {
        try {
            Pane pane = this.viewController.initPane(Pfade.HANDEL_MENUE);
            this.layoutController.addToCenterAnimatedH(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void zeigeSpielerHandel(Handel handel) {
        try {
            Pane pane = this.viewController.initPane(Pfade.SPIELER_HANDEL_AUSWAHL);
            ((SpielerHandelAuswahlController) this.viewController.getLoader().getController()).setAngebotNachfrage(handel);
            this.layoutController.addToCenterAnimatedH(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void zeigeKartenAbgeben(Spieler spieler, int anzahl) {
        try {
            Pane pane = this.viewController.initPane(Pfade.KARTEN_ABGEBEN_MENUE);
            KartenAbgebenMenueController controller = this.viewController.getLoader().getController();
            controller.setAnzahl(anzahl);
            controller.setSpieler(spieler);
            this.layoutController.addToCenterAnimatedH(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void zeigeSieger() {
        try {
            Pane pane = this.viewController.initPane(Pfade.SIEGER);
            Sound.getInstanz().playSoundeffekt(Sound.SIEGER_CLIP);
            this.layoutController.addToCenterAnimatedH(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void zeigeSpielerInfos(Spieler spieler) {
        try {
            Pane pane = this.viewController.initPane(Pfade.SPIELERINFOS);
            FXMLLoader loader = this.viewController.getLoader();
            SpielerInfosController controller = loader.getController();
            controller.setSpieler(spieler);
            this.layoutController.addToCenterAnimatedH(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pane zeigeAvatar(Spieler spieler) {
        try {
            Pane pane = this.viewController.initPane(Pfade.AVATAR);
            AvatarController controller = this.viewController.getLoader().getController();
            controller.setSpieler(spieler);
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean zeigeConfirmation(String text) {
        Confirmation confirmation = new Confirmation(text);
        return confirmation.showAndWait();
    }

    @Override
    public Rohstoff zeigeRohstoffauswahl(String text) {
        Rohstoffauswahl rohstoffauswahl = new Rohstoffauswahl(text);
        return rohstoffauswahl.showAndWait();
    }

    @Override
    public void zeigeInfo(String text) {
        new Info(text).showAndWait();
    }

    @Override
    public void zeigeError(String text) {
        new Error(text).showAndWait();
    }

    @Override
    public SpielfeldController getSpielfeldController() {
        return this.spielfeldController;
    }

    @Override
    public void zeigeMessage(String message) {
        this.spielfeldController.setMessage(message);
    }
}

