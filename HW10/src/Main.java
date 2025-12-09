public class Main {
    public static void main(String[] args) {
        ShortestWordFinder finder = new ShortestWordFinder() {
            @Override
            public String shortest(String a, String b) {
                return a.length() <= b.length() ? a : b;
            }
        };

        String w1 = "Собака";
        System.out.println("Первое слово:" + w1);
        String w2 = "Кот";
        System.out.println("Первое слово:" + w2);
        System.out.println("Самое короткое слово: " + finder.shortest(w1, w2));
    }
}