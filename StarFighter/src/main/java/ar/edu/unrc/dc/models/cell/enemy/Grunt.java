package ar.edu.unrc.dc.models.cell.enemy;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.patterns.StarfighterCommand;
import ar.edu.unrc.dc.patterns.projectileList.ProjectileList;

public class Grunt extends Enemy {

    public Grunt(Position position) {
        super(position);
        health = 100;
        regen = 1;
        armour = 1;
        vision = 5;
        currentHealth = 100;
    }

    public Grunt(Position position, Starfighter starfighter, ProjectileList projectileList) {
        super(position, starfighter);
        this.projectileList = projectileList;
        health = 100;
        regen = 1;
        armour = 1;
        vision = 5;
        currentHealth = 100;
    }

    public Grunt(Position position, Starfighter starfighter, Board board, ProjectileList projectileList) {
        super(position, starfighter, board);
        this.projectileList = projectileList;
        health = 100;
        regen = 1;
        armour = 1;
        vision = 5;
        currentHealth = 100;
    }

	@Override
	public boolean preemptiveAction(StarfighterCommand command) {
        switch (command.getName()) {
            case "pass":
                health += 10;
                currentHealth += 10;
                break;
            case "special":
                health += 20;
                currentHealth += 20;
                break;
            default:
                break;
        }
        return false;
	}

	@Override
	protected void actionSFIsNotSeen() {
        moveToLeft(2);
        if (!isDestroyed()) {
            fire(new EnemyProjectile(position.to(0, -1),4,15));
        }
    }

	@Override
	protected void actionSFIsSeen() {
        moveToLeft(4);
        if (!isDestroyed()) {
            fire(new EnemyProjectile(position.to(0, -1),4,15));
        }
	}

    @Override
    public String toString() {
        return "Grunt";
    }
}
