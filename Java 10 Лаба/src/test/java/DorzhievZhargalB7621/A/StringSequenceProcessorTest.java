package DorzhievZhargalB7621.A;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class StringSequenceProcessorTest {

    @Test
    void testProcessFile_removesSubstring() throws IOException {
        Path inputPath = Paths.get("test_input.txt");
        Path outputPath = Paths.get("test_output.txt");
        String substringToRemove = "REMOVE";

        Files.writeString(inputPath, "This line contains REMOVE and more REMOVE words.");

        StringSequenceProcessor.processFile("test_input.txt", "test_output.txt", substringToRemove);

        String result = Files.readString(outputPath).replace("\r\n", "\n");
        assertEquals("This line contains  and more  words.\n", result);

        Files.deleteIfExists(inputPath);
        Files.deleteIfExists(outputPath);
    }

    @Test
    void testProcessFile_emptyInput() throws IOException {
        Path inputPath = Paths.get("test_input_empty.txt");
        Path outputPath = Paths.get("test_output_empty.txt");
        String substringToRemove = "REMOVE";

        Files.writeString(inputPath, "");

        StringSequenceProcessor.processFile("test_input_empty.txt", "test_output_empty.txt", substringToRemove);

        String result = Files.readString(outputPath).replace("\r\n", "\n");
        assertEquals("", result);

        Files.deleteIfExists(inputPath);
        Files.deleteIfExists(outputPath);
    }

    @Test
    void testProcessFile_noSubstringFound() throws IOException {
        Path inputPath = Paths.get("test_input_no_match.txt");
        Path outputPath = Paths.get("test_output_no_match.txt");
        String substringToRemove = "REMOVE";

        Files.writeString(inputPath, "This line does not have the substring.");

        StringSequenceProcessor.processFile("test_input_no_match.txt", "test_output_no_match.txt", substringToRemove);

        String result = Files.readString(outputPath).replace("\r\n", "\n");
        assertEquals("This line does not have the substring.\n", result);

        Files.deleteIfExists(inputPath);
        Files.deleteIfExists(outputPath);
    }
}
