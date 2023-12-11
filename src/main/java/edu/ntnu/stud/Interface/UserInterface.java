package edu.ntnu.stud.Interface;
import edu.ntnu.stud.Clock.TimeUpdate;
import edu.ntnu.stud.TrainHandling.TrainDeparture;
import edu.ntnu.stud.TrainHandling.TrainDepartureRegister;
import edu.ntnu.stud.TrainHandling.TrainPlatformAllocator;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * The UserInterface class provides a text-based user interface for interacting with
 * the Train Dispatch System. It allows users to add, modify, and view train departures.
 */
public class UserInterface {
    private static final String TIME_FORMAT = "HH:mm";
    private final Scanner scanner;
    private final TrainDepartureRegister register;
    private final TimeUpdate timeUpdater;

    /**
     * Constructs a new UserInterface instance with a given TrainDepartureRegister
     * and TimeUpdate objects.
     *
     * @param register    The TrainDepartureRegister used for managing train departures.
     * @param timeUpdater The TimeUpdate used for updating and managing the system time.
     */
    public UserInterface(TrainDepartureRegister register, TimeUpdate timeUpdater) {
        this.scanner = new Scanner(System.in);
        this.register = register;
        this.timeUpdater = timeUpdater;
    }

    /**
     * Displays the main menu and handles user input to perform various actions such as
     * adding departures, assigning platforms, and viewing departures.
     */
    public void showMenu() {
        while (true) {
            LocalTime currentTime = LocalTime.now();
            System.out.println("Current Time: " + currentTime.format(DateTimeFormatter.ofPattern(TIME_FORMAT)));

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


    /**
     * Handles the addition of a new train departure.
     *
     * @param scanner  Scanner to read user input.
     * @param register TrainDepartureRegister to add the new departure to.
     */
    private static void addTrainDeparture(Scanner scanner, TrainDepartureRegister register) {
        System.out.print("Enter departure time (" + TIME_FORMAT + "): ");
        String timeInput = scanner.next();
        LocalTime departureTime = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern(TIME_FORMAT));


        System.out.print("Enter track: ");
        int track = scanner.nextInt();

        System.out.print("Enter line: ");
        String line = scanner.next();

        System.out.print("Enter train number: ");
        String trainNumber = scanner.next();

        System.out.print("Enter destination: ");
        String destination = scanner.next();

        System.out.print("Enter delay (" + TIME_FORMAT + ") or press Enter for no delay: ");
        scanner.nextLine(); // Consumes the rest of the line
        String delayInput = scanner.nextLine();
        LocalTime delay = delayInput.isEmpty() ? null : LocalTime.parse(delayInput, DateTimeFormatter.ofPattern(TIME_FORMAT));



        TrainDeparture newDeparture = new TrainDeparture(departureTime, track, line, trainNumber, destination, delay);
        register.addTrainDeparture(newDeparture);
        System.out.println("Train departure added successfully.");
    }

    /**
     * Assigns a platform to a specific train departure.
     *
     * @param scanner  Scanner to read user input.
     * @param register TrainDepartureRegister to find the train departure.
     */
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

    /**
     * Displays all train departures currently registered in the system.
     *
     * @param register The TrainDepartureRegister containing the departures to be displayed.
     */

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

    /**
     * Adds a delay to a specified train departure.
     *
     * @param scanner  Scanner to read user input.
     * @param register TrainDepartureRegister to find and update the train departure.
     */

    private static void addDelayToDeparture(Scanner scanner, TrainDepartureRegister register) {
        System.out.print("Enter train number to add delay: ");
        String trainNumberToAddDelay = scanner.next();

        System.out.print("Enter delay (HH:mm) or press Enter for no delay: ");
        String delayInput = scanner.next();
        LocalTime delayToAdd = delayInput.isEmpty() ? null : LocalTime.parse(delayInput, DateTimeFormatter.ofPattern(TIME_FORMAT));

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

    /**
     * Updates the current time in the system.
     *
     * @param scanner     Scanner to read user input.
     * @param timeUpdater TimeUpdate object used to update the current time.
     */
    private static void updateCurrentTime(Scanner scanner, TimeUpdate timeUpdater) {
        timeUpdater.updateCurrentTime(scanner);
    }

    /**
     * Finds and displays a train departure based on the train number provided by the user.
     *
     * @param scanner  Scanner to read user input.
     * @param register TrainDepartureRegister to search for the train departure.
     */
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

    /**
     * Finds and displays all train departures to a specified destination.
     *
     * @param scanner  Scanner to read user input.
     * @param register TrainDepartureRegister to search for departures to the destination.
     */

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

}


