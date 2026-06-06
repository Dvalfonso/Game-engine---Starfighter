package ar.edu.unrc.dc.models.components.Armours;

import ar.edu.unrc.dc.models.components.Armour;
import ar.edu.unrc.dc.models.components.CommonAttributes;

//Medium armour that reduces starfighter vision.
public class BlindArmour extends Armour{
    
    public BlindArmour(){
        super("Blind Armour", 6);
    }

    @Override
    public void apply(CommonAttributes attributes){
        attributes.setArmour(attributes.getArmour() + getBaseArmour());
        attributes.setVision(Math.max(1, attributes.getVision() - 3));
    }

    public String toString(){
        return "Blind armour";
    }
}