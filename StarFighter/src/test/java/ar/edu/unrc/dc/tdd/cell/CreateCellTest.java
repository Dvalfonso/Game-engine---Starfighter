package ar.edu.unrc.dc.tdd.cell;

import ar.edu.unrc.dc.models.*;
import ar.edu.unrc.dc.models.cell.*;
import org.junit.jupiter.api.*;

public class CreateCellTest {

    @Test
    void CreateStarfighterCellTest() {
        Position pos = new Position(0,0);
        Cell starfighter = new Starfighter(pos);

        Assertions.assertEquals(pos, starfighter.getPosition());
    }

    @Test
    void CreateEmptyCellTest() {
        Position pos = new Position(0,0);
        Cell emptySpace = new EmptySpace(pos);

        Assertions.assertEquals(pos, emptySpace.getPosition());
    }

    @Test
    void CreateSFDestroyedTest() {
        Position pos = new Position(0,0);
        Cell starfighterDestroyed = new SFDestroyed(pos);

        Assertions.assertEquals(pos, starfighterDestroyed.getPosition());
    }

    @Test
    void CreateFriendlyProjectileTest() {
        Position pos = new Position(0,0);
        int damage = 30;
        Projectile projectile = new FriendlyProjectile(pos,damage);

        Assertions.assertEquals(pos, projectile.getPosition());
        Assertions.assertEquals(damage, projectile.getDamage());
    }

    @Test
    void CreateEnemyProjectileTest() {
        Position pos = new Position(0,0);
        int damage = 30;
        Projectile projectile = new EnemyProjectile(pos,damage);

        Assertions.assertEquals(pos, projectile.getPosition());
        Assertions.assertEquals(damage, projectile.getDamage());
    }
}
