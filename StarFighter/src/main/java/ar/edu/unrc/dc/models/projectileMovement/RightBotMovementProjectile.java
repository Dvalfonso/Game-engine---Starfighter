package ar.edu.unrc.dc.models.projectileMovement;

import ar.edu.unrc.dc.models.cell.Position;

public class RightBotMovementProjectile implements ProjectileMovement{  
    private Position position;
    private boolean moved;

    public RightBotMovementProjectile(Position initialPos) {
        position = initialPos;
        moved = false;
    }

	@Override
	public Position nextStep() {
        position = position.to(-1,1);
        moved = true;
        return position;
	}

	@Override
	public boolean hasNextStep() {
        return !moved;
	}

	@Override
	public void nextTurn() {
        moved = false;
	}

    public Position getPosition() { return position; }
}
