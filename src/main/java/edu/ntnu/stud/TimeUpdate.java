package edu.ntnu.stud;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TimeUpdate {
    private LocalTime currentTime;

    public TimeUpdate(LocalTime initialTime) {
        this.currentTime = initialTime;
    }

    public void updateCurrentTime(Scanner scanner) {
        System.out.println("Nåværende tidspunkt: " + currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.print("Skriv inn det nye tidspunktet (format: HH:mm): ");

        String inputTime = scanner.next();
        LocalTime newTime = LocalTime.parse(inputTime, DateTimeFormatter.ofPattern("HH:mm"));

        // Oppdater tidspunktet
        currentTime = newTime;

        System.out.println("Tidspunktet er oppdatert til " + currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

}
