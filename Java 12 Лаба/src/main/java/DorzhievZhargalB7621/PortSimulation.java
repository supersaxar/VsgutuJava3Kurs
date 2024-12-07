//Доржиев Жаргал Группа Б-762-1 Вариант 1

package DorzhievZhargalB7621;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PortSimulation {
    public static void main(String[] args) {
        System.out.println("🚢 Начало симуляции работы порта 🚢");

        Port port = new Port(100, 5);

        for (int i = 0; i < 50; i++) {
            port.loadContainers(1);
        }

        Ship[] ships = new Ship[10];

        for (int i = 0; i < ships.length; i++) {
            ships[i] = new Ship("Корабль-" + (i + 1), port, 20, 10);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(ships.length);
        for (Ship ship : ships) {
            executorService.submit(ship);
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
            System.out.println("\n🏁 Симуляция порта завершена. Финальное количество контейнеров: " + port.getCurrentContainerCount());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}