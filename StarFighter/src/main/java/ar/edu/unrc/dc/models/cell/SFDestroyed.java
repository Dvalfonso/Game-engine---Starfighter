package ar.edu.unrc.dc.models.cell;

import ar.edu.unrc.dc.models.cell.enemy.*;

public class SFDestroyed extends Cell{

    public SFDestroyed() {
    }

    public SFDestroyed(Position position) {
        super(position);
    }

	@Override
	public Cell collideWith(Cell b) {
        throw new RuntimeException("If the Starfighter is destroyed, the game should be ended");
	}

	@Override
	protected Cell collideWithStarfighter(Starfighter starfighter) {
        throw new RuntimeException("If the Starfighter is destroyed, the game should be ended");
	}

	@Override
	protected Cell collideWithEnemy(Enemy enemy) {
        throw new RuntimeException("If the Starfighter is destroyed, the game should be ended");
	}

	@Override
	protected Cell collideWithEmptySpace(EmptySpace emptySpace) {
        throw new RuntimeException("If the Starfighter is destroyed, the game should be ended");
	}

	@Override
	protected Cell collideWithFriendlyProjectile(FriendlyProjectile Projectile) {
        throw new RuntimeException("If the Starfighter is destroyed, the game should be ended");
	}

	@Override
	protected Cell collideWithEnemyProjectile(EnemyProjectile Projectile) {
        throw new RuntimeException("If the Starfighter is destroyed, the game should be ended");
	}
}
