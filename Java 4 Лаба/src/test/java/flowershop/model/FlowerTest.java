package flowershop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlowerTest {
    @Test
    void testRoseCreation() {
        Rose rose = new Rose("Red", 10.0, 30, 8);

        assertEquals("Rose", rose.getName());
        assertEquals(10.0, rose.getPrice());
        assertEquals(30, rose.getStemLength());
        assertEquals(8, rose.getFreshnessLevel());
    }

    @Test
    void testTulipCreation() {
        Tulip tulip = new Tulip("Dutch", 5.0, 25, 9);

        assertEquals("Tulip", tulip.getName());
        assertEquals(5.0, tulip.getPrice());
        assertEquals(25, tulip.getStemLength());
        assertEquals(9, tulip.getFreshnessLevel());
    }
}