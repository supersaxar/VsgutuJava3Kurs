//Доржиев Жаргал Группа Б-762-1 Вариант 1

package DorzhievZhargalB7621.B;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JosephusProblem {

    public static int solveWithArrayList(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Количество людей должно быть положительным");
        }

        List<Integer> circle = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            circle.add(i);
        }

        int currentIndex = 0;
        while (circle.size() > 1) {
            currentIndex = (currentIndex + 1) % circle.size();
            circle.remove(currentIndex);
        }

        return circle.get(0);
    }

    public static int solveWithLinkedList(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Количество людей должно быть положительным");
        }

        LinkedList<Integer> circle = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            circle.add(i);
        }

        while (circle.size() > 1) {
            circle.add(circle.removeFirst());
            circle.removeFirst();
        }

        return circle.getFirst();
    }

    public static void main(String[] args) {
        int n = 100000;

        long startArrayList = System.nanoTime();
        int resultArrayList = solveWithArrayList(n);
        long endArrayList = System.nanoTime();

        long startLinkedList = System.nanoTime();
        int resultLinkedList = solveWithLinkedList(n);
        long endLinkedList = System.nanoTime();

        System.out.println("Результат для ArrayList: " + resultArrayList);
        System.out.println("Время выполнения ArrayList: " + (endArrayList - startArrayList) + " нс");

        System.out.println();

        System.out.println("Результат для LinkedList: " + resultLinkedList);
        System.out.println("Время выполнения LinkedList: " + (endLinkedList - startLinkedList) + " нс");

        System.out.println();

        System.out.println("Причины более высокой производительности LinkedList в этой задаче:\n" +
                "\n" +
                "1) Сложность операций удаления:" +
                "\n" +
                "ArrayList: При удалении элемента из середины списка требуется сдвиг всех последующих элементов. Сложность операции O(n).\n" +
                "LinkedList: Удаление элемента происходит за константное время O(1), так как достаточно просто перелинковать соседние узлы.\n" +
                "\n" +
                "2) Внутренняя реализация:" +
                "\n" +
                "В данной задаче мы часто удаляем элементы из середины списка\n" +
                "LinkedList не копирует массив целиком при удалении, как это делает ArrayList\n" +
                "Узлы в LinkedList просто переподключаются, что значительно быстрее\n" +
                "\n" +
                "3) Алгоритм решения:\n" +
                "\n" +
                "В реализации LinkedList мы используем более эффективный метод перемещения элементов\n" +
                "Метод add() и removeFirst() в LinkedList работают за O(1)");
    }
}