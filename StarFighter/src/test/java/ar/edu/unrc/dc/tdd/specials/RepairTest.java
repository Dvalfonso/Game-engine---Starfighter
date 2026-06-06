package ar.edu.unrc.dc.tdd.specials;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.cell.Starfighter;
import ar.edu.unrc.dc.models.components.Specials.Repair;

public class RepairTest{

    private Game game;
    private Starfighter starfighter;
    private Repair repair;

    @BeforeEach
    public void setup(){
        game = new Game(6, 10);
        starfighter = game.getStarfighter();
        repair = new Repair(10);
    }

    @Test
    public void doesNothingWhenStarfighterIsNullTest(){
        starfighter = null;
        game.setStarfighter(starfighter);

        assertDoesNotThrow(() -> repair.apply(game));
    }

    @Test
    public void doesNothingWhenStarfighterIsDeadTest(){
        starfighter.hurt(9999);

        assertTrue(starfighter.isDead());

        repair.apply(game);

        assertEquals(0, starfighter.getCurrentHealth());
        assertDoesNotThrow(() -> repair.apply(game));
    }

    @Test
    public void healsStarfighterBy50Test(){
        repair.apply(game);

        assertEquals(150, starfighter.getCurrentHealth());
    }
}