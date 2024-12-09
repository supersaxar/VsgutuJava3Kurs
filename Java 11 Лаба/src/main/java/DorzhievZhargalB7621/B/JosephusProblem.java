//Доржиев Жаргал Группа Б-762-1 Вариант 1

package DorzhievZhargalB7621.B;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JosephusProblem {

    public static int solveJosephusProblem(List<Integer> circle) {
        if (circle.isEmpty()) {
            throw new IllegalArgumentException("Список не может быть пустым");
        }

        int currentIndex = 0;
        while (circle.size() > 1) {
            currentIndex = (currentIndex + 1) % circle.size();
            circle.remove(currentIndex);
        }

        return circle.get(0);
    }


    public static void main(String[] args) {
        int n = 10000;

        List<Integer> arrayListCircle = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            arrayListCircle.add(i);
        }

        long startArrayList = System.nanoTime();
        int resultArrayList = solveJosephusProblem(arrayListCircle);
        long endArrayList = System.nanoTime();

        System.out.println("Результат для ArrayList: " + resultArrayList);
        System.out.println("Время выполнения ArrayList: " + (endArrayList - startArrayList) + " нс");

        List<Integer> linkedListCircle = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            linkedListCircle.add(i);
        }

        long startLinkedList = System.nanoTime();
        int resultLinkedList = solveJosephusProblem(linkedListCircle);
        long endLinkedList = System.nanoTime();

        System.out.println("\nРезультат для LinkedList: " + resultLinkedList);
        System.out.println("Время выполнения LinkedList: " + (endLinkedList - startLinkedList) + " нс");

        System.out.println();

        System.out.println("Причины более высокой производительности ArrayList в этой задаче:\n" +
                "\n" +
                "1) Быстрый доступ по индексу: " +
                "\n" +
                " ArrayList элементы хранятся в массиве, и доступ к ним происходит мгновенно. В LinkedList доступ к элементам требует перебора узлов, что значительно медленнее.\n" +
                "\n" +
                "2) Удаление перекрывается поиском: " +
                "\n" +
                "Хотя удаление в LinkedList быстрее, большая часть времени уходит на поиск элемента для удаления, так как список нужно перебрать.\n" +
                "\n" +
                "3) Меньше затраты ресурсов компьютера: \n" +
                "\n" +
                "В ArrayList нет дополнительных затрат на управление ссылками между узлами, как в LinkedList, что снижает расходы на операции.\n");
    }
}
