package ar.edu.unrc.dc.views;

import ar.edu.unrc.dc.models.*;

public class BoardView {

    public void showBoard(Game game, String command) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Command issued: ").append(command).append(" (ok)\n");
        stringBuilder.append("Starfighter health: ");
        stringBuilder.append(game.getStarfighter().getCurrentHealth() + "\n");
        stringBuilder.append("Starfighter energy: ");
        stringBuilder.append(game.getStarfighter().getCurrentEnergy() + "\n");

        System.out.println(stringBuilder.toString());

        showBoard(game.getBoard());
    }

    public static void showBoard(Board board) {
        // true fog | false sin fog
        System.out.println(board.toString(true) + "\n");
    }
}
