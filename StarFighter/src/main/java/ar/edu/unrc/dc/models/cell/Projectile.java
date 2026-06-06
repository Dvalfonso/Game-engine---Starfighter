package ar.edu.unrc.dc.models.cell;

import ar.edu.unrc.dc.models.projectileMovement.ProjectileMovement;
import ar.edu.unrc.dc.patterns.projectileList.ProjectileList;

public abstract class Projectile extends Cell {
    private static int nextID = -1;
    private int ID = -1;

    protected int damage;
    private ProjectileMovement projectileMovement;
	private ProjectileList projectileList;

    public Projectile(Position position, int damage) {
        super(position);

        ID = nextID;
        nextID--;

        this.damage = damage;
    }

    public Projectile(Position position) {
        super(position);

        ID = nextID;
        nextID--;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
   
    public void setProjectileMovement(ProjectileMovement pm){
        projectileMovement = pm;
    }

    public void setProjectileList(ProjectileList pl) { projectileList = pl; }

    public void leaveList() {
        if (projectileList != null)
            projectileList.remove(this);
    }

    public Position nextStep() {
       return projectileMovement.nextStep();
    }

    public boolean hasNextStep() {
        return projectileMovement.hasNextStep();
    }

    public void nextTurn() {
        projectileMovement.nextTurn();
    }

    @Override
    public void destroy() {
        destroyed = true;
        leaveList();
    }

	@Override
	protected Cell collideWithStarfighter(Starfighter starfighter) {
        starfighter.hurt(damage);
        leaveList();

        if (starfighter.isDead()) {
            System.out.println("starfighter is dead");
            return new SFDestroyed();
        }

        return starfighter;
	}

}
