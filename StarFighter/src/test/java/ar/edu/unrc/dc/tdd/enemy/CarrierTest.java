package ar.edu.unrc.dc.tdd.enemy;

import static org.junit.jupiter.api.Assertions.*;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.*;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.cell.enemy.*;
import ar.edu.unrc.dc.models.starfighterCommand.*;
import ar.edu.unrc.dc.patterns.StarfighterCommand;

public class CarrierTest {

    private Game game;
    private Board board;
    private Starfighter sf;
    private CarrierTestHelper carrier;

    static class CarrierTestHelper extends Carrier {
        public CarrierTestHelper(Position pos, Starfighter sf, Board b) {
            super(pos, sf, b);
        }

        public void callSeen() { super.actionSFIsSeen(); }
        public void callNotSeen() { super.actionSFIsNotSeen(); }
    }

    @BeforeEach
    void setup() {
        game = new Game(8, 10);
        board = game.getBoard();
        sf = game.getStarfighter();
        EnemyManager enemyManager = game.getEnemyManager();


        carrier = new CarrierTestHelper(new Position(5, 5), sf, board);
        carrier.setManager(enemyManager);
        board.updateCell(new Position(5,5), carrier);
    }

    @Test
    void constructorTest() {
        assertEquals(200, carrier.getCurrentHealth());
        assertEquals(10, carrier.getRegen());
        assertEquals(15, carrier.getArmour());
        assertEquals(15, carrier.getVision());
    }

    @Test
    void actionSFIsNotSeenMovesTwoLeft() {
        Position initial = carrier.getPosition();
        carrier.callNotSeen();
        assertEquals(initial.getCol() - 2, carrier.getPosition().getCol());
    }

    @Test
    void actionSFIsSeenMovesOneLeftAndSpawnsInterceptor() {
        Position initial = carrier.getPosition();


        carrier.callSeen();

        assertEquals(initial.getCol() - 1, carrier.getPosition().getCol());

        Position spawn = carrier.getPosition().to(-1, 0);
        assertTrue(board.getCell(spawn) instanceof Interceptor);
    }

    @Test
    void preemptiveActionSpecialIncreasesRegen() {
        int initialRegen = carrier.getRegen();

        StarfighterCommand cmd = new SpecialCommand(game);

        boolean turnEnded = carrier.preemptiveAction(cmd);

        assertFalse(turnEnded);
        assertEquals(initialRegen + 10, carrier.getRegen());
    }

    @Test
    void preemptiveActionPassMovesLeftAndSpawnsTwoInterceptors() {
        Position initial = carrier.getPosition();

        StarfighterCommand cmd = new PassCommand(game);

        boolean turnEnded = carrier.preemptiveAction(cmd);

        assertTrue(turnEnded);
        assertEquals(initial.getCol() - 2, carrier.getPosition().getCol());

        assertTrue(board.getCell(carrier.getPosition().to(1,0)) instanceof Interceptor);
        assertTrue(board.getCell(carrier.getPosition().to(-1,0)) instanceof Interceptor);
    }
}
