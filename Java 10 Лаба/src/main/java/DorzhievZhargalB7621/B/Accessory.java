package DorzhievZhargalB7621.B;

import java.io.Serializable;

class Accessory implements Serializable {
    private String name;
    private double price;

    public Accessory(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " (Цена: " + price + ")";
    }
}