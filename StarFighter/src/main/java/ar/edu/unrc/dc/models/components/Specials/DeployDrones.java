package ar.edu.unrc.dc.models.components.Specials;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.cell.Cell;
import ar.edu.unrc.dc.models.cell.EmptySpace;
import ar.edu.unrc.dc.models.cell.Position;
import ar.edu.unrc.dc.models.cell.Projectile;
import ar.edu.unrc.dc.models.components.Special;

/**
 * Clears out all the projectiles on the board. The order of removal is from
 * projectiles that spawned earliest to latest.
 */
public class DeployDrones extends Special{
    public DeployDrones(int cost){
        super(cost);
    }

    @Override
    public void apply(Game game){
        game.clearProjectiles();
    }

    public String toString(){
        return "Deploy drones special";
    }
}
