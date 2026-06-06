package ar.edu.unrc.dc.models.components.Armours;

import ar.edu.unrc.dc.models.components.Armour;
import ar.edu.unrc.dc.models.components.CommonAttributes;

//High armour that increments starfighter movement cost.
public class HeavyArmour extends Armour{
    
    public HeavyArmour(){
        super("Heavy armour", 12);
    }

    @Override
    public void apply(CommonAttributes attributes){
        attributes.setArmour(attributes.getArmour() + getBaseArmour());
        attributes.setMoveCost(attributes.getMoveCost() + 2);
    }

    public String toString(){
        return "Heavy armour";
    }
}
