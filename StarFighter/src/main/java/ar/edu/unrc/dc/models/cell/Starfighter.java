package ar.edu.unrc.dc.models.cell;

import ar.edu.unrc.dc.models.components.Regen;

public class Starfighter extends Cell {
    private static int ID = 0;

    private int health;
    private int energy;
    private Regen regen;
    private int armour;
    private int vision;
    private int move;
    private int moveCost;
    private int currentHealth;
    private int currentEnergy;

    public Starfighter(Position position) {
        super(position);

        this.health = 100;
        this.energy = 100;
        this.regen = new Regen(10, 10);
        this.armour = 10;
        this.vision = 8;
        this.move = 10;
        this.moveCost = 10;
        this.currentHealth = 100;
        this.currentEnergy = 100;
    }

    public Starfighter(Position position, int health, int energy, Regen regen, int armour, int vision, int move, int moveCost) {
        super(position);

        this.health = health;
        this.energy = energy;
        this.regen = regen;
        this.armour = armour;
        this.vision = vision;
        this.move = move;
        this.moveCost = moveCost;
        this.currentHealth = health;
        this.currentEnergy = energy;
    }

    public void hurt(int damage) {
        currentHealth -= Math.max(damage-armour, 0);

        if (currentHealth < 0)
            currentHealth = 0;
    }

    public boolean isDead() {
        return currentHealth == 0;
    }

    public int getVision() {
        return vision;
    }

    public int getArmour(){
        return armour;
    }
    
    public int getMaxHealth(){
        return health;
    }

    public int getMaxEnergy(){
        return energy;
    }
    
    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getCurrentEnergy(){
        return currentEnergy;
    }

    public int getMoveCost(){
        return moveCost;
    }

    public Regen getRegen(){
        return regen;
    }

    public void regen(){
        if(currentHealth < health)//Only regenerates if current health is lower than maximum health
            increaseHealth(regen.getHealthRegen());
        if(currentEnergy < energy)//Only regenerates if current energy is lower than maximum energy
            increaseEnergy(regen.getEnergyRegen());
    }

    public void increaseEnergy(int amount) {
        currentEnergy = Math.min(currentEnergy + amount, energy);
    }

    public void decreaseEnergy(int amount){
        currentEnergy = Math.max(currentEnergy - amount, 0);
    }

    public void increaseHealth(int amount){
        this.currentHealth = Math.min(this.currentHealth + amount, this.health);
    }

    public void decreaseHealth(int amount) {
        this.currentHealth -= amount;
        if (this.currentHealth < 0) this.currentHealth = 0;
    }

    //This method allows to surpass the max health limit.
    public void overheal(int amount){
        this.currentHealth += amount;
    }

    //This method allows to surpass the max energy limit.
    public void overenergize(int amount) {
        this.currentEnergy += amount; 
    }

	@Override
	public Cell collideWith(Cell b) {
        return b.collideWithStarfighter(this);
	}

	@Override
	protected Cell collideWithStarfighter(Starfighter starfighter) {
        return new SFDestroyed();
	}

	@Override
    protected Cell collideWithEnemy(Enemy enemy) {
    
        int damage = enemy.getCurrentHealth();
        currentHealth -= damage;
        
        if (currentHealth <= 0) {
            currentHealth = 0;
            return new SFDestroyed(); // El SF murió
        }
        
        return this;
    }

	@Override
    protected Cell collideWithFriendlyProjectile(FriendlyProjectile projectile) {
        return collideWithProjectile(projectile);
    }

    @Override
    protected Cell collideWithEnemyProjectile(EnemyProjectile projectile) {
        return collideWithProjectile(projectile);
    }

    private Cell collideWithProjectile(Projectile projectile) {
        hurt(projectile.getDamage()); 
    
        if (isDead())
            return new SFDestroyed();

        return this;
    }

}
