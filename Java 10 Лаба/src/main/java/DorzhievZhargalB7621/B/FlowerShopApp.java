//Доржиев Жаргал Группа Б-762-1 Вариант 1

package DorzhievZhargalB7621.B;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FlowerShopApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bouquet bouquet = new Bouquet();
        String fileName = "C:\\Users\\Aye11\\IdeaProjects\\Java10Lab\\src\\main\\java\\DorzhievZhargalB7621\\B\\bouquet.txt";

        while (true) {
            System.out.println("1. Добавить цветок\n2. Добавить аксессуар\n3. Посмотреть букет\n4. Сохранить букет\n5. Загрузить букет\n6. Отсортировать цветы по свежести\n7. Найти цветы по длине стебля\n8. Выйти");
            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Введите тип цветка (Роза/Тюльпан): ");
                    String type = scanner.next();
                    System.out.print("Введите цену: ");
                    double price = scanner.nextDouble();
                    System.out.print("Введите уровень свежести: ");
                    int freshness = scanner.nextInt();
                    System.out.print("Введите длину стебля: ");
                    double stem = scanner.nextDouble();

                    if (type.equalsIgnoreCase("Роза")) {
                        bouquet.addFlower(new Rose(price, freshness, stem));
                    } else if (type.equalsIgnoreCase("Тюльпан")) {
                        bouquet.addFlower(new Tulip(price, freshness, stem));
                    } else {
                        System.out.println("Неизвестный тип цветка.");
                    }
                }
                case 2 -> {
                    System.out.print("Введите название аксессуара: ");
                    String name = scanner.next();
                    System.out.print("Введите цену: ");
                    double price = scanner.nextDouble();
                    bouquet.addAccessory(new Accessory(name, price));
                }
                case 3 -> System.out.println(bouquet);
                case 4 -> {
                    try {
                        FileConnector.saveToFile(fileName, bouquet);
                        System.out.println("Букет сохранен в файл.");
                    } catch (IOException e) {
                        System.out.println("Ошибка при сохранении в файл: " + e.getMessage());
                    }
                }
                case 5 -> {
                    try {
                        bouquet = FileConnector.loadFromFile(fileName);
                        System.out.println("Букет загружен из файла.");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Ошибка при загрузке из файла: " + e.getMessage());
                    }
                }
                case 6 -> {
                    bouquet.sortFlowersByFreshness();
                    System.out.println("Цветы отсортированы по свежести.");
                }
                case 7 -> {
                    System.out.print("Введите минимальную длину стебля: ");
                    double min = scanner.nextDouble();
                    System.out.print("Введите максимальную длину стебля: ");
                    double max = scanner.nextDouble();
                    List<Flower> foundFlowers = bouquet.findFlowersByStemLength(min, max);
                    System.out.println("Найденные цветы: " + foundFlowers);
                }
                case 8 -> {
                    System.out.println("Выход...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Недопустимый выбор. Попробуйте еще раз.");
            }
        }
    }
}