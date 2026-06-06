package ar.edu.unrc.dc.tdd.regen;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.components.Regen;
import ar.edu.unrc.dc.models.cell.Starfighter;

public class RegenTest {
    @Test
    public void constructorTest(){
        Starfighter starfighter = new Starfighter(null);
        Regen regen = starfighter.getRegen();
        assertEquals(10, regen.getHealthRegen());
        assertEquals(10, regen.getEnergyRegen());
    }

    @Test
    public void regenStopsAtMaxHealth(){
        Starfighter starfighter = new Starfighter(null);
        
        starfighter.hurt(5);
        starfighter.regen();

        assertEquals(starfighter.getMaxHealth(), starfighter.getCurrentHealth());
    }

    @Test
    public void regenTest(){
        Starfighter starfighter = new Starfighter(null);

        starfighter.hurt(30);
        starfighter.regen();

        assertEquals(starfighter.getMaxHealth() - 10, starfighter.getCurrentHealth());
    }
}
