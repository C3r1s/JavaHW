import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DbManager.initTable();

        while (true) {
            System.out.println("""
                    1 – Показать все автомобили
                    2 – Показать всех производителей
                    3 – Показать автомобили заданного года
                    4 – Показать автомобили заданного производителя
                    5 – Показать автомобили заданного цвета
                    6 – Показать автомобили с объёмом двигателя ≥ X
                    7 – Показать автомобили заданного типа
                    0 – Выход
                    """);
            System.out.print("> ");
            switch (sc.nextLine()) {
                case "1" -> show(DbManager.select(null));
                case "2" -> showBrands();
                case "3" -> {
                    System.out.print("Год: ");
                    show(DbManager.select("year = " + sc.nextLine()));
                }
                case "4" -> {
                    System.out.print("Производитель: ");
                    show(DbManager.select("brand LIKE '" + sc.nextLine() + "'"));
                }
                case "5" -> {
                    System.out.print("Цвет: ");
                    show(DbManager.select("color LIKE '" + sc.nextLine() + "'"));
                }
                case "6" -> {
                    System.out.print("Минимальный объём: ");
                    show(DbManager.select("engine >= " + sc.nextLine()));
                }
                case "7" -> {
                    System.out.print("Тип (седан/хэтчбек/универсал/внедорожник): ");
                    show(DbManager.select("type LIKE '" + sc.nextLine() + "'"));
                }
                case "0" -> { return; }
                default -> System.out.println("Неверный пункт");
            }
        }
    }

    private static void show(List<Car> list) {
        if (list.isEmpty()) System.out.println("Ничего не найдено.");
        else list.forEach(System.out::println);
    }

    private static void showBrands() {
        Set<String> brands = new LinkedHashSet<>();
        DbManager.select(null).forEach(c -> brands.add(c.getBrand()));
        System.out.println("Производители: " + brands);
    }
}