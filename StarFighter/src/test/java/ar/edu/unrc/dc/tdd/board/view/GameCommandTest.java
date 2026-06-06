package ar.edu.unrc.dc.tdd.board.view;

import ar.edu.unrc.dc.controllers.StarfighterGameEngine;
import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.cell.Position;
import ar.edu.unrc.dc.models.cell.Starfighter;
import ar.edu.unrc.dc.views.ConsoleIO;
import ar.edu.unrc.dc.views.GameCommands;
import ar.edu.unrc.dc.views.InputOutput;
import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameCommandTest {
    private StarfighterGameEngine engine;
    private InputOutput io;
    private GameCommands gameCommands;

    @BeforeEach
    void setup() {
        engine = EasyMock.mock(StarfighterGameEngine.class);
        io = EasyMock.mock(ConsoleIO.class);
        gameCommands = new GameCommands();
    }

    @Test
    void moveCommand_test() {
        Game game = EasyMock.mock(Game.class);
        Starfighter sf = EasyMock.mock(Starfighter.class);
        Position pos = EasyMock.mock(Position.class);

        // move
        EasyMock.expect(io.readInt()).andReturn(1);
        // vertical move
        EasyMock.expect(io.readInt()).andReturn(1);
        // horizontal move
        EasyMock.expect(io.readInt()).andReturn(1);

        EasyMock.expect(engine.getGame()).andStubReturn(game);
        EasyMock.expect(game.getStarfighter()).andStubReturn(sf);
        EasyMock.expect(sf.getPosition()).andStubReturn(pos);
        EasyMock.expect(pos.getRow()).andStubReturn(5);
        EasyMock.expect(pos.getCol()).andStubReturn(5);

        EasyMock.expect(engine.getRows()).andStubReturn(10);
        EasyMock.expect(engine.getCols()).andStubReturn(10);

        engine.move(1,1);
        EasyMock.expectLastCall();

        EasyMock.replay(engine,io, game, sf, pos);

        gameCommands.showGameCommands(io,engine);
        EasyMock.verify(io, engine);
    }

    @Test
    void fireCommand_test() {
        // fire
        EasyMock.expect(io.readInt()).andReturn(2);

        engine.fire();
        EasyMock.expectLastCall();

        EasyMock.replay(engine,io);

        gameCommands.showGameCommands(io,engine);
        EasyMock.verify(io, engine);
    }

    @Test
    void passCommand_test() {
        // pass
        EasyMock.expect(io.readInt()).andReturn(3);

        engine.pass();
        EasyMock.expectLastCall();

        EasyMock.replay(engine,io);

        gameCommands.showGameCommands(io,engine);
        EasyMock.verify(io, engine);
    }

    @Test
    void specialCommand_test() {
        // fire
        EasyMock.expect(io.readInt()).andReturn(4);

        engine.special();
        EasyMock.expectLastCall();

        EasyMock.replay(engine,io);

        gameCommands.showGameCommands(io,engine);
        EasyMock.verify(io, engine);
    }

    @Test
    void abortCommand_test() {
        // fire
        EasyMock.expect(io.readInt()).andReturn(5);

        engine.abort();
        EasyMock.expectLastCall();

        EasyMock.replay(engine,io);

        gameCommands.showGameCommands(io,engine);
        EasyMock.verify(io, engine);
    }

    @Test
    void otherSelection_test() {
        EasyMock.expect(io.readInt()).andReturn(12);
        EasyMock.expect(io.readInt()).andReturn(2);

        engine.fire();
        EasyMock.expectLastCall();

        EasyMock.replay(engine,io);

        gameCommands.showGameCommands(io,engine);
        EasyMock.verify(io, engine);
    }
}
