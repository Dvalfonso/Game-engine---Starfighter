package ar.edu.unrc.dc.models.components;

public class Regen {
    int healthRegen;
    int energyRegen;

    public Regen(int healthRegen, int energyRegen) {
        this.healthRegen = healthRegen;
        this.energyRegen = energyRegen;
    }

    public int getHealthRegen() {
        return healthRegen;
    }

    public int getEnergyRegen() {
        return energyRegen;
    }
}
