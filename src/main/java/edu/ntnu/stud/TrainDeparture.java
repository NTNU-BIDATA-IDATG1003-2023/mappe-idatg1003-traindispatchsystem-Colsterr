package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TrainDeparture {
    private LocalTime departureTime; //Avgangstidspunkt for toget
    private int track; // track som toget tilhører, -1 hvis ikke tilordnet
    private String line; // Linjen gtoget tilhører
    private String trainNumber; // Tognummeret
    private String destination; //desinasjon for toget
    private LocalTime delay; // Eventuell forsinkelse
    private String platform; // Eventuell forsinkelse

    // Konstruktør og andre metoder for å opprette og manipulere TrainDeparture-objekter
    public TrainDeparture(LocalTime departureTime,int track, String line, String trainNumber, String destination, LocalTime delay) {
        this.departureTime = departureTime;
        this.track = track;
        this.line = line;
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.delay = delay;
    }

    //Metode for å validere data for togavgangen
    public void validateData() {
        // Sjekk om avgangstid, tognummer og destinasjon er satt
        if (departureTime == null || trainNumber == null || destination == null) {
            throw new IllegalArgumentException("Avgangstid, tognummer og destinasjon må fylles ut.");
        }

        // Sjekk om forsinkelse er negativ
        if (delay != null && delay.isBefore(LocalTime.MIDNIGHT)) {
            throw new IllegalArgumentException("Ugyldig forsinkelse: " + delay);
        }
    }

    // Metode for å sette plattform for avgangen
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    // Metode for å hente plattformen for avgangen
    public String getPlatform() {
        return platform;
    }


    //Metode for å legge til forsinkelse til avgangen
    public void addDelay(LocalTime delay) {
        // Sjekk om forsinkelsen er null eller negativ
        if (delay == null || delay.isBefore(LocalTime.MIDNIGHT)) {
            throw new IllegalArgumentException("Ugyldig forsinkelse: " + delay);
        }

        //Legg til forsinkelsen til avgangstiden
        departureTime = departureTime.plusHours(delay.getHour()).plusMinutes(delay.getMinute());
    }

    // Metode for å fjerne forsinkelse fra avgangen
    public void removeDelay() {
        //Sett forsinkelsen til null
        delay = LocalTime.of(0,0);
    }

    // Metode for å søke etter togavganger basert på tognummer
    public static List<TrainDeparture> searchByTrainNumber(List<TrainDeparture> departures, String trainNumber) {
        List<TrainDeparture> result = new ArrayList<>();
        for (TrainDeparture departure : departures) {
            if (departure.getTrainNumber().equals(trainNumber)) {
                result.add(departure);
            }
        }
        return result;
    }

    // Metode for å søke etter togavganger basert på destinasjon
    public static List<TrainDeparture> searchByDestination(List<TrainDeparture> departures, String destination) {
        List<TrainDeparture> result = new ArrayList<>();
        for (TrainDeparture departure : departures) {
            if (departure.getDestination().equalsIgnoreCase(destination)) {
                result.add(departure);
            }
        }
        return result;
    }



    // Getters og setters for å hente og endre informasjon om tog avgangen

    //Getter for avgangstid
    public LocalTime getDepartureTime() {
        return departureTime;
    }

    //Setter for avgangstid
    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    // Getter for Track
    public int getTrack() {
        return track;
    }

    // Setter for track
    public void setTrack(int track) {
        this.track = track;
    }

    // Setter for linje
    public void setLine(String line) {
        this.line = line;
    }

    // Getter for tognummer
    public String getTrainNumber() {
        return trainNumber;
    }

    // Setter for tognummer
    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    // Getter for destinasjon
    public String getDestination() {
        return destination;
    }

    // Setter for destinasjon
    public void setDestination(String destination) {
        this.destination = destination;
    }

    // Getter for forsinkelse
    public LocalTime getDelay() {
        return delay;
    }

    // Setter for forsinkelse
    public void setDelay(LocalTime delay) {
        this.delay = delay;
    }


    //Legg til andre metoder som du trenger i klassen

    // Metode for å legge til en ny togavgang i listen med kontroll for duplikat tognummer
    public static void addTrainDeparture(List<TrainDeparture> departures, TrainDeparture newDeparture) {
        // Sjekk om toget allerede finnes i listen basert på tognummer
        for (TrainDeparture departure : departures) {
            if (departure.getTrainNumber().equals(newDeparture.getTrainNumber())) {
                throw new IllegalArgumentException("Tog med samme tognummer finnes allerede i listen.");
            }
        }

        // Hvis ingen duplikat ble funnet, legg til den nye avgangen i listen
        departures.add(newDeparture);
    }

    // Metode for å sjekke om et tog med samme tognummer allerede eksisterer i listen
    private static boolean containsTrainWithNumber(List<TrainDeparture> departures, String trainNumber) {
        for (TrainDeparture departure : departures) {
            if (departure.getTrainNumber().equals(trainNumber)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Departure Time: " + departureTime +
                ", Track: " + (track == -1 ? "Not assigned" : track) +
                ", Train Number: " + trainNumber +
                ", Destination: " + destination +
                ", Delay: " + delay;
    }

    //Metode forå skrive ut informasjon om togavgangen til konsollen
    public void printDepartureInfo() {
            System.out.println("Departure Information:");
            System.out.println("Departure Time: " + departureTime);
            System.out.println("Track: " + (track == -1 ? "Not assigned" : track));
            System.out.println("Train Number: " + trainNumber);
            System.out.println("Destination: " + destination);
            System.out.println("Delay: " + delay);
            System.out.println("Platform: " + platform);
    }
}


