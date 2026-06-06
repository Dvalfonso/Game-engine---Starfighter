package ar.edu.unrc.dc.views;

import ar.edu.unrc.dc.controllers.StarfighterGameEngine;

public class GameOverMenu {

    public void showGameOver(InputOutput io, StarfighterGameEngine engine) {
        System.out.println("Game over!");
        System.out.println("Do you want to play again?");
        System.out.println("1) Yes");
        System.out.println("2) No");


        int selection = io.readInt();

        if (selection == 1)
            engine.play(engine.getRows(), engine.getCols());
    }
}
