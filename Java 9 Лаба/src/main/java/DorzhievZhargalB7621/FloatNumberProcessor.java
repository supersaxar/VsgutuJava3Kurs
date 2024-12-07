//Доржиев Жаргал Группа Б-762-1 Вариант 1

package DorzhievZhargalB7621;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FloatNumberProcessor {

    public static class InvalidNumberFormatException extends Exception {
        public InvalidNumberFormatException(String message) {
            super(message);
        }
    }

    public static List<Double> readAndParseFile(String filePath) throws IOException, InvalidNumberFormatException {
        List<Double> numbers = new ArrayList<>();

        if (!Files.exists(Paths.get(filePath))) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length != 2) {
                throw new InvalidNumberFormatException("Invalid line format: " + line);
            }

            String numberString = parts[0];
            String localeString = parts[1];

            try {
                Locale locale = Locale.forLanguageTag(localeString);
                NumberFormat numberFormat = NumberFormat.getInstance(locale);
                Number number = numberFormat.parse(numberString);
                double value = number.doubleValue();

                if (Double.isInfinite(value) || Double.isNaN(value)) {
                    throw new InvalidNumberFormatException("Invalid number value: " + numberString);
                }

                numbers.add(value);
            } catch (ParseException | IllegalArgumentException e) {
                throw new InvalidNumberFormatException("Failed to parse number: " + numberString + ", locale: " + localeString);
            }
        }

        return numbers;
    }

    public static double calculateSum(List<Double> numbers) {
        return numbers.stream().mapToDouble(Double::doubleValue).sum();
    }

    public static double calculateAverage(List<Double> numbers) {
        if (numbers.isEmpty()) {
            return 0.0;
        }
        return calculateSum(numbers) / numbers.size();
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Aye11\\IdeaProjects\\Java9Lab\\src\\main\\java\\DorzhievZhargalB7621\\exampleNumbers.txt";

        try {
            List<Double> numbers = readAndParseFile(filePath);
            double sum = calculateSum(numbers);
            double average = calculateAverage(numbers);

            System.out.println("Sum: " + sum);
            System.out.println("Average: " + average);
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (InvalidNumberFormatException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            System.err.println("Error: Not enough memory to process the file.");
        }
    }
}
