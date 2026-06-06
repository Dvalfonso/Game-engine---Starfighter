package ar.edu.unrc.dc.tdd;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.controllers.*;
import ar.edu.unrc.dc.models.*;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.starfighterMovement.*;

public class StarfighterMovementTest {
    
    @Test
    void validMoveTest(){
        Position start = new Position(2, 2);
        int verticalMove = 1;
        int horizontalMove = 2;
        StarfighterMovement movement = new VerticalHorizontalMovement();
    
        List<Position> path = movement.moveSequence(start, verticalMove, horizontalMove);
    
        assertEquals(Arrays.asList(
            new Position(1, 2),
            new Position(1, 3),
            new Position(1, 4)
        ), path);
    }

    @Test
    void moveExactlyStarfighterMovesTest(){
        StarfighterGameEngine starfighterGameEngine = new StarfighterGameEngine();
        starfighterGameEngine.play(6, 6);

        starfighterGameEngine.getGame().getStarfighter().setPosition(new Position(2, 2));

        int verticalMove = 3;
        int horizontalMove = 0;

        starfighterGameEngine.move(verticalMove, horizontalMove);
        assertEquals(new Position(0,2), starfighterGameEngine.getGame().getStarfighter().getPosition());
    }


    @Test
    void verticalOutOfGridBoundsMoveTest(){
        StarfighterGameEngine game = new StarfighterGameEngine();
        game.play(5, 5);
    
        game.getGame().getStarfighter().setPosition(new Position(2, 2));
    
        int verticalMove = -10;
        int horizontalMove = 0;
    
        assertThrows(IllegalArgumentException.class, () -> {
            game.move(verticalMove, horizontalMove);
        });
    }

    @Test
    void horizontalOutOfGridBoundsMoveTest(){
        StarfighterGameEngine game = new StarfighterGameEngine();
        game.play(5, 5);
    
        game.getGame().getStarfighter().setPosition(new Position(2, 2));
    
        int verticalMove = 0;
        int horizontalMove = -10;
    
        assertThrows(IllegalArgumentException.class, () -> {
            game.move(verticalMove, horizontalMove);
        });
    }
}
