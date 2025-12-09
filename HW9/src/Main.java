import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<String> colors = Arrays.asList("Red", "Green", "Orange");
        System.out.println("Исходный список чисел: " + numbers);
        System.out.println("Перевернутый список чисел: " + reverseList(numbers));
        System.out.println("Исходный список цветов: " + colors);
        System.out.println("Перевернутый список цветов: " + reverseList(colors));
    }

    public static <T> List<T> reverseList(List<T> list) {
        List<T> reversed = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            reversed.add(list.get(i));
        }
        return reversed;
    }
}