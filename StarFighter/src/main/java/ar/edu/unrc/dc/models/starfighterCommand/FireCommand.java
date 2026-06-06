package ar.edu.unrc.dc.models.starfighterCommand;

import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.patterns.StarfighterCommand;

public class FireCommand implements StarfighterCommand{
    private Game game;

    public FireCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        Starfighter starfighter = game.getStarfighter();
        starfighter.regen();
        game.fireWeapon();
    }


	@Override
	public String getName() {
		return "fire";
	}
}
