import java.util.Scanner;

public class B8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число: ");
        String number = scanner.nextLine();

        System.out.print("Введите основание исходной системы счисления: ");
        int sourceBase = scanner.nextInt();

        System.out.print("Введите основание целевой системы счисления: ");
        int targetBase = scanner.nextInt();

        String result = convertBase(number, sourceBase, targetBase);
        System.out.println("Результат: " + result);

        scanner.close();
    }

    public static String convertBase(String number, int sourceBase, int targetBase) {
        int decimal = 0;
        for (int i = 0; i < number.length(); i++) {
            char digit = number.charAt(i);
            int value = Character.isDigit(digit) ? digit - '0' : digit - 'A' + 10;
            decimal = decimal * sourceBase + value;
        }

        if (decimal == 0) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        while (decimal > 0) {
            int remainder = decimal % targetBase;
            if (remainder < 10) {
                result.insert(0, (char) ('0' + remainder));
            } else {
                result.insert(0, (char) ('A' + remainder - 10));
            }
            decimal /= targetBase;
        }

        return result.toString();
    }
}