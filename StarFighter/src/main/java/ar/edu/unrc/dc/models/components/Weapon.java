package ar.edu.unrc.dc.models.components;

import java.util.List;
import ar.edu.unrc.dc.models.cell.Projectile;
import ar.edu.unrc.dc.models.cell.Position;

public abstract class Weapon extends CommonAttributes{

    public abstract List<Projectile> fire(Position starFighterPosition);
    public abstract String toString();
}
