package ar.edu.unrc.dc.tdd.specials;

import static org.easymock.EasyMock.*;

import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.components.Specials.Overcharge;
import ar.edu.unrc.dc.models.cell.Starfighter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OverchargeTest {

    @Test
    public void costIsStoredCorrectlyTest() {
        Overcharge special = new Overcharge(10);

        assertEquals(10, special.getCost());
    }

    @Test
    public void overchargeNullStarfighterTest() {
        Overcharge special = new Overcharge(10);
        Game gameMock = mock(Game.class);
        expect(gameMock.getStarfighter()).andReturn(null);
        replay(gameMock);
            
        assertDoesNotThrow(() -> special.apply(gameMock));
            
        verify(gameMock);
    }

    @Test
    public void applyDoesNothingWhenHealthIsOneTest() {
        Game game = new Game(5, 5);
        Starfighter sf = game.getStarfighter();

        // Deja la vida en 1
        int initialHealth = sf.getCurrentHealth();
        sf.decreaseHealth(initialHealth - 1); 

        int energyBefore = sf.getCurrentEnergy();

        Overcharge special = new Overcharge(10);
        special.apply(game);

        // No debería cambiar nada
        assertEquals(1, sf.getCurrentHealth());
        assertEquals(energyBefore, sf.getCurrentEnergy());
    }

    @Test
    public void applySacrificesHealthAndGainsEnergyTest() {
        Game game = new Game(5, 5);
        Starfighter sf = game.getStarfighter();

        int initialHealth = sf.getCurrentHealth();
        int initialEnergy = sf.getCurrentEnergy();

        Overcharge special = new Overcharge(10);
        special.apply(game);

        // Sacrificio esperado
        int expectedHealthToSacrifice = Math.min(50, initialHealth - 1);
        int expectedEnergyGain = expectedHealthToSacrifice * 2;

        assertEquals(initialHealth - expectedHealthToSacrifice, sf.getCurrentHealth());

        assertEquals(initialEnergy + expectedEnergyGain,sf.getCurrentEnergy());
    }

    @Test
    public void applyDoesNothingWhenStarfighterIsDeadTest() {
        Game game = new Game(5, 5);
        Starfighter sf = game.getStarfighter();

        // Matamos al starfighter
        int initialHealth = sf.getCurrentHealth();
        int initialEnergy = sf.getCurrentEnergy();
        sf.decreaseHealth(initialHealth); 

        assertTrue(sf.isDead());

        Overcharge special = new Overcharge(10);
        special.apply(game);

        // No debería cambiar, sf muerto
        assertEquals(0, sf.getCurrentHealth());
        assertEquals(initialEnergy, sf.getCurrentEnergy());
    }
}
