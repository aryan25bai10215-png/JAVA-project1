import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    public static void saveHistory(ArrayList<ParkingRecord> history) {

        try {

            FileWriter writer = new FileWriter("parking_history.txt");

            for (ParkingRecord record : history) {
                writer.write(record.toString());
                writer.write("\n");
            }

            writer.close();

            System.out.println("Parking history saved successfully.");

        } catch (IOException e) {

            System.out.println("Error while saving parking history.");
        }
    }
}