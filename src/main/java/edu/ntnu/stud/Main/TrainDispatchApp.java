package edu.ntnu.stud.Main;

import edu.ntnu.stud.Interface.UserInterface;
import edu.ntnu.stud.Clock.TimeUpdate;
import edu.ntnu.stud.Departures.TrainDepartureRegister;
import java.time.LocalTime;

/**
 * The TrainDispatchApp class serves as the entry point for the Train Dispatch System.
 * It initializes the necessary components and starts the user interface.
 */
public class TrainDispatchApp {

    /**
     * The main method that serves as the entry point of the application.
     * It creates instances of TrainDepartureRegister and TimeUpdate,
     * and initializes the UserInterface to interact with the system.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        TrainDepartureRegister register = new TrainDepartureRegister();
        TimeUpdate timeUpdater = new TimeUpdate(LocalTime.now());

        UserInterface ui = new UserInterface(register, timeUpdater);
        ui.showMenu();
    }
}
