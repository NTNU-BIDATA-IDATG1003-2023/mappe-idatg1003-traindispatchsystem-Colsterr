package edu.ntnu.stud.TrainHandling;
import java.util.ArrayList;
import java.util.List;


/**
 * The TrainDepartureRegister class maintains a list of all train departures.
 * It provides functionality to add, search, and modify train departures in the list.
 */

public class TrainDepartureRegister {
    private final List<TrainDeparture> departures;

    /**
     * Constructs a new TrainDepartureRegister.
     */

    public TrainDepartureRegister() {
        departures = new ArrayList<>();
    }

    /**
     * Adds a new TrainDeparture to the register.
     *
     * @param newDeparture The TrainDeparture to be added.
     */

    public void addTrainDeparture(TrainDeparture newDeparture) {
        departures.add(newDeparture);
    }

    /**
     * Finds a TrainDeparture by its train number.
     *
     * @param trainNumber The train number of the departure to find.
     * @return The TrainDeparture with the specified train number, or null if not found.
     */
    public TrainDeparture findTrainByTrainNumber(String trainNumber) {
        for (TrainDeparture departure : departures) {
            if (departure.getTrainNumber().equals(trainNumber)) {
                return departure;
            }
        }
        return null;
    }

    /**
     * Finds all departures to a specific destination.
     *
     * @param destination The destination to search for.
     * @return A list of TrainDepartures to the specified destination.
     */

    public List<TrainDeparture> findDeparturesByDestination(String destination) {
        List<TrainDeparture> list = new ArrayList<>();
        for (TrainDeparture departure : departures) {
            if (departure.getDestination().equalsIgnoreCase(destination)) {
                list.add(departure);
            }
        }
        return list;
    }


    /**
     * Gets a list of departures sorted by departure time.
     *
     * @return A sorted list of TrainDepartures.
     */

    public List<TrainDeparture> getSortedDepartures() {
        List<TrainDeparture> sortedDepartures = new ArrayList<>(departures);
        sortedDepartures.sort(new TrainDepartureComparator());
        return sortedDepartures;
    }

    /**
     * Gets the list of all train departures.
     *
     * @return The list of all TrainDepartures.
     */
    public List<TrainDeparture> getDepartures() {
        return departures;
    }
}
