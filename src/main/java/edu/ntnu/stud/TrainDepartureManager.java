package edu.ntnu.stud;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainDepartureManager {
    public static void displaySortedDepartures(List<TrainDeparture> departures) {
        // Sorter togavgangene etter avreisetidspunkt
        Collections.sort(departures, (departure1, departure2) -> departure1.getDepartureTime().compareTo(departure2.getDepartureTime()));

        // Skriv ut oversikten
        System.out.println("Oversikt over togavganger sortert etter avreisetidspunkt:");
        for (TrainDeparture departure : departures) {
            System.out.println(departure);
        }
    }

    public static void main(String[] args) {
        // Opprett noen eksempeltogavganger
        TrainDeparture departure1 = new TrainDeparture(LocalTime.of(10, 0), "Line A", "123", "Station X", null);
        TrainDeparture departure2 = new TrainDeparture(LocalTime.of(9, 30), "Line B", "456", "Station Y", LocalTime.of(0, 15));
        TrainDeparture departure3 = new TrainDeparture(LocalTime.of(11, 15), "Line A", "789", "Station Z", LocalTime.of(0, 5));

        List<TrainDeparture> departures = new ArrayList<>();
        departures.add(departure1);
        departures.add(departure2);
        departures.add(departure3);

        // Vis oversikten over togavganger
        displaySortedDepartures(departures);
    }
}
