package ar.edu.unrc.dc.stepdefinitions;

import ar.edu.unrc.dc.BoardParser;
import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.cell.enemy.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class BoardStepDefinitions {
    private final Context context;

    public BoardStepDefinitions(Context context) {
        this.context = context;
    }

    @Given("the following board string:")
    public void the_following_board(String string) {
        context.boardSinParsear = string;
        context.board = BoardParser.fromString(context.boardSinParsear);
        context.game = BoardParser.ParserGameBoard(context.boardSinParsear);
    }

    @Then("the board should have {int} rows and {int} columns")
    public void the_board_should_have(int rows, int cols) {
        Assertions.assertTrue(context.board.getRows() == rows && context.board.getCols() == cols);
    }

    @And("the cell at row {int} column {int} should be EmptySpace")
    public void the_cell_should_be_empty_space(int row, int col) {
        Position pos = new Position(row, col);
        Cell cell = context.board.getCell(pos);
        Assertions.assertInstanceOf(EmptySpace.class, cell);
    }

    @Then("the cell at row {int} column {int} should be Starfighter")
    public void the_cell_should_be_the_starfighter(int row, int col) {
        Position pos = new Position(row, col);
        Cell cell = context.board.getCell(pos);
        Assertions.assertInstanceOf(Starfighter.class, cell);
    }

    @And("the cell at row {int} column {int} should be Grunt")
    public void the_cell_should_be_grunt(int row, int col) {
        Position pos = new Position(row, col);
        Cell cell = context.board.getCell(pos);
        Assertions.assertInstanceOf(Grunt.class, cell);
    }

    @And("the cell at row {int} column {int} should be FriendlyProjectile")
    public void the_cell_should_be_friendly_projectile(int row, int col) {
        Position pos = new Position(row, col);
        Cell cell = context.board.getCell(pos);
        Assertions.assertInstanceOf(FriendlyProjectile.class, cell);
    }

    @And("the cell at row {int} column {int} should be Fighter")
    public void the_cell_should_be_fighter(int row, int col) {
        Position pos = new Position(row, col);
        Cell cell = context.board.getCell(pos);
        Assertions.assertInstanceOf(Fighter.class, cell);
    }

    @And("the cell at row {int} column {int} should be Carrier")
    public void the_cell_should_be_carrier(int row, int col) {
        Position pos = new Position(row, col);
        Cell cell = context.board.getCell(pos);
        Assertions.assertInstanceOf(Carrier.class, cell);
    }

    @And("the cell at row {int} column {int} should be Interceptor")
    public void the_cell_should_be_interceptor(int row, int col) {
        Position pos = new Position(row, col);
        Cell cell = context.board.getCell(pos);
        Assertions.assertInstanceOf(Interceptor.class, cell);
    }

    @And("the cell at row {int} column {int} should be Pylon")
    public void the_cell_should_be_pylon(int row, int col) {
        Position pos = new Position(row, col);
        Cell cell = context.board.getCell(pos);
        Assertions.assertInstanceOf(Pylon.class, cell);
    }

    @And("the cell at row {int} column {int} should be EnemyProjectile")
    public void the_cell_should_be_enemy_projectile(int row, int col) {
        Position pos = new Position(row, col);
        Cell cell = context.board.getCell(pos);
        Assertions.assertInstanceOf(EnemyProjectile.class, cell);
    }

    @And("the cell at row {int} column {int} should be SFDestroyed")
    public void the_cell_should_be_sfDestroyed(int row, int col) {
        Position pos = new Position(row, col);
        Cell cell = context.board.getCell(pos);
        Assertions.assertInstanceOf(SFDestroyed.class, cell);
    }
}
