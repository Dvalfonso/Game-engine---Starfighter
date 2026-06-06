package ar.edu.unrc.dc.models.components.Specials;

import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.cell.Starfighter;
import ar.edu.unrc.dc.models.components.Special;

/**
 * Increase current health by 50 units. 
 * This is the only case that current health may exceed the total health for a Starfighter.
 */
public class Repair extends Special{
    public Repair(int cost){
        super(cost);
    }

    @Override
    public void apply(Game game){
        Starfighter starfighter = game.getStarfighter();

        if(starfighter == null || starfighter.isDead()) return;

        starfighter.overheal(50);
    }

    public String toString(){
        return "Repair special";
    }
}
