package ar.edu.unrc.dc.models.setup;

import java.util.List;

public class NavigationSetup {
    private SetupFactory factory;
    private int currentPosition;

    public NavigationSetup() {
        factory = new SetupFactory();
        currentPosition = 0;
    }

    public NavigationSetup(SetupFactory factory) {
        this.factory = factory;
        currentPosition = 0;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setupNext(int n) {
        currentPosition += n;
    }

    public void setupBack(int n) {
        currentPosition -= n;
    }
    public void select(int n) {
        if (currentPosition == 1) {
            factory.setWeapon(n);
        }else if (currentPosition == 2) {
            factory.setArmour(n);
        }else if (currentPosition == 3) {
            factory.setEngine(n);
        }else if (currentPosition == 4) {
            factory.setSpecial(n);
        }else if (currentPosition == 5) {
            // Setup sumary and change game state
        }
    }
    
    public Setup createSetup() {
        return factory.createSetup();
    }

    public void setCurrentPosition(int n) {
        this.currentPosition = n;
    }
}
