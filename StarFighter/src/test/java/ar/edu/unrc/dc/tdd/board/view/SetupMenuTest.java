package ar.edu.unrc.dc.tdd.board.view;

import ar.edu.unrc.dc.controllers.StarfighterGameEngine;
import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.setup.NavigationSetup;
import ar.edu.unrc.dc.models.setup.Setup;
import ar.edu.unrc.dc.views.ConsoleIO;
import ar.edu.unrc.dc.views.InputOutput;
import ar.edu.unrc.dc.views.SetupMenu;
import com.sun.tools.jconsole.JConsoleContext;
import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class SetupMenuTest {
    private StarfighterGameEngine engine;
    private InputOutput io;
    private Game game;
    private SetupMenu setupMenu;

    @BeforeEach
    public void setup() {
        engine = EasyMock.mock(StarfighterGameEngine.class);
        io = EasyMock.mock(ConsoleIO.class);
        game = EasyMock.mock(Game.class);
        setupMenu = new SetupMenu();
    }

    @Test
    public void test_showSetupMenu1() {
        NavigationSetup nav = EasyMock.mock(NavigationSetup.class);
        Setup setup = EasyMock.mock(Setup.class);
        // Estado inicial
        EasyMock.expect(game.getGameState()).andReturn(GameState.WeaponSetup).anyTimes();

        EasyMock.expect(game.getNavigationSetup()).andReturn(nav).anyTimes();
        EasyMock.expect(nav.getCurrentPosition()).andReturn(1)
                .andReturn(5);

        // Lectura del usuario
        EasyMock.expect(io.readInt()).andReturn(1); // selecciona weapon
        EasyMock.expect(io.readInt()).andReturn(2); // next
        EasyMock.expect(io.readInt()).andReturn(10); // steps

        engine.select(1);
        EasyMock.expectLastCall();

        engine.setupNext(10);
        EasyMock.expectLastCall();

        EasyMock.expect(nav.createSetup()).andReturn(setup);

        engine.confirmSetup(setup);
        EasyMock.expectLastCall();

        engine.changeState(GameState.SetupSumary);
        EasyMock.expectLastCall();

        EasyMock.replay(io, game, engine, nav);

        new SetupMenu().showSetupMenu(io, game, engine);

        EasyMock.verify(io, game, engine, nav);
    }

    @Test
    public void test_showSetupMenu2() {
        NavigationSetup nav = EasyMock.mock(NavigationSetup.class);
        Setup setup = EasyMock.mock(Setup.class);

        // Siempre en modo setup
        EasyMock.expect(game.getGameState()).andReturn(GameState.WeaponSetup).anyTimes();
        EasyMock.expect(game.getNavigationSetup()).andReturn(nav).anyTimes();


        EasyMock.expect(nav.getCurrentPosition()).andReturn(1).times(2);
        EasyMock.expect(nav.getCurrentPosition()).andReturn(2).times(2);
        EasyMock.expect(nav.getCurrentPosition()).andReturn(3).times(2);
        EasyMock.expect(nav.getCurrentPosition()).andReturn(4).times(2);
        EasyMock.expect(nav.getCurrentPosition()).andReturn(5).anyTimes();

        EasyMock.expect(io.readInt()).andReturn(1);
        EasyMock.expect(io.readInt()).andReturn(2);
        EasyMock.expect(io.readInt()).andReturn(1);

        engine.select(1);
        EasyMock.expectLastCall();
        engine.setupNext(1);
        EasyMock.expectLastCall();

        // Iteración 2: pos=2
        EasyMock.expect(io.readInt()).andReturn(1);
        EasyMock.expect(io.readInt()).andReturn(2);
        EasyMock.expect(io.readInt()).andReturn(1);

        engine.select(1);
        EasyMock.expectLastCall();
        engine.setupNext(1);
        EasyMock.expectLastCall();

        EasyMock.expect(io.readInt()).andReturn(1);
        EasyMock.expect(io.readInt()).andReturn(2);
        EasyMock.expect(io.readInt()).andReturn(1);

        engine.select(1);
        EasyMock.expectLastCall();
        engine.setupNext(1);
        EasyMock.expectLastCall();

        EasyMock.expect(io.readInt()).andReturn(1);
        EasyMock.expect(io.readInt()).andReturn(2);
        EasyMock.expect(io.readInt()).andReturn(1);

        engine.select(1);
        EasyMock.expectLastCall();
        engine.setupNext(1);
        EasyMock.expectLastCall();

        EasyMock.expect(nav.createSetup()).andReturn(setup);

        engine.confirmSetup(setup);
        EasyMock.expectLastCall();

        engine.changeState(GameState.SetupSumary);
        EasyMock.expectLastCall();

        EasyMock.replay(io, game, engine, nav, setup);

        new SetupMenu().showSetupMenu(io, game, engine);

        EasyMock.verify(io, game, engine, nav, setup);
    }

    @Test
    public void test_badCurrentPosition() {
        NavigationSetup nav = EasyMock.mock(NavigationSetup.class);
        EasyMock.expect(game.getNavigationSetup()).andReturn(nav).anyTimes();

        EasyMock.expect(game.getGameState()).andReturn(GameState.WeaponSetup).anyTimes();

        EasyMock.expect(nav.getCurrentPosition()).andReturn(-1);
        engine.changeState(GameState.NotStarted);
        EasyMock.expectLastCall();

        EasyMock.replay(engine,game,io,nav);

        setupMenu.showSetupMenu(io,game,engine);

        EasyMock.verify(engine,game,io,nav);
    }

    @Test
    public void test_showSetupMenu_back_simple() {
        NavigationSetup nav = EasyMock.mock(NavigationSetup.class);
        InputOutput io = EasyMock.mock(InputOutput.class);
        Game game = EasyMock.mock(Game.class);
        StarfighterGameEngine engine = EasyMock.mock(StarfighterGameEngine.class);

        SetupMenu setupMenu = new SetupMenu();

        final int[] callCount = {0};
        EasyMock.expect(game.getGameState()).andAnswer(() -> {
            callCount[0]++;
            if (callCount[0] < 5) return GameState.ArmourSetup;
            return GameState.SetupSumary;
        }).anyTimes();


        EasyMock.expect(game.getNavigationSetup()).andReturn(nav).anyTimes();

        EasyMock.expect(nav.getCurrentPosition()).andReturn(2).anyTimes();

        EasyMock.expect(io.readInt()).andReturn(3);
        engine.select(3);
        EasyMock.expectLastCall();

        EasyMock.expect(io.readInt()).andReturn(1);
        EasyMock.expect(io.readInt()).andReturn(1);

        engine.setupBack(1);
        EasyMock.expectLastCall();

        EasyMock.replay(io, game, engine, nav);

        setupMenu.showSetupMenu(io, game, engine);

        EasyMock.verify(io, game, engine, nav);
    }
}
