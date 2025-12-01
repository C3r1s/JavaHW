import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Box3D box1 = new Box3D(5.0, 3.0, 2.0);
        System.out.println("Объект Box3D {" + box1 + "}");
        System.out.println("Объём: " + box1.getVolume());
        System.out.println();

        System.out.println("Новые значения");
        System.out.print("Ширина: ");
        double newWidth = scanner.nextDouble();

        System.out.print("Высота: ");
        double newHeight = scanner.nextDouble();

        System.out.print("Глубина: ");
        double newDepth = scanner.nextDouble();

        Box3D box2 = new Box3D(newWidth, newHeight, newDepth);
        System.out.println("Объект Box3D {" + box2 + "}");
        System.out.println("Объём: " + box2.getVolume());

        scanner.close();
    }
}