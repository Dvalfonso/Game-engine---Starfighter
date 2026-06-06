package ar.edu.unrc.dc.models.projectileMovement;

import ar.edu.unrc.dc.models.cell.*;

public interface ProjectileMovement {

    public abstract Position nextStep();
    public abstract boolean hasNextStep();
    public abstract void nextTurn();
}
