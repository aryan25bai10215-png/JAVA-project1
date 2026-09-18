import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static ParkingLot lot = new ParkingLot();

    static ArrayList<ParkingRecord> history = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {

            System.out.println();
            System.out.println("==================================");
            System.out.println("     SMART PARKING MANAGEMENT");
            System.out.println("==================================");
            System.out.println("1. Vehicle Entry");
            System.out.println("2. Vehicle Exit");
            System.out.println("3. Parking Status");
            System.out.println("4. Search Vehicle");
            System.out.println("5. Parking History");
            System.out.println("0. Exit");
            System.out.println("==================================");

            System.out.print("Enter your choice: ");

            String input = sc.nextLine();

            int choice;

            try {

                choice = Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {

                case 1:
                    vehicleEntry();
                    break;

                case 2:
                    vehicleExit();
                    break;

                case 3:
                    lot.showStatus();
                    break;

                case 4:
                    searchVehicle();
                    break;

                case 5:
                    showHistory();
                    break;

                case 0:

                    FileManager.saveHistory(history);

                    System.out.println("Thank you for using Smart Parking!");
                    sc.close();

                    return;

                default:

                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // VEHICLE ENTRY
    static void vehicleEntry() {

        System.out.println();
        System.out.println("---------- VEHICLE ENTRY ----------");

        System.out.print("Enter vehicle number: ");
        String number = sc.nextLine().trim();

        if (number.isEmpty()) {

            System.out.println("Vehicle number cannot be empty.");
            return;
        }

        System.out.print("Enter owner name: ");
        String owner = sc.nextLine().trim();

        if (owner.isEmpty()) {

            System.out.println("Owner name cannot be empty.");
            return;
        }

        // Check whether vehicle is already parked
        if (lot.findVehicle(number) != null) {

            System.out.println("This vehicle is already parked!");
            return;
        }

        System.out.println();
        System.out.println("Select vehicle type:");
        System.out.println("1. Bike");
        System.out.println("2. Car");
        System.out.println("3. SUV");
        System.out.println("4. EV");

        System.out.print("Enter type: ");

        String typeInput = sc.nextLine();

        int typeChoice;

        try {

            typeChoice = Integer.parseInt(typeInput);

        } catch (NumberFormatException e) {

            System.out.println("Invalid vehicle type!");
            return;
        }

        VehicleType vehicleType;

        switch (typeChoice) {

            case 1:
                vehicleType = VehicleType.BIKE;
                break;

            case 2:
                vehicleType = VehicleType.CAR;
                break;

            case 3:
                vehicleType = VehicleType.SUV;
                break;

            case 4:
                vehicleType = VehicleType.EV;
                break;

            default:

                System.out.println("Invalid vehicle type!");
                return;
        }

        // Find an empty slot
        ParkingSlot slot = lot.findAvailableSlot();

        if (slot == null) {

            System.out.println("Parking is full!");
            return;
        }

        // Create vehicle object
        Vehicle vehicle = new Vehicle(
                number,
                owner,
                vehicleType
        );

        // Park vehicle
        slot.park(vehicle);

        // Create parking record
        ParkingRecord record = new ParkingRecord(
                vehicle,
                slot.getId()
        );

        history.add(record);

        System.out.println();
        System.out.println("Vehicle parked successfully!");
        System.out.println("Vehicle Number : " + number);
        System.out.println("Owner          : " + owner);
        System.out.println("Vehicle Type   : " + vehicleType);
        System.out.println("Allocated Slot : " + slot.getId());
        System.out.println("Entry Time     : " + record.getEntryTime());
    }

    // VEHICLE EXIT
    static void vehicleExit() {

        System.out.println();
        System.out.println("---------- VEHICLE EXIT ----------");

        System.out.print("Enter vehicle number: ");
        String number = sc.nextLine().trim();

        ParkingSlot slot = lot.findVehicle(number);

        if (slot == null) {

            System.out.println("Vehicle not found in parking.");
            return;
        }

        ParkingRecord record = null;

        // Find active parking record
        for (ParkingRecord r : history) {

            if (r.getVehicle().getNumber().equalsIgnoreCase(number)
                    && r.getExitTime() == null) {

                record = r;
                break;
            }
        }

        if (record == null) {

            System.out.println("Parking record not found.");
            return;
        }

        // Get exit time
        LocalDateTime exitTime = LocalDateTime.now();

        // Calculate fee
        double fee = FeeCalculator.calculate(
                slot.getVehicle().getType(),
                record.getEntryTime(),
                exitTime
        );

        // Update record
        record.exit(fee);

        // Remove vehicle from slot
        slot.removeVehicle();

        System.out.println();
        System.out.println("Vehicle exited successfully!");
        System.out.println("Vehicle Number : " + number);
        System.out.println("Slot Number    : " + record.getSlotId());
        System.out.println("Entry Time     : " + record.getEntryTime());
        System.out.println("Exit Time      : " + record.getExitTime());
        System.out.println("Parking Fee    : Rs." + fee);
    }

    // SEARCH VEHICLE
    static void searchVehicle() {

        System.out.println();
        System.out.println("---------- SEARCH VEHICLE ----------");

        System.out.print("Enter vehicle number: ");
        String number = sc.nextLine().trim();

        ParkingSlot slot = lot.findVehicle(number);

        if (slot == null) {

            System.out.println("Vehicle not found.");

        } else {

            System.out.println("Vehicle found!");
            System.out.println("Slot: " + slot.getId());
            System.out.println("Vehicle Number: "
                    + slot.getVehicle().getNumber());
            System.out.println("Owner: "
                    + slot.getVehicle().getOwner());
            System.out.println("Type: "
                    + slot.getVehicle().getType());
        }
    }

    // SHOW HISTORY
    static void showHistory() {

        System.out.println();
        System.out.println("---------- PARKING HISTORY ----------");

        if (history.isEmpty()) {

            System.out.println("No parking records available.");
            return;
        }

        for (ParkingRecord record : history) {

            System.out.println(record);
        }
    }
}