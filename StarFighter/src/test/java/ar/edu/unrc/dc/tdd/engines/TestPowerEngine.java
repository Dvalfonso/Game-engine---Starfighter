package ar.edu.unrc.dc.tdd.engines;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.components.CommonAttributes;
import ar.edu.unrc.dc.models.components.Regen;
import ar.edu.unrc.dc.models.components.Engine;
import ar.edu.unrc.dc.models.components.Engines.PowerEngine;

public class TestPowerEngine {

    @Test
    public void applyPowerEngineShouldUpdateAttributesCorrectly() {
        CommonAttributes attributes = new CommonAttributes();
        attributes.setMove(1);
        attributes.setMoveCost(6);
        attributes.setEnergy(10);
        Regen reg = new Regen(0, 3);
        attributes.setRegen(reg);

        Engine powerEngine = new PowerEngine();
        powerEngine.apply(attributes);

        assertEquals("Power engine", powerEngine.getName());
        assertEquals(1 + 3, attributes.getMove());
        assertEquals(6 + 3, attributes.getMoveCost());
        assertEquals(10 + 80, attributes.getEnergy());
        assertEquals(3 + 10, attributes.getRegen().getEnergyRegen());
    }

    @Test
    public void powerEngineGettersShouldReturnConfiguredValues() {
        PowerEngine engine = new PowerEngine();

        assertEquals("Power engine", engine.getName());
        assertEquals(3, engine.getMove());
        assertEquals(3, engine.getCostMove());
        assertEquals(80, engine.getEnergy());
        assertEquals(10, engine.getRegenEnergy().getEnergyRegen());
    }

    @Test
    public void applyingPowerEngineTwiceShouldAccumulateEffects() {
        CommonAttributes attributes = new CommonAttributes();
        attributes.setMove(0);
        attributes.setMoveCost(0);
        attributes.setEnergy(0);
        attributes.setRegen(new Regen(0, 0));

        PowerEngine engine = new PowerEngine();
        engine.apply(attributes);
        engine.apply(attributes);

        assertEquals(3 * 2, attributes.getMove());
        assertEquals(3 * 2, attributes.getMoveCost());
        assertEquals(80 * 2, attributes.getEnergy());
        assertEquals(10 * 2, attributes.getRegen().getEnergyRegen());
    }
}
