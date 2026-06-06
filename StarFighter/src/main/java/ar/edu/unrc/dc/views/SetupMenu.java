package ar.edu.unrc.dc.views;

import ar.edu.unrc.dc.controllers.StarfighterGameEngine;
import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.setup.Setup;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class SetupMenu {
    public void showSetupMenu(InputOutput io, Game game, StarfighterGameEngine engine) {
        try {
            while (game.getGameState() != GameState.NotStarted && game.getGameState() != GameState.InGame && game.getGameState() != GameState.SetupSumary) {
                int currentPosition = game.getNavigationSetup().getCurrentPosition();
                String currentComponent = "";

                if (currentPosition < 1) {
                    engine.changeState(GameState.NotStarted);
                    break;
                }
                if (currentPosition == 1) currentComponent = "weapon";
                if (currentPosition == 2) currentComponent = "armour";
                if (currentPosition == 3) currentComponent = "engine";
                if (currentPosition == 4) currentComponent = "special";
                if (currentPosition > 4) {
                    Setup setup = game.getNavigationSetup().createSetup();
                    engine.confirmSetup(setup);
                    engine.changeState(GameState.SetupSumary);
                    break;
                }

                System.out.println("Welcome to Starfighter setup stage, please select your " + currentComponent);

                switch (currentComponent) {
                    case "weapon":
                        System.out.println(" 1.Rocket\n 2.Snipe\n 3.Splitter\n 4.Spread\n 5.Standard\n");
                        break;
                    case "engine":
                        System.out.println(" 1.AccelerationEngine\n 2.BalancedEngine\n 3.PowerEngine\n");
                        break;
                    case "armour":
                        System.out.println(" 1.BlindArmour\n 2.HeavyArmour\n 3.NoRegenArmour\n 4.TankArmour\n");
                        break;
                    case "special":
                        System.out.println(" 1.Overcharge\n 2.Recall\n 3.Repair\n 4.Deploy drones\n 5.Orbital stricke\n");
                        break;
                    default:
                        System.out.println("1.item1 2.item2 3.item3, Dont put trash pls");
                        break;
                }

                int selectedComponent = io.readInt();
                engine.select(selectedComponent);

                System.out.println("Now, where do yo wanna go?\n 0.Home\n 1.weapon\n 2.armour\n 3.engine\n 4.special\n 5.setupSumary");
                System.out.println("You are in " + currentComponent + " setup");
                System.out.println("1.back 2.next");
                int backNextOption = io.readInt();
                System.out.println("How far do you wanna go?");
                int numberOfSteps = io.readInt();
                if (backNextOption == 1) {
                    engine.setupBack(numberOfSteps);
                }
                if (backNextOption == 2) {
                    engine.setupNext(numberOfSteps);
                    // Verificar si después de avanzar, la posición es > 4, entonces crear setup y cambiar estado
                    int newPosition = game.getNavigationSetup().getCurrentPosition();
                    if (newPosition > 4) {
                        Setup setup = game.getNavigationSetup().createSetup();
                        engine.confirmSetup(setup);
                        engine.changeState(GameState.SetupSumary);
                        break;
                    }
                }
            }
        } catch (NoSuchElementException e) {
            return;
        }
    }
}