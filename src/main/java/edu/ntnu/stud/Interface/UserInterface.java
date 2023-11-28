package edu.ntnu.stud.Interface;
import edu.ntnu.stud.Clock.TimeUpdate;
import edu.ntnu.stud.Departures.TrainDeparture;
import edu.ntnu.stud.Departures.TrainDepartureRegister;
import edu.ntnu.stud.PlatformAllocator.TrainPlatformAllocator;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final TrainDepartureRegister register;
    private final TimeUpdate timeUpdater;

    public UserInterface(TrainDepartureRegister register, TimeUpdate timeUpdater) {
        this.scanner = new Scanner(System.in);
        this.register = register;
        this.timeUpdater = timeUpdater;
    }

    public void showMenu() {
        while (true) {
            LocalTime currentTime = LocalTime.now();
            System.out.println("Current Time: " + currentTime.format(DateTimeFormatter.ofPattern("HH:mm")));

            System.out.println("Train Departure Menu:");
            System.out.println("1. Add Train Departure");
            System.out.println("2. Assign Platform to Departure");
            System.out.println("3. Display All Departures");
            System.out.println("4. Add Delay to Departure");
            System.out.println("5. Update current time");
            System.out.println("6. Find train by train number");
            System.out.println("7. Find train by destination");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addTrainDeparture(scanner, register);
                    break;
                case 2:
                    assignPlatform(scanner, register);
                    break;

                case 3:
                    displayAllDepartures(register);
                    break;

                case 4:
                    addDelayToDeparture(scanner, register);
                    break;

                case 5:
                    updateCurrentTime(scanner, timeUpdater);
                    break;

                case 6:
                    findTrainByTrainNumber(scanner, register);
                    break;

                case 7:
                    findDeparturesByDestination(scanner, register);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void findDeparturesByDestination(Scanner scanner, TrainDepartureRegister register) {
        System.out.print("Enter destination to find departures: ");
        String destinationToFind = scanner.next();

        List<TrainDeparture> departuresByDestination = register.findDeparturesByDestination(destinationToFind);

        if (!departuresByDestination.isEmpty()) {
            System.out.println("Train departures to " + destinationToFind + ":");
            for (TrainDeparture departure : departuresByDestination) {
                System.out.println(departure.toString());
            }
        } else {
            System.out.println("No departures to " + destinationToFind + " found.");
        }
    }


    private static void addTrainDeparture(Scanner scanner, TrainDepartureRegister register) {
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
        scanner.nextLine(); // Leser og ignorerer resten av den nåværende linjen (inkludert linjeskifttegnet)
        String delayInput = scanner.nextLine(); //Leser neste linje, som vl være enten en tom streng (hvis brukeren trykker Enter) eller en ny verdi
        LocalTime delay = delayInput.isEmpty() ? null : LocalTime.parse(delayInput, DateTimeFormatter.ofPattern("HH:mm"));


        TrainDeparture newDeparture = new TrainDeparture(departureTime, track, line, trainNumber, destination, delay);
        register.addTrainDeparture(newDeparture);
        System.out.println("Train departure added successfully.");
    }


    private static void assignPlatform(Scanner scanner, TrainDepartureRegister register) {
        System.out.print("Enter train number to assign a platform: ");
        String trainNumberToAssignPlatform = scanner.next();

        System.out.print("Enter platform: ");
        String platform = scanner.next();

        try {
            TrainPlatformAllocator.assignPlatform(register.getDepartures(), trainNumberToAssignPlatform, platform);
            System.out.println("Platform assigned successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void displayAllDepartures(TrainDepartureRegister register) {
        List<TrainDeparture> departures = register.getSortedDepartures();

        if (departures.isEmpty()) {
            System.out.println("No departures to display.");
        } else {
            for (TrainDeparture departure : departures) {
                System.out.println(departure.toString());
            }
        }
    }


    private static void addDelayToDeparture(Scanner scanner, TrainDepartureRegister register) {
        System.out.print("Enter train number to add delay: ");
        String trainNumberToAddDelay = scanner.next();

        System.out.print("Enter delay (HH:mm) or press Enter for no delay: ");
        String delayInput = scanner.next();
        LocalTime delayToAdd = delayInput.isEmpty() ? null : LocalTime.parse(delayInput, DateTimeFormatter.ofPattern("HH:mm"));

        TrainDeparture departure = register.findTrainByTrainNumber(trainNumberToAddDelay);

        if (departure != null) {
            if (delayToAdd != null) {
                departure.setDelay(delayToAdd);
                System.out.println("Delay added successfully.");

                // Add delay to departure time
                departure.addDelay(delayToAdd);
            } else {
                System.out.println("No delay added.");
            }
        } else {
            System.out.println("Train with the specified number not found.");
        }
    }

    private static void updateCurrentTime(Scanner scanner, TimeUpdate timeUpdater) {
        timeUpdater.updateCurrentTime(scanner);
    }

    private static void findTrainByTrainNumber(Scanner scanner, TrainDepartureRegister register) {
        System.out.print("Enter train number to find: ");
        String trainNumberToFind = scanner.next();

        TrainDeparture foundDeparture = register.findTrainByTrainNumber(trainNumberToFind);

        if (foundDeparture != null) {
            System.out.println("Train found: " + foundDeparture.toString());
        } else {
            System.out.println("Train with the specified number not found.");
        }
    }



}


