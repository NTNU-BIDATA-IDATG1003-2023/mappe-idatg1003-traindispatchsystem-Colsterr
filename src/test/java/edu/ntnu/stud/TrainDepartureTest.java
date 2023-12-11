package edu.ntnu.stud;

import edu.ntnu.stud.TrainHandling.TrainDeparture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TrainDepartureTest {
    private List<TrainDeparture> departures;

    @BeforeEach
    void setUp() {
        departures = new ArrayList<>();
    }

    // Testing AddTrainDeparture method
    @Test
    void testAddTrainDeparture() {
        LocalTime departureTime = LocalTime.of(10, 0);
        String line = "F1";
        String trainNumber = "123";
        String destination = "Gjøvik";

        TrainDeparture newDeparture = new TrainDeparture(departureTime, -1, line, trainNumber, destination, null);
        TrainDeparture.addTrainDeparture(departures, newDeparture);

        assertEquals(1, departures.size());
        assertEquals(newDeparture, departures.get(0));
    }

    //Test to see if they are duplicate train numbers
    @Test
    void testAddTrainDepartureWithDuplicateTrainNumber() {
        LocalTime departureTime1 = LocalTime.of(10, 0);
        String line1 = "F1";
        String trainNumber = "123";
        String destination1 = "Gjøvik";

        LocalTime departureTime2 = LocalTime.of(11, 0);
        String line2 = "F2";
        String destination2 = "Oslo";

        TrainDeparture departure1 = new TrainDeparture(departureTime1, -1, line1, trainNumber, destination1, null);
        TrainDeparture departure2 = new TrainDeparture(departureTime2, -1, line2, trainNumber, destination2, null);

        TrainDeparture.addTrainDeparture(departures, departure1);

        assertThrows(IllegalArgumentException.class, () -> TrainDeparture.addTrainDeparture(departures, departure2));
    }

    // Test to see if platform is set correctly
    @Test
    void testSetPlatform() {
        LocalTime departureTime = LocalTime.of(10, 0);
        String line = "F1";
        String trainNumber = "123";
        String destination = "Gjøvik";

        TrainDeparture newDeparture = new TrainDeparture(departureTime, -1, line, trainNumber, destination, null);
        newDeparture.setPlatform("Platform A");

        assertEquals("Platform A", newDeparture.getPlatform());
    }

    // Test to see if delay is added correctly
    @Test
    void testAddDelay() {
        LocalTime departureTime = LocalTime.of(10, 0);
        String line = "F1";
        String trainNumber = "123";
        String destination = "Gjøvik";
        LocalTime delay = LocalTime.of(0, 30);

        TrainDeparture newDeparture = new TrainDeparture(departureTime, -1, line, trainNumber, destination, delay);
        newDeparture.addDelay(LocalTime.of(0, 10));

        assertEquals(LocalTime.of(10, 10), newDeparture.getDepartureTime());
    }

    @Test
    void testAddInvalidDelay() {
        LocalTime departureTime = LocalTime.of(10, 0);
        String line = "F1";
        String trainNumber = "123";
        String destination = "Gjøvik";
        LocalTime delay = LocalTime.of(0, 0);

        TrainDeparture newDeparture = new TrainDeparture(departureTime, -1, line, trainNumber, destination, delay);

        assertThrows(IllegalArgumentException.class, () -> newDeparture.addDelay(LocalTime.of(0, 0)));
    }
}
