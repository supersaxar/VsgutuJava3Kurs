package flowershop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccessoryTest {
    @Test
    void testAccessoryCreation() {
        Accessory accessory = new Accessory("Ribbon", 2.5);

        assertEquals("Ribbon", accessory.getName());
        assertEquals(2.5, accessory.getPrice());
    }
}