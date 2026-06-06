package ar.edu.unrc.dc.tdd.engines;

import ar.edu.unrc.dc.models.components.CommonAttributes;
import ar.edu.unrc.dc.models.components.Engines.BalancedEngine;
import ar.edu.unrc.dc.models.components.Regen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBalancedEngine {

    @Test
    public void applyBalancedEngineShouldUpdateAttributesCorrectly() {
        CommonAttributes attributes = new CommonAttributes();
        attributes.setMove(10);
        attributes.setMoveCost(2);
        attributes.setEnergy(100);

        Regen reg = new Regen(0, 3);
        attributes.setRegen(reg);

        BalancedEngine balancedEngine = new BalancedEngine();

        balancedEngine.apply(attributes);

        assertEquals("Balanced Engine", balancedEngine.getName());
        assertEquals(10 + 4, attributes.getMove());
        assertEquals(2 + 5, attributes.getMoveCost());
        assertEquals(100 + 60, attributes.getEnergy());
        assertEquals(0, attributes.getRegen().getHealthRegen());
        assertEquals(3 + 7, attributes.getRegen().getEnergyRegen());
    }

    @Test
    public void balancedEngineGettersShouldReturnConfiguredValues() {
        BalancedEngine engine = new BalancedEngine();

        assertEquals("Balanced Engine", engine.getName());
        assertEquals(4, engine.getMove());
        assertEquals(5, engine.getCostMove());
        assertEquals(60, engine.getEnergy());
        assertEquals(7, engine.getRegenEnergy().getEnergyRegen());
    }

    @Test
    public void applyingBalancedEngineTwiceShouldAccumulateEffects() {
        CommonAttributes attributes = new CommonAttributes();
        attributes.setMove(0);
        attributes.setMoveCost(0);
        attributes.setEnergy(0);
        attributes.setRegen(new Regen(0, 0));

        BalancedEngine engine = new BalancedEngine();
        engine.apply(attributes);
        engine.apply(attributes);

        assertEquals(4 * 2, attributes.getMove());
        assertEquals(5 * 2, attributes.getMoveCost());
        assertEquals(60 * 2, attributes.getEnergy());
        assertEquals(7 * 2, attributes.getRegen().getEnergyRegen());
    }
}
