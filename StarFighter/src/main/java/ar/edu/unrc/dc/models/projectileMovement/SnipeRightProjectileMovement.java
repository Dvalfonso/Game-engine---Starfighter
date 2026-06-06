package ar.edu.unrc.dc.models.projectileMovement;

import ar.edu.unrc.dc.models.cell.Position;

public class SnipeRightProjectileMovement implements ProjectileMovement{
    private Position position;
    private boolean moved;

    public SnipeRightProjectileMovement(Position initialPos){
        position = initialPos;
        moved = false;
    }

	@Override
	public Position nextStep() {
        position = position.to(0,8);
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
    public boolean moved() { return moved; }
}
