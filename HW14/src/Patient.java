public class Patient {
    private int id;
    private String fullName;
    private int age;

    public Patient(int id, String fullName, int age) {
        this.id = id; this.fullName = fullName; this.age = age;
    }
    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return id + "  " + fullName + "  " + age + " лет";
    }
}