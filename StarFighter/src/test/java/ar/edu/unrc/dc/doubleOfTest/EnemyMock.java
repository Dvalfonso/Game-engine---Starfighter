package ar.edu.unrc.dc.doubleOfTest;

import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.patterns.StarfighterCommand;

public class EnemyMock extends Enemy {

    public EnemyMock(Starfighter starfighter) {
        super(new Position(0,0), starfighter);
        health = 10;
        regen = 10;
        armour = 10;
        vision = 10;
        currentHealth = 10;
    }

    public EnemyMock() {
        health = 20;
        regen = 1;
        armour = 5;
        vision = 10;
        currentHealth = 20;
    }

	@Override
	public boolean preemptiveAction(StarfighterCommand command) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'preemptiveAction'");
	}

	@Override
	protected void actionSFIsNotSeen() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'actionSFIsNotSeen'");
	}

	@Override
	protected void actionSFIsSeen() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'actionSFIsSeen'");
	}
}
