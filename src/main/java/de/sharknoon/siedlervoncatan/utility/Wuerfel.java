package de.sharknoon.siedlervoncatan.utility;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;
import java.util.Random;

public class Wuerfel
implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final Random RANDOM = new Random();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public static int generiereZufallsZahl(int maxWert) {
        return RANDOM.nextInt(maxWert) + 1;
    }

    public void addListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    public void wuerfeln() {
        int w1 = Wuerfel.generiereZufallsZahl(6);
        int w2 = Wuerfel.generiereZufallsZahl(6);
        int ergebnis = w1 + w2;
        this.support.firePropertyChange("wuerfeln", 0, ergebnis);
        this.support.firePropertyChange("wuerfel1", 0, w1);
        this.support.firePropertyChange("wuerfel2", 0, w2);
    }
}

