import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {

        LinkedList<String> colors = new LinkedList<>();

        colors.add("Красный");
        colors.add("Оранжевый");
        colors.add("Желтый");
        colors.add("Зеленый");
        colors.add("Голубой");
        colors.add("Синий");
        colors.add("Фиолетовый");

        System.out.println("Список: " + colors);

        System.out.println("\nСписок итераторов в прямом направлении:");
        ListIterator<String> iterator = colors.listIterator();

        while (iterator.hasNext()) {
            int index = iterator.nextIndex();
            String element = iterator.next();
            System.out.println("Индекс = " + index + ", Элемент = " + element);
        }

        System.out.println("\nСписок итераторов в обратном направлении:");
        while (iterator.hasPrevious()) {
            int index = iterator.previousIndex();
            String element = iterator.previous();
            System.out.println("Индекс = " + index + ", Элемент = " + element);
        }
    }
}
