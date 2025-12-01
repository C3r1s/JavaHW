public class Student extends Human {
    private String speciality, group;
    private int rating;

    public Student() {
    }

    public Student(String lastName, String firstName, int age, String speciality, String group, int rating) {
        super(lastName, firstName, age);
        this.speciality = speciality;
        this.group = group;
        this.rating = rating;
    }

    public Student(Human human, String speciality, String group, int rating) {
        super(human);
        this.speciality = speciality;
        this.group = group;
        this.rating = rating;
    }

    public Student(Student other) {
        super(other);
        this.speciality = other.speciality;
        this.group = other.group;
        this.rating = other.rating;
        System.out.println("StudentCopyConstructor:\t" + Integer.toHexString(hashCode()));
    }

    @Override
    public void info() {
        super.info();
        System.out.print(" " + speciality + " " + group + " " + rating);
    }

    @Override
    public String toString() {
        return  super.toString() + " " + speciality + " " + group + " " + rating;
    }
}