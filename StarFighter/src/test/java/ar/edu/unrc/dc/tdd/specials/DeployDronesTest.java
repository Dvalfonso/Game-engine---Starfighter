package ar.edu.unrc.dc.tdd.specials;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import ar.edu.unrc.dc.models.setup.*;
import org.junit.jupiter.api.*;

import ar.edu.unrc.dc.models.*;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.components.Specials.DeployDrones;

public class DeployDronesTest {
    private Game game;
    private Board board;
    private DeployDrones deployDrones;

    @BeforeEach
    public void setUp() {
        game = new Game(5, 5);
        board = game.getBoard();
        deployDrones = new DeployDrones(10);
        SetupFactory setupFactory = new SetupFactory();
        Setup setup = setupFactory.createSetup();
        setup.setSpecial(deployDrones);
        game.setSetup(setup);

        FriendlyProjectile fp1 = new FriendlyProjectile(new Position(1, 1), 1);
        FriendlyProjectile fp2 = new FriendlyProjectile(new Position(2, 2), 2);
        EnemyProjectile ep1 = new EnemyProjectile(new Position(3, 3), 3);
        EnemyProjectile ep2 = new EnemyProjectile(new Position(4, 4), 4);

        game.getFriendlyProjectiles().add(fp1);
        game.getFriendlyProjectiles().add(fp2);
        game.getEnemyProjectiles().add(ep1);
        game.getEnemyProjectiles().add(ep2);

        board.updateCell(fp1.getPosition(), fp1);
        board.updateCell(fp2.getPosition(), fp2);
        board.updateCell(ep1.getPosition(), ep1);
        board.updateCell(ep2.getPosition(), ep2);
    }

    @Test
    public void deployDronesClearsAllProjectilesTest() {
        deployDrones.apply(game);

        assertTrue(game.getFriendlyProjectiles().isEmpty(), "Friendly projectiles list should be empty");
        assertTrue(game.getEnemyProjectiles().isEmpty(), "Enemy projectiles list should be empty");

        List<Position> positions = List.of(
            new Position(1, 1),
            new Position(2, 2),
            new Position(3, 3),
            new Position(4, 4)
        );

        for (Position pos : positions) {
            assertTrue(board.getCell(pos) instanceof EmptySpace,
                "Position " + pos + " should be empty after DeployDrones");
        }
    }
}
