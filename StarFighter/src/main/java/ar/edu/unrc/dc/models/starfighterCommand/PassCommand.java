package ar.edu.unrc.dc.models.starfighterCommand;

import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.cell.Starfighter;
import ar.edu.unrc.dc.patterns.StarfighterCommand;

public class PassCommand implements StarfighterCommand {
	private Game game;
	
	public PassCommand(Game game){
		this.game = game;
	}

	@Override
	public void execute() {
		Starfighter starfighter = game.getStarfighter();
		starfighter.regen();
		starfighter.regen();
	}

	@Override
	public String getName() {
        return "pass";
	}
}
