package ar.edu.unrc.dc.tdd.game;

import ar.edu.unrc.dc.models.*;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.cell.enemy.Grunt;
import ar.edu.unrc.dc.models.components.Armours.BlindArmour;
import ar.edu.unrc.dc.models.components.Engines.AcelerationEngine;
import ar.edu.unrc.dc.models.components.Specials.Overcharge;
import ar.edu.unrc.dc.models.components.Weapons.Rocket;
import ar.edu.unrc.dc.models.projectileMovement.*;
import ar.edu.unrc.dc.models.setup.Setup;
import ar.edu.unrc.dc.models.starfighterMovement.StarfighterMovement;
import ar.edu.unrc.dc.models.starfighterMovement.VerticalHorizontalMovement;

import static org.junit.jupiter.api.Assertions.*;

import ar.edu.unrc.dc.patterns.projectileList.ProjectileList;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class GameTest {
    
    private Game game;

    @Test 
    public void constructorGameTest(){
        game = new Game(6,10);
        assertTrue(game.getFriendlyProjectiles().isEmpty());
        assertTrue(game.getEnemyProjectiles().isEmpty());
    }

    @BeforeEach
    void setUp() {
        game = new Game(6,10);
    }

    @Test 
    public void projectileListTest(){
        Projectile p1 = new FriendlyProjectile(new Position(0, 2),0);
        Projectile p2 = new FriendlyProjectile(new Position(1, 4),0);
        Projectile p3 = new FriendlyProjectile(new Position(2, 6),0);

        ProjectileList l = new ProjectileLinkedList(game.getBoard());

        l.add(p1);
        l.add(p2);
        l.add(p3);

        game.setFriendlyProjectiles(l);
        assertEquals(3,game.getFriendlyProjectiles().size());
    }

    @Test
    void fireWeaponTest() {
        game.clearProjectiles();
        game.fireWeapon();
        assertEquals(0, game.getFriendlyProjectiles().size());
    }

    @Test
    void fireWeaponTest2() {
        game.clearProjectiles();
        game.moveStarfighter(0,3);
        game.fireWeapon();
        assertEquals(2, game.getFriendlyProjectiles().size());
    }
   
    //StarFighter Movement Tests:
    @Test
    void verticalMoveDownTest() {
        Position start = game.getStarfighter().getPosition(); 
        game.moveStarfighter(2, 0); 

        Position end = game.getStarfighter().getPosition();
        assertEquals(new Position(start.getRow() - 2, start.getCol()), end);
    }

    @Test
    void verticalMoveUpTest() {
        Position start = game.getStarfighter().getPosition(); 
        game.moveStarfighter(-2, 0);

        Position end = game.getStarfighter().getPosition();
        assertEquals(new Position(start.getRow() + 2, start.getCol()), end);
    }

    @Test
    void horizontalMoveRightTest() {
        Position start = game.getStarfighter().getPosition(); // (3,0)
        game.moveStarfighter(0, 3); 

        Position end = game.getStarfighter().getPosition();
        assertEquals(new Position(start.getRow(), start.getCol() + 3), end);
    }

    @Test
    void horizontalMoveLeftTest() {
        game.getStarfighter().setPosition(new Position(3, 4));
        Position start = game.getStarfighter().getPosition();

        game.moveStarfighter(0, -2);

        Position end = game.getStarfighter().getPosition();
        assertEquals(new Position(start.getRow(), start.getCol() - 2), end);
    }

    @Test
    void diagonalMoveDownRightTest() {
        Position start = game.getStarfighter().getPosition(); // (3,0)
       
        game.moveStarfighter(1, 2); 

        Position end = game.getStarfighter().getPosition();
        assertEquals(new Position(start.getRow() - 1, start.getCol() + 2), end);
    }

    @Test
    void getNavigationSetupTest() {
        Assertions.assertEquals(0, game.getNavigationSetup().getCurrentPosition());
    }

    @Test
    void getSetupTest() {
        Setup setup = game.getSetup();
        Assertions.assertInstanceOf(Rocket.class, setup.getWeapon());
        Assertions.assertInstanceOf(BlindArmour.class, setup.getArmour());
        Assertions.assertInstanceOf(AcelerationEngine.class, setup.getEngine());
        Assertions.assertInstanceOf(Overcharge.class, setup.getSpecial());
    }

    @Test
    void setStarfighterMovementTest() {
        StarfighterMovement sfMovement = new VerticalHorizontalMovement();
        game.setStarFighterMovement(sfMovement);
        Assertions.assertInstanceOf(VerticalHorizontalMovement.class, game.getStarfighterMovement());
    }

    @Test
    void changeGameStateTest1() {
        game.changeGameState(GameState.WeaponSetup);
        Assertions.assertInstanceOf(GameState.WeaponSetup.getClass(), game.getGameState());
    }

    @Test
    void changeGameStateTest2() {
        game.changeGameState(GameState.ArmourSetup);
        Assertions.assertInstanceOf(GameState.ArmourSetup.getClass(), game.getGameState());
    }

    @Test
    void changeGameStateTest3() {
        game.changeGameState(GameState.EngineSetup);
        Assertions.assertInstanceOf(GameState.EngineSetup.getClass(), game.getGameState());
    }

    @Test
    void changeGameStateTest4() {
        game.changeGameState(GameState.SpecialSetup);
        Assertions.assertInstanceOf(GameState.SpecialSetup.getClass(), game.getGameState());
    }

    @Test
    void changeGameStateTest5() {
        game.changeGameState(GameState.SetupSumary);
        Assertions.assertInstanceOf(GameState.SetupSumary.getClass(), game.getGameState());
    }
}
