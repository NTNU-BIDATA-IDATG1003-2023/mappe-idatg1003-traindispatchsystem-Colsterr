package edu.ntnu.stud.TrainHandling;
import java.util.List;

/**
 * The TrainPlatformAllocator class provides functionality for assigning platforms
 * to train departures. It allows the assignment of a specific platform to a train
 * departure identified by its train number.
 */
public class TrainPlatformAllocator {

    /**
     * Assigns a specified platform to a train departure.
     * This method searches through a list of TrainDeparture objects for a departure
     * matching the given train number and assigns the specified platform to it.
     *
     * @param departures  A list of TrainDeparture objects to search through.
     * @param trainNumber The train number of the departure to which the platform is to be assigned.
     * @param platform    The platform to assign to the train departure.
     * @throws IllegalArgumentException if no departure with the specified train number is found.
     */
    public static void assignPlatform(List<TrainDeparture> departures, String trainNumber, String platform) {
        TrainDeparture departureToAssignPlatform = null;
        for (TrainDeparture departure : departures) {
            if (departure.getTrainNumber().equals(trainNumber)) {
                departureToAssignPlatform = departure;
                break;
            }
        }

        if (departureToAssignPlatform == null) {
            throw new IllegalArgumentException("No departure found with the specified train number.");
        }

        departureToAssignPlatform.setPlatform(platform);
    }
}




