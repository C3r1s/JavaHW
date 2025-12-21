import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DbManager.init();
        while (true) {
            System.out.println("""
                    СИСТЕМА УПРАВЛЕНИЯ БОЛЬНИЦЕЙ
                    1. Добавить пациента
                    2. Просмотр пациентов
                    3. Посмотреть врачей
                    4. Записаться на приём
                    5. Выход
                    Ваш выбор:""");
            switch (sc.nextLine()) {
                case "1" -> addPatient();
                case "2" -> showPatients();
                case "3" -> showDoctors();
                case "4" -> assignPatient();
                case "5" -> {
                    System.out.println("СПАСИБО ЗА ИСПОЛЬЗОВАНИЕ СИСТЕМЫ!");
                    return;
                }
                default -> System.out.println("Неверный пункт");
            }
        }
    }

    private static void addPatient() {
        System.out.print("ФИО пациента: ");
        String name = sc.nextLine();
        System.out.print("Возраст: ");
        int age = Integer.parseInt(sc.nextLine());
        DbManager.insertPatient(new Patient(0, name, age));
        System.out.println("Пациент добавлен.");
    }

    private static void showPatients() {
        List<Patient> list = DbManager.getPatients();
        if (list.isEmpty()) System.out.println("Пациентов нет.");
        else list.forEach(System.out::println);
    }

    private static void showDoctors() {
        List<Doctor> list = DbManager.getDoctors();
        if (list.isEmpty()) System.out.println("Врачей нет.");
        else list.forEach(System.out::println);
    }

    private static void assignPatient() {
        showPatients();
        System.out.print("ID пациента: ");
        int pId = Integer.parseInt(sc.nextLine());
        showDoctors();
        System.out.print("ID врача: ");
        int dId = Integer.parseInt(sc.nextLine());
        DbManager.assignPatientToDoctor(pId, dId);
        System.out.println("Запись выполнена.");
    }
}