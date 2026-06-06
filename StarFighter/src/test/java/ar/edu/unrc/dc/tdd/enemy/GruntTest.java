package ar.edu.unrc.dc.tdd.enemy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.*;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.cell.enemy.Grunt;
import ar.edu.unrc.dc.patterns.StarfighterCommand;

public class GruntTest {

    private Game game;
    private Board board;
    private Starfighter starfighter;
    private GruntTestHelper grunt;

    //For testing protected methods
    public static class GruntTestHelper extends Grunt {
        public GruntTestHelper(Position position, Starfighter starfighter, Board board) {
            super(position, starfighter, board, new ProjectileLinkedList(board));
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
        grunt = new GruntTestHelper(new Position(5, 7), starfighter, board);
    }

    @Test
    public void constructorTest() {
        assertEquals(100, grunt.getCurrentHealth());
        assertEquals(1, grunt.getRegen());
        assertEquals(1, grunt.getArmour());
        assertEquals(5, grunt.getVision());
    }

    @Test
    public void preemptiveActionWithPassCommandTest() {
        StarfighterCommand passCommand = new StarfighterCommand() {
            @Override public void execute() {}
            @Override public String getName() { return "pass"; }
        };

        boolean result = grunt.preemptiveAction(passCommand);

        assertFalse(result);
        assertEquals(110, grunt.getCurrentHealth());
    }

    @Test
    public void preemptiveActionWithSpecialCommandTest() {
        StarfighterCommand specialCommand = new StarfighterCommand() {
            @Override public void execute() {}
            @Override public String getName() { return "special"; }
        };

        boolean result = grunt.preemptiveAction(specialCommand);

        assertFalse(result);
        assertEquals(120, grunt.getCurrentHealth());
    }

    @Test
    public void preemptiveActionWithOtherCommandTest() {
        StarfighterCommand otherCommand = new StarfighterCommand() {
            @Override public void execute() {}
            @Override public String getName() { return "fire"; }
        };

        boolean result = grunt.preemptiveAction(otherCommand);

        assertFalse(result);
        assertEquals(100, grunt.getCurrentHealth());
    }

    @Test
    public void actionSFIsNotSeenMovesAndFiresTest() {
        Position initial = new Position(grunt.getPosition().getRow(), grunt.getPosition().getCol());
        grunt.callActionSFIsNotSeen();
        assertEquals(initial.getCol() - 2, grunt.getPosition().getCol());
        assertFalse(grunt.isDestroyed());
    }

    @Test
    public void actionSFIsSeenMovesAndFiresTest() {
        Position initial = new Position(grunt.getPosition().getRow(), grunt.getPosition().getCol());
        grunt.callActionSFIsSeen();
        assertEquals(initial.getCol() - 4, grunt.getPosition().getCol());
        assertFalse(grunt.isDestroyed());
    }
}
