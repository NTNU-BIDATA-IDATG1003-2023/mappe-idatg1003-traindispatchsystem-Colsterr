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

    public void validateData() {
        if (departureTime == null || trainNumber == null || destination == null) {
            throw new IllegalArgumentException("Avgangstid, tognummer og destinasjon må fylles ut.");
        }
        if (delay != null && delay.isBefore(LocalTime.MIDNIGHT)) {
            throw new IllegalArgumentException("Ugyldig forsinkelse: " + delay);
        }
    }


    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }

    public void addDelay(LocalTime delay) {
        if (delay == null || delay.equals(LocalTime.of(0, 0))) {
            throw new IllegalArgumentException("Ugyldig forsinkelse: " + delay);
        }
        departureTime = departureTime.plusHours(delay.getHour()).plusMinutes(delay.getMinute());
    }

    public void removeDelay() {
        delay = LocalTime.of(0, 0);
    }

    public static List<TrainDeparture> searchByTrainNumber(List<TrainDeparture> departures, String trainNumber) {
        List<TrainDeparture> result = new ArrayList<>();
        for (TrainDeparture departure : departures) {
            if (departure.getTrainNumber().equals(trainNumber)) {
                result.add(departure);
            }
        }
        return result;
    }

    public static List<TrainDeparture> searchByDestination(List<TrainDeparture> departures, String destination) {
        List<TrainDeparture> result = new ArrayList<>();
        for (TrainDeparture departure : departures) {
            if (departure.getDestination().equalsIgnoreCase(destination)) {
                result.add(departure);
            }
        }
        return result;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalTime getDelay() {
        return delay;
    }

    public void setDelay(LocalTime delay) {
        if (delay != null && delay.equals(LocalTime.of(0, 0))) {
            throw new IllegalArgumentException("Ugyldig forsinkelse: " + delay);
        }
        this.delay = delay;
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

    public void printDepartureInfo() {
        System.out.println("Departure Information:");
        System.out.println("Departure Time: " + departureTime);
        System.out.println("Track: " + (track == -1 ? "Not assigned" : track));
        System.out.println("Line: " + line);
        System.out.println("Train Number: " + trainNumber);
        System.out.println("Destination: " + destination);
        System.out.println("Delay: " + (delay != null ? delay : "No delay"));
        System.out.println("Platform: " + (platform != null ? platform : "Not assigned"));
    }
}
