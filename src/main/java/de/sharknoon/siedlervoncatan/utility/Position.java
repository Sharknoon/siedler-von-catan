package de.sharknoon.siedlervoncatan.utility;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Position implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int xAchse;
    private final int yAchse;
    private final Set<Position> nachbarn;

    public Position(int xAchse, int yAchse) {
        this.xAchse = xAchse;
        this.yAchse = yAchse;
        this.nachbarn = new HashSet<>();
    }

    public int hashCode() {
        int result = 1;
        result = 31 * result + this.xAchse;
        result = 31 * result + this.yAchse;
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Position other)) {
            return false;
        }
        if (this.xAchse != other.xAchse) {
            return false;
        }
        return this.yAchse == other.yAchse;
    }

    public Set<Position> getNachbarn() {
        if (this.nachbarn.isEmpty()) {
            this.setNachbarn();
        }
        return this.nachbarn;
    }

    public int getxAchse() {
        return this.xAchse;
    }

    public int getyAchse() {
        return this.yAchse;
    }

    public boolean isNachbar(Position position) {
        int deltaX = Math.abs(this.xAchse - position.xAchse);
        int deltaY = Math.abs(this.yAchse - position.yAchse);
        int summe = deltaX + deltaY;
        return deltaX <= 1 && deltaY <= 2 && summe == 2;
    }

    private void setNachbarn() {
        for (int x = -1; x <= 1; ++x) {
            for (int y = -2; y <= 2; ++y) {
                int xNeu = this.xAchse + x;
                int yNeu = this.yAchse + y;
                Position posNeu = new Position(xNeu, yNeu);
                if (!posNeu.isNachbar(this)) continue;
                this.nachbarn.add(posNeu);
            }
        }
    }

    public boolean isNachbarlandschaft(Position position) {
        int deltaX = Math.abs(this.xAchse - position.xAchse);
        int deltaY = Math.abs(this.yAchse - position.yAchse);
        return deltaX == 2 && deltaY == 0 || deltaX == 1 && deltaY == 3;
    }

    public String toString() {
        return String.format("(%d,%d)", this.xAchse, this.yAchse);
    }
}

