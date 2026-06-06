package ar.edu.unrc.dc.tdd.enemy;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.cell.Position;
import ar.edu.unrc.dc.models.cell.Starfighter;
import ar.edu.unrc.dc.models.cell.enemy.Interceptor;
import ar.edu.unrc.dc.models.starfighterCommand.FireCommand;
import ar.edu.unrc.dc.patterns.StarfighterCommand;

public class InterceptorTest {
    private Game game;
    private Board board;
    private Starfighter starfighter;
    private InterceptorTestHelper interceptor;

    //For testing protected methods
    static class InterceptorTestHelper extends Interceptor {
        public InterceptorTestHelper(Position position, Starfighter starfighter, Board board) {
            super(position, starfighter, board);
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
        interceptor = new InterceptorTestHelper(new Position(5, 7), starfighter, board);
    }

    @Test
    public void constructorTest() {
        assertEquals(50, interceptor.getCurrentHealth());
        assertEquals(0, interceptor.getRegen());
        assertEquals(0, interceptor.getArmour());
        assertEquals(5, interceptor.getVision());
        assertEquals(50, interceptor.getCurrentHealth());
    }

    @Test
    public void preemptiveActionWithFireCommandMovesVerticallyTest() {
        StarfighterCommand fireCommand = new StarfighterCommand() {
            @Override
            public void execute() {}
            @Override
            public String getName() { return "fire"; }
        };

        boolean result = interceptor.preemptiveAction(fireCommand);

        assertTrue(result);
        assertTrue(interceptor.getPosition().getRow() < 7);
    }

    @Test
    public void preemptiveActionWithOtherCommandDoesNothingTest() {
        StarfighterCommand shieldCommand = new StarfighterCommand() {
            @Override
            public void execute() {}
            @Override
            public String getName() { return "shield"; }
        };

        boolean result = interceptor.preemptiveAction(shieldCommand);

        assertFalse(result);
        assertEquals(5, interceptor.getPosition().getRow());
    }

    @Test
    public void actionSFIsNotSeenMovesLeftTest() {
        Position initial = new Position(interceptor.getPosition().getRow(), interceptor.getPosition().getCol());
        interceptor.callActionSFIsNotSeen();
        assertEquals(initial.getCol() - 3, interceptor.getPosition().getCol());
    }

    @Test
    public void actionSFIsSeenMovesLeftTest() {
        Position initial = new Position(interceptor.getPosition().getRow(), interceptor.getPosition().getCol());
        interceptor.callActionSFIsSeen();
        assertEquals(initial.getCol() - 3, interceptor.getPosition().getCol());
    }

    @Test
    public void testPreemptiveActionStopsIfAlreadySameRow() {
        Interceptor i = new Interceptor(new Position(2, 7), starfighter, board);
        StarfighterCommand fireCommand = new StarfighterCommand() {
            @Override
            public void execute() {}
            @Override
            public String getName() { return "fire"; }
        };

        boolean result = i.preemptiveAction(fireCommand);

        assertTrue(result);
        assertEquals(2, i.getPosition().getRow());
    }
}
