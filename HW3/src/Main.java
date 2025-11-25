import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int n = sc.nextInt();

        int[] array = new int[n];

        while (true) {

            System.out.println("\nМеню:");
            System.out.println("1. Ввод элементов массива");
            System.out.println("2. Отображение элементов массива");
            System.out.println("3. Поиск элемента в массиве");
            System.out.println("4. Сортировка массива (пузырьком)");
            System.out.println("5. Выход");
            System.out.print("Сделайте свой выбор: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    inputArray(array);
                    break;

                case 2:
                    displayArray(array);
                    break;

                case 3:
                    System.out.print("Введите значение для поиска: ");
                    int value = sc.nextInt();
                    searchValue(array, value);
                    break;

                case 4:
                    bubbleSort(array);
                    System.out.println("Массив отсортирован!");
                    break;

                case 5:
                    System.out.println("Выход...");
                    return;

                default:
                    System.out.println("Некорректный выбор!");
            }
        }
    }

    public static void inputArray(int[] arr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите элементы массива: ");

        for (int i = 0; i < arr.length; i++) {
            System.out.print("arr[" + i + "] = ");
            arr[i] = sc.nextInt();
        }
    }

    public static void displayArray(int[] arr) {
        System.out.print("Массив: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void searchValue(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                System.out.println("Элемент найден на позиции: " + i);
                return;
            }
        }
        System.out.println("Элемент не найден.");
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
