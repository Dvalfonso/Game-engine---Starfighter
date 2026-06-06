package ar.edu.unrc.dc.stepdefinitions;

import ar.edu.unrc.dc.models.cell.Enemy;
import ar.edu.unrc.dc.models.cell.Position;
import ar.edu.unrc.dc.models.cell.Starfighter;
import ar.edu.unrc.dc.models.components.Specials.*;
import ar.edu.unrc.dc.models.setup.Setup;
import ar.edu.unrc.dc.models.starfighterCommand.SpecialCommand;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class SpecialStepDefinitions {
    private final Context context;
    private List<Enemy> enemiesBeforeStrike = new ArrayList<>();
    private int starfighterHealthBeforeOvercharge;
    private int starfighterEnergyBeforeOvercharge;

    public SpecialStepDefinitions(Context context) {
        this.context = context;
    }

    @And("execute the deploy drones command")
    public void execute_deploy_drones() {
        Setup dronesSetup = context.game.getSetup();

        dronesSetup.setSpecial(new DeployDrones(10));

        context.game.setSetup(dronesSetup);

        context.game.playTurn(new SpecialCommand(context.game));
    }

    @And("execute the Repair command")
    public void execute_repair() {
        Setup repairSetup = context.game.getSetup();
        repairSetup.setSpecial(new Repair(10));

        context.game.setSetup(repairSetup);

        context.game.playTurn(new SpecialCommand(context.game));
    }

    @And("execute the orbital strike command")
    public void execute_orbital_strike() {
        Setup orbitalStrikeSetup = context.game.getSetup();
        orbitalStrikeSetup.setSpecial(new OrbitalStrike(10));

        context.game.setSetup(orbitalStrikeSetup);

        enemiesBeforeStrike.addAll(context.game.getEnemies());

        context.game.playTurn(new SpecialCommand(context.game));
    }

    @And("execute the overcharge command")
    public void execute_overcharge() {
        Setup overchargeSetup = context.game.getSetup();
        overchargeSetup.setSpecial(new Overcharge(10));

        context.game.setSetup(overchargeSetup);
        starfighterHealthBeforeOvercharge = context.game.getStarfighter().getCurrentHealth();
        starfighterEnergyBeforeOvercharge = context.game.getStarfighter().getCurrentEnergy();
        context.game.playTurn(new SpecialCommand(context.game));
    }

    @And("execute the recall command")
    public void execute_recall() {
        Setup recallSetup = context.game.getSetup();
        recallSetup.setSpecial(new Recall(10));

        context.game.setSetup(recallSetup);

        context.game.playTurn(new SpecialCommand(context.game));
    }

    @Then("all the projectiles disappear from the board")
    public void projectiles_disappear() {
        Assertions.assertEquals(0, context.game.getFriendlyProjectiles().size());
        Assertions.assertEquals(0, context.game.getEnemyProjectiles().size());
    }

    @Then("the starfighter heals 50 units")
    public void starfighter_heals() {
        Assertions.assertEquals(150, context.game.getStarfighter().getCurrentHealth());
    }

    @Then("all the enemies take damage")
    public void all_enemies_take_damage() {
        List<Enemy> enemyAfterStrike = context.game.getEnemies();

        for (Enemy after : enemyAfterStrike) {
            for (Enemy before : enemiesBeforeStrike) {
                if (before.equals(after)) {
                    Assertions.assertTrue(before.getCurrentHealth() >= after.getCurrentHealth());
                }
            }
        }
    }

    @Then("the starfighter lose current health for gaining current energy")
    public void starfighter_lose_health_gain_energy() {
        Assertions.assertTrue(starfighterHealthBeforeOvercharge >= context.game.getStarfighter().getCurrentHealth());
        Assertions.assertTrue(starfighterEnergyBeforeOvercharge <= context.game.getStarfighter().getCurrentEnergy());
    }

    @Then("the Starfighter teleport to the spawn location of the game")
    public void starfighter_teleport_to_spawn() {
        Position spawnPosition = new Position(4,0);
        Assertions.assertTrue(spawnPosition.equals(context.game.getStarfighter().getPosition()));
    }
}
