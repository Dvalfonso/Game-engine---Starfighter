package ar.edu.unrc.dc.tdd.projectiles.movements;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import ar.edu.unrc.dc.models.cell.Position;
import ar.edu.unrc.dc.models.projectileMovement.RightRocketMovement;

public class RightRocketMovementTest {

    Position initialPos;
    RightRocketMovement movement;

    @BeforeEach
    public void setup() {
        initialPos = new Position(0,0);
        movement = new RightRocketMovement(initialPos);
    }

    @Test
    public void nextStepTest() {
        movement.nextStep();
        assertEquals(initialPos.to(0,1), movement.getPosition());
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
        assertEquals(2, movement.getMovePerTurn());
        assertEquals(0, movement.getMovedActualTurn());
    }
}
