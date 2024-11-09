import java.util.Scanner;

public class B7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите десятичное число: ");
        int decimal = scanner.nextInt();

        System.out.print("Введите основание новой системы счисления: ");
        int base = scanner.nextInt();

        String result = convertDecimalToBase(decimal, base);
        System.out.println("Результат: " + result);

        scanner.close();
    }

    public static String convertDecimalToBase(int decimal, int base) {
        if (decimal == 0) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        while (decimal > 0) {
            int remainder = decimal % base;
            if (remainder < 10) {
                result.insert(0, (char) ('0' + remainder));
            } else {
                result.insert(0, (char) ('A' + remainder - 10));
            }
            decimal /= base;
        }

        return result.toString();
    }
}