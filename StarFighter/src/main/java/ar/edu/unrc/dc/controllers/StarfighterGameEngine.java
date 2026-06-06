package ar.edu.unrc.dc.controllers;

import ar.edu.unrc.dc.models.*;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.setup.Setup;
import ar.edu.unrc.dc.models.starfighterCommand.*;
import ar.edu.unrc.dc.patterns.StarfighterCommand;
import ar.edu.unrc.dc.views.ConsoleView;

/**
 * The core engine for the Starfighter turn-based terminal game.
 * Manages the game state, including the grid, entities, and turn execution.
 */
public class StarfighterGameEngine {
    Game game;
    boolean isActive;
    ConsoleView display;
    private int rows;
    private int cols;

    public Game getGame() {
        return game;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Starts a new game with the provided grid size and console display.
     * Initializes observers and sets the initial game state.
     */
    public void play(int rows, int cols, ConsoleView consoleDisplay) {
        checkPlayPrecondition(rows, cols);
        this.rows = rows;
        this.cols = cols;

        game = new Game(rows, cols);
        isActive = true;
        display = consoleDisplay;
        game.attach(display);
        game.setGameState(GameState.NotStarted);
    }

    /**
     * Starts the game with a default ConsoleDisplay.
     */
    public void play(int rows, int cols) {
        play(rows, cols, new ConsoleView(this));
    }

    /**
     * Move the Starfighter. Checks for collisions along the entire path.
     * @param verticalMove negative is up, positive is down
     * @param horizontalMove negative is left, positive is right
     */
    public void move(int verticalMove, int horizontalMove) {

        validateMove(verticalMove, horizontalMove);
        if (!isActive) throw new RuntimeException(
            "There is not a game instance");

        playTurn(new MoveCommand(game, verticalMove, horizontalMove));
    }

    public void fire() {
        if (!isActive) throw new RuntimeException(
            "There is not a game instance");
        playTurn(new FireCommand(game));
    }

    public void pass() {
        if (!isActive) throw new RuntimeException(
            "There is not a game instance");

        playTurn(new PassCommand(game));
    }

    public void special() {
        if (!isActive) throw new RuntimeException(
            "There is not a game instance");
        playTurn(new SpecialCommand(game));
    }

    public void abort() {
        if (!isActive) throw new RuntimeException(
            "There is not a game instance");

        isActive = false;
        game.abort();
    }

    /**
     * Validates board size before initializing a new game.
     */
    private void checkPlayPrecondition(int rows, int cols) {
        if (rows < 3 || rows > 10) throw new IllegalArgumentException(
            "Invalid row amount: must be in [3,10] was " + rows);

        if (cols < 5 || cols > 30) throw new IllegalArgumentException(
            "Invalid col amount: must be in [5,30] was " + cols);
    }

    /**
     * Checks that the movement does not lead outside the board.
     */
    private void validateMove(int verticalMove, int horizontalMove) {
        Position currentPos = game.getStarfighter().getPosition();
        int destinationRow = currentPos.getRow() + verticalMove;
        int destinationCol = currentPos.getCol() + horizontalMove;

        // Validate range
        if(destinationRow < 0 || destinationRow >= game.getBoard().getRows() || destinationCol < 0 || destinationCol >= game.getBoard().getCols())
            throw new IllegalArgumentException("Destination out of grid bounds.");
    }

    public boolean isGameEnd() {
        return !isActive;
    }

    private void playTurn(StarfighterCommand command) {
        game.playTurn(command);
    }

    public void changeState(GameState gameState) {
        game.changeGameState(gameState);
        if (gameState == GameState.GameOver) {
            isActive = false;
        }
    }

    public void setupBack(int steps) {
        game.getNavigationSetup().setupBack(steps);
    }

    public void setupNext(int steps) {
        game.getNavigationSetup().setupNext(steps);
    }

    public void confirmSetup(Setup setup) {
        game.setSetup(setup);
    }

    public void select(int selection) {
        game.getNavigationSetup().select(selection);
    }
}
