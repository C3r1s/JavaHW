public class Human {
    private String lastName, firstName;
    private int age;

    public Human() {
    }

    public Human(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }

    public Human(Human other) {
        this.lastName = other.lastName;
        this.firstName = other.firstName;
        this.age = other.age;
        System.out.println("HumanCopyConstructor:\t" + Integer.toHexString(hashCode()));
    }

    public void info() {
        System.out.print("\n" + lastName + " " + firstName + " " + age + " ");
    }

    @Override
    public String toString() {
        return "\n" + lastName + " " + firstName + " " + age + " ";
    }
}