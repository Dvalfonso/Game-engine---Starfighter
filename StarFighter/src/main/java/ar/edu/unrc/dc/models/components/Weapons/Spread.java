package ar.edu.unrc.dc.models.components.Weapons;

import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.components.Weapon;
import ar.edu.unrc.dc.models.projectileMovement.*;

import java.util.*;
/**
 * Spread Projectiles are three, top-right,bot-right and midle, top and 
 * bot travels on diagonal path, midle travels like Standard.
 */
public class Spread extends Weapon {
    private int projectilesDamage;

    public Spread() {
        projectilesDamage = 75;
    }

    public  List<Projectile> fire(Position starfighterPosition){
        
        Position topPos = starfighterPosition.to(1,1);
        Position midPos = starfighterPosition.to(0,1);
        Position botPos = starfighterPosition.to(-1,1);

        Projectile top = new FriendlyProjectile(topPos, projectilesDamage);
        top.setProjectileMovement(new RightTopMovementProjectile(topPos));

        Projectile midle = new FriendlyProjectile(midPos, projectilesDamage);
        midle.setProjectileMovement(new RightMovementProjectile(midPos,1));

        Projectile bottom = new FriendlyProjectile(botPos, projectilesDamage);
        bottom.setProjectileMovement(new RightBotMovementProjectile(botPos));

        return List.of(top,midle,bottom);
    };

    public String toString(){
        return "Spread";
    }
}
