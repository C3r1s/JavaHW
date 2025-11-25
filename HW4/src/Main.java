import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
        System.out.println(numbers);

        Collections.shuffle(numbers);


        int[] uniqueArray = numbers.stream().mapToInt(Integer::intValue).toArray();


        System.out.println(Arrays.toString(uniqueArray));
    }
}