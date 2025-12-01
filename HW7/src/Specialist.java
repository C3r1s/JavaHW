public class Specialist extends Graduate {
    private int diplomaGrade;

    public Specialist(String lastName, String firstName, int age,
                      String speciality, String group, int rating,
                      String topic, int diplomaGrade) {
        super(lastName, firstName, age, speciality, group, rating, topic);
        this.diplomaGrade = diplomaGrade;
    }

    public Specialist(Graduate graduate, int diplomaGrade) {
        super(graduate);
        this.diplomaGrade = diplomaGrade;
        System.out.println("SpecialistCopyConstructor:\t" + Integer.toHexString(hashCode()));
    }

    @Override
    public void info() {
        super.info();
        System.out.print(" Оценка по диплому: " + diplomaGrade);
    }

    @Override
    public String toString() {
        return super.toString() + " Оценка по диплому: " + diplomaGrade;
    }
}
