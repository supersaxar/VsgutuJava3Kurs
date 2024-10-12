import java.util.Random;

public class A3 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Random rand = new Random();

        System.out.println("S perehodom:");
        for (int i = 0; i < n; i++) {
            System.out.println(rand.nextInt(100)); // случайные числа от 0 до 99
        }

        System.out.println("Bez perehoda:");
        for (int i = 0; i < n; i++) {
            System.out.print(rand.nextInt(100) + " ");
        }
    }
}
