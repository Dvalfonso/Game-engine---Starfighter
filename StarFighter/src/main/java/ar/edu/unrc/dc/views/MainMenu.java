package ar.edu.unrc.dc.views;

import ar.edu.unrc.dc.controllers.StarfighterGameEngine;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.cell.Starfighter;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainMenu {
    public void showMenu(InputOutput io, StarfighterGameEngine engine) {
        try {
            System.out.println("Press 1 to start");
            int selection = io.readInt();
            if (selection == 1) {
                engine.changeState(GameState.WeaponSetup);
            }
        } catch (NoSuchElementException e) {
            return;
        }
    }
}
