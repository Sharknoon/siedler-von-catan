package de.sharknoon.siedlervoncatan;

import de.sharknoon.siedlervoncatan.utility.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class PositionTest {

    @Test
    public void positionsTest() {
        Position p1 = new Position(3, 2);
        Position p2 = new Position(3, 0);
        Position p3 = new Position(4, 1);
        Position p4 = new Position(4, 3);
        Position p5 = new Position(3, 4);
        Position p6 = new Position(2, 3);
        Position p7 = new Position(2, 1);
        Position p8 = new Position(2, 1);
        Position p9 = new Position(1, 1);
        Assertions.assertEquals(p9.getxAchse(), 1L);
        Assertions.assertEquals(p9.getyAchse(), 1L);
        Set<Position> spos = p1.getNachbarn();
        Assertions.assertTrue(spos.contains(p2));
        Assertions.assertTrue(spos.contains(p3));
        Assertions.assertTrue(spos.contains(p4));
        Assertions.assertTrue(spos.contains(p5));
        Assertions.assertTrue(spos.contains(p6));
        Assertions.assertTrue(spos.contains(p7));
        Assertions.assertFalse(spos.contains(p9));
        Assertions.assertFalse(p8.isNachbar(p8));
        Assertions.assertTrue(p1.isNachbar(p8));
        Assertions.assertFalse(p2.isNachbar(p4));
        Assertions.assertFalse(p2.isNachbar(p5));
        Assertions.assertFalse(p2.isNachbar(p6));
        Assertions.assertTrue(p2.isNachbar(p3));
    }

}