package edu.ntnu.stud;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TrainDispatchApp {
    public static void main(String[] args) {
        List<TrainDeparture> departures = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        TimeUpdate timeUpdater = new TimeUpdate(LocalTime.now());

        while (true) {
            System.out.println("Train Departure Menu:");
            System.out.println("1. Add Train Departure");
            System.out.println("2. Assign Platform to Departure");
            System.out.println("3. Display All Departures");
            System.out.println("4. Add Delay to Departure");
            System.out.println("5. Update current time");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addTrainDeparture(scanner, departures);
                    break;

                case 2:
                    assignPlatform(scanner, departures);
                    break;

                case 3:
                    displayAllDepartures(departures);
                    break;

                case 4:
                    addDelayToDeparture(scanner, departures);
                    break;

                case 5:
                    updateCurrentTime(scanner, timeUpdater);
                    break;

                case 6:
                    scanner.close();
                    System.exit(0);



                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addTrainDeparture(Scanner scanner, List<TrainDeparture> departures) {
        System.out.print("Enter departure time (HH:mm): ");
        String timeInput = scanner.next();
        LocalTime departureTime = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("HH:mm"));

        System.out.print("Enter track: ");
        int track = scanner.nextInt();

        System.out.print("Enter line: ");
        String line = scanner.next();

        System.out.print("Enter train number: ");
        String trainNumber = scanner.next();

        System.out.print("Enter destination: ");
        String destination = scanner.next();

        System.out.print("Enter delay (HH:mm) or press Enter for no delay: ");
        String delayInput = scanner.next();
        LocalTime delay = delayInput.isEmpty() ? null : LocalTime.parse(delayInput, DateTimeFormatter.ofPattern("HH:mm"));

        TrainDeparture newDeparture = new TrainDeparture(departureTime, track, line, trainNumber, destination, delay);
        TrainDeparture.addTrainDeparture(departures, newDeparture);
        System.out.println("Train departure added successfully.");
    }

    private static void assignPlatform(Scanner scanner, List<TrainDeparture> departures) {
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
    }

    private static void displayAllDepartures(List<TrainDeparture> departures) {
        if (departures.isEmpty()) {
            System.out.println("No departures to display.");
        } else {
            Collections.sort(departures, new TrainDepartureComparator());
            for (TrainDeparture departure : departures) {
                System.out.println(departure.toString());
            }
        }
    }

    private static void addDelayToDeparture(Scanner scanner, List<TrainDeparture> departures) {
        System.out.print("Enter train number to add delay: ");
        String trainNumberToAddDelay = scanner.next();

        System.out.print("Enter delay (HH:mm) or press Enter for no delay: ");
        String delayInput = scanner.next();
        LocalTime delayToAdd = delayInput.isEmpty() ? null : LocalTime.parse(delayInput, DateTimeFormatter.ofPattern("HH:mm"));

        try {
            TrainDelayManager.addDelay(departures, trainNumberToAddDelay, delayToAdd);
            System.out.println("Delay added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void updateCurrentTime(Scanner scanner, TimeUpdate timeUpdater) {
        timeUpdater.updateCurrentTime(scanner);
    }

}
