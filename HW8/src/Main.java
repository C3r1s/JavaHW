public class Main {
    public static void main(String[] args) {
        System.out.println("Скорости транспортных средств:");
        for (TransportSpeeds vehicle : TransportSpeeds.values()) {
            System.out.println(vehicle + " типичная скорость составляет " + vehicle.getSpeed() + " миль в час.");
        }
    }
}