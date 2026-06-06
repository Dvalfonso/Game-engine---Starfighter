package ar.edu.unrc.dc.tdd.armour;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.components.CommonAttributes;
import ar.edu.unrc.dc.models.components.Regen;
import ar.edu.unrc.dc.models.components.Armour;
import ar.edu.unrc.dc.models.components.Armours.*;

public class ArmourTest {
    
    @Test
    public void noRegenArmourTest() {
        CommonAttributes attrs = new CommonAttributes();
        attrs.setRegen(new Regen(10, 10));
        attrs.setArmour(10);

        Armour armour = new NoRegenArmour();
        
        armour.apply(attrs);
        
        assertEquals(10 + armour.getBaseArmour(), attrs.getArmour());
        assertEquals(0, attrs.getRegen().getHealthRegen());
    }

    @Test
    public void nullRegenTest(){
        CommonAttributes attrs = new CommonAttributes();
        attrs.setRegen(null);

        Armour armour = new NoRegenArmour();
        
        armour.apply(attrs);

        assertEquals(10, attrs.getRegen().getHealthRegen());
        assertEquals(10, attrs.getRegen().getEnergyRegen());
    }

    @Test
    public void heavyArmourTest() {
        CommonAttributes attrs = new CommonAttributes();
        attrs.setArmour(10);
        attrs.setMoveCost(10);

        Armour armour = new HeavyArmour();
        
        armour.apply(attrs);
        
        assertEquals(10 + armour.getBaseArmour(), attrs.getArmour());
        assertEquals(10 + 2, attrs.getMoveCost());
    }

    @Test
    public void blindArmourTest() {
        CommonAttributes attrs = new CommonAttributes();
        attrs.setArmour(10);
        attrs.setVision(10);

        Armour armour = new BlindArmour();
        
        armour.apply(attrs);
        
        assertEquals(10 + armour.getBaseArmour(), attrs.getArmour());
        assertEquals(10 - 3, attrs.getVision());
    }

    @Test
    public void tankArmourTest() {
        CommonAttributes attrs = new CommonAttributes();
        attrs.setHealth(100);
        attrs.setEnergy(100);
        attrs.setRegen(new Regen(10, 10));
        attrs.setArmour(10);
        attrs.setMoveCost(10);

        Armour armour = new TankArmour();
        
        armour.apply(attrs);
        
        assertEquals(10 + armour.getBaseArmour(), attrs.getArmour());
        assertEquals((int) (10 * 0.5), attrs.getRegen().getHealthRegen());
        assertEquals((int) (10 * 0.75), attrs.getRegen().getEnergyRegen());
        assertEquals(10 + 1, attrs.getMoveCost());
        assertEquals((int) (100 * 0.85), attrs.getHealth());
    }

    @Test
    public void tankArmourNullRegenTest() {
        CommonAttributes attrs = new CommonAttributes();
        attrs.setHealth(100);
        attrs.setEnergy(100);
        attrs.setRegen(null);
        attrs.setArmour(10);
        attrs.setMoveCost(10);

        Armour armour = new TankArmour();
        
        armour.apply(attrs);
        
        assertEquals(10 + armour.getBaseArmour(), attrs.getArmour());
        assertEquals(10, attrs.getRegen().getHealthRegen());
        assertEquals(10, attrs.getRegen().getEnergyRegen());
        assertEquals(10 + 1, attrs.getMoveCost());
        assertEquals((int) (100 * 0.85), attrs.getHealth());
    }
}
