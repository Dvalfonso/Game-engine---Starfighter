package ar.edu.unrc.dc.models.components;

import ar.edu.unrc.dc.models.Game;

public abstract class Special extends CommonAttributes{
    private final int cost;

    public Special(int cost){
        this.cost = cost;
    }

    public int getCost(){
        return cost;
    }

    public abstract void apply(Game game);
}
