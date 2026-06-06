package ar.edu.unrc.dc.tdd.specials;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.easymock.EasyMock.*;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.cell.enemy.*;
import ar.edu.unrc.dc.models.components.Specials.Recall;

public class RecallTest {
    private Game game;
    private Board board;
    private Starfighter starfighter;
    private Recall recall;

    @BeforeEach
    public void setup(){
        game = new Game(6, 10);
        board = game.getBoard();
        starfighter = game.getStarfighter();
        recall = new Recall(20);
    }

    @Test
    public void doesNothingWhenStarfighterIsNullTest(){
        Game gameMock = mock(Game.class);
        
        expect(gameMock.getStarfighter()).andReturn(null);

        replay(gameMock);

        assertDoesNotThrow(() -> recall.apply(gameMock));
        verify(gameMock);
    }

    @Test
    public void doesNothingWhenStarfighterIsDeadTest(){
        starfighter.hurt(9999);

        Position spawn = game.getInitialPosition();
        Cell before = board.getCell(spawn);

        recall.apply(game);

        assertEquals(before, board.getCell(spawn));
    }

    @Test
    public void doesNothingWhenSpawnCellIsNullTest(){
        Game gameMock = mock(Game.class);
        Board boardMock = mock(Board.class);

        expect(gameMock.getStarfighter()).andReturn(starfighter);
        expect(gameMock.getBoard()).andReturn(boardMock);
        expect(gameMock.getInitialPosition()).andReturn(new Position(3, 0));
        expect(boardMock.getCell(anyObject(Position.class))).andReturn(null);

        replay(gameMock, boardMock);

        assertDoesNotThrow(() -> recall.apply(gameMock));
        verify(gameMock, boardMock);
    }

    @Test
    public void doesNothingWhenStarfighterAlreadyAtSpawn() {
        Position spawn = game.getInitialPosition();

        recall.apply(game);

        assertEquals(starfighter, board.getCell(spawn));
        assertEquals(spawn, starfighter.getPosition());
    }


    @Test
    public void teleportsStarfighterTest(){
        Position spawn = game.getInitialPosition();
        
        game.moveStarfighter(1, 1);
        board.updateCell(spawn, new EmptySpace());
        recall.apply(game);
        
        assertEquals(starfighter, board.getCell(spawn));
        assertEquals(spawn, starfighter.getPosition());
    }

    @Test
    public void teleportCollidesButStarfighterSurvivesTest() {
        Position spawn = game.getInitialPosition();

        game.moveStarfighter(1, 1);
        Enemy dummy = new Interceptor(new Position(3, 0), starfighter);
        board.updateCell(spawn, dummy);
        recall.apply(game);

        assertEquals(starfighter, board.getCell(spawn));
        assertEquals(spawn, starfighter.getPosition());
        assertEquals(50, starfighter.getCurrentHealth());
    }

    @Test
    public void teleportCollidesAndStarfighterDiesTest() {
        Position spawn = game.getInitialPosition();

        game.moveStarfighter(1, 1);
        Enemy dummy = new Fighter(new Position(3, 0), starfighter);
        board.updateCell(spawn, dummy);
        recall.apply(game);

        assertFalse(board.getCell(spawn) instanceof Starfighter);
        assertTrue(board.getCell(spawn) instanceof SFDestroyed);
    }
}
