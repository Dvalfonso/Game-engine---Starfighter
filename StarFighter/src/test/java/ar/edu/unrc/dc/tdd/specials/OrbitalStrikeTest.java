package ar.edu.unrc.dc.tdd.specials;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.cell.enemy.*;
import ar.edu.unrc.dc.models.components.Specials.OrbitalStrike;

public class OrbitalStrikeTest {
    private Game game;
    private Board board;
    private Starfighter starfighter;
    private OrbitalStrike orbitalStrike;

    @BeforeEach
    public void setup(){
        game = new Game(6, 10);
        board = game.getBoard();
        starfighter = game.getStarfighter();
        orbitalStrike = new OrbitalStrike(20);
    }

    @Test
    public void orbitalStrikeWithNoEnemiesTest() {
        orbitalStrike.apply(game);
    
        assertTrue(game.getEnemies().isEmpty());
    }

    @Test
    public void orbitalStrikeWithNullEnemiesDoesNothingTest() {
        Game gameMock = mock(Game.class);
        expect(gameMock.getEnemies()).andReturn(null);
        replay(gameMock);
            
        assertDoesNotThrow(() -> orbitalStrike.apply(gameMock));
            
        verify(gameMock);
    }

    @Test
    public void orbitalStrikeDealsDamageInOrderTest() {
        Enemy grunt = new Grunt(new Position(2, 2), starfighter, null);
        Enemy fighter = new Fighter(new Position(3, 3), starfighter);

        game.getEnemies().add(grunt);
        game.getEnemies().add(fighter);

        orbitalStrike.apply(game);

        assertEquals(55, fighter.getCurrentHealth());
        assertEquals(1, grunt.getCurrentHealth());
        assertFalse(fighter.isDead());
        assertFalse(grunt.isDead());
    }

    @Test
    public void orbitalStrikeKillsEnemyAndCleansCellTest() {
        Enemy interceptor = new Interceptor(new Position(2,2), starfighter);

        game.getEnemies().add(interceptor);

        orbitalStrike.apply(game);

        assertTrue(interceptor.isDead());
        assertTrue(board.getCell(interceptor.getPosition()) instanceof EmptySpace);
    }

    @Test
    public void orbitalStrikeDoesNothingToAlreadyDeadEnemyTest() {
        Enemy grunt = new Grunt(new Position(2, 2), starfighter, null);

        game.getEnemies().add(grunt);

        grunt.hurt(1000);
        assertTrue(grunt.isDead());

        board.updateCell(grunt.getPosition(), grunt);

        orbitalStrike.apply(game);

        assertTrue(grunt.isDead());
        assertFalse(board.getCell(grunt.getPosition()) instanceof EmptySpace);
    }
}
