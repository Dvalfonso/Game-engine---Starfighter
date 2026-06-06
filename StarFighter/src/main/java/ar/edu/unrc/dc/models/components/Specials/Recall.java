package ar.edu.unrc.dc.models.components.Specials;

import ar.edu.unrc.dc.models.cell.Cell;
import ar.edu.unrc.dc.models.cell.Position;
import ar.edu.unrc.dc.models.cell.Starfighter;
import ar.edu.unrc.dc.models.components.Special;
import ar.edu.unrc.dc.models.Game;

/**
 * Teleport the Starfighter to the spawn location of the game.
 */
public class Recall extends Special{
    public Recall(int cost){
        super(cost);
    }

    @Override
    public void apply(Game game){
        Starfighter starfighter = game.getStarfighter();
        
        if(starfighter == null || starfighter.isDead()) return;

        Position spawnPosition = game.getInitialPosition();

        Cell spawnCell = game.getBoard().getCell(spawnPosition);

        if (spawnCell != null && !(spawnCell instanceof Starfighter)) {
            game.getBoard().moveCell(starfighter, spawnPosition);
        }
    }

    public String toString(){
        return "Recall special";
    }
}
