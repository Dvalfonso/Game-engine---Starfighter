package ar.edu.unrc.dc.models.cell.enemy;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.ProjectileLinkedList;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.patterns.StarfighterCommand;
import ar.edu.unrc.dc.patterns.projectileList.ProjectileList;

public class Fighter extends Enemy {

    public Fighter(Position position) {
        super(position);
        health = 150;
        regen = 5;
        armour = 5;
        vision = 10;
        currentHealth = 150;
    }

    public Fighter(Position position, Starfighter starfighter) {
        super(position, starfighter);
        this.projectileList = new ProjectileLinkedList();
        health = 150;
        regen = 5;
        armour = 5;
        vision = 10;
        currentHealth = 150;
    }

    public Fighter(Position position, Starfighter starfighter, ProjectileList projectileList) {
        super(position, starfighter);
        this.projectileList = projectileList;
        health = 150;
        regen = 5;
        armour = 5;
        vision = 10;
        currentHealth = 150;
    }

    public Fighter(Position position, Starfighter starfighter, Board board, ProjectileList projectileList) {
        super(position, starfighter, board);
        this.projectileList = projectileList;
        health = 150;
        regen = 5;
        armour = 5;
        vision = 10;
        currentHealth = 150;
    }

	@Override
	public boolean preemptiveAction(StarfighterCommand command) {
        boolean turnEnd = false;

        switch (command.getName()) {
            case "fire":
                armour += 1;
                turnEnd = false;
                break;
            case "pass":
                moveToLeft(6);
                if (isDestroyed())
                    return true;
                if (position.to(0, -1).getCol() > 0) { //Parche
                    fire(new EnemyProjectile(position.to(0, -1), 10, 100));
                }
                turnEnd = true;
                break;
        }
        return turnEnd;
	}

	@Override
	protected void actionSFIsNotSeen() {
        moveToLeft(3);
        if(!isDestroyed()) {
            fire(new EnemyProjectile(position.to(0, -1),3,20));
        }
	}

	@Override
	protected void actionSFIsSeen() {
        moveToLeft(1);
        if(!isDestroyed()) {
            fire(new EnemyProjectile(position.to(0, -1),6,50));
        }
	}

    @Override
    public String toString() {
        return "Fighter";
    }
}
