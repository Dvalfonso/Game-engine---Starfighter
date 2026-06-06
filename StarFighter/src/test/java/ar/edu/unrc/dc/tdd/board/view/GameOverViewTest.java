package ar.edu.unrc.dc.tdd.board.view;

import ar.edu.unrc.dc.controllers.StarfighterGameEngine;
import ar.edu.unrc.dc.models.cell.Starfighter;
import ar.edu.unrc.dc.views.ConsoleIO;
import ar.edu.unrc.dc.views.GameOverMenu;
import ar.edu.unrc.dc.views.InputOutput;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

public class GameOverViewTest {
    @Test
    void playAgainTest() {
        InputOutput io = EasyMock.mock(ConsoleIO.class);
        StarfighterGameEngine engine = EasyMock.mock(StarfighterGameEngine.class);
        EasyMock.expect(io.readInt()).andReturn(1);
        EasyMock.expect(engine.getRows()).andReturn(10);
        EasyMock.expect(engine.getCols()).andReturn(30);
        engine.play(10,30);
        EasyMock.expectLastCall();

        EasyMock.replay(io, engine);

        GameOverMenu gameOverMenu = new GameOverMenu();
        gameOverMenu.showGameOver(io, engine);

        EasyMock.verify(io,engine);
    }
}
