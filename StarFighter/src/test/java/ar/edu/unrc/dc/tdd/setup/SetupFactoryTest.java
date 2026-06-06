package ar.edu.unrc.dc.tdd.setup;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.components.Armour;
import ar.edu.unrc.dc.models.components.Engine;
import ar.edu.unrc.dc.models.components.Special;
import ar.edu.unrc.dc.models.components.Weapon;
import ar.edu.unrc.dc.models.components.Armours.BlindArmour;
import ar.edu.unrc.dc.models.components.Armours.HeavyArmour;
import ar.edu.unrc.dc.models.components.Armours.NoRegenArmour;
import ar.edu.unrc.dc.models.components.Armours.TankArmour;
import ar.edu.unrc.dc.models.components.Engines.AcelerationEngine;
import ar.edu.unrc.dc.models.components.Engines.BalancedEngine;
import ar.edu.unrc.dc.models.components.Engines.PowerEngine;
import ar.edu.unrc.dc.models.components.Specials.DeployDrones;
import ar.edu.unrc.dc.models.components.Specials.OrbitalStrike;
import ar.edu.unrc.dc.models.components.Specials.Overcharge;
import ar.edu.unrc.dc.models.components.Specials.Recall;
import ar.edu.unrc.dc.models.components.Specials.Repair;
import ar.edu.unrc.dc.models.components.Weapons.Rocket;
import ar.edu.unrc.dc.models.components.Weapons.Snipe;
import ar.edu.unrc.dc.models.components.Weapons.Splitter;
import ar.edu.unrc.dc.models.components.Weapons.Spread;
import ar.edu.unrc.dc.models.components.Weapons.Standard;
import ar.edu.unrc.dc.models.setup.Setup;
import ar.edu.unrc.dc.models.setup.SetupFactory;

public class SetupFactoryTest {
    @Test
    public void testSetArmourOptions() {
        SetupFactory factory = new SetupFactory();

        factory.setArmour(1);
        assertTrue(factory.createSetup().getArmour() instanceof BlindArmour);

        factory = new SetupFactory();
        factory.setArmour(2);
        assertTrue(factory.createSetup().getArmour() instanceof HeavyArmour);

        factory = new SetupFactory();
        factory.setArmour(3);
        assertTrue(factory.createSetup().getArmour() instanceof NoRegenArmour);

        factory = new SetupFactory();
        factory.setArmour(4);
        assertTrue(factory.createSetup().getArmour() instanceof TankArmour);
    }

    @Test
    public void testSetEngineOptions() {
        SetupFactory factory = new SetupFactory();

        factory.setEngine(1);
        assertTrue(factory.createSetup().getEngine() instanceof AcelerationEngine);

        factory = new SetupFactory();
        factory.setEngine(2);
        assertTrue(factory.createSetup().getEngine() instanceof BalancedEngine);

        factory = new SetupFactory();
        factory.setEngine(3);
        assertTrue(factory.createSetup().getEngine() instanceof PowerEngine);
    }

    @Test
    public void testSetSpecialOptions() {
        SetupFactory factory = new SetupFactory();

        factory.setSpecial(1);
        assertTrue(factory.createSetup().getSpecial() instanceof Overcharge);

        factory = new SetupFactory();
        factory.setSpecial(2);
        assertTrue(factory.createSetup().getSpecial() instanceof Recall);

        factory = new SetupFactory();
        factory.setSpecial(3);
        assertTrue(factory.createSetup().getSpecial() instanceof Repair);

        factory = new SetupFactory();
        factory.setSpecial(4);
        assertTrue(factory.createSetup().getSpecial() instanceof DeployDrones);

        factory = new SetupFactory();
        factory.setSpecial(5);
        assertTrue(factory.createSetup().getSpecial() instanceof OrbitalStrike);
    }

    @Test
    public void testSetWeaponOptions() {
        SetupFactory factory = new SetupFactory();

        factory.setWeapon(1);
        assertTrue(factory.createSetup().getWeapon() instanceof Rocket);

        factory = new SetupFactory();
        factory.setWeapon(2);
        assertTrue(factory.createSetup().getWeapon() instanceof Snipe);

        factory = new SetupFactory();
        factory.setWeapon(3);
        assertTrue(factory.createSetup().getWeapon() instanceof Splitter);

        factory = new SetupFactory();
        factory.setWeapon(4);
        assertTrue(factory.createSetup().getWeapon() instanceof Spread);

        factory = new SetupFactory();
        factory.setWeapon(5);
        assertTrue(factory.createSetup().getWeapon() instanceof Standard);
    }

    @Test
    public void testCreateSetupDefaults() {
        SetupFactory factory = new SetupFactory();

        Setup setup = factory.createSetup();

        assertTrue(setup.getWeapon() instanceof Rocket);
        assertTrue(setup.getArmour() instanceof BlindArmour);
        assertTrue(setup.getEngine() instanceof AcelerationEngine);
        assertTrue(setup.getSpecial() instanceof Overcharge);
    }

    @Test
    public void testCreateSetupWithSelectedValues() {
        SetupFactory factory = new SetupFactory();

        factory.setWeapon(2);  // Snipe
        factory.setArmour(3);  // NoRegen
        factory.setEngine(2);  // Balanced
        factory.setSpecial(5); // OrbitalStrike

        Setup setup = factory.createSetup();

        assertTrue(setup.getWeapon() instanceof Snipe);
        assertTrue(setup.getArmour() instanceof NoRegenArmour);
        assertTrue(setup.getEngine() instanceof BalancedEngine);
        assertTrue(setup.getSpecial() instanceof OrbitalStrike);
    }

    @Test
    public void testCreateSetupDoesNotOverwrite() {
        SetupFactory factory = new SetupFactory();

        Weapon w = EasyMock.mock(Weapon.class);
        Armour a = EasyMock.mock(Armour.class);
        Engine e = EasyMock.mock(Engine.class);
        Special s = EasyMock.mock(Special.class);

        // Set factory internal fields manually (simulate scenario futuro)
        factory.setWeapon(1);
        factory.setArmour(1);
        factory.setEngine(1);
        factory.setSpecial(1);

        Setup setup = factory.createSetup();

        assertNotNull(setup.getWeapon());
        assertNotNull(setup.getArmour());
        assertNotNull(setup.getEngine());
        assertNotNull(setup.getSpecial());
    }
}