import java.util.*;
import java.util.stream.Collectors;

public class BFull {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество чисел:");
        int n = scanner.nextInt();
        int[] numbers = new int[n];

        System.out.println("Введите числа:");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        List<Integer> evenNumbers = new ArrayList<>();
        List<Integer> oddNumbers = new ArrayList<>();
        for (int num : numbers) {
            if (num % 2 == 0) {
                evenNumbers.add(num);
            } else {
                oddNumbers.add(num);
            }
        }
        System.out.println("Первое задание");
        System.out.println("Четные числа: " + evenNumbers);
        System.out.println("Нечетные числа: " + oddNumbers);
        System.out.println();

        int max = Arrays.stream(numbers).max().orElseThrow(NoSuchElementException::new);
        int min = Arrays.stream(numbers).min().orElseThrow(NoSuchElementException::new);
        System.out.println("Второе задание");
        System.out.println("Наибольшее число: " + max);
        System.out.println("Наименьшее число: " + min);
        System.out.println();

        List<Integer> divisibleBy3Or9 = new ArrayList<>();
        for (int num : numbers) {
            if (num % 3 == 0 || num % 9 == 0) {
                divisibleBy3Or9.add(num);
            }
        }
        System.out.println("Третье задание");
        System.out.println("Числа, которые делятся на 3 или на 9: " + divisibleBy3Or9);
        System.out.println();

        List<Integer> divisibleBy5And7 = new ArrayList<>();
        for (int num : numbers) {
            if (num % 5 == 0 && num % 7 == 0) {
                divisibleBy5And7.add(num);
            }
        }
        System.out.println("Четвертое задание");
        System.out.println("Числа, которые делятся на 5 и на 7: " + divisibleBy5And7);
        System.out.println();


        List<Integer> uniqueDigits = new ArrayList<>();
        for (int num : numbers) {
            if (num >= 100 && num <= 999) {
                String numStr = String.valueOf(num);
                if (numStr.charAt(0) != numStr.charAt(1) && numStr.charAt(0) != numStr.charAt(2) && numStr.charAt(1) != numStr.charAt(2)) {
                    uniqueDigits.add(num);
                }
            }
        }
        System.out.println("Пятое задание");
        System.out.println("Трехзначные числа без одинаковых цифр: " + uniqueDigits);
        System.out.println();

        List<Integer> primeNumbers = new ArrayList<>();
        for (int num : numbers) {
            if (isPrime(num)) {
                primeNumbers.add(num);
            }
        }
        System.out.println("Шестое задание");
        System.out.println("Простые числа: " + primeNumbers);
        System.out.println();

        Arrays.sort(numbers);
        System.out.println("Седьмое задание");
        System.out.println("Числа в порядке возрастания: " + Arrays.toString(numbers));
        int[] descendingNumbers = Arrays.stream(numbers).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        System.out.println("Числа в порядке убывания: " + Arrays.toString(descendingNumbers));
        System.out.println();

        Map<Integer, Long> frequencyMap = Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        List<Map.Entry<Integer, Long>> sortedByFrequency = new ArrayList<>(frequencyMap.entrySet());
        sortedByFrequency.sort((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()));
        System.out.println("Восьмое задание");
        System.out.println("Числа в порядке убывания частоты встречаемости: " + sortedByFrequency);
        System.out.println();

        List<Integer> luckyNumbers = new ArrayList<>();
        for (int num : numbers) {
            if (sumOfDigits(num) == 7) {
                luckyNumbers.add(num);
            }
        }
        System.out.println("Девятое задание");
        System.out.println("«Счастливые» числа: " + luckyNumbers);
        System.out.println();

        List<Integer> palindromes = new ArrayList<>();
        for (int num : numbers) {
            if (isPalindrome(num)) {
                palindromes.add(num);
            }
        }
        System.out.println("Десятое задание");
        System.out.println("Числа-палиндромы: " + palindromes);
        System.out.println();

        List<Integer> halfSumNeighbors = new ArrayList<>();
        for (int i = 1; i < numbers.length - 1; i++) {
            if (numbers[i] == (numbers[i - 1] + numbers[i + 1]) / 2) {
                halfSumNeighbors.add(numbers[i]);
            }
        }
        System.out.println("Одиннадцатое задание");
        System.out.println("Элементы, которые равны полусумме соседних элементов: " + halfSumNeighbors);
        System.out.println();
    }

    private static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private static int sumOfDigits(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    private static boolean isPalindrome(int num) {
        String str = String.valueOf(num);
        return str.equals(new StringBuilder(str).reverse().toString());
    }
}
