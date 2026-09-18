import java.util.ArrayList;

public class ParkingLot {

    private ArrayList<ParkingSlot> slots;

    public ParkingLot() {

        slots = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            slots.add(new ParkingSlot(i));
        }
    }

    public ParkingSlot findAvailableSlot() {

        for (ParkingSlot slot : slots) {

            if (slot.isAvailable()) {
                return slot;
            }
        }

        return null;
    }

    public ParkingSlot findVehicle(String number) {

        for (ParkingSlot slot : slots) {

            if (!slot.isAvailable()
                    && slot.getVehicle().getNumber().equalsIgnoreCase(number)) {

                return slot;
            }
        }

        return null;
    }

    public void showStatus() {

        System.out.println();
        System.out.println("========== PARKING STATUS ==========");

        for (ParkingSlot slot : slots) {
            System.out.println(slot);
        }

        System.out.println("====================================");
    }
}