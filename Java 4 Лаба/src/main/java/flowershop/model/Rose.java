package flowershop.model;

public class Rose extends Flower {
    private String color;

    public Rose(String color, double price, int stemLength, int freshnessLevel) {
        super("Rose", price, stemLength, freshnessLevel);
        this.color = color;
    }
}