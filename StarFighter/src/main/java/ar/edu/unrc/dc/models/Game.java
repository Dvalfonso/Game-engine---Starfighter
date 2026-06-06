package ar.edu.unrc.dc.models;

import java.util.*;

import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.components.Weapons.*;
import ar.edu.unrc.dc.models.setup.*;
import ar.edu.unrc.dc.models.starfighterMovement.*;
import ar.edu.unrc.dc.patterns.*;
import ar.edu.unrc.dc.patterns.projectileList.*;

public class Game implements Subject{
    private Board board;
    private Setup setup;

    private ProjectileList friendlyProjectiles;
    private EnemyManager enemyManager;

    private Starfighter starfighter;
    private StarfighterMovement stMov;
    private List<DisplayObserver> observers;
    private GameState gameState;
    private NavigationSetup navigationSetup;
    private Position initialPosition;
    private SetupFactory setupFactory;

    public Game() {
        observers = new ArrayList<>();
    }

    public Game(int rows, int cols){
        observers = new ArrayList<>();

        this.board = new Board(rows,cols);
        board.setGame(this);
        board.setObservers(observers);

        this.initialPosition = new Position(getInitialRow(rows), 0);
        this.starfighter = new Starfighter(initialPosition);
        this.stMov = new VerticalHorizontalMovement();
        board.updateCell(initialPosition, starfighter);

        this.gameState = GameState.NotStarted;
        this.navigationSetup = new NavigationSetup();

        this.friendlyProjectiles = new ProjectileLinkedList(board);
        this.enemyManager = new EnemyManager(board, starfighter);

        this.setupFactory = new SetupFactory();
        this.setup = setupFactory.createSetup();
    }

    private int getInitialRow(int rows) {
        if (rows % 2 == 0) {
            return (rows / 2) - 1;
        } else {
            return rows / 2;
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public NavigationSetup getNavigationSetup() {
        return navigationSetup;
    }

    public void setStarfighter(Starfighter starfighter) {
        this.starfighter = starfighter;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        notifyObservers("Setup");
    }

    public Setup getSetup() {
        if (setup == null) {
            setup = new Setup();
            // Inicializar con un weapon por defecto para que los tests funcionen
            setup.setWeapon(new Standard());
        }
        return setup;
    }

    public void setSetup(Setup setup) {
        this.setup = setup;
    }

    public Board getBoard(){ return board; }

    public void setBoard(Board board) { this.board = board; }

    public Starfighter getStarfighter(){
        return starfighter;
    }

    public Position getInitialPosition(){
        return initialPosition;
    }

    public void setStarFighterMovement(StarfighterMovement stMov){
        this.stMov = stMov;
    }

    public List<Enemy> getEnemies(){
        return enemyManager.getEnemies();
    }

    public List<Projectile> getFriendlyProjectiles() {
        return friendlyProjectiles.getList();
    }

    public void setFriendlyProjectiles(ProjectileList newProjectiles){
        this.friendlyProjectiles = newProjectiles;
    }

    public void fireWeapon() {
        List<Projectile> created = setup.getWeapon().fire(starfighter.getPosition());

        for (Projectile projectile:created) {
            friendlyProjectiles.add(projectile);
        }
    }
    
    public List<Projectile> getEnemyProjectiles() {
        return enemyManager.getEnemyProjectiles();
    }

    public void playTurn(StarfighterCommand command) {
        friendlyProjectiles.move();
        enemyManager.moveProjectiles();
        command.execute();
        enemyManager.enemyVisionUpdate();
        enemyManager.enemyAct(command);
        enemyManager.enemyVisionUpdate();
        enemyManager.enemySpawn();
        notifyObservers("playTurn");
    }

    public void setEnemyManager(EnemyManager enemyManager) {
        this.enemyManager = enemyManager;
    }

    public void moveStarfighter(int verticalMove, int horizontalMove) {
        if (starfighter.isDestroyed()) {
            throw new RuntimeException("El starfighter fue destruido");
        } else {
            Position currentPosition = starfighter.getPosition();

            List<Position> path = stMov.moveSequence(currentPosition, verticalMove, horizontalMove);

            for (Position p : path) {
                board.moveCell(starfighter, p);
            }
            notifyObservers("move");
        }
    }

    @Override
    public void attach(DisplayObserver observer) {
        observers.add(observer);
    }

    @Override
    public void deattach(DisplayObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String command) {
        for (DisplayObserver observer:observers)
            observer.update(command, this);
    }

    public void abort() {
        gameState = GameState.GameOver;
        notifyObservers("abort");
    }

    public void changeGameState(GameState gameState) {
        this.gameState = gameState;
        if (gameState == GameState.WeaponSetup) navigationSetup.setCurrentPosition(1);
        if (gameState == GameState.ArmourSetup) navigationSetup.setCurrentPosition(2);
        if (gameState == GameState.EngineSetup) navigationSetup.setCurrentPosition(3);
        if (gameState == GameState.SpecialSetup) navigationSetup.setCurrentPosition(4);
        if (gameState == GameState.SetupSumary) navigationSetup.setCurrentPosition(5);
        notifyObservers("Change state");
    }

    public void clearProjectiles() {
        friendlyProjectiles.removeAll();
        enemyManager.clearEnemyProjectiles();
    }

    public StarfighterMovement getStarfighterMovement() {
        return stMov;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }
}
