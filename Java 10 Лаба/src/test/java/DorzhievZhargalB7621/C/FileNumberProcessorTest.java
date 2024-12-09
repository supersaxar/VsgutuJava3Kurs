package DorzhievZhargalB7621.C;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileNumberProcessorTest {
    private static final String BASE_DIRECTORY = "Java 10 Лаба/src/main/java/DorzhievZhargalB7621/C/output";
    private static final String FILE_NAME = "random_numbers.txt";

    @BeforeEach
    void setUp() {
        File directory = new File(BASE_DIRECTORY);
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }

    @Test
    void testCreateDirectory() {
        FileNumberProcessor.createDirectory();

        File directory = new File(BASE_DIRECTORY);
        assertTrue(directory.exists(), "Директория должна быть создана");
        assertTrue(directory.isDirectory(), "Должна быть именно директория");
    }

    @Test
    void testGenerateRandomNumbers() {
        List<Integer> numbers = FileNumberProcessor.generateRandomNumbers(10, 100);

        assertEquals(10, numbers.size(), "Должно быть сгенерировано 10 чисел");
        assertTrue(numbers.stream().allMatch(n -> n >= 0 && n < 100),
                "Все числа должны быть в диапазоне [0, 100)");
    }

    @Test
    void testWriteAndReadNumbers() throws IOException {
        List<Integer> originalNumbers = Arrays.asList(5, 2, 8, 1, 9);

        FileNumberProcessor.writeNumbersToFile(originalNumbers, FILE_NAME);
        List<Integer> readNumbers = FileNumberProcessor.readAndSortNumbers();

        List<Integer> expectedSortedNumbers = Arrays.asList(1, 2, 5, 8, 9);
        assertEquals(expectedSortedNumbers, readNumbers, "Числа должны быть отсортированы");
    }
}
