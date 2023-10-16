package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  // TODO: Fill in the main method and any other methods you need.
    public static void main(String[] args) {
        List<TrainDeparture> departures = new ArrayList<>();

        // Create train departures
        TrainDeparture departure1 = new TrainDeparture(LocalTime.of(10, 0), "Line A", "123", "Station X", null);
        TrainDeparture departure2 = new TrainDeparture(LocalTime.of(9, 30), "Line B", "456", "Station Y", LocalTime.of(0, 15));

        // Add them to the list
        departures.add(departure1);
        departures.add(departure2);

        // You can add more departures as needed

        //Validate data
        departure1.validateData();

        //Add a delay to a departure
        departure1.addDelay(LocalTime.of(0,10));

        // Remove the Delay from a departure
        departure1.removeDelay();

        //Search for departures by train number or destination
        List<TrainDeparture> searchResults = TrainDeparture.searchByTrainNumber(departures, "123");

        //Display and sort Trains
    }

}

