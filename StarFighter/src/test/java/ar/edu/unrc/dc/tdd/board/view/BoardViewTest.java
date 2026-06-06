package ar.edu.unrc.dc.tdd.board.view;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.cell.Position;
import ar.edu.unrc.dc.models.cell.Starfighter;
import ar.edu.unrc.dc.models.components.Regen;
import ar.edu.unrc.dc.views.BoardView;
import org.easymock.EasyMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardViewTest {
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream out;

    @BeforeEach
    void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void showBoard_prints_basic_info() {

        // único mock
        Game game = EasyMock.mock(Game.class);

        // objetos reales
        Position pos = new Position(0, 0);
        Starfighter sf = new Starfighter(pos);

        Board board = EasyMock.mock(Board.class);

        EasyMock.expect(game.getStarfighter()).andReturn(sf).anyTimes();
        EasyMock.expect(game.getBoard()).andReturn(board);
        EasyMock.expect(board.toString(true)).andReturn("BOARD");

        EasyMock.replay(game, board);

        new BoardView().showBoard(game, "MOVE");

        String output = out.toString();

        assertTrue(output.contains("Command issued: MOVE (ok)"));
        assertTrue(output.contains("Starfighter health: 100"));
        assertTrue(output.contains("Starfighter energy: 100"));
        assertTrue(output.contains("BOARD"));

        EasyMock.verify(game);
    }
}
