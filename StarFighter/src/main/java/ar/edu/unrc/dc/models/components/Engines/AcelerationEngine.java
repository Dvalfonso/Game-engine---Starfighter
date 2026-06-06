package ar.edu.unrc.dc.models.components.Engines;

import ar.edu.unrc.dc.models.components.Engine;
import ar.edu.unrc.dc.models.components.Regen;

public class AcelerationEngine extends Engine {

    public AcelerationEngine() {
        super("Aceleration engine",6, 5,45,new Regen(0, 5));
    }

    public String toString(){
        return "Aceleration engine";
    }
}