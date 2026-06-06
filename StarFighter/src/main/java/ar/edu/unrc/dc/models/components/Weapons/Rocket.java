package ar.edu.unrc.dc.models.components.Weapons;

import ar.edu.unrc.dc.models.components.Weapon;
import ar.edu.unrc.dc.models.projectileMovement.RightRocketMovement;
import ar.edu.unrc.dc.models.cell.*;
import java.util.*;

public class Rocket extends Weapon {
    private int projectileDamage;

    public Rocket(){
        this.projectileDamage = 150;
    }

    public List<Projectile> fire(Position starFighterPosition){
        Position spawnPostion1 = starFighterPosition.to(-1,-1);
        Position spawnPostion2 = starFighterPosition.to(1,-1);

        Projectile p1 = new FriendlyProjectile(spawnPostion1, projectileDamage); 
        p1.setProjectileMovement(new RightRocketMovement(spawnPostion1));

        Projectile p2 = new FriendlyProjectile(spawnPostion2, projectileDamage); 
        p2.setProjectileMovement(new RightRocketMovement(spawnPostion2));

        return List.of(p1,p2);
    }

    public String toString(){
        return "Rocket";
    }
}
