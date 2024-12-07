package DorzhievZhargalB7621.B;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class FlowerShopTests {

    private Bouquet bouquet;

    @BeforeEach
    void setUp() {
        bouquet = new Bouquet();
    }

    @Test
    void testAddFlowerToBouquet() {
        Flower rose = new Rose(15.5, 8, 25.0);
        bouquet.addFlower(rose);
        assertEquals(3, bouquet.toString().split("\n")[1].split(",").length, "Цветок должен быть добавлен в букет");
    }

    @Test
    void testAddAccessoryToBouquet() {
        Accessory ribbon = new Accessory("Лента", 5.0);
        bouquet.addAccessory(ribbon);
        assertTrue(bouquet.toString().contains("Лента"), "Аксессуар должен быть добавлен в букет");
    }

    @Test
    void testCalculateTotalCost() {
        bouquet.addFlower(new Rose(15.5, 8, 25.0));
        bouquet.addAccessory(new Accessory("Лента", 5.0));
        assertEquals(20.5, bouquet.calculateTotalCost(), 0.01, "Стоимость букета должна быть корректной");
    }

    @Test
    void testFindFlowersByStemLength() {
        bouquet.addFlower(new Rose(15.5, 5, 25.0));
        bouquet.addFlower(new Tulip(10.0, 8, 20.0));
        List<Flower> foundFlowers = bouquet.findFlowersByStemLength(18.0, 22.0);
        assertEquals(1, foundFlowers.size(), "Должен быть найден только один цветок в заданном диапазоне длины стебля");
    }

    @Test
    void testBouquetSerializationAndDeserialization() {
        String fileName = "bouquet.txt";
        bouquet.addFlower(new Rose(15.5, 5, 25.0));
        bouquet.addAccessory(new Accessory("Лента", 5.0));
        try {
            FileConnector.saveToFile(fileName, bouquet);
            Bouquet loadedBouquet = FileConnector.loadFromFile(fileName);
            assertEquals(bouquet.toString(), loadedBouquet.toString(), "Загруженный букет должен совпадать с исходным");
        } catch (IOException | ClassNotFoundException e) {
            fail("Ошибка при сохранении или загрузке: " + e.getMessage());
        }
    }
}
