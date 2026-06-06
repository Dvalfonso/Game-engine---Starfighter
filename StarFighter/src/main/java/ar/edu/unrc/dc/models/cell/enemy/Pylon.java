package ar.edu.unrc.dc.models.cell.enemy;

import java.util.List;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.patterns.StarfighterCommand;
import ar.edu.unrc.dc.patterns.projectileList.ProjectileList;

public class Pylon extends Enemy {
    private List<Enemy> enemies;

    public Pylon(Position position) {
        super(position);
        health = 300;
        regen = 0;
        armour = 0;
        vision = 5;
        currentHealth = 300;
    }

    public Pylon(Position position, Starfighter starfighter, List<Enemy> enemies, Board board, ProjectileList projectileList) {
        super(position, starfighter, board);
        this.projectileList = projectileList;
        health = 300;
        regen = 0;
        armour = 0;
        vision = 5;
        currentHealth = 300;

        this.enemies = enemies;
    }

	@Override
	public boolean preemptiveAction(StarfighterCommand command) {
		// The Pylon don't have preemptive action
        return false;
	}

	@Override
	protected void actionSFIsNotSeen() {
        moveToLeft(2);
        if (!isDestroyed()) {
            heal(10);
        }
	}

	@Override
	protected void actionSFIsSeen() {
        moveToLeft(1);
        if (!isDestroyed()) {
            fire(new EnemyProjectile(position.to(0, -1),2,70));
        }
	}

	private void heal(int health) {
        for (Enemy enemy : enemies)
            enemy.regenerate(health);
	}

    @Override
    public String toString() {
        return "Pylon";
    }
}
