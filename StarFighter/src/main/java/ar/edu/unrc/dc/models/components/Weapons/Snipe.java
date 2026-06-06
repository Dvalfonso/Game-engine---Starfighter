package ar.edu.unrc.dc.models.components.Weapons;

import ar.edu.unrc.dc.models.components.Weapon;
import ar.edu.unrc.dc.models.projectileMovement.SnipeRightProjectileMovement;
import ar.edu.unrc.dc.models.cell.*;
import java.util.*;

public class Snipe extends Weapon{
    private int projectileDamage;

    public Snipe(){
        this.projectileDamage = 1000;
    }

    public List<Projectile> fire(Position starFighterPosition){
        Position spawnPos = starFighterPosition.to(0,1);

        Projectile projectile = new FriendlyProjectile(spawnPos,projectileDamage);
        projectile.setProjectileMovement(new SnipeRightProjectileMovement(spawnPos));

        return List.of(projectile);
    }

    public String toString(){
        return "Snipe";
    }
}
