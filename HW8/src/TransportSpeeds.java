public enum TransportSpeeds {
    CAR(65),
    TRUCK(55),
    AIRPLANE(600),
    TRAIN(70),
    BOAT(22);

    private final int speed;

    TransportSpeeds(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}