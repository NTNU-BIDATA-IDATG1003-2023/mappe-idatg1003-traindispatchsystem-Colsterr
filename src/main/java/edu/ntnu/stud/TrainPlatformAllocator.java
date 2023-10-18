package edu.ntnu.stud;

import java.util.List;


public class TrainPlatformAllocator {
    public static void assignPlatform(List<TrainDeparture> departures, String trainNumber, String platform) {
        // Søk etter avgangen basert på tognummer
        TrainDeparture departureToAssignPlatform = null;
        for (TrainDeparture departure : departures) {
            if (departure.getTrainNumber().equals(trainNumber)) {
                if (departureToAssignPlatform == null) {
                    departureToAssignPlatform = departure;
                } else {
                    // Håndter situasjonen der det er flere avganger med samme tognummer
                    throw new IllegalArgumentException("Multiple departures found with the same train number. Please specify which one to assign a platform to.");
                }
            }
        }

        if (departureToAssignPlatform == null) {
            throw new IllegalArgumentException("No departure found with the specified train number.");
        }

        // Sett plattformen for avgangen
        departureToAssignPlatform.setPlatform(platform);
    }
}



