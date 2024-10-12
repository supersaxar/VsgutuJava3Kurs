public class A5 {
    public static void main(String[] args) {
        int sum = 0;
        int product = 1;

        for (String arg : args) {
            int num = Integer.parseInt(arg);
            sum += num;
            product *= num;
        }

        System.out.println("Summa: " + sum);
        System.out.println("Proizvedenie: " + product);
    }
}
