package ar.edu.unrc.dc.stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ar.edu.unrc.dc.controllers.StarfighterGameEngine;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.setup.Setup;
import ar.edu.unrc.dc.views.SetupMenu;
import io.cucumber.java.en.*;

public class WeaponSelectionStepDefinitions {
    private final Context context;

    public WeaponSelectionStepDefinitions(Context context){
        this.context = context;
    }

    @Given("I am on the weapon setup stage")
    public void i_am_on_the_weapon_setup_stage(){
        context.engine = new StarfighterGameEngine();
        
        context.engine.play(6, 10);
        
        context.game = context.engine.getGame();
        context.setupMenu = new SetupMenu();

        context.engine.changeState(GameState.WeaponSetup);
        context.game.getNavigationSetup().setCurrentPosition(1);
    }

    @When("I select weapon {int}")
    public void i_select_weapon(int option){
        context.engine.select(option);
    }

    @When("I select weapon option {int}")
    public void i_select_weapon_option(int option){
        context.engine.select(option);
    }
    
    @Then("the selected weapon is option {int}")
    public void the_selected_weapon_is_option(int option){
        Setup setup = context.game.getNavigationSetup().createSetup();

        if (option == 3) {
            assertEquals("Splitter", setup.getWeapon().toString());
        } else if (option == 1){
                assertEquals("Rocket", setup.getWeapon().toString());
            } else {
                throw new AssertionError("Unsupported weapon option in test: " + option);
    }
    }
}
