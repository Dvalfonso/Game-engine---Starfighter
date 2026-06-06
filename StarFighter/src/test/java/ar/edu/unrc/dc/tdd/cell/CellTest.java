package ar.edu.unrc.dc.tdd.cell;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.cell.*;

public class CellTest {
    @Test
    void ChangePositionTest() {
        Position pos = new Position(5,5);
        Cell starfighter = new Starfighter(pos);

        Position newPos = new Position(5,7);
        starfighter.setPosition(newPos);

        Assertions.assertEquals(new Position(5,7), starfighter.getPosition());
    }
}
