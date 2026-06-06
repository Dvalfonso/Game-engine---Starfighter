package ar.edu.unrc.dc.models.projectileMovement;

import ar.edu.unrc.dc.models.cell.Position;

public class RightRocketMovement implements ProjectileMovement {
    private Position position;
    private int movePerTurn;
    private int movedActualTurn;

    public RightRocketMovement(Position initialPos){
        position = initialPos;
        movePerTurn = 1;
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
        movePerTurn *= 2;
	}

    public Position getPosition() { return position; }
    public int getMovePerTurn() { return movePerTurn; }
    public int getMovedActualTurn() { return movedActualTurn; }
}
