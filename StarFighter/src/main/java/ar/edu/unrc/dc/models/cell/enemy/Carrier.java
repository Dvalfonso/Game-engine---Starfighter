package ar.edu.unrc.dc.models.cell.enemy;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.patterns.StarfighterCommand;

public class Carrier extends Enemy {

    public Carrier(Position position) {
        super(position);
        health = 200;
        regen = 10;
        armour = 15;
        vision = 15;
        currentHealth = 200;
    }

    public Carrier(Position position, Starfighter starfighter) {
        super(position, starfighter);
        health = 200;
        regen = 10;
        armour = 15;
        vision = 15;
        currentHealth = 200;
    }

    public Carrier(Position position, Starfighter starfighter, Board board) {
        super(position, starfighter, board);
        health = 200;
        regen = 10;
        armour = 15;
        vision = 15;
        currentHealth = 200;
    }

	@Override
	public boolean preemptiveAction(StarfighterCommand command) {
        boolean turnEnd = false;

        switch (command.getName()) {
            case "special":
                regen += 10;
                turnEnd = false;
                break;
            case "pass":
                moveToLeft(2);
                if (isDestroyed())
                    return true;
                manager.spawInterceptor(position.to(1,0));
                manager.spawInterceptor(position.to(-1,0));
                turnEnd = true;
                break;
        }
        return turnEnd;
	}

	@Override
	protected void actionSFIsNotSeen() {
        moveToLeft(2);
	}

	@Override
	protected void actionSFIsSeen() {
        moveToLeft(1);
        if (!isDestroyed())
            manager.spawInterceptor(position.to(-1,0));
	}

    @Override
    public String toString() {
        return "Carrier";
    }
}
