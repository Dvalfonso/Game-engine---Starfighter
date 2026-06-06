package ar.edu.unrc.dc.stepdefinitions;

import ar.edu.unrc.dc.models.cell.Position;
import ar.edu.unrc.dc.models.cell.SFDestroyed;
import ar.edu.unrc.dc.models.starfighterCommand.PassCommand;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class StarfighterCollisionStepDefinitions {
    private final Context context;

    public StarfighterCollisionStepDefinitions(Context context) {
        this.context = context;
    }

    @Then("the starfighter takes damage on the collision")
    public void starfighter_takes_damage() {
        Assertions.assertTrue(context.game.getStarfighter().getCurrentHealth() < 100);
    }

    @Then("the starfighter is destroyed at row {int} col {int}")
    public void starfighter_is_destroyed(int row, int col) {
        Position pos = new Position(row, col);
        Assertions.assertInstanceOf(SFDestroyed.class, context.board.getCell(pos));
    }

    @And("execute the pass command")
    public void pass_command() {
        context.game.playTurn(new PassCommand(context.game));
    }
}
