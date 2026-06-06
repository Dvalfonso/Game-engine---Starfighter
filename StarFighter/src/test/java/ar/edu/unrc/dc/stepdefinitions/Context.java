package ar.edu.unrc.dc.stepdefinitions;

import ar.edu.unrc.dc.controllers.StarfighterGameEngine;
import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.views.SetupMenu;

public class Context {
    public Game game;
    public String boardSinParsear;
    public Board board;
    public RuntimeException exception;
    public SetupMenu setupMenu;
    public StarfighterGameEngine engine;


    public Context() {
        this.game = null;
        this.board = null;
        this.boardSinParsear = null;
        this.exception = null;
        this.setupMenu = null;
        this.engine = null;
    }
}
