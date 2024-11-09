public class B2 {
    public static void main(String[] args) {
        String[] array = new String[3];
        array[0] = "Hello";
        array[1] = "world";
        array[2] = "zxc";
        for (int i = 2; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
    }
}
