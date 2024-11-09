public class B5 {
    public static void main(String[] args) {
        int number = 129;
        String binary = Integer.toBinaryString(number);
        int significantZeros = countSignificantZeros(binary);

        System.out.println("Число: " + number);
        System.out.println("Двоичная запись: " + binary);
        System.out.println("Количество значащих нулей: " + significantZeros);
    }

    public static int countSignificantZeros(String binary) {
        int count = 0;
        boolean foundOne = false;

        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                foundOne = true;
            } else if (foundOne && binary.charAt(i) == '0') {
                count++;
            }
        }

        return count;
    }
}