public class Graduate extends Student {
    private String topic;

    public Graduate(String lastName, String firstName, int age, String speciality, String group, int rating, String topic) {
        super(lastName, firstName, age, speciality, group, rating);
        this.topic = topic;
    }

    public Graduate(Student student, String topic)
    {
        super(student);
        this.topic = topic;
        System.out.println("GraduateCopyConstructor:\t" + Integer.toHexString(hashCode()));
    }

    public Graduate(Graduate other) {
        super(other); // вызывает Student(Student other)
        this.topic = other.topic;
        System.out.println("GraduateCopyConstructor:\t" + Integer.toHexString(hashCode()));
    }

    @Override
    public void info() {
        super.info();
        System.out.println(" " + topic);
    }

    @Override
    public String toString() {
        return super.toString() + " " + topic;
    }
}