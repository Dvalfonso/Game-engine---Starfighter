package ar.edu.unrc.dc.models.cell;

public class EmptySpace extends Cell{

    public EmptySpace() {
    }

    public EmptySpace(Position position) {
        super(position);
    }

	@Override
	public Cell collideWith(Cell b) {
        return b.collideWithEmptySpace(this);
	}

	@Override
	protected Cell collideWithStarfighter(Starfighter starfighter) {
        return starfighter;
	}

	@Override
	protected Cell collideWithEnemy(Enemy enemy) {
        return enemy;
	}

	@Override
	protected Cell collideWithEmptySpace(EmptySpace emptySpace) {
        return this;
	}

	@Override
	protected Cell collideWithFriendlyProjectile(FriendlyProjectile projectile) {
        return projectile;
	}

	@Override
	protected Cell collideWithEnemyProjectile(EnemyProjectile projectile) {
        return projectile;
	}
}
