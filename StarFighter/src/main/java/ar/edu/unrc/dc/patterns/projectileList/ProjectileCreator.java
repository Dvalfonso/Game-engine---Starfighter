package ar.edu.unrc.dc.patterns.projectileList;

import ar.edu.unrc.dc.models.cell.Projectile;

public interface ProjectileCreator {

    public void fire(Projectile projectile);
    public void setList(ProjectileList projectileList);
}
