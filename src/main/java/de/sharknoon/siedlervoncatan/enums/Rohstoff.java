package de.sharknoon.siedlervoncatan.enums;

import de.sharknoon.siedlervoncatan.utility.Pfade;
import javafx.scene.image.Image;

public enum Rohstoff {
    LEHM(new Image(Pfade.LEHM)),
    ERZ(new Image(Pfade.ERZ)),
    HOLZ(new Image(Pfade.HOLZ)),
    WOLLE(new Image(Pfade.WOLLE)),
    KORN(new Image(Pfade.KORN));

    private final Image image;

    Rohstoff(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }

    public static Rohstoff getRohstoff(String rohstoff) {
        switch (rohstoff.toLowerCase()) {
            case "holz" -> {
                return HOLZ;
            }
            case "lehm" -> {
                return LEHM;
            }
            case "wolle" -> {
                return WOLLE;
            }
            case "korn" -> {
                return KORN;
            }
            case "erz" -> {
                return ERZ;
            }
        }
        return null;
    }
}

