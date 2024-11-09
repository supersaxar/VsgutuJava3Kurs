import java.util.Scanner;

public class B3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите k");
        int k = sc.nextInt();
        System.out.println("Введите n");
        int n = sc.nextInt();
        System.out.println("Введите m");
        int m = sc.nextInt();
        if (k >= n && k <= m) {
            if (k == n) {
                System.out.println("k принадлежит интервалу [n; m] и также интервалу [n; m)");
            } else if (k == m) {
                System.out.println("k принадлежит интервалу [n; m] и также интервалу (n; m]");
            } else {
                System.out.println("k принадлежит интервалу (n; m)");
            }
        }
    }
}

