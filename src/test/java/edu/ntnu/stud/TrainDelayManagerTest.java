package edu.ntnu.stud;
import edu.ntnu.stud.TrainHandling.TrainDelayManager;
import edu.ntnu.stud.TrainHandling.TrainDeparture;
import org.junit.Test;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TrainDelayManagerTest {
    @Test
    public void testAddDelayToTrainDeparture() {
        // Create a list of train departures
        List<TrainDeparture> departures = new ArrayList<>();
        LocalTime initialTime = LocalTime.of(10, 0);
        TrainDeparture departure1 = new TrainDeparture(initialTime, 1, "F1", "111", "Gjøvik", null);
        departures.add(departure1);

        //Test adding a delay
        LocalTime delay = LocalTime.of(0, 15);
        TrainDelayManager.addDelay(departures, "111", delay);

        // Check if the delay is added correctly
        LocalTime expectedTime = LocalTime.of(10, 15);
        assertEquals(expectedTime, departure1.getDepartureTime());
    }

    @Test
    public void testAddDelayToNonExistentTrainDeparture() {
        // Create an empty list of train departures
        List<TrainDeparture> departures = new ArrayList<>();

        // Test adding a delay to a non-existent train departure
        try {
            LocalTime delay = LocalTime.of(0, 15);
            TrainDelayManager.addDelay(departures, "222", delay);
            fail("Expected IllegalArgumentException, but no exception was thrown.");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("No departure found with the specified train number."));
        }
    }

    @Test
    public void testAddNullDelayToTrainDeparture() {
        // Create a list of train departures
        List<TrainDeparture> departures = new ArrayList<>();
        LocalTime initialTime = LocalTime.of(10, 0);
        TrainDeparture departure1 = new TrainDeparture(initialTime, 1, "F1", "111", "Gjøvik", null);
        departures.add(departure1);

        // Test adding a null delay
        TrainDelayManager.addDelay(departures, "111", null);

        // Checkingg if the departure time remains the same
        assertEquals(initialTime, departure1.getDepartureTime());
    }
}
