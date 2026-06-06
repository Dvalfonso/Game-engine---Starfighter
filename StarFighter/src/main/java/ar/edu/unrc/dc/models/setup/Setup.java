package ar.edu.unrc.dc.models.setup;

import ar.edu.unrc.dc.models.components.Armour;
import ar.edu.unrc.dc.models.components.Engine;
import ar.edu.unrc.dc.models.components.Special;
import ar.edu.unrc.dc.models.components.Weapon;

public class Setup {
    private Weapon weapon;
    private Armour armour;
    private Engine engine;
    private Special special;

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armour getArmour() {
        return armour;
    }

    public void setArmour(Armour armour) {
        this.armour = armour;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Special getSpecial() {
        return special;
    }

    public void setSpecial(Special special) {
        this.special = special;
    }
}
