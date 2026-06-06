package ar.edu.unrc.dc.models;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import ar.edu.unrc.dc.models.cell.*;
import ar.edu.unrc.dc.models.cell.enemy.*;
import ar.edu.unrc.dc.patterns.DisplayObserver;

public class Board {
    private Game game;
    private List<DisplayObserver> observers;

    private Integer rows;
    private Integer columns;
    private List<List<Cell>> gridCell;

    /**
     * Board grid constructor
     * @param r Number of rows, must be an integer between 3 and 10 included
     * @param c Number of columns, must be an integer between 5 and 30 included
     */
    public Board(int r , int c){
        observers = new ArrayList<>();
        this.rows = r ;
        this.columns = c ; 
        this.gridCell = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            ArrayList<Cell> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                row.add(new EmptySpace(new Position(i, j)));
            }
            gridCell.add(row);
        }
    }

    public void setObservers(List<DisplayObserver> observers) {
        this.observers = observers;
    }

    public void setGame(Game game) { this.game = game; }

    public int getRows(){ return rows; }

    public int getCols(){ return columns; }

    public Cell getCell(Position p){
        checkPosition(p);

        int i  = p.getRow();
        int j  = p.getCol();
        return gridCell.get(i).get(j);
    }

    public void moveCell(Cell cell, Position p) {
        Position currentPos = cell.getPosition();
        updateCell(currentPos, new EmptySpace(currentPos));

        if (isValidPosition(p)) {
            Cell targetCell = getCell(p);
            Cell newCell = cell.collideWith(targetCell);
            updateCell(p, newCell);
            if (newCell instanceof SFDestroyed) {
                abortGame();
                return;
            }
        } else
            cell.destroy();

        notifyObservers();
    }

    public void updateCell(Position p, Cell cell) {
        checkPosition(p);

        int i = p.getRow();
        int j = p.getCol();
        cell.setPosition(p);
        gridCell.get(i).set(j, cell);
    }

    public void updateCellWithCollision(Position p, Cell cell) {
        checkPosition(p);

        Cell targetCell = getCell(p);
        updateCell(p, cell.collideWith(targetCell));
    }

    public Position spawnPosition() {
        Position randomPosition;
        do {
            randomPosition = generateRandomPosition();
        } while (getCell(randomPosition) instanceof Enemy);

        return randomPosition;
    }

    public boolean isValidPosition(Position p){
        int i  = p.getRow();
        int j  = p.getCol();
        return i >= 0 && i < rows && j >= 0 && j < columns;
    }

	private Position generateRandomPosition() {
        int row = ThreadLocalRandom.current().nextInt(0, rows);
        int col = ThreadLocalRandom.current().nextInt(0, columns);
        return new Position(row,col);
	}

    private void checkPosition(Position position) {
        if (!isValidPosition(position)) throw new IllegalArgumentException(
            "Invalid position: " + position + ". Rows valid [0.." + (rows-1) + "], Cols valid [0.." + (columns-1) + "]");
    }

    public String toString(boolean fog) {
        StringBuilder sb = new StringBuilder();
        Starfighter sf = findStarfighter();
        boolean hasSF = sf != null;
            
        Position sfPosition = hasSF ? sf.getPosition() : null;
            
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
            
                Cell cell = gridCell.get(i).get(j);
            
                if (fog && hasSF) {
                    int distanceWithSF = sfPosition.manhattanDistance(cell.getPosition());
                    if (distanceWithSF > sf.getVision()) {
                        sb.append("? ");
                        continue;
                    }
                }
            
                switch (cell) {
                    case Starfighter s -> sb.append("S ");
                    case FriendlyProjectile fp -> sb.append("* ");
                    case SFDestroyed sd -> sb.append("X ");
                    case Grunt g -> sb.append("G ");
                    case Fighter f -> sb.append("F ");
                    case Carrier c -> sb.append("C ");
                    case Interceptor it -> sb.append("I ");
                    case Pylon p -> sb.append("P ");
                    case EnemyProjectile ep -> sb.append("< ");
                    case EmptySpace es -> sb.append("_ ");
                    case null, default -> sb.append("_ ");
                }
            }
            if (i < rows - 1)
                sb.append("\n");
        }

        return sb.toString();
    }

	private Starfighter findStarfighter() {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                if (gridCell.get(i).get(j) instanceof Starfighter)
                    return (Starfighter) gridCell.get(i).get(j);
        return null;
    }

    private void notifyObservers() {
        for (DisplayObserver observer:observers)
            observer.update(this);
    }

    private void abortGame() {
        if (game != null)
            game.abort();
    }
}
