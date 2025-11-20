package lesson_1;

import java.util.Scanner;

public class Input {

    static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Name: ");
        String name = sc.next();

        System.out.print("Age: ");
        int age = sc.nextInt();

        System.out.print("Height: ");
        double height = sc.nextFloat();
        System.out.printf("Name: %s. Age: %d. Height: %.2f", name, age, height);
        sc.close();
    }

}
