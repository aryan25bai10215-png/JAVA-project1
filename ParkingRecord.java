import java.time.LocalDateTime;

public class ParkingRecord {

    private Vehicle vehicle;
    private int slotId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double fee;

    public ParkingRecord(Vehicle vehicle, int slotId) {

        this.vehicle = vehicle;
        this.slotId = slotId;
        this.entryTime = LocalDateTime.now();
        this.exitTime = null;
        this.fee = 0;
    }

    public void exit(double fee) {

        this.exitTime = LocalDateTime.now();
        this.fee = fee;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getSlotId() {
        return slotId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public double getFee() {
        return fee;
    }

    @Override
    public String toString() {

        return "Vehicle: " + vehicle.getNumber()
                + " | Owner: " + vehicle.getOwner()
                + " | Type: " + vehicle.getType()
                + " | Slot: " + slotId
                + " | Entry: " + entryTime
                + " | Exit: " + exitTime
                + " | Fee: Rs." + fee;
    }
}