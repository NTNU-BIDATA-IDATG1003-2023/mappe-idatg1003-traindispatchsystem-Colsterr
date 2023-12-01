package edu.ntnu.stud.Departures;
import java.util.Comparator;

/**
 * The TrainDepartureComparator class is a custom comparator used to compare TrainDeparture objects
 * based on their departure times.
 */
public class TrainDepartureComparator implements Comparator<TrainDeparture> {

    /**
     * Compares two TrainDeparture objects based on their departure times.
     *
     * @param departure1 The first TrainDeparture object to compare.
     * @param departure2 The second TrainDeparture object to compare.
     * @return A negative integer if departure1 precedes departure2 in terms of departure time,
     *         zero if they have the same departure time, or a positive integer if departure1 follows departure2.
     */
    @Override
    public int compare(TrainDeparture departure1, TrainDeparture departure2) {
        return departure1.getDepartureTime().compareTo(departure2.getDepartureTime());
    }
}
