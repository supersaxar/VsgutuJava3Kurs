package DorzhievZhargalB7621;

import org.junit.Test;
import static org.junit.Assert.*;

public class PortSimulationTest {
    @Test
    public void testPortCreation() {
        Port port = new Port(100, 5);
        assertEquals(0, port.getCurrentContainerCount());
    }

    @Test
    public void testLoadContainers() {
        Port port = new Port(100, 5);
        assertTrue(port.loadContainers(50));
        assertEquals(50, port.getCurrentContainerCount());
    }

    @Test
    public void testUnloadContainers() {
        Port port = new Port(100, 5);
        port.loadContainers(50);
        assertTrue(port.unloadContainers(20));
        assertEquals(30, port.getCurrentContainerCount());
    }

    @Test
    public void testOverloadPrevention() {
        Port port = new Port(100, 5);
        assertTrue(port.loadContainers(100));
        assertFalse(port.loadContainers(1));
    }

    @Test
    public void testShipInitialization() {
        Port port = new Port(100, 5);
        Ship ship = new Ship("TestShip", port, 20, 10);
        assertNotNull(ship);
    }
}
