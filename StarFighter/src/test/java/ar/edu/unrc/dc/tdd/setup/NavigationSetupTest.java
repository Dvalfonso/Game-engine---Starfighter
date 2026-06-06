package ar.edu.unrc.dc.tdd.setup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import ar.edu.unrc.dc.models.setup.NavigationSetup;
import ar.edu.unrc.dc.models.setup.Setup;
import ar.edu.unrc.dc.models.setup.SetupFactory;

public class NavigationSetupTest {
@Test
    public void testSetupNext() {
        SetupFactory mockFactory = EasyMock.createMock(SetupFactory.class);
        NavigationSetup nav = new NavigationSetup(mockFactory);

        assertEquals(0, nav.getCurrentPosition());
        nav.setupNext(2);
        assertEquals(2, nav.getCurrentPosition());
    }

    @Test
    public void testSetupBack() {
        SetupFactory mockFactory = EasyMock.createMock(SetupFactory.class);
        NavigationSetup nav = new NavigationSetup(mockFactory);

        nav.setCurrentPosition(3);
        nav.setupBack(1);
        assertEquals(2, nav.getCurrentPosition());
    }

    @Test
    public void testSelectWeapon() {
        SetupFactory mockFactory = EasyMock.createMock(SetupFactory.class);
        NavigationSetup nav = new NavigationSetup(mockFactory);

        nav.setCurrentPosition(1);
        
        mockFactory.setWeapon(7);
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockFactory);
        nav.select(7);
        EasyMock.verify(mockFactory);
    }

    @Test
    public void testSelectArmour() {
        SetupFactory mockFactory = EasyMock.createMock(SetupFactory.class);
        NavigationSetup nav = new NavigationSetup(mockFactory);

        nav.setCurrentPosition(2);
        
        mockFactory.setArmour(4);
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockFactory);
        nav.select(4);
        EasyMock.verify(mockFactory);
    }

    @Test
    public void testSelectEngine() {
        SetupFactory mockFactory = EasyMock.createMock(SetupFactory.class);
        NavigationSetup nav = new NavigationSetup(mockFactory);

        nav.setCurrentPosition(3);

        mockFactory.setEngine(9);
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockFactory);
        nav.select(9);
        EasyMock.verify(mockFactory);
    }

    @Test
    public void testSelectSpecial() {
        SetupFactory mockFactory = EasyMock.createMock(SetupFactory.class);
        NavigationSetup nav = new NavigationSetup(mockFactory);

        nav.setCurrentPosition(4);

        mockFactory.setSpecial(2);
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockFactory);
        nav.select(2);
        EasyMock.verify(mockFactory);
    }

    @Test
    public void testSelectNoActionOnPosition5() {
        SetupFactory mockFactory = EasyMock.createMock(SetupFactory.class);
        NavigationSetup nav = new NavigationSetup(mockFactory);

        nav.setCurrentPosition(5);

        // No se llama a nada → no se esperan interacciones
        EasyMock.replay(mockFactory);
        nav.select(99);
        EasyMock.verify(mockFactory);
    }

    @Test
    public void testCreateSetup() {
        SetupFactory mockFactory = EasyMock.createMock(SetupFactory.class);
        NavigationSetup nav = new NavigationSetup(mockFactory);

        Setup mockSetup = EasyMock.createMock(Setup.class);

        EasyMock.expect(mockFactory.createSetup()).andReturn(mockSetup);

        EasyMock.replay(mockFactory);

        Setup result = nav.createSetup();

        assertSame(mockSetup, result);
        EasyMock.verify(mockFactory);
    }
}
