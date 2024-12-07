package DorzhievZhargalB7621;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FloatNumberProcessorTest {

    @Test
    public void testReadAndParseFile_ValidFile() throws Exception {
        String filePath = "test_valid_numbers.txt";
        createTestFile(filePath, "123.45,en-US\n678.90,en-US");

        List<Double> numbers = FloatNumberProcessor.readAndParseFile(filePath);
        Assertions.assertEquals(2, numbers.size());
        Assertions.assertEquals(123.45, numbers.get(0), 0.001);
        Assertions.assertEquals(678.90, numbers.get(1), 0.001);

        deleteTestFile(filePath);
    }

    @Test
    public void testReadAndParseFile_InvalidFormat() {
        String filePath = "test_invalid_format.txt";
        createTestFile(filePath, "123.45\n678.90,en-US");

        Assertions.assertThrows(FloatNumberProcessor.InvalidNumberFormatException.class, () -> {
            FloatNumberProcessor.readAndParseFile(filePath);
        });

        deleteTestFile(filePath);
    }

    @Test
    public void testReadAndParseFile_FileNotFound() {
        String filePath = "non_existent_file.txt";

        Assertions.assertThrows(IOException.class, () -> {
            FloatNumberProcessor.readAndParseFile(filePath);
        });
    }

    @Test
    public void testCalculateSum() {
        List<Double> numbers = List.of(1.0, 2.0, 3.0);
        double sum = FloatNumberProcessor.calculateSum(numbers);
        Assertions.assertEquals(6.0, sum, 0.001);
    }

    @Test
    public void testCalculateAverage() {
        List<Double> numbers = List.of(1.0, 2.0, 3.0);
        double average = FloatNumberProcessor.calculateAverage(numbers);
        Assertions.assertEquals(2.0, average, 0.001);

        numbers = List.of();
        average = FloatNumberProcessor.calculateAverage(numbers);
        Assertions.assertEquals(0.0, average, 0.001);
    }

    private void createTestFile(String filePath, String content) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        } catch (IOException e) {
            Assertions.fail("Failed to create test file: " + e.getMessage());
        }
    }

    private void deleteTestFile(String filePath) {
        try {
            Files.deleteIfExists(new File(filePath).toPath());
        } catch (IOException e) {
            Assertions.fail("Failed to delete test file: " + e.getMessage());
        }
    }
}
