package ar.edu.unrc.dc.models.starfighterCommand;

import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.cell.Starfighter;
import ar.edu.unrc.dc.models.components.Special;
import ar.edu.unrc.dc.patterns.StarfighterCommand;

public class SpecialCommand implements StarfighterCommand{
    private Game game;

    public SpecialCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        Starfighter starfighter = game.getStarfighter();
        
        starfighter.regen();

        Special special = game.getSetup().getSpecial();
        int specialCost = special.getCost();

        starfighter.decreaseEnergy(specialCost);
        special.apply(game);
    }

	@Override
	public String getName() {
		return "special";
	}
}
