package edu.ntnu.stud.Main;

import edu.ntnu.stud.Interface.UserInterface;
import edu.ntnu.stud.Clock.TimeUpdate;
import edu.ntnu.stud.Departures.TrainDepartureRegister;

import java.time.LocalTime;

public class TrainDispatchApp {
    public static void main(String[] args) {
        TrainDepartureRegister register = new TrainDepartureRegister();
        TimeUpdate timeUpdater = new TimeUpdate(LocalTime.now());

        UserInterface ui = new UserInterface(register, timeUpdater);
        ui.showMenu();
    }
}
