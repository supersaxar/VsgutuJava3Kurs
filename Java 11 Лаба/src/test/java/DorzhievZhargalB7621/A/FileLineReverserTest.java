package DorzhievZhargalB7621.A;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FileLineReverserTest {
    private final FileLineReverser reverser = new FileLineReverser();

    @Test
    void testReadLinesFromFile(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("test_input.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toFile()))) {
            writer.write("Первая строка\n");
            writer.write("Вторая строка\n");
            writer.write("Третья строка");
        }

        List<String> lines = reverser.readLinesFromFile(tempFile.toString());

        assertEquals(3, lines.size());
        assertEquals("Первая строка", lines.get(0));
        assertEquals("Вторая строка", lines.get(1));
        assertEquals("Третья строка", lines.get(2));
    }

    @Test
    void testReverseLinesOrder() {
        List<String> originalLines = Arrays.asList("Первая", "Вторая", "Третья");

        List<String> reversedLines = reverser.reverseLinesOrder(originalLines);

        assertEquals(3, reversedLines.size());
        assertEquals("Третья", reversedLines.get(0));
        assertEquals("Вторая", reversedLines.get(1));
        assertEquals("Первая", reversedLines.get(2));
    }

    @Test
    void testWriteLinestoFile(@TempDir Path tempDir) throws IOException {
        List<String> lines = Arrays.asList("Первая строка", "Вторая строка", "Третья строка");

        Path tempOutputFile = tempDir.resolve("test_output.txt");

        reverser.writeLinestoFile(lines, tempOutputFile.toString());

        List<String> readLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(tempOutputFile.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                readLines.add(line);
            }
        }

        assertEquals(3, readLines.size());
        assertEquals("Первая строка", readLines.get(0));
        assertEquals("Вторая строка", readLines.get(1));
        assertEquals("Третья строка", readLines.get(2));
    }
}