package ar.edu.unrc.dc.tdd.board.view;

import ar.edu.unrc.dc.controllers.StarfighterGameEngine;
import ar.edu.unrc.dc.models.Game;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.components.Armour;
import ar.edu.unrc.dc.models.components.Engine;
import ar.edu.unrc.dc.models.components.Special;
import ar.edu.unrc.dc.models.components.Weapon;
import ar.edu.unrc.dc.models.setup.Setup;
import ar.edu.unrc.dc.views.ConsoleIO;
import ar.edu.unrc.dc.views.InputOutput;
import ar.edu.unrc.dc.views.SummaryMenu;
import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SummaryMenuTest {
    InputOutput io;
    StarfighterGameEngine engine;
    Game game;
    SummaryMenu summaryMenu;

    @BeforeEach
    void setup() {
        io = EasyMock.mock(ConsoleIO.class);
        engine = EasyMock.mock(StarfighterGameEngine.class);
        game = EasyMock.mock(Game.class);
        summaryMenu = new SummaryMenu();
    }

    @Test
    void showSummarySetupNull() {
        EasyMock.expect(game.getSetup()).andReturn(null);
        EasyMock.replay(io, engine, game);

        summaryMenu.showSumary(io,engine,game);

        EasyMock.verify(io,engine,game);
    }

    @Test
    void showSummarySetupConfirm() {
        Setup setup = EasyMock.mock(Setup.class);
        EasyMock.expect(game.getSetup()).andReturn(setup);

        Weapon weapon = EasyMock.mock(Weapon.class);
        Armour armour = EasyMock.mock(Armour.class);
        Engine engine1 = EasyMock.mock(Engine.class);
        Special special = EasyMock.mock(Special.class);
        EasyMock.expect(setup.getWeapon()).andReturn(weapon);
        EasyMock.expect(setup.getArmour()).andReturn(armour);
        EasyMock.expect(setup.getEngine()).andReturn(engine1);
        EasyMock.expect(setup.getSpecial()).andReturn(special);

        EasyMock.expect(io.readInt()).andReturn(1);

        engine.changeState(GameState.InGame);
        EasyMock.expectLastCall();

        EasyMock.replay(io, engine, game, setup, weapon, armour, engine1, special);

        summaryMenu.showSumary(io,engine,game);

        EasyMock.verify(io, engine, game, setup, weapon, armour, engine1, special);
    }

    @Test
    void showSummarySetupGoBack() {
        Setup setup = EasyMock.mock(Setup.class);
        EasyMock.expect(game.getSetup()).andReturn(setup);

        Weapon weapon = EasyMock.mock(Weapon.class);
        Armour armour = EasyMock.mock(Armour.class);
        Engine engine1 = EasyMock.mock(Engine.class);
        Special special = EasyMock.mock(Special.class);
        EasyMock.expect(setup.getWeapon()).andReturn(weapon);
        EasyMock.expect(setup.getArmour()).andReturn(armour);
        EasyMock.expect(setup.getEngine()).andReturn(engine1);
        EasyMock.expect(setup.getSpecial()).andReturn(special);

        EasyMock.expect(io.readInt()).andReturn(2);

        EasyMock.expect(io.readInt()).andReturn(1);
        engine.setupBack(1);
        EasyMock.expectLastCall();

        EasyMock.replay(io, engine, game, setup, weapon, armour, engine1, special);

        summaryMenu.showSumary(io,engine,game);

        EasyMock.verify(io, engine, game, setup, weapon, armour, engine1, special);
    }
}
