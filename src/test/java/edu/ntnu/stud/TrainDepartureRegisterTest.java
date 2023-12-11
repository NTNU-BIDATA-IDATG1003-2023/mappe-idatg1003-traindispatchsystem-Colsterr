package edu.ntnu.stud;

import edu.ntnu.stud.TrainHandling.TrainDeparture;
import edu.ntnu.stud.TrainHandling.TrainDepartureRegister;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalTime;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Test class for TrainDepartureRegister.
 * Tests the functionalities of adding, finding, and sorting train departures.
 */
public class TrainDepartureRegisterTest {

    private TrainDepartureRegister register;

    /**
     * Sets up a new TrainDepartureRegister instance before each test.
     */
    @Before
    public void setUp() {
        register = new TrainDepartureRegister();
    }

    /**
     * Tests adding a new TrainDeparture and finding it by train number.
     */
    @Test
    public void testAddAndFindTrainDeparture() {
        LocalTime departureTime = LocalTime.of(10, 30);
        TrainDeparture departure = new TrainDeparture(departureTime, 1, "L1", "100", "Oslo", null);
        register.addTrainDeparture(departure);

        assertEquals(departure, register.findTrainByTrainNumber("100"));
        assertNull(register.findTrainByTrainNumber("101")); // Verifying non-existent train number
    }

    /**
     * Tests finding train departures by their destination.
     */
    @Test
    public void testFindDeparturesByDestination() {
        register.addTrainDeparture(new TrainDeparture(LocalTime.of(10, 30), 1, "L1", "100", "Oslo", null));
        register.addTrainDeparture(new TrainDeparture(LocalTime.of(11, 00), 2, "L2", "101", "Bergen", null));
        register.addTrainDeparture(new TrainDeparture(LocalTime.of(11, 30), 3, "L3", "102", "Oslo", null));

        List<TrainDeparture> departuresToOslo = register.findDeparturesByDestination("Oslo");
        assertEquals(2, departuresToOslo.size()); // Expecting two departures to Oslo
    }

    /**
     * Tests getting a list of train departures sorted by departure time.
     */
    @Test
    public void testGetSortedDepartures() {
        register.addTrainDeparture(new TrainDeparture(LocalTime.of(11, 00), 2, "L2", "101", "Bergen", null));
        register.addTrainDeparture(new TrainDeparture(LocalTime.of(10, 30), 1, "L1", "100", "Oslo", null));

        List<TrainDeparture> sortedDepartures = register.getSortedDepartures();
        assertTrue(sortedDepartures.get(0).getDepartureTime().isBefore(sortedDepartures.get(1).getDepartureTime()));
    }
}
