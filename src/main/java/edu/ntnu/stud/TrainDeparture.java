package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
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

    public void addDelay(LocalTime delay) {
        if (delay == null || delay.equals(LocalTime.of(0, 0))) {
            throw new IllegalArgumentException("Ugyldig forsinkelse: " + delay);
        }
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
            throw new IllegalArgumentException("Tog med samme tognummer finnes allerede i listen.");
        }
        departures.add(newDeparture);
    }

    private static boolean containsTrainWithNumber(List<TrainDeparture> departures, String trainNumber) {
        return departures.stream().anyMatch(departure -> departure.getTrainNumber().equals(trainNumber));
    }

    @Override
    public String toString() {
        return "Departure Time: " + departureTime +
                ", Track: " + (track == -1 ? "Not assigned" : track) +
                ", Line: " + line +
                ", Train Number: " + trainNumber +
                ", Destination: " + destination +
                ", Delay: " + (delay != null ? delay : "No delay") +
                ", Platform: " + (platform != null ? platform : "Not assigned");
    }

}
