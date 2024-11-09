import java.util.*;

public class AFull {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vvedite kolichestvo chisel: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        String[] numbers = new String[n];
        System.out.println("Vvedite " + n + " chisel:");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextLine();
        }

        String shortest = numbers[0], longest = numbers[0];
        for (String num : numbers) {
            if (num.length() < shortest.length()) shortest = num;
            if (num.length() > longest.length()) longest = num;
        }
        System.out.println("Samoe korotkoe chislo: " + shortest + ", dlina: " + shortest.length());
        System.out.println("Samoe dlinnoe chislo: " + longest + ", dlina: " + longest.length());

        Arrays.sort(numbers, Comparator.comparingInt(String::length));
        System.out.println("Chisla v poryadke vozrastaniya dliny: " + Arrays.toString(numbers));

        double avgLength = Arrays.stream(numbers).mapToInt(String::length).average().orElse(0);
        System.out.println("Chisla, dlina kotoryh menshe sredney (" + avgLength + "):");
        for (String num : numbers) {
            if (num.length() < avgLength) {
                System.out.println(num + ", dlina: " + num.length());
            }
        }

        String minDistinctDigits = numbers[0];
        for (String num : numbers) {
            if (distinctDigits(num) < distinctDigits(minDistinctDigits)) {
                minDistinctDigits = num;
            }
        }
        System.out.println("Chislo s minimalnym kolichestvom razlichnyh tsifr: " + minDistinctDigits);

        int evenDigitNumbers = 0;
        int equalEvenOddDigits = 0;
        for (String num : numbers) {
            if (hasOnlyEvenDigits(num)) {
                evenDigitNumbers++;
                if (hasEqualEvenOddDigits(num)) {
                    equalEvenOddDigits++;
                }
            }
        }
        System.out.println("Kolichestvo chisel tolko s chetnymi tsiframi: " + evenDigitNumbers);
        System.out.println("Iz nih s ravnym chislom chetnyh i nechetnyh tsifr: " + equalEvenOddDigits);

        String strictlyIncreasing = findStrictlyIncreasing(numbers);
        System.out.println("Chislo s tsiframi v strogom poryadke vozrastaniya: " +
                (strictlyIncreasing != null ? strictlyIncreasing : "ne naydeno"));

        String allDistinct = findAllDistinct(numbers);
        System.out.println("Chislo, sostoyashchee tolko iz razlichnyh tsifr: " +
                (allDistinct != null ? allDistinct : "ne naydeno"));

        String secondPalindrome = findSecondPalindrome(numbers);
        System.out.println("Vtoroy palindrom: " +
                (secondPalindrome != null ? secondPalindrome : "ne naydeno"));

        if (args.length == 3) {
            double a = Double.parseDouble(args[0]);
            double b = Double.parseDouble(args[1]);
            double c = Double.parseDouble(args[2]);
            solveQuadraticEquation(a, b, c);
        } else {
            System.out.println("Dlya resheniya kvadratnogo uravneniya neobhodimo peredat 3 parametra.");
        }
    }

    private static int distinctDigits(String num) {
        return (int) num.chars().distinct().count();
    }

    private static boolean hasOnlyEvenDigits(String num) {
        return num.chars().allMatch(ch -> (ch - '0') % 2 == 0);
    }

    private static boolean hasEqualEvenOddDigits(String num) {
        int even = 0, odd = 0;
        for (char ch : num.toCharArray()) {
            if ((ch - '0') % 2 == 0) even++;
            else odd++;
        }
        return even == odd;
    }

    private static String findStrictlyIncreasing(String[] numbers) {
        for (String num : numbers) {
            boolean increasing = true;
            for (int i = 1; i < num.length(); i++) {
                if (num.charAt(i) <= num.charAt(i-1)) {
                    increasing = false;
                    break;
                }
            }
            if (increasing) return num;
        }
        return null;
    }

    private static String findAllDistinct(String[] numbers) {
        for (String num : numbers) {
            if (num.length() == distinctDigits(num)) return num;
        }
        return null;
    }

    private static String findSecondPalindrome(String[] numbers) {
        boolean foundFirst = false;
        for (String num : numbers) {
            if (isPalindrome(num)) {
                if (foundFirst) return num;
                foundFirst = true;
            }
        }
        return null;
    }

    private static boolean isPalindrome(String num) {
        return new StringBuilder(num).reverse().toString().equals(num);
    }

    private static void solveQuadraticEquation(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println("Korni uravneniya: " + root1 + " i " + root2);
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            System.out.println("Uravnenie imeet odin koren: " + root);
        } else {
            System.out.println("Uravnenie ne imeet deystvitelnyh korney");
        }
    }
}




//Доржиев Жаргал, 12:00 5.10.2024 11:00 9.11.2024