package ar.edu.unrc.dc.models.cell;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.EnemyManager;
import ar.edu.unrc.dc.models.ProjectileLinkedList;
import ar.edu.unrc.dc.patterns.StarfighterCommand;
import ar.edu.unrc.dc.patterns.projectileList.*;
import java.util.*;

public abstract class Enemy extends Cell implements ProjectileCreator {
    private static int nextID = 1;
    private int ID;

    private Position backStep;
    protected Starfighter starfighter;
    protected Board board;
    protected ProjectileList projectileList;
    protected EnemyManager manager;

    protected int health;
    protected int regen;
    protected int armour;
    protected int vision;
    protected int currentHealth;
    protected boolean seenByStarfighter;
    protected boolean seeStarfighter;

    public Enemy() {
    }

    // Solo para BoardParser
    public Enemy(Position position) {
        super(position);
        backStep = position;

        projectileList = new ProjectileLinkedList();
        ID = nextID;
        nextID++;
    }

    public Enemy(Position position, Starfighter starfighter) {
        super(position);
        backStep = position;

        ID = nextID;
        nextID++;

        projectileList = new ProjectileLinkedList();
        this.starfighter = starfighter;
        updateVision();
    }

    public Enemy(Position position, Starfighter starfighter, Board board) {
        super(position);
        backStep = position;

        ID = nextID;
        nextID++;

        projectileList = new ProjectileLinkedList(board);
        this.starfighter = starfighter;
        updateVision();
        this.board = board;
    }

    public void setStarfighter(Starfighter starfighter) {
        this.starfighter = starfighter;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setManager(EnemyManager manager) { this.manager = manager; }

    public void setID(int ID) { this.ID = ID; }

    public int getID(){ return this.ID; }

    public void hurt(int damage) {
        currentHealth -= Math.max(damage-armour, 0);

        if (currentHealth < 0)
            currentHealth = 0;
    }

    public boolean isDead() {
        return currentHealth == 0;
    }

    public void regenerate() {
        if (currentHealth >= health || isDead())
            return;

        health = currentHealth + regen >= health
                ? health 
                : currentHealth + regen;
    }

    public void regenerate(int regen) {
        if (currentHealth >= health || isDead())
            return;

        health = currentHealth + regen >= health
                ? health 
                : currentHealth + regen;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getArmour(){
        return armour;
    }

    public int getRegen(){
        return regen;
    }

    public int getVision(){
        return vision;
    }
    
    public void updateVision() {
        int distance = position.manhattanDistance(starfighter.getPosition());

        seenByStarfighter = starfighter.getVision() >= distance;
        seeStarfighter = vision >= distance;
    }

    @Override
    public void setList(ProjectileList projectileList) {
        this.projectileList = projectileList;
    }

    @Override
    public void fire(Projectile projectile) {
        projectileList.add(projectile);
    }

	protected abstract void actionSFIsSeen();
	protected abstract void actionSFIsNotSeen();

    public void act() {
        if (seeStarfighter)
            actionSFIsSeen();
        else
            actionSFIsNotSeen();
    }

    // @return true if the turn end, false if not
    public abstract boolean preemptiveAction(StarfighterCommand command);

    public void moveToLeft(int cant) {
        for (int i = 1; i <= cant && !isDead(); i++) {
            board.moveCell(this, position.to(0,-1));
            backStep = position;
        }
    }

    public void moveVertical(int moves) {
        if (moves == 0)
            return;

        int direction = moves < 0 ? -1 : 1;
        int cantMoves = Math.abs(moves);

        for (int i = 1; i <= cantMoves && !isDead(); i++) {
            board.moveCell(this, position.to(direction,0));
            backStep = position;
        }
    }

	@Override
	public Cell collideWith(Cell b) {
        return b.collideWithEnemy(this);
	}

	@Override
	protected Cell collideWithStarfighter(Starfighter starfighter) {
        return starfighter.collideWithEnemy(this);
	}

	@Override
	protected Cell collideWithEnemy(Enemy enemy) {
        board.moveCell(enemy, enemy.backStep);
        return this;
	}
    
	@Override
	protected Cell collideWithFriendlyProjectile(FriendlyProjectile projectile) {
        hurt(projectile.getDamage());
        projectile.leaveList();

        if (isDead()) {
            leaveManager();
            return new EmptySpace();
        }

        return this;
	}

	@Override
	protected Cell collideWithEnemyProjectile(EnemyProjectile projectile) {
        regenerate(projectile.getDamage());
        return this;
	}

    public void leaveManager() {
        if (manager != null)
            manager.remove(this);
    }
}
