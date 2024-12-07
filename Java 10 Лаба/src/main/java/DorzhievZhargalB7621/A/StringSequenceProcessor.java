//Доржиев Жаргал Группа Б-762-1 Вариант 1

package DorzhievZhargalB7621.A;

import java.io.*;
import java.nio.file.*;

public class StringSequenceProcessor {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\Aye11\\IdeaProjects\\Java10Lab\\src\\main\\java\\DorzhievZhargalB7621\\A\\input.txt";
        String outputFile = "C:\\Users\\Aye11\\IdeaProjects\\Java10Lab\\src\\main\\java\\DorzhievZhargalB7621\\A\\output.txt";
        String substringToRemove = "УБРАТЬ";

        try {
            processFile(inputFile, outputFile, substringToRemove);
            System.out.println("Processing complete. Output written to " + outputFile);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public static void processFile(String inputFile, String outputFile, String substringToRemove) throws IOException {
        Path inputPath = Paths.get(inputFile);
        Path outputPath = Paths.get(outputFile);

        try (BufferedReader reader = Files.newBufferedReader(inputPath);
             BufferedWriter writer = Files.newBufferedWriter(outputPath)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String modifiedLine = line.replace(substringToRemove, "");
                writer.write(modifiedLine);
                writer.newLine();
            }
        }
    }
}
