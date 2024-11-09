public class B6 {
    public static void main(String[] args) {
        int decimalNumber = 81;
        String representation = "100";

        int base = findBase(decimalNumber, representation);

        if (base != -1) {
            System.out.println("Число " + decimalNumber + " записывается как " + representation +
                    " в системе счисления с основанием " + base);
        } else {
            System.out.println("Не удалось найти подходящее основание системы счисления");
        }
    }

    public static int findBase(int decimalNumber, String representation) {
        for (int base = 2; base <= 36; base++) {
            if (Integer.toString(decimalNumber, base).equals(representation)) {
                return base;
            }
        }
        return -1; // Если подходящее основание не найдено
    }
}