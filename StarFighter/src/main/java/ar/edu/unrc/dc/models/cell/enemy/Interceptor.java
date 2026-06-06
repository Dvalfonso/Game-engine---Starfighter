package ar.edu.unrc.dc.models.cell.enemy;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.patterns.StarfighterCommand;

public class Interceptor extends Enemy {

    public Interceptor(Position position) {
        super(position);
        health = 50;
        regen = 0;
        armour = 0;
        vision = 5;
        currentHealth = 50;
    }

    public Interceptor(Position position, Starfighter starfighter) {
        super(position, starfighter);
        health = 50;
        regen = 0;
        armour = 0;
        vision = 5;
        currentHealth = 50;
    }

    public Interceptor(Position position, Starfighter starfighter, Board board) {
        super(position, starfighter, board);
        health = 50;
        regen = 0;
        armour = 0;
        vision = 5;
        currentHealth = 50;
    }

	@Override
	public boolean preemptiveAction(StarfighterCommand command) {
        if (!((command.getName() == "fire")))
            return false;

        int verDirection = position.verDirectionRelativeTo(starfighter.getPosition());

        while (!position.isSameRow(starfighter.getPosition())) {
            moveVertical(-1 * verDirection);

            if (isDead() || isDestroyed() || board.getCell(position.to(-1 * verDirection,0)) instanceof Enemy)
                break;
        }
        return true;
	}

	@Override
	protected void actionSFIsNotSeen() {
        moveToLeft(3);
	}

	@Override
	protected void actionSFIsSeen() {
        moveToLeft(3);
	}

    @Override
    public String toString() {
        return "Interceptor";
    }
}
