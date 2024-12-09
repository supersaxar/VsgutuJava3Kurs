//Доржиев Жаргал Группа Б-762-1 Вариант 1

package DorzhievZhargalB7621.C;
import java.io.*;
import java.util.*;

public class FileNumberProcessor {
    private static final String BASE_DIRECTORY = "Java 10 Лаба/src/main/java/DorzhievZhargalB7621/C/output";
    private static final String FILE_NAME = "random_numbers.txt";

    public static void createDirectory() {
        File directory = new File(BASE_DIRECTORY);
        if (!directory.exists()) {
            boolean dirCreated = directory.mkdirs();
            if (!dirCreated) {
                throw new RuntimeException("Не удалось создать директорию");
            }
        }
    }

    public static List<Integer> generateRandomNumbers(int count, int bound) {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numbers.add(random.nextInt(bound));
        }
        return numbers;
    }

    public static List<Integer> readAndSortNumbers() throws IOException {
        File file = new File(BASE_DIRECTORY, FILE_NAME);
        List<Integer> numbers = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            }
        }

        Collections.sort(numbers);
        return numbers;
    }

    public static void writeNumbersToFile(List<Integer> numbers, String fileName) throws IOException {
        createDirectory();
        File file = new File(BASE_DIRECTORY, fileName);

        try (PrintWriter writer = new PrintWriter(file)) {
            for (Integer number : numbers) {
                writer.println(number);
            }
        }
    }


    public static void main(String[] args) {
        try {
            List<Integer> randomNumbers = generateRandomNumbers(20, 100);

            writeNumbersToFile(randomNumbers, FILE_NAME);

            List<Integer> sortedNumbers = readAndSortNumbers();
            writeNumbersToFile(sortedNumbers, FILE_NAME);

            System.out.println("Файл успешно создан и отсортирован.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
