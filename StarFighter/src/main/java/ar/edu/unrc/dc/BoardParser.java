package ar.edu.unrc.dc;

import ar.edu.unrc.dc.models.*;
import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.cell.enemy.*;
import ar.edu.unrc.dc.models.projectileMovement.*;
import ar.edu.unrc.dc.models.setup.SetupFactory;
import ar.edu.unrc.dc.patterns.projectileList.ProjectileList;

import java.util.ArrayList;
import java.util.List;

public class BoardParser {
    static Starfighter sf = null;
    static ProjectileList friendlyProjectiles;
    static List<Projectile> enemyProjectiles;
    static List<Enemy> enemies;

    /**
     * Crea un Board a partir de un string de varias líneas donde
     * cada celda está separada por espacios. Ejemplo:
     *
     *  S _ G
     *  _ * _
     *
     */
    public static Board fromString(String boardString) {
        String[] rows = boardString.strip().split("\n");

        int rowCount = rows.length;
        int colCount = rows[0].trim().split("\\s+").length;

        Board board = new Board(rowCount, colCount);
        friendlyProjectiles =  new ProjectileLinkedList(board);
        enemyProjectiles = new ArrayList<>();
        enemies = new ArrayList<>();

        for (int i = 0; i < rowCount; i++) {
            String[] symbols = rows[i].trim().split("\\s+");

            for (int j = 0; j < colCount; j++) {
                char symbol = symbols[j].charAt(0);
                Position pos = new Position(i, j);
                Cell cell = createCellFromSymbol(symbol, pos);

                if (cell instanceof Starfighter) {
                    sf = (Starfighter) cell;
                } else if (cell instanceof FriendlyProjectile) {
                    FriendlyProjectile p = (FriendlyProjectile) cell;
                    p.setDamage(30);
                    p.setProjectileMovement(new RightMovementProjectile(pos, 5));//
                    friendlyProjectiles.add((Projectile) cell);
                } else if (cell instanceof EnemyProjectile) {
                    EnemyProjectile p = (EnemyProjectile) cell;
                    p.setDamage(30);
                    p.setProjectileMovement(new LeftMovementProjectile(pos,5));//
                    enemyProjectiles.add((Projectile) cell);
                } else if (cell instanceof Fighter) {
                    Fighter f = (Fighter) cell;
                    f.setBoard(board);
                    enemies.add(f);
                } else if (cell instanceof Carrier) {
                    enemies.add((Carrier) cell);
                } else if (cell instanceof Grunt) {
                    enemies.add((Grunt) cell);
                } else if (cell instanceof Interceptor) {
                    enemies.add((Interceptor) cell);
                } else if (cell instanceof Pylon) {
                    enemies.add((Pylon) cell);
                }
                board.updateCell(pos, cell);
            }
        }

        return board;
    }


    private static Cell createCellFromSymbol(char symbol, Position position) {
        return switch (symbol) {
            case 'S' -> new Starfighter(position);
            case '*' -> new FriendlyProjectile(position);
            case 'X' -> new SFDestroyed(position);
            case 'G' -> new Grunt(position);
            case 'F' -> new Fighter(position);
            case 'C' -> new Carrier(position);
            case 'I' -> new Interceptor(position);
            //TODO tenemos que mejorar la creacion de tableros para que coincida mejor
            case 'P' -> new Pylon(position, new Starfighter(new Position(0,0)), new ArrayList<Enemy>(), new Board(10,10), new ProjectileLinkedList());
            case '<' -> new EnemyProjectile(position);
            case '_', ' ' -> new EmptySpace(position);
            default -> throw new IllegalArgumentException("Símbolo desconocido: " + symbol);
        };
    }

    /**
     * Crea un Game a partir de un string de varias líneas donde
     * cada celda está separada por espacios. Ejemplo:
     *
     *  S _ G
     *  _ * _
     *
     */
    public static Game ParserGameBoard(String boardString) {
        Board board = fromString(boardString);
        Game newGame = new Game(board.getRows(), board.getCols());
        newGame.setBoard(board);
        newGame.setGameState(GameState.InGame);
        newGame.setStarfighter(sf);
        newGame.setFriendlyProjectiles(friendlyProjectiles);

        EnemyManager enemyManager = new EnemyManager(newGame.getBoard(), newGame.getStarfighter());
        enemyManager.addEnemyProjectiles(enemyProjectiles);
        enemyManager.addEnemies(enemies);

        newGame.setEnemyManager(enemyManager);

        setEnemiesThings(newGame.getStarfighter(), newGame.getBoard());
        SetupFactory setupFactory = new SetupFactory();
        newGame.setSetup(setupFactory.createSetup());

        return newGame;
    }

    private static void setEnemiesThings(Starfighter starfighter, Board board) {
        for (Enemy e : enemies) {
            e.setStarfighter(starfighter);
            e.setBoard(board);
        }
    }
}
