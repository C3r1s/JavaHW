public class Teacher extends Human {
    private String speciality;
    private int experience;

    public Teacher(String lastName, String firstName, int age, String speciality, int experience) {
        super(lastName, firstName, age);
        this.speciality = speciality;
        this.experience = experience;
    }

    @Override
    public void info() {
        super.info();
        System.out.print(" " + speciality + " " + experience);
    }

    @Override
    public String toString() {
        return super.toString() + " " + speciality + " " + experience;
    }
}