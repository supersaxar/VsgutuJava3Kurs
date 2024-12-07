package DorzhievZhargalB7621;

public class Ship implements Runnable {
    private final String name;
    private final Port port;
    private int containerCapacity;
    private int currentContainers;
    private final int maxLoad;

    public Ship(String name, Port port, int containerCapacity, int maxLoad) {
        this.name = name;
        this.port = port;
        this.containerCapacity = containerCapacity;
        this.currentContainers = (int)(Math.random() * containerCapacity); // Начальная случайная загрузка
        this.maxLoad = maxLoad;
        System.out.println("🛳️ " + name + " создан: начальная загрузка " + currentContainers + " контейнеров, грузоподъемность " + containerCapacity);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("\n🚢 " + name + " начинает " + (i+1) + "-й рейс");
                processDocking();
                Thread.sleep((long) (Math.random() * 2000));
            }
            System.out.println("✅ " + name + " завершил работу");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void processDocking() throws InterruptedException {
        Dock dock = port.acquireDock(name);
        try {
            System.out.println(name + " прибыл на причал № " + dock.getId());

            if (currentContainers < containerCapacity) {
                int loadAmount = Math.min(maxLoad, containerCapacity - currentContainers);
                if (port.unloadContainers(loadAmount)) {
                    currentContainers += loadAmount;
                    System.out.println(name + " загрузил " + loadAmount + " контейнеров из порта. На борту: " + currentContainers);
                } else {
                    System.out.println(name + " не смог загрузить контейнеры из порта");
                }
            }

            if (currentContainers > 0) {
                int unloadAmount = Math.min(maxLoad, currentContainers);
                if (port.loadContainers(unloadAmount)) {
                    currentContainers -= unloadAmount;
                    System.out.println(name + " выгрузил " + unloadAmount + " контейнеров в порт. Осталось на борту: " + currentContainers);
                } else {
                    System.out.println(name + " не смог выгрузить контейнеры в порт");
                }
            }
        } finally {
            port.releaseDock(dock, name);
        }
    }
}