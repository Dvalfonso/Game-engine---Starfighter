package ar.edu.unrc.dc.models.setup;

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
import ar.edu.unrc.dc.models.components.Weapons.Standard;
import ar.edu.unrc.dc.models.components.Weapons.Rocket;
import ar.edu.unrc.dc.models.components.Weapons.Snipe;
import ar.edu.unrc.dc.models.components.Weapons.Splitter;
import ar.edu.unrc.dc.models.components.Weapons.Spread;

public class SetupFactory {
    private Setup setup;
    private Armour armour;
    private Engine engine;
    private Special special;
    private Weapon weapon;

    public SetupFactory() {
        this.setup = new Setup();
    }

    public void setArmour(int n) {
        if (n == 1) armour = new BlindArmour();
        if (n == 2) armour = new HeavyArmour();
        if (n == 3) armour = new NoRegenArmour();
        if (n == 4) armour = new TankArmour();
    }

    public void setEngine(int n) {
        if (n == 1) engine = new AcelerationEngine();
        if (n == 2) engine = new BalancedEngine();
        if (n == 3) engine = new PowerEngine();
    }

    public void setSpecial(int n) {
        if (n == 1) special = new Overcharge(10);
        if (n == 2) special = new Recall(10);
        if (n == 3) special = new Repair(10);
        if (n == 4) special = new DeployDrones(10);
        if (n == 5) special = new OrbitalStrike(10);
    }

    public void setWeapon(int n) {
        if (n == 1) weapon = new Rocket();
        if (n == 2) weapon = new Snipe();
        if (n == 3) weapon = new Splitter();
        if (n == 4) weapon = new Spread();
        if (n == 5) weapon = new Standard();

    }

    public Setup createSetup() {
        if (weapon == null) this.setWeapon(1);
        if (armour == null) this.setArmour(1);
        if (engine == null) this.setEngine(1);
        if (special == null) this.setSpecial(1);

        // Asignar los valores al setup
        setup.setWeapon(weapon);
        setup.setArmour(armour);
        setup.setEngine(engine);
        setup.setSpecial(special);

        return setup;
    }
}
