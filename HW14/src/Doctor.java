public class Doctor {
    private int id;
    private String fullName;
    private String speciality;

    public Doctor(int id, String fullName, String speciality) {
        this.id = id; this.fullName = fullName; this.speciality = speciality;
    }
    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getSpeciality() { return speciality; }

    @Override
    public String toString() {
        return id + "  " + fullName + "  " + speciality;
    }
}