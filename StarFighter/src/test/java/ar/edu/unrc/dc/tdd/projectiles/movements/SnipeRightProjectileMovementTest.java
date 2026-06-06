package ar.edu.unrc.dc.tdd.projectiles.movements;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import ar.edu.unrc.dc.models.cell.Position;
import ar.edu.unrc.dc.models.projectileMovement.*;

public class SnipeRightProjectileMovementTest {

    Position initialPos;
    SnipeRightProjectileMovement movement;

    @BeforeEach
    public void setup() {
        initialPos = new Position(0,0);
        movement = new SnipeRightProjectileMovement(initialPos);
    }

    @Test
    public void nextStepTest() {
        movement.nextStep();
        assertEquals(initialPos.to(0,8), movement.getPosition());
        assertTrue(movement.moved());
    }

    @Test
    public void hasNextStep_True_Test() {
        assertTrue(movement.hasNextStep());
    }

    @Test
    public void hasNextStep_False_Test() {
        movement.nextStep();
        assertFalse(movement.hasNextStep());
    }

    @Test
    public void nextTurnTest() {
        movement.nextTurn();
        assertFalse(movement.moved());
    }
}
