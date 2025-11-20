import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Введите размер поля: ");
        int fieldSize = sc.nextInt();

        System.out.print("Введите количество символов: ");
        int blockSize = sc.nextInt();

        for (int row = 0; row < fieldSize; row++) {
            for (int innerRow = 0; innerRow < blockSize; innerRow++) {

                for (int col = 0; col < fieldSize; col++) {

                    boolean isFilled = (row + col) % 2 == 0;

                    for (int innerCol = 0; innerCol < blockSize; innerCol++) {
                        System.out.print(isFilled ? "* " : "  ");
                    }
                }
                System.out.println();
            }
        }
    }
}
