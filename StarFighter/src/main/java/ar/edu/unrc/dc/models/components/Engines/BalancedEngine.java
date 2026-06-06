package ar.edu.unrc.dc.models.components.Engines;

import ar.edu.unrc.dc.models.components.Engine;
import ar.edu.unrc.dc.models.components.Regen;

public class BalancedEngine extends Engine {

    public BalancedEngine() {
        super("Balanced Engine",4, 5,60,new Regen(0, 7));
    }

    public String toString(){
        return "Balanced engine";
    }
}
