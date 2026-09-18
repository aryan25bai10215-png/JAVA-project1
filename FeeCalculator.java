import java.time.Duration;
import java.time.LocalDateTime;

public class FeeCalculator {

    public static double calculate(
            VehicleType type,
            LocalDateTime entry,
            LocalDateTime exit) {

        long minutes = Duration.between(entry, exit).toMinutes();

        long hours = (long) Math.ceil(minutes / 60.0);

        if (hours < 1) {
            hours = 1;
        }

        double rate;

        switch (type) {

            case BIKE:
                rate = 10;
                break;

            case CAR:
                rate = 20;
                break;

            case SUV:
                rate = 30;
                break;

            case EV:
                rate = 15;
                break;

            default:
                rate = 20;
        }

        return hours * rate;
    }
}