package de.sharknoon.siedlervoncatan.utility;

import de.sharknoon.siedlervoncatan.Spielstart;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Pfade {
    public static final String BAU_MENUE = getResource("/fxml/BauMenue.fxml");
    public static final String ENTWICKLUNGSKARTEN = getResource("/fxml/Entwicklungskarten.fxml");
    public static final String HANDEL_MENUE = getResource("/fxml/HandelMenue.fxml");
    public static final String HAUPT_MENUE = getResource("/fxml/Hauptmenue.fxml");
    public static final String KARTEN_ABGEBEN_MENUE = getResource("/fxml/KartenAbgebenMenue.fxml");
    public static final String NEUES_SPIEL_MENUE = getResource("/fxml/NeuesSpielMenue.fxml");
    public static final String ROOTLAYOUT = getResource("/fxml/RootLayout.fxml");
    public static final String SIEGER = getResource("/fxml/Sieger.fxml");
    public static final String SPIELER_ANLEGEN = getResource("/fxml/SpielerAnlegen.fxml");
    public static final String SPIELER_HANDEL_AUSWAHL = getResource("/fxml/SpielerHandelAuswahl.fxml");
    public static final String SPIELFELD = getResource("/fxml/Spielfeld.fxml");
    public static final String SPIEL_INFOS = getResource("/fxml/SpielInfos.fxml");
    public static final String WUERFEL_MENUE = getResource("/fxml/WuerfelMenue.fxml");
    public static final String ZUG_MENUE = getResource("/fxml/ZugMenue.fxml");
    public static final String SPIELERINFOS = getResource("/fxml/SpielerInfos.fxml");
    public static final String AVATAR = getResource("/fxml/Avatar.fxml");
    public static final String AUDIO_MENUE = getResource("/fxml/Audio.fxml");
    public static final String BAUKOSTEN = getResource("/fxml/Baukosten.fxml");
    public static final String ANLEITUNG = getResource("/fxml/Anleitung.fxml");
    public static final String ROHSTOFFAUSWAHL = getResource("/fxml/Rohstoffauswahl.fxml");
    public static final String INFO = getResource("/fxml/Info.fxml");
    public static final String ERROR = getResource("/fxml/Error.fxml");
    public static final String CONFIRMATION = getResource("/fxml/Confirmation.fxml");
    public static final String HOLZ = getResource("/bilder/holz.png");
    public static final String LEHM = getResource("/bilder/lehm.png");
    public static final String WOLLE = getResource("/bilder/wolle.png");
    public static final String KORN = getResource("/bilder/korn.png");
    public static final String ERZ = getResource("/bilder/erz.png");
    public static final String LOGO = getResource("/bilder/logo.png");
    public static final String AVATAR_BLAU = getResource("/bilder/avatar_blau.png");
    public static final String AVATAR_BRAUN = getResource("/bilder/avatar_braun.png");
    public static final String AVATAR_GELB = getResource("/bilder/avatar_gelb.png");
    public static final String AVATAR_GRUEN = getResource("/bilder/avatar_gruen.png");
    public static final String AVATAR_ROT = getResource("/bilder/avatar_rot.png");
    public static final String AVATAR_WEISS = getResource("/bilder/avatar_weiss.png");
    public static final String WUERFEL_EINS = getResource("/bilder/dice1.png");
    public static final String WUERFEL_ZWEI = getResource("/bilder/dice2.png");
    public static final String WUERFEL_DREI = getResource("/bilder/dice3.png");
    public static final String WUERFEL_VIER = getResource("/bilder/dice4.png");
    public static final String WUERFEL_FUENF = getResource("/bilder/dice5.png");
    public static final String WUERFEL_SECHS = getResource("/bilder/dice6.png");
    public static final String STRASSE_BLAU = getResource("/bilder/Strasse_blau.png");
    public static final String STRASSE_BRAUN = getResource("/bilder/Strasse_braun.png");
    public static final String STRASSE_GELB = getResource("/bilder/Strasse_gelb.png");
    public static final String STRASSE_GRUEN = getResource("/bilder/Strasse_gruen.png");
    public static final String STRASSE_ROT = getResource("/bilder/Strasse_rot.png");
    public static final String STRASSE_WEISS = getResource("/bilder/Strasse_weiss.png");
    public static final String STADT_BLAU = getResource("/bilder/Stadt_blau.png");
    public static final String STADT_BRAUN = getResource("/bilder/Stadt_braun.png");
    public static final String STADT_GELB = getResource("/bilder/Stadt_gelb.png");
    public static final String STADT_GRUEN = getResource("/bilder/Stadt_gruen.png");
    public static final String STADT_ROT = getResource("/bilder/Stadt_rot.png");
    public static final String STADT_WEISS = getResource("/bilder/Stadt_weiss.png");
    public static final String SIEDLUNG_BLAU = getResource("/bilder/Siedlung_blau.png");
    public static final String SIEDLUNG_BRAUN = getResource("/bilder/Siedlung_braun.png");
    public static final String SIEDLUNG_GELB = getResource("/bilder/Siedlung_gelb.png");
    public static final String SIEDLUNG_GRUEN = getResource("/bilder/Siedlung_gruen.png");
    public static final String SIEDLUNG_ROT = getResource("/bilder/Siedlung_rot.png");
    public static final String SIEDLUNG_WEISS = getResource("/bilder/Siedlung_weiss.png");
    public static final String LANDSCHAFT_ACKERLAND = getResource("/bilder/landschaft_ackerland.png");
    public static final String LANDSCHAFT_GEBIRGE = getResource("/bilder/landschaft_gebirge.png");
    public static final String LANDSCHAFT_HUEGELLAND = getResource("/bilder/landschaft_huegelland.png");
    public static final String LANDSCHAFT_WALD = getResource("/bilder/landschaft_wald.png");
    public static final String LANDSCHAFT_WEIDELAND = getResource("/bilder/landschaft_weideland.png");
    public static final String LANDSCHAFT_WUESTE = getResource("/bilder/landschaft_wueste.png");
    public static final String ANLEITUNG_1 = getResource("/bilder/anleitung_1.jpg");
    public static final String ANLEITUNG_2 = getResource("/bilder/anleitung_2.jpg");
    public static final String ANLEITUNG_3 = getResource("/bilder/anleitung_3.jpg");
    public static final String ANLEITUNG_4 = getResource("/bilder/anleitung_4.jpg");
    public static final String ANLEITUNG_5 = getResource("/bilder/anleitung_5.jpg");
    public static final String ANLEITUNG_6 = getResource("/bilder/anleitung_6.jpg");
    public static final String ANLEITUNG_7 = getResource("/bilder/anleitung_7.jpg");
    public static final String ANLEITUNG_8 = getResource("/bilder/anleitung_8.jpg");
    public static final String ANLEITUNG_9 = getResource("/bilder/anleitung_9.jpg");
    public static final String ANLEITUNG_10 = getResource("/bilder/anleitung_10.jpg");
    public static final String ANLEITUNG_11 = getResource("/bilder/anleitung_11.jpg");
    public static final String ANLEITUNG_12 = getResource("/bilder/anleitung_12.jpg");
    public static final String SOUND_MENUE = getResource("/sounds/menue.wav");
    public static final String SOUND_MEER = getResource("/sounds/meer.wav");
    public static final String SOUND_HANDEL = getResource("/sounds/handel.wav");
    public static final String SOUND_WUERFEL = getResource("/sounds/dice.wav");
    public static final String SOUND_WUERFEL_WURF = getResource("/sounds/diceshake.wav");
    public static final String SOUND_BAU = getResource("/sounds/bauen.wav");
    public static final String SOUND_KLICK = getResource("/sounds/click.wav");
    public static final String SOUND_ERROR = getResource("/sounds/error.wav");
    public static final String SOUND_SIEGER = getResource("/sounds/sieger.wav");
    public static final String SOUND_EVIL_LAUGH = getResource("/sounds/evilLaugh.wav");
    public static final String SOUND_PAPER = getResource("/sounds/paper.wav");
    public static final Path SAVES = Paths.get(System.getProperty("user.home"), "Siedler von Catan", "saves");

    private static String getResource(String path) {
        URL resource = Spielstart.class.getResource(path);
        if (resource == null) {
            return "";
        }
        return resource.toExternalForm();
    }

    private static InputStream getResourceAsStream(String path) {
        return Spielstart.class.getResourceAsStream(path);
    }
}

