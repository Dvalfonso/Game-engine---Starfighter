package ar.edu.unrc.dc.models.components.Armours;

import ar.edu.unrc.dc.models.components.Armour;
import ar.edu.unrc.dc.models.components.CommonAttributes;
import ar.edu.unrc.dc.models.components.Regen;

//Very high armour that reduces starfighter health, health and energy regeneration and increments starfighter movement cost.
public class TankArmour extends Armour{
    
    public TankArmour(){
        super("Tank Armour", 20);
    }

    @Override
    public void apply(CommonAttributes attributes){
        attributes.setArmour(attributes.getArmour() + getBaseArmour());
        attributes.setHealth((int) (attributes.getHealth() * 0.85));
        attributes.setMoveCost(attributes.getMoveCost() + 1);
        Regen oldRegen = attributes.getRegen();

        if(oldRegen != null)
            attributes.setRegen(new Regen(
                Math.max(0, (int) (oldRegen.getHealthRegen() * 0.5)),
                Math.max(0, (int) (oldRegen.getEnergyRegen() * 0.75))
            ));
        else
            attributes.setRegen(new Regen(10, 10));
        
    }

    public String toString(){
        return "Tank armour";
    }
}
