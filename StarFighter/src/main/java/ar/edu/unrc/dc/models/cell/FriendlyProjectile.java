package ar.edu.unrc.dc.models.cell;

public class FriendlyProjectile extends Projectile {

    public FriendlyProjectile(Position position) {
        super(position);
    }

    public FriendlyProjectile(Position position, int damage) {
        super(position, damage);
    }

	@Override
	public Cell collideWith(Cell b) {
        return b.collideWithFriendlyProjectile(this);
	}

	@Override
	protected Cell collideWithEnemyProjectile(EnemyProjectile projectile) {
        int enemyProjDam = projectile.getDamage();

        if (damage < enemyProjDam) {
            projectile.setDamage(enemyProjDam - damage);
            leaveList();
            return projectile;
        }
        if (damage > enemyProjDam) {
            damage -= enemyProjDam;
            projectile.leaveList();
            return this;
        }

        return new EmptySpace();
	}

	@Override
	protected Cell collideWithEnemy(Enemy enemy) {
        enemy.hurt(damage);
        leaveList();

        if (enemy.isDead()) {
            enemy.leaveManager();
            return new EmptySpace();
        }

        return enemy;
	}

	@Override
	protected Cell collideWithFriendlyProjectile(FriendlyProjectile projectile) {
        projectile.setDamage(projectile.getDamage() + damage);
        leaveList();
        return projectile;
	}
}
