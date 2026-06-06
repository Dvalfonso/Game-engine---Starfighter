package ar.edu.unrc.dc.tdd.enemy;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.cell.Position;
import ar.edu.unrc.dc.models.cell.Starfighter;
import ar.edu.unrc.dc.models.cell.Enemy;
import ar.edu.unrc.dc.models.cell.enemy.Pylon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PylonTest {

    @Test
    void pylonConstructorShouldSetCorrectInitialStats() {
        Board board = new Board(5, 5);
        Starfighter sf = new Starfighter(new Position(0, 0));
        List<Enemy> enemies = new ArrayList<>();

        Pylon pylon = new Pylon(new Position(3, 4), sf, enemies, board, null);

        assertEquals(300, pylon.getCurrentHealth());
        assertFalse(pylon.isDead());
    }

    @Test
    void pylonHurtShouldReduceHealthCorrectly() {
        Board board = new Board(5, 5);
        Starfighter sf = new Starfighter(new Position(0, 0));
        List<Enemy> enemies = new ArrayList<>();

        Pylon pylon = new Pylon(new Position(2, 2), sf, enemies, board, null);

        pylon.hurt(50);

        assertEquals(250, pylon.getCurrentHealth());
    }

    @Test
    void pylonShouldDieWhenHealthReachesZero() {
        Board board = new Board(5, 5);
        Starfighter sf = new Starfighter(new Position(0, 0));
        List<Enemy> enemies = new ArrayList<>();

        Pylon pylon = new Pylon(new Position(1, 1), sf, enemies, board, null);

        pylon.hurt(1000);

        assertTrue(pylon.isDead());
        assertEquals(0, pylon.getCurrentHealth());
    }

    @Test
    void pylonPositionShouldBeStoredCorrectly() {
        Board board = new Board(5, 5);
        Starfighter sf = new Starfighter(new Position(0, 0));
        List<Enemy> enemies = new ArrayList<>();
        Position pos = new Position(4, 4);

        Pylon pylon = new Pylon(pos, sf, enemies, board, null);

        assertEquals(pos, pylon.getPosition());
    }

    @Test
    void pylonCanChangePosition() {
        Board board = new Board(5, 5);
        Starfighter sf = new Starfighter(new Position(0, 0));
        List<Enemy> enemies = new ArrayList<>();

        Pylon pylon = new Pylon(new Position(2, 3), sf, enemies, board, null);

        Position newPos = new Position(1, 1);
        pylon.setPosition(newPos);

        assertEquals(newPos, pylon.getPosition());
    }
}
