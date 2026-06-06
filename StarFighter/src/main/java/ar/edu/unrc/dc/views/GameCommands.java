package ar.edu.unrc.dc.views;

import ar.edu.unrc.dc.controllers.StarfighterGameEngine;

public class GameCommands {
    public void showGameCommands(InputOutput io, StarfighterGameEngine engine) {
        System.out.println("Press 1 to start");
        System.out.println("1) Move");
        System.out.println("2) Fire");
        System.out.println("3) Pass");
        System.out.println("4) Special");
        System.out.println("5) Abort");

        boolean selected = false;

        do {
            int selection = io.readInt();
            switch (selection) {
                case 1:
                    boolean correctPos = false;
                    int verticalmove = -100;
                    int horizontalMove = -100;
                    while (!correctMove(verticalmove, horizontalMove, engine)) {
                        System.out.println("Select the vertical steps: ");
                        verticalmove = io.readInt();
                        System.out.println("Select the horizontal steps: ");
                        horizontalMove = io.readInt();
                    }
                    engine.move(verticalmove, horizontalMove);
                    selected = true;
                    break;
                case 2:
                    engine.fire();
                    selected = true;
                    break;
                case 3:
                    engine.pass();
                    selected = true;
                    break;
                case 4:
                    engine.special();
                    selected = true;
                    break;
                case 5:
                    engine.abort();
                    selected = true;
                    break;
                default:
                    break;
            }
        } while (!selected);
    }

    private boolean correctMove(int verticalMove, int horizontalMove, StarfighterGameEngine engine) {
        int sfRow = engine.getGame().getStarfighter().getPosition().getRow();
        int sfCol= engine.getGame().getStarfighter().getPosition().getCol();

        int gameRows = engine.getRows();
        int gameCols = engine.getCols();

        if (sfRow + verticalMove < 0 || sfRow + verticalMove >= gameCols) return false;
        if (sfCol + horizontalMove < 0 || sfCol + horizontalMove >= gameCols) return false;

        return true;
    }
}
