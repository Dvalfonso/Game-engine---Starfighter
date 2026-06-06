package ar.edu.unrc.dc.models.components.Engines;

import ar.edu.unrc.dc.models.components.Engine;
import ar.edu.unrc.dc.models.components.Regen;

public class PowerEngine extends Engine {
    

    public PowerEngine() {
        super("Power engine",3, 3,80,new Regen(0, 10));
    }

    public String toString(){
        return "Power engine";
    }
}
