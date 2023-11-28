package edu.ntnu.stud.Departures;

import java.time.LocalTime;
import java.util.List;

public class TrainDeparture {
    private LocalTime departureTime;
    private int track;
    private String line;
    private String trainNumber;
    private String destination;
    private LocalTime delay;
    private String platform;

    public TrainDeparture(LocalTime departureTime, int track, String line, String trainNumber, String destination, LocalTime delay) {
        this.departureTime = departureTime;
        this.track = track;
        this.line = line;
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.delay = delay;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }

    public String getDestination() {
        return destination;
    }

    public void setDelay(LocalTime delay) {
        this.delay = delay;
    }

    public LocalTime getDelay() {
        return delay;
    }

    public void addDelay(LocalTime delay) {
        if (delay == null || delay.equals(LocalTime.of(0, 0))) {
            throw new IllegalArgumentException("Ugyldig forsinkelse: " + delay);
        }
        this.delay = delay;
        departureTime = departureTime.plusHours(delay.getHour()).plusMinutes(delay.getMinute());
    }



    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public static void addTrainDeparture(List<TrainDeparture> departures, TrainDeparture newDeparture) {
        if (containsTrainWithNumber(departures, newDeparture.getTrainNumber())) {
            throw new IllegalArgumentException("Train with the same train number already exists in the list.");
        }
        departures.add(newDeparture);
    }

    private static boolean containsTrainWithNumber(List<TrainDeparture> departures, String trainNumber) {
        return departures.stream().anyMatch(departure -> departure.getTrainNumber().equals(trainNumber));
    }

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