package edu.ntnu.stud.DelayManager;
import edu.ntnu.stud.Departures.TrainDeparture;
import java.time.LocalTime;
import java.util.List;

/**
 * The TrainDelayManager class provides functionality to manage delays of train departures.
 * It allows adding a specified delay to a train departure identified by its train number.
 */

public class TrainDelayManager {

    /**
     * Adds a specified delay to a train departure.
     * This method searches for a departure by its train number in the provided list of departures.
     * If the departure is found, the specified delay is added to it.
     *
     * @param departures  A list of TrainDeparture objects to search through.
     * @param trainNumber The train number of the departure to which the delay is to be added.
     * @param delay       The delay to be added to the departure time.
     * @throws IllegalArgumentException if no departure with the specified train number is found.
     */
    public static void addDelay(List<TrainDeparture> departures, String trainNumber, LocalTime delay) {
        TrainDeparture departureToAddDelay = null;
        for (TrainDeparture departure : departures) {
            if (departure.getTrainNumber().equals(trainNumber)) {
                departureToAddDelay = departure;
                break;
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






