package ar.edu.unrc.dc.models.projectileMovement;

import ar.edu.unrc.dc.models.cell.Position;

public class SplitterProjectileMovement implements ProjectileMovement {
    private Position position;

    public SplitterProjectileMovement(Position initialPos){
        position = initialPos;
    }

	@Override
	public Position nextStep() { return position; }

	@Override
	public boolean hasNextStep() { return false; }

	@Override
	public void nextTurn() { }

    public Position getPosition() { return position; }
}
