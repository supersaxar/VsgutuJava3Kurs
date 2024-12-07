package DorzhievZhargalB7621.B;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Bouquet implements Serializable {
    private List<Flower> flowers;
    private List<Accessory> accessories;

    public Bouquet() {
        flowers = new ArrayList<>();
        accessories = new ArrayList<>();
    }

    public void addFlower(Flower flower) {
        flowers.add(flower);
    }

    public void addAccessory(Accessory accessory) {
        accessories.add(accessory);
    }

    public double calculateTotalCost() {
        double flowerCost = flowers.stream().mapToDouble(Flower::getPrice).sum();
        double accessoryCost = accessories.stream().mapToDouble(Accessory::getPrice).sum();
        return flowerCost + accessoryCost;
    }

    public void sortFlowersByFreshness() {
        flowers.sort(Comparator.comparingInt(Flower::getFreshnessLevel).reversed());
    }

    public List<Flower> findFlowersByStemLength(double min, double max) {
        return flowers.stream()
                .filter(flower -> flower.getStemLength() >= min && flower.getStemLength() <= max)
                .toList();
    }

    @Override
    public String toString() {
        return "Букет: \nЦветы: " + flowers + "\nАксессуары: " + accessories;
    }
}