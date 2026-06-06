package ar.edu.unrc.dc.models.components;


// Atributos para las implementacion de las interfaces
public  class CommonAttributes {
    private int health;
    private int energy;
    private Regen regen;
    private int armour;
    private int vision;
    private int move;
    private int moveCost;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public Regen getRegen() {
        return regen;
    }

    public void setRegen(Regen regen) {
        this.regen = regen;
    }

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public int getVision() {
        return vision;
    }

    public void setVision(int vision) {
        this.vision = vision;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getMoveCost() {
        return moveCost;
    }

    public void setMoveCost(int moveCost) {
        this.moveCost = moveCost;
    }
}
