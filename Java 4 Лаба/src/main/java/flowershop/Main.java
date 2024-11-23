package flowershop;

import flowershop.model.*;
import java.util.Scanner;
import java.util.List;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Bouquet bouquet = new Bouquet();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Добавить розу");
            System.out.println("2. Добавить тюльпан");
            System.out.println("3. Добавить аксессуар");
            System.out.println("4. Показать стоимость букета");
            System.out.println("5. Сортировать по свежести");
            System.out.println("6. Найти цветы по длине стебля");
            System.out.println("7. Выход");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addRose();
                    break;
                case 2:
                    addTulip();
                    break;
                case 3:
                    addAccessory();
                    break;
                case 4:
                    System.out.printf("Общая стоимость букета: %.2f\n",
                            bouquet.calculateTotalPrice());
                    break;
                case 5:
                    bouquet.sortByFreshness();
                    displayFlowers();
                    break;
                case 6:
                    findFlowersByStemLength();
                    break;
                case 7:
                    return;
            }
        }
    }

    private static void addRose() {
        System.out.println("Введите цвет розы:");
        String color = scanner.next();
        System.out.println("Введите цену:");
        double price = scanner.nextDouble();
        System.out.println("Введите длину стебля (см):");
        int stemLength = scanner.nextInt();
        System.out.println("Введите уровень свежести (1-10):");
        int freshness = scanner.nextInt();

        bouquet.addFlower(new Rose(color, price, stemLength, freshness));
    }

    private static void addTulip() {
        System.out.println("Введите сорт тюльпана:");
        String variety = scanner.next();
        System.out.println("Введите цену:");
        double price = scanner.nextDouble();
        System.out.println("Введите длину стебля (см):");
        int stemLength = scanner.nextInt();
        System.out.println("Введите уровень свежести (1-10):");
        int freshness = scanner.nextInt();

        bouquet.addFlower(new Tulip(variety, price, stemLength, freshness));
    }

    private static void addAccessory() {
        System.out.println("Введите название аксессуара:");
        String name = scanner.next();
        System.out.println("Введите цену:");
        double price = scanner.nextDouble();

        bouquet.addAccessory(new Accessory(name, price));
    }

    private static void displayFlowers() {
        System.out.println("\nЦветы в букете (отсортированы по свежести):");
        for (Flower flower : bouquet.getFlowers()) {
            System.out.printf("%s - Свежесть: %d, Длина стебля: %d см\n",
                    flower.getName(), flower.getFreshnessLevel(),
                    flower.getStemLength());
        }
    }

    private static void findFlowersByStemLength() {
        System.out.println("Введите минимальную длину стебля:");
        int minLength = scanner.nextInt();
        System.out.println("Введите максимальную длину стебля:");
        int maxLength = scanner.nextInt();

        List<Flower> found = bouquet.findFlowersByStemLength(minLength, maxLength);
        System.out.println("\nНайденные цветы:");
        for (Flower flower : found) {
            System.out.printf("%s - Длина стебля: %d см\n",
                    flower.getName(), flower.getStemLength());
        }
    }
}