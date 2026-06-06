package ar.edu.unrc.dc.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class StarfighterMoveStepDefinitions {
    private final Context context;

    public StarfighterMoveStepDefinitions(Context context) {
        this.context = context;
    }

    @And("move the starfighter {int} vertical steps and {int} horizontal steps")
    public void move_the_starfighter(int verticalMove, int horizontalMove) {
        try {
            context.game.moveStarfighter(verticalMove, horizontalMove);
            context.board = context.game.getBoard();
        } catch (RuntimeException e) {
            context.exception = e;
        }
    }

    @Then("the game doesnt accept the action")
    public void the_game_does_not_accept_the_action() {
        Assertions.assertNotNull(context.exception);
    }
}
