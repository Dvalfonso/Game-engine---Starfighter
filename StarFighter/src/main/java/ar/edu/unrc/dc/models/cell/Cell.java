package ar.edu.unrc.dc.models.cell;

import ar.edu.unrc.dc.models.Board;

public abstract class Cell {
    protected Position position;
    protected boolean destroyed;
    private Board board;

    public Cell() {
    }

    public Cell(Position position) {
        this.position = position;
        destroyed = false;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void destroy() {
        destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    /*
     * All the method for collitions must be called from who is moving
     */
    public abstract Cell collideWith(Cell b);
    protected abstract Cell collideWithStarfighter(Starfighter starfighter);
    protected abstract Cell collideWithEnemy(Enemy enemy);
    protected abstract Cell collideWithFriendlyProjectile(FriendlyProjectile Projectile);
    protected abstract Cell collideWithEnemyProjectile(EnemyProjectile Projectile);

    protected Cell collideWithEmptySpace(EmptySpace emptySpace) {
        return this;
    }
}
