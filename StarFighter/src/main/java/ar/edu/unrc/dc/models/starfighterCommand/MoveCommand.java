package ar.edu.unrc.dc.models.starfighterCommand;

import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.patterns.StarfighterCommand;

public class MoveCommand implements StarfighterCommand{
    private Game game;
    private int vertical;
    private int horizontal;

    public MoveCommand(Game game, int vertical, int horizontal) {
        this.game = game;
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

	@Override
	public void execute() {
        Starfighter starfighter = game.getStarfighter();
        starfighter.regen();
        
        Position start = starfighter.getPosition();
        Position end = new Position(vertical, horizontal);
        
        int distance = start.manhattanDistance(end);

        int cost = distance * starfighter.getMoveCost();

        starfighter.decreaseEnergy(cost);
        game.moveStarfighter(vertical, horizontal);
	}

	@Override
	public String getName() {
        return "move";
	}
}
