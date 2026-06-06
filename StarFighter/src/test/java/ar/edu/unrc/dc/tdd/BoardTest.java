package ar.edu.unrc.dc.tdd;

import static org.junit.jupiter.api.Assertions.*;

import ar.edu.unrc.dc.BoardParser;
import ar.edu.unrc.dc.models.cell.enemy.*;
import org.junit.jupiter.api.*;
import ar.edu.unrc.dc.models.*;
import ar.edu.unrc.dc.models.cell.*;

public class BoardTest {
    @Test 
    public void constructorBoardTest1(){ 
        Board b1 = new Board(3,5);
        assertEquals(3,b1.getRows());
        assertEquals(5,b1.getCols());
    }

    @Test 
    public void constructorBoardTest2(){
        Board b2 = new Board(10,30);
        assertEquals(10,b2.getRows());
        assertEquals(30,b2.getCols());
    }

    @Test void testBordesValidatePosicion1(){
        Board board = new Board(6, 20);
        assertTrue(board.isValidPosition(new Position(0, 0)));
        assertTrue(board.isValidPosition(new Position(board.getRows()-1, board.getCols() -1)));
    }

    @Test 
    public void testValidatePosicion1(){
        Board board = new Board(8, 27);
        assertTrue(board.isValidPosition(new Position(5,25)));
    }

    @Test
    public void testValidatePosicion2(){
        Board board = new Board(8, 27);
        assertFalse(board.isValidPosition(new Position(9,25)));
    }

    @Test
    public void testGetCell1(){
        Board board = new Board(6, 20);
        Position pos = new Position(4, 15);
        Cell cell = board.getCell(pos);
        assertEquals(pos, cell.getPosition());
    }
    @Test
    public void testGetCell2(){
        Board board = new Board(6, 20);
        Position pos = new Position(8, 15);
        assertThrows(IllegalArgumentException.class , () -> {
            Cell cell = board.getCell(pos);
        });
    }

    @Test
    public void testMoveCellOutOfBoard() {
        Board board = new Board(5, 5);

        Starfighter sf = new Starfighter(new Position(1,1));
        board.moveCell(sf, new Position(99, 99));
        assertTrue(sf.isDestroyed());
    }

    @Test
    public void testMoveCell() {
        Board board = new Board(5, 5);

        Position origin = new Position(1,1);
        Position dest = new Position(1,2);

        Starfighter sf = new Starfighter(origin);
        board.updateCell(origin, sf);

        board.moveCell(sf, dest);

        assertTrue(board.getCell(origin) instanceof EmptySpace);
        assertSame(sf, board.getCell(dest)); 
    }

    @Test
    public void testMoveCellThrowsException2() {
        Board board = new Board(5, 5);

        Starfighter sf = new Starfighter(new Position(-1,0)); 
        Position dest = new Position(0,0);

        assertThrows(IllegalArgumentException.class, () -> {
            board.moveCell(sf, dest);
        });
    }

    @Test
    public void testUpdateCellUpdatesCell() {
        Board board = new Board(5, 5);
        Position pos = new Position(2, 2);

        Starfighter sf = new Starfighter(pos);

        board.updateCell(pos, sf);

        assertSame(sf, board.getCell(pos));
    }

    @Test
    public void testUpdateCellThrowsException() {
        Board board = new Board(5, 5);
        Position invalidPos = new Position(board.getRows(), 0); 
        Starfighter sf = new Starfighter(new Position(0, 0));

        assertThrows(IllegalArgumentException.class, () -> {
            board.updateCell(invalidPos, sf);
        });
    }

    @Test
    public void testGetCellThrowsNullPointer() {
        Board board = new Board(5, 5);
        assertThrows(NullPointerException.class, () -> board.getCell(null));
    }

    @Test
    void toStringTest() {
        Board board = BoardParser.fromString("""
        _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
        _ _ G _ _ _ C _ _ _ _ _ _ _ _ _ I _ _ _ _ _ _ _ _ _ _ _ _ _
        _ _ _ _ _ _ _ _ _ _ _ F _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
        _ _ _ _ _ _ _ _ < _ _ _ _ _ _ _ _ _ _ _ _ _ _ X _ _ _ _ _ _
        _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ * _ _ _ _ _ _ _ _
        _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
        _ _ _ _ _ _ _ _ _ G _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
        _ _ _ S _ _ < _ _ _ _ _ _ P _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
        _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
        _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
        """);

        String expected = """
        _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
        _ _ G _ _ _ C _ _ _ _ _ _ _ _ _ I _ _ _ _ _ _ _ _ _ _ _ _ _
        _ _ _ _ _ _ _ _ _ _ _ F _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
        _ _ _ _ _ _ _ _ < _ _ _ _ _ _ _ _ _ _ _ _ _ _ X _ _ _ _ _ _
        _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ * _ _ _ _ _ _ _ _
        _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
        _ _ _ _ _ _ _ _ _ G _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
        _ _ _ S _ _ < _ _ _ _ _ _ P _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
        _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
        _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
        """;

        String[] expTokens = expected.trim().split("\\s+");
        String[] actTokens = board.toString(false).trim().split("\\s+");

        assertArrayEquals(expTokens, actTokens);
    }
}
