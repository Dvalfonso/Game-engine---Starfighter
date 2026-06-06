package ar.edu.unrc.dc.tdd.cell;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import ar.edu.unrc.dc.models.*;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.cell.enemy.*;

public class IntegrationBoardWithCellCollisionTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(10, 10);
    }
    
    
    @Test
    public void testStarfighterMovesToEmptySpace() {

        Position startPos = new Position(5, 2);
        Position targetPos = new Position(5, 3);

        Starfighter starfighter = new Starfighter(startPos);
        
        board.updateCell(startPos, starfighter);

        board.moveCell(starfighter, targetPos);

        Cell cellAtStart = board.getCell(startPos);
        assertTrue(cellAtStart instanceof EmptySpace);

        Cell cellAtTarget = board.getCell(targetPos);
        assertTrue(cellAtTarget instanceof Starfighter);

        assertEquals(targetPos, starfighter.getPosition());
    }



    @Test
    public void testStarfighterDestroysEnemyAndTakesDamage() {
    
        Position sfStartPos = new Position(3, 1);
        Position enemyPos = new Position(3, 2); 

        // Crear el Starfighter en su posición inicial
        // El constructor por defecto le da 100 de vida
        Starfighter starfighter = new Starfighter(sfStartPos);
        
        int sfInitialHealth = starfighter.getCurrentHealth(); 

        Enemy grunt = new Grunt(enemyPos, starfighter, null);
        grunt.hurt(20);

        // Obtener la vida actual del Grunt para saber cuánto daño recibirá el SF
        int gruntCurrentHealth = grunt.getCurrentHealth();

        // Colocar ambas entidades en el tablero
        board.updateCell(sfStartPos, starfighter);
        board.updateCell(enemyPos, grunt);

        // Mover el Starfighter hacia la posición donde está el Enemy

        board.moveCell(starfighter, enemyPos);

        Cell cellAtStartPos = board.getCell(sfStartPos);
        assertTrue(cellAtStartPos instanceof EmptySpace);
        
        Cell cellAtEnemyPos = board.getCell(enemyPos);
        assertTrue(cellAtEnemyPos instanceof Starfighter);
    
        // --- Verificación 3: El Starfighter debe haber actualizado su posición interna ---
        assertEquals(enemyPos, starfighter.getPosition());


        int expectedDamage = gruntCurrentHealth; // El daño es la vida del Grunt (100)
        int expectedHealthAfterCollision = sfInitialHealth - expectedDamage; // 100 - 100 = 0 o menos
        
        assertEquals(expectedHealthAfterCollision, starfighter.getCurrentHealth());

        // --- Verificación 5: El Starfighter NO debe estar muerto ---
        assertFalse(starfighter.isDead());
    }

    @Test
    public void testStarfighterDiesCollidingWithEnemy() {
        
        // Definir las posiciones en el tablero
        Position sfStartPos = new Position(4, 1); 
        Position enemyPos = new Position(4, 2);    
        
        
        Starfighter starfighter = new Starfighter(sfStartPos);
        
        
        starfighter.hurt(60);
        int sfInitialHealth = starfighter.getCurrentHealth(); 
        
        assertEquals(50, sfInitialHealth);
        
        // Crear un Enemy fuerte (Fighter tiene 150 de vida por defecto)
        Enemy fighter = new Fighter(enemyPos, starfighter);
        int fighterCurrentHealth = fighter.getCurrentHealth();
        
        
        board.updateCell(sfStartPos, starfighter);
        board.updateCell(enemyPos, fighter);
        
        
        board.moveCell(starfighter, enemyPos);
        
        Cell cellAtEnemyPos = board.getCell(enemyPos);
        assertTrue(cellAtEnemyPos instanceof SFDestroyed);
        
        
        assertEquals(0, starfighter.getCurrentHealth());
        
        assertTrue(starfighter.isDead());
        
        //Verificacion si Enemy también fue destruido
        assertFalse(cellAtEnemyPos instanceof Enemy);
    }


    @Test
    public void testFriendlyProjectileKillsEnemy() {

        Position projectilePos = new Position(2, 4); 
        Position enemyPos = new Position(2, 5);


        int projectileDamage = 100;
        FriendlyProjectile projectile = new FriendlyProjectile(projectilePos, projectileDamage);


        Starfighter starfighter = new Starfighter(new Position(0, 0));

        Enemy grunt = new Grunt(enemyPos, starfighter, null);

        grunt.hurt(50);

        int gruntHealthBeforeCollision = grunt.getCurrentHealth();
        int gruntArmour = 1;

        board.updateCell(projectilePos, projectile);
        board.updateCell(enemyPos, grunt);

        // Daño efectivo = max(damage - armour, 0)
        int expectedDamage = Math.max(projectileDamage - gruntArmour, 0);

        
        int expectedHealthAfterHit = gruntHealthBeforeCollision - expectedDamage;

        board.moveCell(projectile, enemyPos);

        Cell cellAtProjectilePos = board.getCell(projectilePos);
        assertTrue(cellAtProjectilePos instanceof EmptySpace);
        
        // Como el proyectil mata al Enemy, ambos se destruyen
        Cell cellAtEnemyPos = board.getCell(enemyPos);
        
        assertFalse(cellAtEnemyPos instanceof Enemy);
    
        //  El Enemy debe estar marcado como MUERTO
        assertTrue(grunt.isDead());
    }



@Test
void testFriendlyProjectileVsEnemyProjectile_FriendlyWins() {

    Position friendlyPos = new Position(3, 2);
    Position enemyProjPos = new Position(3, 3);
    

    int friendlyDamage = 80;
    FriendlyProjectile friendlyProjectile = new FriendlyProjectile(friendlyPos, friendlyDamage);
    

    int enemyDamage = 30;
    EnemyProjectile enemyProjectile = new EnemyProjectile(enemyProjPos, enemyDamage);
    

    board.updateCell(friendlyPos, friendlyProjectile);
    board.updateCell(enemyProjPos, enemyProjectile);

    
    // Calcular el daño esperado del proyectil amigo después de la colisión
    int expectedDamageAfterCollision = friendlyDamage - enemyDamage;

    // Mover el proyectil amigo hacia la posición del proyectil enemigo
    board.moveCell(friendlyProjectile, enemyProjPos);


    //Verificación 1: La posición ORIGINAL del proyectil amigo debe quedar vacia
    Cell cellAtFriendlyPos = board.getCell(friendlyPos);
    assertTrue(cellAtFriendlyPos instanceof EmptySpace);
    
    // La posición del proyectil ENEMIGO debe tener al proyectil amigo
    Cell cellAtEnemyPos = board.getCell(enemyProjPos);
    assertTrue(cellAtEnemyPos instanceof FriendlyProjectile);
    
    // El proyectil amigo debe tener su daño REDUCIDO 
    assertEquals(expectedDamageAfterCollision, friendlyProjectile.getDamage());



    assertEquals(enemyProjPos, friendlyProjectile.getPosition());
    }


    @Test
    public void testFriendlyProjectileVsEnemyProjectile_EnemyWins() {

        Position friendlyPos = new Position(5, 3);
        Position enemyProjPos = new Position(5, 4);


        int friendlyDamage = 25;
        FriendlyProjectile friendlyProjectile = new FriendlyProjectile(friendlyPos, friendlyDamage);


        int enemyDamage = 70;
        EnemyProjectile enemyProjectile = new EnemyProjectile(enemyProjPos, enemyDamage);


        board.updateCell(friendlyPos, friendlyProjectile);
        board.updateCell(enemyProjPos, enemyProjectile);

        // Calcular el daño esperado del proyectil enemigo después de la colisión
        int expectedDamageAfterCollision = enemyDamage - friendlyDamage;

        // Verifica que quedaron en las posiciones originales de los proyectiles
        board.moveCell(friendlyProjectile, enemyProjPos);

        Cell cellAtFriendlyPos = board.getCell(friendlyPos);
        assertTrue(cellAtFriendlyPos instanceof EmptySpace);
        

        Cell cellAtEnemyPos = board.getCell(enemyProjPos);
        assertTrue(cellAtEnemyPos instanceof EnemyProjectile);

        // Verifica que el proyectil enemigo debe tener su daño REDUCIDO ---
        assertEquals(expectedDamageAfterCollision, enemyProjectile.getDamage());


        assertEquals(enemyProjPos, enemyProjectile.getPosition());
    }



    @Test
    public void testFriendlyProjectileVsEnemyProjectile_Draw() {
        
        Position friendlyPos = new Position(6, 2);
        Position enemyProjPos = new Position(6, 3);

        // Crear ambos proyectiles con el mismo daño
        int equalDamage = 50;
        FriendlyProjectile friendlyProjectile = new FriendlyProjectile(friendlyPos, equalDamage);
        EnemyProjectile enemyProjectile = new EnemyProjectile(enemyProjPos, equalDamage);

        int friendlyDamage = friendlyProjectile.getDamage(); 
        int enemyDamage = enemyProjectile.getDamage();      


        board.updateCell(friendlyPos, friendlyProjectile);
        board.updateCell(enemyProjPos, enemyProjectile);

        board.moveCell(friendlyProjectile, enemyProjPos);

        // La posición ORIGINAL del proyectil amigo debe quedar vacia
        Cell cellAtFriendlyPos = board.getCell(friendlyPos);
        assertTrue(cellAtFriendlyPos instanceof EmptySpace);
        
        // La posición del proyectil enemigo también debe quedar vacia
        Cell cellAtEnemyPos = board.getCell(enemyProjPos);
        assertTrue(cellAtEnemyPos instanceof EmptySpace);
    }
}
