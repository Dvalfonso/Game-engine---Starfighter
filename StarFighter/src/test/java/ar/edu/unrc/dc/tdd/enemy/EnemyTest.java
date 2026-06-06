package ar.edu.unrc.dc.tdd.enemy;

import org.junit.jupiter.api.Test;
import ar.edu.unrc.dc.doubleOfTest.EnemyMock;
import ar.edu.unrc.dc.models.*;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.cell.enemy.*;
import io.cucumber.java.lu.a.as;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    @Test
    public void enemyHurtTest() {
        Enemy enemy = new EnemyMock();

        enemy.hurt(13);

        assertEquals(12, enemy.getCurrentHealth());
    }

    @Test
    public void enemyShouldDiedTest() {
        Enemy enemy = new EnemyMock();

        enemy.hurt(25);

        assertTrue(enemy.isDead());
    }

    @Test
    public void enemyRegenerateSpecificAmountTest() {
        Enemy enemy = new EnemyMock();

        enemy.hurt(10);
        enemy.regenerate(5);

        assertEquals(15, enemy.getCurrentHealth());
    }

    @Test
    public void enemyRegenerateTest() {
        Enemy enemy = new EnemyMock();

        enemy.hurt(10);
        enemy.regenerate();

        assertEquals(15, enemy.getCurrentHealth());
    }

    @Test
    public void enemyHurtWithZeroDamageShouldNotChangeHealth() {
        Enemy enemy = new EnemyMock();

        int initialHealth = enemy.getCurrentHealth();

        enemy.hurt(0);  

        assertEquals(initialHealth, enemy.getCurrentHealth());
        assertFalse(enemy.isDead());
    }

    @Test
    public void enemyRegenerateDoesNothingWhenAtFullHealth() {
        Enemy enemy = new EnemyMock();

        int initialHealth = enemy.getCurrentHealth();


        enemy.regenerate();
        assertEquals(initialHealth, enemy.getCurrentHealth());

        enemy.regenerate(10);
        assertEquals(initialHealth, enemy.getCurrentHealth());
    }

    @Test
    public void enemyRegenerateDoesNothingWhenDead() {
        Enemy enemy = new EnemyMock();

        enemy.hurt(1000);
        assertTrue(enemy.isDead());
        assertEquals(0, enemy.getCurrentHealth());

        enemy.regenerate();
        assertEquals(0, enemy.getCurrentHealth());

        enemy.regenerate(50);
        assertEquals(0, enemy.getCurrentHealth());
    }

    @Test
    public void moveVerticalUp() {
        Board board = new Board(10, 10);

        Position position = new Position(5,5);
        Enemy enemy = new Interceptor(position);
        enemy.setBoard(board);

        board.updateCell(position, enemy);

        enemy.moveVertical(2);

        assertSame(board.getCell(position.to(2,0)), enemy);
    }

    @Test
    public void moveVerticalDown() {
        Board board = new Board(10, 10);

        Position position = new Position(5,5);
        Enemy enemy = new Interceptor(position);
        enemy.setBoard(board);

        board.updateCell(position, enemy);

        enemy.moveVertical(-2);

        assertSame(board.getCell(position.to(-2,0)), enemy);
    }

    @Test
    public void moveVerticalZero() {
        Board board = new Board(10, 10);

        Position position = new Position(5,5);
        Enemy enemy = new Interceptor(position);
        enemy.setBoard(board);

        board.updateCell(position, enemy);

        enemy.moveVertical(0);

        assertSame(board.getCell(position), enemy);
    }
}
