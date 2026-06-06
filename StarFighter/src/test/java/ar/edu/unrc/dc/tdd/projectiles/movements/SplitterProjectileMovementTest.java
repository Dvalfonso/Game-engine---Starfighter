package ar.edu.unrc.dc.tdd.projectiles.movements;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import ar.edu.unrc.dc.models.cell.Position;
import ar.edu.unrc.dc.models.projectileMovement.*;

public class SplitterProjectileMovementTest {

    Position initialPos;
    SplitterProjectileMovement movement;

    @BeforeEach
    public void setup() {
        initialPos = new Position(0,0);
        movement = new SplitterProjectileMovement(initialPos);
    }

    @Test
    public void nextStepTest() {
        movement.nextStep();
        assertEquals(initialPos, movement.getPosition());
    }

    @Test
    public void hasNextStep_AfterStpe_Test() {
        movement.nextStep();
        assertFalse(movement.hasNextStep());
    }

    @Test
    public void hasNextStep_BeforeStep_Test() {
        assertFalse(movement.hasNextStep());
    }

    @Test
    public void hasNextStep_AfterNextTurn_Test() {
        movement.nextTurn();
        assertEquals(initialPos, movement.getPosition());
        assertFalse(movement.hasNextStep());
    }
}
