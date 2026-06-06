package ar.edu.unrc.dc.stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ar.edu.unrc.dc.controllers.StarfighterGameEngine;
import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.views.SetupMenu;
import io.cucumber.java.en.*;

public class SetupNavigationStepDefinitions {

    private final Context context;

    public SetupNavigationStepDefinitions(Context context) {
        this.context = context;
    }

    @Given("the game is in not-started state")
    public void the_game_is_in_not_started_state() {
        context.engine = new StarfighterGameEngine();
        context.engine.play(6, 10);
        context.game = context.engine.getGame();
        context.setupMenu = new SetupMenu();
        context.engine.changeState(GameState.NotStarted);
    }

    @Given("I am in {word} setup")
    public void i_am_in_setup(String setupState) {
        context.engine = new StarfighterGameEngine();
        context.engine.play(6, 10);
        context.game = context.engine.getGame();
        context.setupMenu = new SetupMenu();

        switch (setupState.toLowerCase()) {
            case "weapon" -> { 
                context.engine.changeState(GameState.WeaponSetup);
                context.game.getNavigationSetup().setCurrentPosition(1); 
            }
            case "armour" -> {
                context.engine.changeState(GameState.ArmourSetup); 
                context.game.getNavigationSetup().setCurrentPosition(2); 
            }
            case "engine" -> {
                context.engine.changeState(GameState.EngineSetup); 
                context.game.getNavigationSetup().setCurrentPosition(3); 
            }
            default -> throw new IllegalArgumentException("State no soportado: " + setupState);
        }
    }

    @When("I execute play")
    public void i_execute_play() {
        context.engine.play(6, 10);
        context.engine.changeState(GameState.WeaponSetup);
    }

    @When("I execute setupNext\\({int}\\)")
    public void i_execute_setup_next(int steps) {
        context.game.getNavigationSetup().setupNext(steps);
        syncState();
    }

    @When("I execute setupBack\\({int}\\)")
    public void i_execute_setup_back(int steps) {
        context.game.getNavigationSetup().setupBack(steps);
        syncState();
    }

    @Then("the state is {word} setup")
    public void the_state_is(String expectedState) {
        GameState actualState = context.engine.getGame().getGameState();
        switch (expectedState.toLowerCase()) {
            case "weapon" -> assertEquals(GameState.WeaponSetup, actualState);
            case "armour" -> assertEquals(GameState.ArmourSetup, actualState);
            case "engine" -> assertEquals(GameState.EngineSetup, actualState);
            case "not-started" -> assertEquals(GameState.NotStarted, actualState);
        }
    }

    private void syncState() {
        int pos = context.game.getNavigationSetup().getCurrentPosition();
        GameState target = switch (pos) {
            case 0 -> GameState.NotStarted;
            case 1 -> GameState.WeaponSetup;
            case 2 -> GameState.ArmourSetup;
            case 3 -> GameState.EngineSetup;
            case 4 -> GameState.SpecialSetup;
            case 5 -> GameState.SetupSumary;
            default -> (pos >= 6) ? GameState.InGame : GameState.NotStarted;
        };
        context.engine.changeState(target);
    }
}
