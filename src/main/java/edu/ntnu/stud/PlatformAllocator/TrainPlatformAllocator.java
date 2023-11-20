package edu.ntnu.stud.PlatformAllocator;

import edu.ntnu.stud.Departures.TrainDeparture;

import java.util.List;

public class TrainPlatformAllocator {
    public static void assignPlatform(List<TrainDeparture> departures, String trainNumber, String platform) {
        // Søk etter avgangen basert på tognummer
        TrainDeparture departureToAssignPlatform = null;
        for (TrainDeparture departure : departures) {
            if (departure.getTrainNumber().equals(trainNumber)) {
                departureToAssignPlatform = departure;
                break; // Avslutt søket når vi har funnet den første avgangen med matchende tognummer
            }
        }

        if (departureToAssignPlatform == null) {
            throw new IllegalArgumentException("No departure found with the specified train number.");
        }

        // Sett plattformen for avgangen
        departureToAssignPlatform.setPlatform(platform);
    }
}




