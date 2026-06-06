package ar.edu.unrc.dc.models;

import java.util.*;

import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.patterns.projectileList.*;

public class ProjectileLinkedList implements ProjectileList {
    private List<Projectile> projectiles;
    private Board board;

    public ProjectileLinkedList() {
        projectiles = new LinkedList<>();
    }

    public ProjectileLinkedList(Board board) {
        projectiles = new LinkedList<>();
        this.board = board;
    }

	@Override
	public void add(Projectile projectile) {
        if (board.isValidPosition(projectile.getPosition())) {
            projectile.setProjectileList(this);
            board.moveCell(projectile, projectile.getPosition());
            projectiles.addLast(projectile);
        }
	}

	@Override
	public void remove(Projectile projectile) {
        projectiles.remove(projectile);
	}

	@Override
	public void move() {
        List<Projectile> cProjectiles = List.copyOf(projectiles);
        for (Projectile projectile:cProjectiles) {

            while (projectile.hasNextStep() && projectiles.contains(projectile))
                board.moveCell(projectile, projectile.nextStep());
        }

        for (Projectile projectile: projectiles) {
            projectile.nextTurn();
        }
	}

	@Override
	public void removeAll() {
        for (Projectile projectile:projectiles)
            board.updateCell(projectile.getPosition(), new EmptySpace());

        projectiles.clear();
	}

	@Override
	public List<Projectile> getList() {
        return projectiles;
	}
}
