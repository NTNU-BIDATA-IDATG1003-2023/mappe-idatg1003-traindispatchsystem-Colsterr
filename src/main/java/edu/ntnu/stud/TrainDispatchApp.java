package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
    // TODO: Fill in the main method and any other methods you need.
    public static void main(String[] args) {
        List<TrainDeparture> departures = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Train Departure Menu:");
            System.out.println("1. Add Train Departure");
            System.out.println("2. Assign Platform to Departure");
            System.out.println("3. Display All Departures");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add Train Departure
                    System.out.print("Enter departure time (HH:mm): ");
                    String timeInput = scanner.next();
                    LocalTime departureTime = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("HH:mm"));

                    System.out.print("Enter line: ");
                    String line = scanner.next();

                    System.out.print("Enter train number: ");
                    String trainNumber = scanner.next();

                    System.out.print("Enter destination: ");
                    String destination = scanner.next();

                    System.out.print("Enter delay (HH:mm) or press Enter for no delay: ");
                    String delayInput = scanner.next();
                    LocalTime delay = delayInput.isEmpty() ? null : LocalTime.parse(delayInput, DateTimeFormatter.ofPattern("HH:mm"));

                    TrainDeparture newDeparture = new TrainDeparture(departureTime, line, trainNumber, destination, delay);
                    TrainDeparture.addTrainDeparture(departures, newDeparture);
                    break;

                case 2:
                    // Assign Platform to Departure
                    System.out.print("Enter train number to assign a platform: ");
                    String trainNumberToAssignPlatform = scanner.next();

                    System.out.print("Enter platform: ");
                    String platform = scanner.next();

                    try {
                        TrainPlatformAllocator.assignPlatform(departures, trainNumberToAssignPlatform, platform);
                        System.out.println("Platform assigned successfully.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    // Display All Departures
                    for (TrainDeparture departure : departures) {
                        System.out.println(departure.toString());
                    }
                    break;

                case 4:
                    // Exit
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

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
            departure1.addDelay(LocalTime.of(0, 10));

            // Remove the Delay from a departure
            departure1.removeDelay();

            //Search for departures by train number or destination
            List<TrainDeparture> searchResults = TrainDeparture.searchByTrainNumber(departures, "123");

            //Display and sort Trains
            for (TrainDeparture departure : departures) {
                System.out.println(departure.toString());
            }

            //Creating a TimeUpdate instance and update the time
            TimeUpdate timeUpdater = new TimeUpdate(LocalTime.now());
            timeUpdater.updateCurrentTime();
        }

    }
}

