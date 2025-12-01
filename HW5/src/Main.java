import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите строку: ");
        String input = scanner.nextLine();

        int lowercaseCount = 0;
        int uppercaseCount = 0;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                lowercaseCount++;
            }
            else if (ch >= 'A' && ch <= 'Z') {
                uppercaseCount++;
            }
        }

        System.out.println("Количество строчных букв — " + lowercaseCount);
        System.out.println("Количество прописных букв — " + uppercaseCount);

        scanner.close();
    }
}