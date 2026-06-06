package ar.edu.unrc.dc.models.components.Weapons;

import java.util.*;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.components.Weapon;
import ar.edu.unrc.dc.models.projectileMovement.RightMovementProjectile;

public class Standard extends Weapon {
    private int projectilesDamage;

    public Standard(){
        projectilesDamage = 100;
    }

    @Override
    public List<Projectile> fire(Position startFighterPosition) {
        Position spawnPosition = startFighterPosition.to(0,1);

        Projectile projectile = new FriendlyProjectile(spawnPosition, projectilesDamage);
        projectile.setProjectileMovement(new RightMovementProjectile(spawnPosition, 5));

        return List.of(projectile);
    }


     public String toString(){
        return "Standard";
    }
    
}
