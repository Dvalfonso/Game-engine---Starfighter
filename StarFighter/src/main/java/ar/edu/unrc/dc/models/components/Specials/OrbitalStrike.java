package ar.edu.unrc.dc.models.components.Specials;

import java.util.Comparator;
import java.util.List;

import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.cell.EmptySpace;
import ar.edu.unrc.dc.models.cell.Enemy;
import ar.edu.unrc.dc.models.components.Special;

/**
 * All enemies take 100 damage, reduced by their armour value.
 * The order of taking damage is from enemies that spawned earliest to latest.
 */
public class OrbitalStrike extends Special{
    public OrbitalStrike(int cost){
        super(cost);
    }

    @Override
    public void apply(Game game){
        List<Enemy> enemies = game.getEnemies();

        if(enemies == null || enemies.isEmpty()) return;

        enemies.sort(Comparator.comparingInt(Enemy::getID));

        for(Enemy enemy : enemies){
            if(!(enemy.isDead())){
                enemy.hurt(100);

                if(enemy.isDead()){
                    game.getBoard().updateCell(enemy.getPosition(), new EmptySpace());
                }
            }
        }
    }

    public String toString(){
        return "Orbital stricke special";
    }
}
