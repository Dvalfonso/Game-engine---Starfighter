package ar.edu.unrc.dc.tdd.setup;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.components.Armour;
import ar.edu.unrc.dc.models.components.Engine;
import ar.edu.unrc.dc.models.components.Special;
import ar.edu.unrc.dc.models.components.Weapon;
import ar.edu.unrc.dc.models.setup.Setup;


public class SetupTest {
    @Test
    public void testWeaponSetterGetter() {
        Setup setup = new Setup();
        Weapon weapon = EasyMock.mock(Weapon.class);

        setup.setWeapon(weapon);

        assertSame(weapon, setup.getWeapon());
    }

    @Test
    public void testArmourSetterGetter() {
        Setup setup = new Setup();
        Armour armour = EasyMock.mock(Armour.class);

        setup.setArmour(armour);

        assertSame(armour, setup.getArmour());
    }

    @Test
    public void testEngineSetterGetter() {
        Setup setup = new Setup();
        Engine engine = EasyMock.mock(Engine.class);

        setup.setEngine(engine);

        assertSame(engine, setup.getEngine());
    }

    @Test
    public void testSpecialSetterGetter() {
        Setup setup = new Setup();
        Special special = EasyMock.mock(Special.class);

        setup.setSpecial(special);

        assertSame(special, setup.getSpecial());
    }

    @Test
    public void testOverwriteWeapon() {
        Setup setup = new Setup();
        Weapon w1 = EasyMock.mock(Weapon.class);
        Weapon w2 = EasyMock.mock(Weapon.class);

        setup.setWeapon(w1);
        setup.setWeapon(w2);

        assertSame(w2, setup.getWeapon());
    }

    @Test
    public void testOverwriteArmour() {
        Setup setup = new Setup();
        Armour a1 = EasyMock.mock(Armour.class);
        Armour a2 = EasyMock.mock(Armour.class);

        setup.setArmour(a1);
        setup.setArmour(a2);

        assertSame(a2, setup.getArmour());
    }

    @Test
    public void testOverwriteEngine() {
        Setup setup = new Setup();
        Engine e1 = EasyMock.mock(Engine.class);
        Engine e2 = EasyMock.mock(Engine.class);

        setup.setEngine(e1);
        setup.setEngine(e2);

        assertSame(e2, setup.getEngine());
    }

    @Test
    public void testOverwriteSpecial() {
        Setup setup = new Setup();
        Special s1 = EasyMock.mock(Special.class);
        Special s2 = EasyMock.mock(Special.class);

        setup.setSpecial(s1);
        setup.setSpecial(s2);

        assertSame(s2, setup.getSpecial());
    }

    @Test
    public void testNullAssignments() {
        Setup setup = new Setup();

        setup.setWeapon(null);
        setup.setArmour(null);
        setup.setEngine(null);
        setup.setSpecial(null);

        assertNull(setup.getWeapon());
        assertNull(setup.getArmour());
        assertNull(setup.getEngine());
        assertNull(setup.getSpecial());
    }

    @Test
    public void testMixedAssignments() {
        Setup setup = new Setup();

        Weapon weapon = EasyMock.mock(Weapon.class);
        Engine engine = EasyMock.mock(Engine.class);

        setup.setWeapon(weapon);
        setup.setEngine(engine);

        assertSame(weapon, setup.getWeapon());
        assertSame(engine, setup.getEngine());
        assertNull(setup.getArmour());
        assertNull(setup.getSpecial());
    }
}