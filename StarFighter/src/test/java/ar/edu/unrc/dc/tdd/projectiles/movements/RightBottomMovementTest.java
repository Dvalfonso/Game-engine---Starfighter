package ar.edu.unrc.dc.tdd.projectiles.movements;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import ar.edu.unrc.dc.models.cell.Position;
import ar.edu.unrc.dc.models.projectileMovement.RightBotMovementProjectile;

public class RightBottomMovementTest {

    Position initialPos;
    RightBotMovementProjectile movement;

    @BeforeEach
    public void setup() {
        initialPos = new Position(0,0);
        movement = new RightBotMovementProjectile(initialPos);
    }

    @Test
    public void nextStepTest() {
        movement.nextStep();
        assertEquals(initialPos.to(-1,1), movement.getPosition());
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
        assertTrue(movement.hasNextStep());
    }
}
