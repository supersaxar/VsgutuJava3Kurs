package flowershop.model;

public abstract class Flower {
    private String name;
    private double price;
    private int stemLength;
    private int freshnessLevel;

    public Flower(String name, double price, int stemLength, int freshnessLevel) {
        this.name = name;
        this.price = price;
        this.stemLength = stemLength;
        this.freshnessLevel = freshnessLevel;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStemLength() { return stemLength; }
    public int getFreshnessLevel() { return freshnessLevel; }
}


