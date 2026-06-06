package ar.edu.unrc.dc.views;

import ar.edu.unrc.dc.controllers.StarfighterGameEngine;
import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.cell.Starfighter;
import ar.edu.unrc.dc.models.setup.Setup;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class SummaryMenu {

    public void showSumary(InputOutput io, StarfighterGameEngine engine, Game game) {
        Setup setup = game.getSetup();
        if (setup == null) {
            System.out.println("Setup not available");
            return;
        }
        System.out.println("This is your setup: ");
        System.out.println("-Weapon: " + setup.getWeapon());
        System.out.println("-Armour: " + setup.getArmour());
        System.out.println("-Engine: " + setup.getEngine());
        System.out.println("-Special: " + setup.getSpecial());

        try {
            System.out.println("Press 1 to confirm, 2 for go back");
            int selection = io.readInt();
            if (selection == 1) {
                engine.changeState(GameState.InGame);
            } else {
                System.out.println("How far do you wanna go?");
                int steps = io.readInt();
                engine.setupBack(steps);
            }
        } catch (NoSuchElementException e) {
            return;
        }
    }
}
