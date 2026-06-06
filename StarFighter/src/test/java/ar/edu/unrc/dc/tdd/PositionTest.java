package ar.edu.unrc.dc.tdd;

import ar.edu.unrc.dc.models.cell.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    public void manhattanDistance1Test() {
        Position p1 = new Position(3, 7);
        Position p2 = new Position(0, 0);

        assertEquals(10, p1.manhattanDistance(p2));
    }

    @Test
    public void manhattanDistance2Test() {
        Position p1 = new Position(-1, -1);
        Position p2 = new Position(1, 1);

        assertEquals(4, p1.manhattanDistance(p2));
    }


    @Test
    public void testToCreatesNewOffsetPosition() {
        Position p = new Position(2, 3);
        Position moved = p.to(5, -2);

        assertEquals(new Position(7, 1), moved);
        assertNotSame(p, moved); 
    }

    @Test
    public void testEqualsSameCoordinates() {
        Position p1 = new Position(4, 4);
        Position p2 = new Position(4, 4);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void testEqualsDifferentCoordinates() {
        Position p1 = new Position(1, 2);
        Position p2 = new Position(2, 1);

        assertNotEquals(p1, p2);
    }

    @Test
    public void testEqualsAgainstNull() {
        Position p = new Position(1, 1);
        assertNotEquals(null, p);
    }

    @Test
    public void testEqualsAgainstDifferentClass() {
        Position p = new Position(1, 1);
        assertNotEquals(p, "string");
    }

    @Test
    public void testToStringFormat() {
        Position p = new Position(5, -3);
        assertEquals("(5,-3)", p.toString());
    }

    @Test
    public void testIsSameRowTrue() {
        Position p1 = new Position(2, 5);
        Position p2 = new Position(2, 9);

        assertTrue(p1.isSameRow(p2));
    }

    @Test
    public void testIsSameRowFalse() {
        Position p1 = new Position(2, 5);
        Position p2 = new Position(1, 5);

        assertFalse(p1.isSameRow(p2));
    }

    @Test
    public void testIsSameColTrue() {
        Position p1 = new Position(3, 4);
        Position p2 = new Position(9, 4);

        assertTrue(p1.isSameCol(p2));
    }

    @Test
    public void testIsSameColFalse() {
        Position p1 = new Position(3, 4);
        Position p2 = new Position(3, 5);

        assertFalse(p1.isSameCol(p2));
    }

    @Test
    public void testVerDirectionRelativeToAbove() {
        Position p1 = new Position(5, 5);
        Position p2 = new Position(3, 5);

        assertEquals(1, p1.verDirectionRelativeTo(p2)); 
    }

    @Test
    public void testVerDirectionRelativeToBelow() {
        Position p1 = new Position(1, 5);
        Position p2 = new Position(4, 5);

        assertEquals(-1, p1.verDirectionRelativeTo(p2));
    }

    @Test
    public void testVerDirectionRelativeToSameRow() {
        Position p1 = new Position(3, 4);
        Position p2 = new Position(3, 20);

        assertEquals(0, p1.verDirectionRelativeTo(p2));
    }
}
