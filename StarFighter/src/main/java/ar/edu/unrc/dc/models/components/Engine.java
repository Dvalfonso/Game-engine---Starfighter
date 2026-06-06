package ar.edu.unrc.dc.models.components;

public abstract class Engine extends CommonAttributes {


  private final String name;
  private final int move;
  private final int moveCost;
  private final int energy;
  private final Regen regenEnergy;


  public Engine(String name, int move, int moveCost, int energy, Regen regenEnergy){
    this.name = name;
    this.move = move;
    this.moveCost = moveCost;
    this.energy = energy;
    this.regenEnergy = regenEnergy;
  }

  public int getMove(){
    return move;
  }

  public int getCostMove(){
    return moveCost;
  }

  public int getEnergy(){
    return energy;
  }

  public Regen getRegenEnergy(){
    return regenEnergy;
  }

  public String getName(){
    return name;
  }

  public void apply(CommonAttributes attributes){
    attributes.setMove(attributes.getMove() + getMove());
    attributes.setMoveCost(attributes.getMoveCost() + getCostMove());
    attributes.setEnergy(attributes.getEnergy() + getEnergy());
    int regenEnergyNew = attributes.getRegen().getEnergyRegen() + regenEnergy.getEnergyRegen();
    Regen newRegen = new Regen(0, regenEnergyNew);
    attributes.setRegen(newRegen);

  }

}
