import java.util.*;

public class Main {
    public static void main(String[] args) {

        Map<String, Integer> originalMap = new HashMap<>();
        originalMap.put("X", 1);
        originalMap.put("Y", 2);
        originalMap.put("Z", 3);

        System.out.println("Исходный Map: " + originalMap);

        Map<Integer, List<String>> swappedMap = new HashMap<>();

        for (Map.Entry<String, Integer> entry : originalMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            swappedMap.computeIfAbsent(value, k -> new ArrayList<>())
                    .add(key);
        }

        System.out.println("Изменённый Map: " + swappedMap);
    }
}
