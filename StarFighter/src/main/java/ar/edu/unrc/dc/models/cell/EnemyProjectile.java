package ar.edu.unrc.dc.models.cell;

import ar.edu.unrc.dc.models.projectileMovement.LeftMovementProjectile;

public class EnemyProjectile extends Projectile {

    public EnemyProjectile(Position position) {
        super(position);
    }

    public EnemyProjectile(Position position, int damage) {
        super(position, damage);
    }

    public EnemyProjectile(Position position, int cellsMove, int damage) {
        super(position, damage);
        setProjectileMovement(new LeftMovementProjectile(position, cellsMove));
    }

	@Override
	public Cell collideWith(Cell b) {
        return b.collideWithEnemyProjectile(this);
	}

	@Override
	protected Cell collideWithEnemy(Enemy enemy) {
        enemy.regenerate(damage);
        leaveList();
        return enemy;
	}

	@Override
	protected Cell collideWithFriendlyProjectile(FriendlyProjectile projectile) {
        return projectile.collideWithEnemyProjectile(this);
	}

    /*
     * this method have to be called from the projectile is moving
     */
	@Override
	protected Cell collideWithEnemyProjectile(EnemyProjectile projectile) {
        projectile.setDamage(projectile.getDamage() + damage);
        return projectile;
	}
}

