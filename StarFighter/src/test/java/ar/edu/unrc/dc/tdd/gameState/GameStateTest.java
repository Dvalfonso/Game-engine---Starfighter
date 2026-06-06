package ar.edu.unrc.dc.tdd.gameState;

import ar.edu.unrc.dc.controllers.StarfighterGameEngine;
import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.GameState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameStateTest {
    private Game game;

    @BeforeEach
    void setup() {
        game = new Game(10, 30);
    }

    @Test
    void notStartedGameTest() {
        Assertions.assertEquals(GameState.NotStarted, game.getGameState());
    }

    @Test
    void changeToWeaponSetup() {
        game.changeGameState(GameState.WeaponSetup);
        Assertions.assertEquals(1, game.getNavigationSetup().getCurrentPosition());
        Assertions.assertEquals(GameState.WeaponSetup, game.getGameState());
    }

    @Test
    void changeToArmourSetup() {
        game.changeGameState(GameState.ArmourSetup);
        Assertions.assertEquals(2, game.getNavigationSetup().getCurrentPosition());
        Assertions.assertEquals(GameState.ArmourSetup, game.getGameState());
    }

    @Test
    void changeToEngineSetup() {
        game.changeGameState(GameState.EngineSetup);
        Assertions.assertEquals(3, game.getNavigationSetup().getCurrentPosition());
        Assertions.assertEquals(GameState.EngineSetup, game.getGameState());
    }

    @Test
    void changeToSpecialSetup() {
        game.changeGameState(GameState.SpecialSetup);
        Assertions.assertEquals(4, game.getNavigationSetup().getCurrentPosition());
        Assertions.assertEquals(GameState.SpecialSetup, game.getGameState());
    }

    @Test
    void changeToSetupSumary() {
        game.changeGameState(GameState.SetupSumary);
        Assertions.assertEquals(5, game.getNavigationSetup().getCurrentPosition());
        Assertions.assertEquals(GameState.SetupSumary, game.getGameState());
    }
}
