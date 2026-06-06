package ar.edu.unrc.dc.models.components.Armours;

import ar.edu.unrc.dc.models.components.Armour;
import ar.edu.unrc.dc.models.components.CommonAttributes;
import ar.edu.unrc.dc.models.components.Regen;

//High armour that prevents starfighter health regeneration.
public class NoRegenArmour extends Armour{
    
    public NoRegenArmour(){
        super("No Regen Armour", 12);
    }

    @Override
    public void apply(CommonAttributes attributes){
        attributes.setArmour(attributes.getArmour() + getBaseArmour());

        Regen newRegen = attributes.getRegen();
        if(newRegen != null)
            attributes.setRegen(new Regen(0, newRegen.getEnergyRegen()));
        else
            attributes.setRegen(new Regen(10, 10));
    }

    public String toString(){
        return "NoRegen armour";
    }
}
