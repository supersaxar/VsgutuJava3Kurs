//Доржиев Жаргал Группа Б-762-1 Вариант 1
package DorzhievZhargalB7621.B;

import java.util.Scanner;

public class User_Client {
    public static void main(String[] args) {

        String instanceName = args.length > 0 ? args[0] : "Default";
        System.out.println("Running instance: " + instanceName);

        Scanner scan = new Scanner(System.in);
        String name;
        System.out.print("Введи свое имя: ");
        name = scan.next();
        User_Interface ui = new User_Interface(name);
        ui.newGame();
    }
}