package ar.edu.unrc.dc.models.projectileMovement;

import ar.edu.unrc.dc.models.cell.Position;

public class RightMovementProjectile implements ProjectileMovement{
    private int movePerTurn;
    private int movedActualTurn;
    private Position position;

    public RightMovementProjectile(Position initialPos, int movePerTurn) {
        position = initialPos;
        this.movePerTurn = movePerTurn;
        movedActualTurn = 0;
    }

	@Override
	public Position nextStep() {
        position = position.to(0,1);
        movedActualTurn++;
        return position;
	}

	@Override
	public boolean hasNextStep() {
        return movePerTurn - movedActualTurn > 0;
	}

	@Override
	public void nextTurn() {
        movedActualTurn = 0;
	}
}
