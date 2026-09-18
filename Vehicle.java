public class Vehicle {

    private String number;
    private String owner;
    private VehicleType type;

    public Vehicle(String number, String owner, VehicleType type) {
        this.number = number;
        this.owner = owner;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public String getOwner() {
        return owner;
    }

    public VehicleType getType() {
        return type;
    }

    @Override
    public String toString() {
        return number + " | " + owner + " | " + type;
    }
}

enum VehicleType {
    BIKE,
    CAR,
    SUV,
    EV
}