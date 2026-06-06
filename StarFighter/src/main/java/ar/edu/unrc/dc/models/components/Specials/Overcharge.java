package ar.edu.unrc.dc.models.components.Specials;

import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.cell.Starfighter;
import ar.edu.unrc.dc.models.components.Special;

/**
 * Sacrifices current health for gaining current energy.
 * It will use up to 50 current health as long as the Starfighter is not destroyed.
 * The conversion is: 1 current health for 2 current energy.
 */
public class Overcharge extends Special{
    public Overcharge(int cost){
        super(cost);
    }

    @Override
    public void apply(Game game){
        Starfighter starfighter = game.getStarfighter();

        if(starfighter == null || starfighter.isDead()) return;

        int currentHealth = starfighter.getCurrentHealth();

        if(currentHealth <= 1) return;

        int healthToSacrifice = Math.min(50, currentHealth - 1);

        int energyGained = healthToSacrifice * 2;

        starfighter.decreaseHealth(healthToSacrifice);
        starfighter.overenergize(energyGained);
    }

    public String toString(){
        return "Overcharge special";
    }
}
