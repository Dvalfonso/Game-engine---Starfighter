package ar.edu.unrc.dc.models.components.Weapons;

import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.components.Weapon;
import ar.edu.unrc.dc.models.projectileMovement.SplitterProjectileMovement;
import java.util.*;

public class Splitter extends Weapon{
    private int projectileDamage;

    public Splitter(){
        this.projectileDamage = 200;
    }  

    public List<Projectile> fire(Position starFighterPosition){
        Position spawnPosition = starFighterPosition.to(0,1);

        Projectile projectile = new FriendlyProjectile(spawnPosition, projectileDamage);
        projectile.setProjectileMovement(new SplitterProjectileMovement(spawnPosition));

        return List.of(projectile);
    }

    public String toString(){
        return "Splitter";
    }
}
