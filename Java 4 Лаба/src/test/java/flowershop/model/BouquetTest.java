package flowershop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BouquetTest {
    private Bouquet bouquet;
    private Rose rose1;
    private Rose rose2;
    private Tulip tulip;
    private Accessory ribbon;

    @BeforeEach
    void setUp() {
        bouquet = new Bouquet();
        rose1 = new Rose("Red", 10.0, 30, 8);
        rose2 = new Rose("White", 12.0, 35, 5);
        tulip = new Tulip("Dutch", 5.0, 25, 9);
        ribbon = new Accessory("Ribbon", 2.5);
    }

    @Test
    void testAddFlower() {
        bouquet.addFlower(rose1);

        assertEquals(1, bouquet.getFlowers().size());
        assertTrue(bouquet.getFlowers().contains(rose1));
    }

    @Test
    void testAddAccessory() {
        bouquet.addAccessory(ribbon);

        assertEquals(1, bouquet.getAccessories().size());
        assertTrue(bouquet.getAccessories().contains(ribbon));
    }

    @Test
    void testCalculateTotalPrice() {
        bouquet.addFlower(rose1);
        bouquet.addFlower(rose2);
        bouquet.addFlower(tulip);
        bouquet.addAccessory(ribbon);

        assertEquals(29.5, bouquet.calculateTotalPrice());
    }

    @Test
    void testSortByFreshness() {
        bouquet.addFlower(rose1);
        bouquet.addFlower(rose2);
        bouquet.addFlower(tulip);

        bouquet.sortByFreshness();

        List<Flower> sortedFlowers = bouquet.getFlowers();
        assertEquals(tulip, sortedFlowers.get(0));
        assertEquals(rose1, sortedFlowers.get(1));
        assertEquals(rose2, sortedFlowers.get(2));
    }

    @Test
    void testFindFlowersByStemLength() {
        bouquet.addFlower(rose1);
        bouquet.addFlower(rose2);
        bouquet.addFlower(tulip);

        List<Flower> foundFlowers = bouquet.findFlowersByStemLength(28, 32);

        assertEquals(1, foundFlowers.size());
        assertTrue(foundFlowers.contains(rose1));
    }

    @Test
    void testFindFlowersByStemLengthNoMatch() {
        bouquet.addFlower(rose1);
        bouquet.addFlower(rose2);
        bouquet.addFlower(tulip);

        List<Flower> foundFlowers = bouquet.findFlowersByStemLength(40, 50);

        assertTrue(foundFlowers.isEmpty());
    }

    @Test
    void testEmptyBouquetPrice() {
        assertEquals(0.0, bouquet.calculateTotalPrice());
    }
}