public class Car {
    private final String brand;
    private final String model;
    private final double engine;
    private final int year;
    private final String color;
    private final String type;

    public Car(String brand, String model, double engine,
               int year, String color, String type) {
        this.brand = brand;
        this.model = model;
        this.engine = engine;
        this.year = year;
        this.color = color;
        this.type = type;
    }

    /* геттеры */
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getEngine() { return engine; }
    public int    getYear()   { return year; }
    public String getColor()  { return color; }
    public String getType()   { return type; }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %-4.1f %d  %-7s %s",
                brand, model, engine, year, color, type);
    }
}