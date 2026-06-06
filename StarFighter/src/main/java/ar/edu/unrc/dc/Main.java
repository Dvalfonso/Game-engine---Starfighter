package ar.edu.unrc.dc;

import ar.edu.unrc.dc.controllers.*;
import ar.edu.unrc.dc.models.*;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.components.Specials.*;
import ar.edu.unrc.dc.models.setup.Setup;
import ar.edu.unrc.dc.models.starfighterCommand.*;

public class Main {
    static StarfighterGameEngine engine = new StarfighterGameEngine();

    public static void main(String[] args) {

        ///**
        StarfighterGameEngine engine1 = new StarfighterGameEngine();
        engine1.play(10,30);
        //**/


        /**
        Game game = BoardParser.ParserGameBoard(
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
                "_ _ G _ _ _ C _ _ _ _ _ _ _ _ _ I _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
                "_ _ _ _ _ _ _ _ _ _ _ F _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
                "_ _ _ _ _ _ _ _ < _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ X _ _ _ _ _\n" +
                "_ _ * * * * G _ _ _ _ _ _ _ _ _ _ _ _ _ _ * _ _ _ _ _ _ _ _\n" +
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
                "_ _ _ _ _ _ _ _ _ G _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
                "_ _ _ S _ _ < _ _ _ _ _ _ P _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n");

        game.playTurn(new PassCommand(game));
        game.playTurn(new PassCommand(game));
        game.playTurn(new PassCommand(game));
        game.playTurn(new PassCommand(game));
        game.playTurn(new PassCommand(game));
        game.playTurn(new PassCommand(game));
        game.playTurn(new PassCommand(game));
        **/

    }
}
