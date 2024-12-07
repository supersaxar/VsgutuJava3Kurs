package DorzhievZhargalB7621;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Port {
    private final BlockingQueue<Dock> docks;
    private final AtomicInteger containerCount;
    private final int capacity;

    public Port(int capacity, int dockCount) {
        this.capacity = capacity;
        this.containerCount = new AtomicInteger(0);
        this.docks = new ArrayBlockingQueue<>(dockCount);

        for (int i = 0; i < dockCount; i++) {
            docks.offer(new Dock(i));
        }
        System.out.println("Порт создан: " + dockCount + " причалов, максимальная вместимость " + capacity + " контейнеров");
    }

    public Dock acquireDock(String shipName) throws InterruptedException {
        Dock dock = docks.take();
        System.out.println("🚢 " + shipName + " занял причал № " + dock.getId());
        return dock;
    }

    public void releaseDock(Dock dock, String shipName) {
        docks.offer(dock);
        System.out.println("🏁 " + shipName + " освободил причал № " + dock.getId());
    }

    public boolean loadContainers(int count) {
        int currentCount;
        do {
            currentCount = containerCount.get();
            if (currentCount + count > capacity) {
                System.out.println("⚠️ Превышена вместимость порта при загрузке " + count + " контейнеров");
                return false;
            }
        } while (!containerCount.compareAndSet(currentCount, currentCount + count));
        System.out.println("📦 Загружено " + count + " контейнеров. Всего в порту: " + containerCount.get());
        return true;
    }


    public boolean unloadContainers(int count) {
        int currentCount;
        do {
            currentCount = containerCount.get();
            if (currentCount - count < 0) {
                System.out.println("⚠️ Недостаточно контейнеров для выгрузки " + count + " контейнеров");
                return false;
            }
        } while (!containerCount.compareAndSet(currentCount, currentCount - count));
        System.out.println("📦 Выгружено " + count + " контейнеров. Всего в порту: " + containerCount.get());
        return true;
    }

    public int getCurrentContainerCount() {
        return containerCount.get();
    }
}