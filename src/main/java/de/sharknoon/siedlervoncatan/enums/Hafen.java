package de.sharknoon.siedlervoncatan.enums;

public enum Hafen {
    DREI_ZU_EINS(null),
    HOLZ_HAFEN(Rohstoff.HOLZ),
    LEHM_HAFEN(Rohstoff.LEHM),
    WOLLE_HAFEN(Rohstoff.WOLLE),
    ERZ_HAFEN(Rohstoff.ERZ),
    KORN_HAFEN(Rohstoff.KORN);

    private final Rohstoff rohstoff;

    Hafen(Rohstoff rohstoff) {
        this.rohstoff = rohstoff;
    }

    public Rohstoff getRohstoff() {
        return this.rohstoff;
    }
}

