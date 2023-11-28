package edu.ntnu.stud.Clock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The TimeUpdate class is used for managing the current time within the system.
 * It allows updating the system's time based on user input.
 */
public class TimeUpdate {
    public LocalTime currentTime;

    /**
     * Constructs a TimeUpdate object with an initial time.
     *
     * @param initialTime The initial time to set for the system.
     */

    public TimeUpdate(LocalTime initialTime) {
        this.currentTime = initialTime;
    }

    /**
     * Updates the current time of the system based on user input.
     * The method prompts the user to enter a new time, which is then parsed and
     * used to update the currentTime field.
     *
     * @param scanner The Scanner object used for reading user input.
     */

    public void updateCurrentTime(Scanner scanner) {
        System.out.println("Nåværende tidspunkt: " + currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.print("Skriv inn det nye tidspunktet (format: HH:mm): ");

        String inputTime = scanner.next();
        LocalTime newTime = LocalTime.parse(inputTime, DateTimeFormatter.ofPattern("HH:mm"));

        currentTime = newTime;

        System.out.println("Tidspunktet er oppdatert til " + currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

}
