package ar.edu.unrc.dc.tdd.board.view;

import ar.edu.unrc.dc.controllers.StarfighterGameEngine;
import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.cell.Starfighter;
import ar.edu.unrc.dc.models.components.Armour;
import ar.edu.unrc.dc.models.components.Engine;
import ar.edu.unrc.dc.models.components.Special;
import ar.edu.unrc.dc.models.components.Weapon;
import ar.edu.unrc.dc.models.setup.NavigationSetup;
import ar.edu.unrc.dc.models.setup.Setup;
import ar.edu.unrc.dc.views.*;
import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConsoleViewTest {
    ConsoleView consoleView;
    StarfighterGameEngine engine;
    Game game;
    InputOutput io;

    @BeforeEach
    void setup() {
        engine = EasyMock.mock(StarfighterGameEngine.class);
        this.game = EasyMock.mock(Game.class);
        this.io = EasyMock.mock(ConsoleIO.class);
        this.consoleView = new ConsoleView(engine);
    }

    @Test
    public void test_update_notStarted_branch() {
        MainMenu mainMenu = EasyMock.mock(MainMenu.class);

        EasyMock.expect(game.getGameState()).andReturn(GameState.NotStarted).anyTimes();

        mainMenu.showMenu(io, engine);
        EasyMock.expectLastCall();

        consoleView.setMainMenu(mainMenu);
        consoleView.setIO(io);

        EasyMock.replay(engine, game, mainMenu, io);

        consoleView.update("cmd", game);

        EasyMock.verify(mainMenu);
    }


    @Test
    public void test_update_summary_branch() {
        SummaryMenu summaryMenu = EasyMock.mock(SummaryMenu.class);

        EasyMock.expect(game.getGameState()).andReturn(GameState.SetupSumary).anyTimes();

        summaryMenu.showSumary(io, engine, game);
        EasyMock.expectLastCall();

        consoleView.setSummaryMenu(summaryMenu);
        consoleView.setIO(io);

        EasyMock.replay(engine, game, summaryMenu, io);

        consoleView.update("cmd", game);

        EasyMock.verify(summaryMenu);
    }

    @Test
    public void test_update_setup_branch() {
        SetupMenu setupMenu = EasyMock.mock(SetupMenu.class);

        EasyMock.expect(game.getGameState()).andReturn(GameState.WeaponSetup).anyTimes();

        setupMenu.showSetupMenu(io, game, engine);
        EasyMock.expectLastCall();

        consoleView.setSetupMenu(setupMenu);
        consoleView.setIO(io);

        EasyMock.replay(engine, game, setupMenu, io);

        consoleView.update("cmd", game);

        EasyMock.verify(setupMenu);
    }

    @Test
    public void test_update_gameOver_branch() {
        GameOverMenu gameOverMenu = EasyMock.mock(GameOverMenu.class);

        EasyMock.expect(game.getGameState()).andReturn(GameState.GameOver).anyTimes();

        gameOverMenu.showGameOver(io, engine);
        EasyMock.expectLastCall();

        consoleView.setGameOverMenu(gameOverMenu);
        consoleView.setIO(io);

        EasyMock.replay(engine, game, gameOverMenu, io);

        consoleView.update("cmd", game);

        EasyMock.verify(gameOverMenu);
    }

    @Test
    public void test_update_inGame_branch() {
        BoardView boardView = EasyMock.mock(BoardView.class);
        GameCommands gameCommands = EasyMock.mock(GameCommands.class);

        EasyMock.expect(game.getGameState()).andReturn(GameState.InGame).anyTimes();

        boardView.showBoard(game, "cmd");
        EasyMock.expectLastCall();

        gameCommands.showGameCommands(io, engine);
        EasyMock.expectLastCall();


        consoleView.setBoardView(boardView);
        consoleView.setIO(io);
        consoleView.setGameCommands(gameCommands);

        EasyMock.replay(engine, game, boardView, io, gameCommands);

        consoleView.update("cmd", game);

        EasyMock.verify(boardView);
    }
}
