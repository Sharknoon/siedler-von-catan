package de.sharknoon.siedlervoncatan.enums;

public enum Landschaft {
    WALD(Rohstoff.HOLZ),
    WEIDELAND(Rohstoff.WOLLE),
    ACKERLAND(Rohstoff.KORN),
    HUEGELLAND(Rohstoff.LEHM),
    GEBIRGE(Rohstoff.ERZ),
    WUESTE(null);

    private final Rohstoff rohstoff;

    Landschaft(Rohstoff rohstoff) {
        this.rohstoff = rohstoff;
    }

    public Rohstoff getRohstoff() {
        return this.rohstoff;
    }

}

