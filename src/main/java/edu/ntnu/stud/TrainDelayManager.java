package edu.ntnu.stud;



import java.time.LocalTime;
import java.util.List;

public class TrainDelayManager {
    public static void addDelay(List<TrainDeparture> departures, String trainNumber, LocalTime delay) {
        // Søk etter avgangen basert på tognummer
        TrainDeparture departureToAddDelay = null;
        for (TrainDeparture departure : departures) {
            if (departure.getTrainNumber().equals(trainNumber)) {
                departureToAddDelay = departure;
                break; // Avslutt søket når vi har funnet den første avgangen med matchende tognummer
            }
        }

        if (departureToAddDelay == null) {
            throw new IllegalArgumentException("No departure found with the specified train number.");
        }

        // Legg til forsinkelse på avgangen
        if (delay != null) {
            departureToAddDelay.addDelay(delay);
        }
    }
}






