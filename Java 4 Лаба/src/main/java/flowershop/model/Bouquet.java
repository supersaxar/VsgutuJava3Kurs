package flowershop.model;

import flowershop.model.Accessory;
import flowershop.model.Flower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Bouquet {
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

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Flower flower : flowers) {
            totalPrice += flower.getPrice();
        }
        for (Accessory accessory : accessories) {
            totalPrice += accessory.getPrice();
        }
        return totalPrice;
    }

    public void sortByFreshness() {
        Collections.sort(flowers, (f1, f2) ->
                Integer.compare(f2.getFreshnessLevel(), f1.getFreshnessLevel()));
    }

    public List<Flower> findFlowersByStemLength(int minLength, int maxLength) {
        List<Flower> result = new ArrayList<>();
        for (Flower flower : flowers) {
            if (flower.getStemLength() >= minLength &&
                    flower.getStemLength() <= maxLength) {
                result.add(flower);
            }
        }
        return result;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public List<Accessory> getAccessories() {
        return accessories;
    }
}