public class Box3D extends Box {
    private double depth;

    public Box3D(double width, double height, double depth) {
        super(width, height);
        this.depth = depth;
    }

    public double getVolume() {
        return width * height * depth;
    }

    @Override
    public String toString() {
        return "ширина = " + width + ", высота = " + height + ", глубина = " + depth;
    }
}