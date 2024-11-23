package flowershop.model;

public class Tulip extends Flower {
    private String variety;

    public Tulip(String variety, double price, int stemLength, int freshnessLevel) {
        super("Tulip", price, stemLength, freshnessLevel);
        this.variety = variety;
    }
}