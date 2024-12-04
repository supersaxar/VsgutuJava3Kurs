package DorzhievZhargalB7621;

import java.util.function.Function;

public class CurryingExamples {

    public static Function<Integer, Function<Integer, Integer>> add =
            x -> y -> x + y;

    public static Function<String, Function<String, Boolean>> matchesRegex =
            regex -> input -> input.matches(regex);

    public static Function<String, Function<String, String[]>> splitByRegex =
            regex -> input -> input.split(regex);

    public static void main(String[] args) {
        System.out.println("Сложение: " + add.apply(5).apply(10));

        String regex = "\\d+";
        String testString = "1234";
        System.out.println("Проверка на регулярное выражение: " + matchesRegex.apply(regex).apply(testString));

        String splitRegex = ",";
        String splitString = "a,b,c";
        String[] result = splitByRegex.apply(splitRegex).apply(splitString);
        System.out.println("Результат разбиения: ");
        for (String s : result) {
            System.out.println(s);
        }
    }
}