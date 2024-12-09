//Доржиев Жаргал Группа Б-762-1 Вариант 1

package DorzhievZhargalB7621.A;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileLineReverser {
    public List<String> readLinesFromFile(String inputFilePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public List<String> reverseLinesOrder(List<String> lines) {
        List<String> reversedLines = new ArrayList<>(lines);
        Collections.reverse(reversedLines);
        return reversedLines;
    }

    public void writeLinestoFile(List<String> lines, String outputFilePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public static void main(String[] args) {
        FileLineReverser reverser = new FileLineReverser();
        try {
            String projectDir = System.getProperty("user.dir");
            
            Path inputPath = Paths.get(projectDir, "Java 11 Лаба", "src", "main", "java", "DorzhievZhargalB7621", "A", "input.txt");
            Path outputPath = Paths.get(projectDir, "Java 11 Лаба", "src", "main", "java", "DorzhievZhargalB7621", "A", "output.txt");

            List<String> lines = reverser.readLinesFromFile(inputPath.toString());
            List<String> reversedLines = reverser.reverseLinesOrder(lines);
            reverser.writeLinestoFile(reversedLines, outputPath.toString());
            System.out.println("Строки успешно обращены и записаны в output.txt");
        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлами: " + e.getMessage());
        }
    }
}
