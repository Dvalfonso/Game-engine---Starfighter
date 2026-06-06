package ar.edu.unrc.dc.models.cell;

import java.util.Objects;

public class Position {
    private int row;
    private int col;

    public Position() {
    }

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Position to(int i, int j) {
        return new Position(row + i, col + j);
    }

    public int manhattanDistance(Position position) {
        return (Math.abs(row - position.row) + Math.abs(col - position.col));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "(" + row + "," + col +")";
    }

    public boolean isSameRow(Position position) {
        return row == position.row;
    }

    public boolean isSameCol(Position position) {
        return col == position.col;
    }

    public int verDirectionRelativeTo(Position position) {
        int diff = row - position.row;
        return diff == 0 ? 0 : diff/Math.abs(diff);
    }
}
