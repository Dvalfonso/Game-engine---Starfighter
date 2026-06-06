package ar.edu.unrc.dc.models.components;

public abstract class Armour extends CommonAttributes {

    private final String name;
    private final int baseArmour;

    public Armour(String name, int baseArmour){
        this.name = name;
        this.baseArmour = baseArmour;
    }

    public int getBaseArmour(){
        return baseArmour;
    }

    public abstract void apply(CommonAttributes attributes);
}
