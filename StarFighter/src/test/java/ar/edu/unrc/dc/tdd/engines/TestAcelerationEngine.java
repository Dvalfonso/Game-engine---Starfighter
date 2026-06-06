package ar.edu.unrc.dc.tdd.engines;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.components.CommonAttributes;
import ar.edu.unrc.dc.models.components.Regen;
import ar.edu.unrc.dc.models.components.Engines.AcelerationEngine;
import ar.edu.unrc.dc.models.components.Engine;

public class TestAcelerationEngine {

    @Test
    public void applyAcelerationEngineShouldUpdateAttributesCorrectly() {
        CommonAttributes attributes = new CommonAttributes();
        attributes.setMove(23);
        attributes.setEnergy(23);
        attributes.setMoveCost(43);
        Regen reg = new Regen(0, 9);
        attributes.setRegen(reg);

        Engine acelerationEngine = new AcelerationEngine();
        acelerationEngine.apply(attributes);

        assertEquals("Aceleration engine", acelerationEngine.getName());
        assertEquals(23 + 6, attributes.getMove());
        assertEquals(43 + 5, attributes.getMoveCost());
        assertEquals(23 + 45, attributes.getEnergy());
        assertEquals(9 + 5, attributes.getRegen().getEnergyRegen());
    }

    @Test
    public void acelerationEngineGettersShouldReturnConfiguredValues() {
        AcelerationEngine engine = new AcelerationEngine();

        assertEquals("Aceleration engine", engine.getName());
        assertEquals(6, engine.getMove());
        assertEquals(5, engine.getCostMove());
        assertEquals(45, engine.getEnergy());
        assertEquals(5, engine.getRegenEnergy().getEnergyRegen());
    }


    @Test
    public void applyingAcelerationEngineTwiceShouldAccumulateEffects() {
        CommonAttributes attributes = new CommonAttributes();
        attributes.setMove(0);
        attributes.setEnergy(0);
        attributes.setMoveCost(0);
        attributes.setRegen(new Regen(0, 0));

        AcelerationEngine engine = new AcelerationEngine();
        engine.apply(attributes);
        engine.apply(attributes);

        assertEquals(6 * 2, attributes.getMove());
        assertEquals(5 * 2, attributes.getMoveCost());
        assertEquals(45 * 2, attributes.getEnergy());
        assertEquals(5 * 2, attributes.getRegen().getEnergyRegen());
    }
}
