package ar.edu.unrc.dc.models;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.cell.enemy.*;
import ar.edu.unrc.dc.patterns.*;
import ar.edu.unrc.dc.patterns.projectileList.ProjectileList;

public class EnemyManager {
    private List<Enemy> enemies;
    private ProjectileList projectiles;
    private Board board;
    private Starfighter starfighter;

    public EnemyManager(Board board, Starfighter starfighter) {
        this.enemies = new LinkedList<>();
        projectiles = new ProjectileLinkedList(board);
        this.board = board;
        this.starfighter = starfighter;
    }

    public void setEnemy(Enemy enemy) { enemies.add(enemy); }

    public List<Enemy> getEnemies() { return enemies; }

    public void add(Enemy enemy) {
        enemy.setManager(this);
        enemy.setList(projectiles);
        enemies.addLast(enemy);
    }

    public void addEnemies(List<Enemy> enemies) {
        for (Enemy e : enemies) {
            if (e != null) {
                e.setList(this.projectiles);
                e.setBoard(this.board);
                e.setStarfighter(this.starfighter);
                e.setManager(this);
                this.enemies.addLast(e);
            }
        }
    }

    public List<Projectile> getEnemyProjectiles() {
        return projectiles.getList();
    }

    public void addEnemyProjectiles(List<Projectile> projectiles) {
        for (Projectile proj : projectiles) {
            board.updateCellWithCollision(proj.getPosition(), proj);
            this.projectiles.add(proj);
        }
    }

    public void enemyVisionUpdate() {
        for (Enemy enemy : enemies)
            enemy.updateVision();
    }

    public void clearEnemyProjectiles() {
        projectiles.removeAll();
    }

    public void moveProjectiles() {
        projectiles.move();
    }

    public void enemyAct(StarfighterCommand command) {
        if (starfighter == null || starfighter.isDestroyed())
            return;

        List<Enemy> mustAct = new LinkedList<>();

        for (Enemy enemy : List.copyOf(enemies)) {
            boolean turnEnd = enemy.preemptiveAction(command);
            if (!turnEnd)
                mustAct.addLast(enemy);
        }

        for (Enemy enemy : mustAct)
                enemy.act();
    }

    public void enemySpawn() {
        int spawnRate = ThreadLocalRandom.current().nextInt(101);
        if (spawnRate > 45)
            return;

        Position spawnPosition = board.spawnPosition();
        Enemy enemy = new Grunt(spawnPosition, starfighter, board, projectiles);

        int number = ThreadLocalRandom.current().nextInt(101);

        if (number>25 && number < 50) enemy = new Fighter(spawnPosition, starfighter, board, projectiles);
        else if (number < 75) enemy = new Carrier(spawnPosition, starfighter, board);
        else if (number <= 100) enemy = new Pylon(spawnPosition, starfighter, enemies, board, projectiles);

        add(enemy);
        board.moveCell(enemy, spawnPosition);
    }

    public void remove(Enemy enemy) {
        enemies.remove(enemy);
    }

	public void spawInterceptor(Position position) {
        if (board.isValidPosition(position)) {
            if (!(board.getCell(position) instanceof Enemy)) {
                Interceptor interceptor = new Interceptor(position, starfighter, board);
                add(interceptor);
                board.moveCell(interceptor, position);
            }
        }
	}
}
