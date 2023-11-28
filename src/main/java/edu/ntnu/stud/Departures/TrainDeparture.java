package edu.ntnu.stud.Departures;

import java.time.LocalTime;
import java.util.List;

/**
 * Represents a single train departure, encapsulating details such as departure time, track,
 * line, train number, destination, delay, and platform.
 */

public class TrainDeparture {
    private LocalTime departureTime;
    private int track;
    private String line;
    private String trainNumber;
    private String destination;
    private LocalTime delay;
    private String platform;

    /**
     * Constructs a new TrainDeparture object with the specified details.
     *
     * @param departureTime The scheduled departure time of the train.
     * @param track         The track number from which the train will depart.
     * @param line          The line number or name of the train service.
     * @param trainNumber   The unique identifier for the train.
     * @param destination   The final destination of the train.
     * @param delay         The delay added to the original departure time, if any.
     */
    public TrainDeparture(LocalTime departureTime, int track, String line, String trainNumber, String destination, LocalTime delay) {
        this.departureTime = departureTime;
        this.track = track;
        this.line = line;
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.delay = delay;
    }

    /**
     * Sets the platform for this train departure.
     *
     * @param platform The platform to be assigned to the train departure.
     */

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * Gets the assigned platform for this train departure.
     *
     * @return The platform assigned to this train departure.
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * Gets the destination of the train departure.
     *
     * @return The destination of the train.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets a delay for the train departure.
     *
     * @param delay The delay time to be set for the train departure.
     */
    public void setDelay(LocalTime delay) {
        this.delay = delay;
    }

    /**
     * Adds a delay to the existing departure time of the train.
     * Throws an IllegalArgumentException if the delay is null or zero.
     *
     * @param delay The delay time to be added to the departure time.
     * @throws IllegalArgumentException If the delay is null or zero.
     */

    public void addDelay(LocalTime delay) {
        if (delay == null || delay.equals(LocalTime.of(0, 0))) {
            throw new IllegalArgumentException("Ugyldig forsinkelse: " + delay);
        }
        this.delay = delay;
        departureTime = departureTime.plusHours(delay.getHour()).plusMinutes(delay.getMinute());
    }

    /**
     * Gets the departure time of the train.
     *
     * @return The departure time of the train.
     */
    public LocalTime getDepartureTime() {
        return departureTime;
    }

    /**
     * Gets the train number.
     *
     * @return The train number.
     */
    public String getTrainNumber() {
        return trainNumber;
    }

    /**
     * Adds a TrainDeparture object to the list of departures.
     * Throws an IllegalArgumentException if a departure with the same train number already exists.
     *
     * @param departures    The list of train departures to which the new departure will be added.
     * @param newDeparture  The new TrainDeparture object to add.
     * @throws IllegalArgumentException If a departure with the same train number already exists in the list.
     */
    public static void addTrainDeparture(List<TrainDeparture> departures, TrainDeparture newDeparture) {
        if (containsTrainWithNumber(departures, newDeparture.getTrainNumber())) {
            throw new IllegalArgumentException("Train with the same train number already exists in the list.");
        }
        departures.add(newDeparture);
    }

    /**
     * Checks if a train number is present in the list of departures.
     *
     * @param departures  The list of train departures.
     * @param trainNumber The train number to check for.
     * @return True if the train number is found in the list, otherwise false.
     */
    private static boolean containsTrainWithNumber(List<TrainDeparture> departures, String trainNumber) {
        return departures.stream().anyMatch(departure -> departure.getTrainNumber().equals(trainNumber));
    }

    /**
     * Returns a string representation of the train departure.
     *
     * @return A string detailing the departure time, track, line, train number, destination, delay, and platform.
     */
    @Override
    public String toString() {
        String delayString = (delay != null && !delay.equals(LocalTime.MIDNIGHT)) ? delay.toString() : "No delay";

        return "Departure Time: " + departureTime +
                ", Track: " + (track == -1 ? "Not assigned" : track) +
                ", Line: " + line +
                ", Train Number: " + trainNumber +
                ", Destination: " + destination +
                ", Delay: " + delayString +
                ", Platform: " + (platform != null ? platform : "Not assigned");
    }
}
