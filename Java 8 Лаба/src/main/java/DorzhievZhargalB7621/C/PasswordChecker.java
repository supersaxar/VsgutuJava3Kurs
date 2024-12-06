package DorzhievZhargalB7621.C;

import java.util.regex.Pattern;

public class PasswordChecker {
    public static boolean isStrongPassword(String password) {
        if (password == null || password.length() < 10) {
            return false;
        }

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d_]{10,}$";
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(password).matches();
    }

    public static void main(String[] args) {
        String password = "StrongPass1_";
        if (isStrongPassword(password)) {
            System.out.println("Пароль силен");
        } else {
            System.out.println("Пароль не силен");
        }
    }
}
