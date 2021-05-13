package de.sharknoon.siedlervoncatan.utility;

import de.sharknoon.siedlervoncatan.enums.Rohstoff;
import de.sharknoon.siedlervoncatan.utility.popup.Popup;

import java.util.ArrayList;
import java.util.Collection;

public class Baukosten
extends Popup {
    public static final Collection<Rohstoff> SIEDLUNG = new ArrayList<>();
    public static final Collection<Rohstoff> STADT;
    public static final Collection<Rohstoff> STRASSE;
    public static final Collection<Rohstoff> ENTWICKLUNGSKARTE;

    static {
        SIEDLUNG.add(Rohstoff.HOLZ);
        SIEDLUNG.add(Rohstoff.LEHM);
        SIEDLUNG.add(Rohstoff.KORN);
        SIEDLUNG.add(Rohstoff.WOLLE);
        STADT = new ArrayList<>();
        STADT.add(Rohstoff.ERZ);
        STADT.add(Rohstoff.ERZ);
        STADT.add(Rohstoff.ERZ);
        STADT.add(Rohstoff.KORN);
        STADT.add(Rohstoff.KORN);
        STRASSE = new ArrayList<>();
        STRASSE.add(Rohstoff.HOLZ);
        STRASSE.add(Rohstoff.LEHM);
        ENTWICKLUNGSKARTE = new ArrayList<>();
        ENTWICKLUNGSKARTE.add(Rohstoff.ERZ);
        ENTWICKLUNGSKARTE.add(Rohstoff.WOLLE);
        ENTWICKLUNGSKARTE.add(Rohstoff.KORN);
    }
}

