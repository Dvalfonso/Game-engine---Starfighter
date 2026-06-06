package ar.edu.unrc.dc.tdd.enemy;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import ar.edu.unrc.dc.models.*;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.cell.enemy.Fighter;
import ar.edu.unrc.dc.models.starfighterCommand.*;
import ar.edu.unrc.dc.patterns.StarfighterCommand;

public class FighterTest {

    private Game game;
    private Board board;
    private Starfighter starfighter;
    private FighterTestHelper fighter;

    static class FighterTestHelper extends Fighter {
        public FighterTestHelper(Position pos, Starfighter sf, Board board) {
            super(pos, sf, board, new ProjectileLinkedList(board));
        }

        public void callActionSFIsNotSeen() {
            super.actionSFIsNotSeen();
        }

        public void callActionSFIsSeen() {
            super.actionSFIsSeen();
        }
    }

    @BeforeEach
    public void setup() {
        game = new Game(6, 10);
        starfighter = game.getStarfighter();
        board = game.getBoard();
        fighter = new FighterTestHelper(new Position(5, 7), starfighter, board);
    }

    @Test
    public void constructorTest() {
        assertEquals(150, fighter.getCurrentHealth());
        assertEquals(5, fighter.getRegen());
        assertEquals(5, fighter.getArmour());
        assertEquals(10, fighter.getVision());
    }

    @Test
    public void preemptiveFireIncreasesArmour() {
        int before = fighter.getArmour();

        StarfighterCommand fireCmd = new FireCommand(game);

        boolean turnEnd = fighter.preemptiveAction(fireCmd);

        assertFalse(turnEnd);
        assertEquals(before + 1, fighter.getArmour());
    }

    @Test
    public void preemptivePassMovesAndFiresProjectile() {
        StarfighterCommand passCmd = new PassCommand(game);

        Position start = fighter.getPosition();

        boolean turnEnd = fighter.preemptiveAction(passCmd);

        assertTrue(turnEnd);
        assertTrue(fighter.getPosition().getCol() < start.getCol());
    }

    @Test
    public void actionSFIsNotSeenMovesThreeLeftAndFires() {
        Position start = fighter.getPosition();
        fighter.callActionSFIsNotSeen();

        assertEquals(start.getCol() - 3, fighter.getPosition().getCol());
    }

    @Test
    public void actionSFIsSeenMovesOneLeftAndFires() {
        Position start = fighter.getPosition();
        fighter.callActionSFIsSeen();

        assertEquals(start.getCol() - 1, fighter.getPosition().getCol());
    }
}
