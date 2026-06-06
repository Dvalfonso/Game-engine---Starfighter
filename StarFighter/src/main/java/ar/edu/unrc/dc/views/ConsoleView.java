package ar.edu.unrc.dc.views;

import ar.edu.unrc.dc.controllers.StarfighterGameEngine;
import ar.edu.unrc.dc.models.*;
import ar.edu.unrc.dc.patterns.DisplayObserver;

public class ConsoleView implements DisplayObserver {
    private final StarfighterGameEngine engine;
    private InputOutput io;
    private BoardView boardView;
    private SetupMenu setupMenu;
    private SummaryMenu summaryMenu;
    private MainMenu mainMenu;
    private GameOverMenu gameOverMenu;
    private GameCommands gameCommands;

    public ConsoleView(StarfighterGameEngine engine) {
        this.engine = engine;
        this.io = new ConsoleIO();
        this.boardView = new BoardView();
        this.setupMenu = new SetupMenu();
        this.summaryMenu = new SummaryMenu();
        this.mainMenu = new MainMenu();
        this.gameOverMenu = new GameOverMenu();
        this.gameCommands = new GameCommands();
    }

    @Override
    public void update(String command, Game game) {
        System.out.println("Entra con state: " + game.getGameState());
        if(game.getGameState() == GameState.NotStarted) {
            mainMenu.showMenu(io, engine);
        } else if (game.getGameState() == GameState.SetupSumary) {
            summaryMenu.showSumary(io, engine, game);
        } else if (game.getGameState() != GameState.InGame && game.getGameState() != GameState.GameOver) {
            setupMenu.showSetupMenu(io,game,engine);
        } else if (game.getGameState() == GameState.GameOver) {
            gameOverMenu.showGameOver(io, engine);
        } else {
            boardView.showBoard(game, command);
            gameCommands.showGameCommands(io, engine);
        }
    }

    @Override
    public void update(Board board) {
        BoardView.showBoard(board);
    }

    // Inyecciones para testear con mocks

    public void setSetupMenu(SetupMenu setupMenu) {
        this.setupMenu = setupMenu;
    }

    public void setSummaryMenu(SummaryMenu summaryMenu) {
        this.summaryMenu = summaryMenu;
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void setGameOverMenu(GameOverMenu gameOverMenu) {
        this.gameOverMenu = gameOverMenu;
    }

    public void setBoardView(BoardView boardView) {
        this.boardView = boardView;
    }

    public void setGameCommands(GameCommands gameCommands) {
        this.gameCommands = gameCommands;
    }

    public void setIO(InputOutput io) {
        this.io = io;
    }
}
