package edu.ntnu.stud;

import edu.ntnu.stud.TrainHandling.TrainDeparture;
import edu.ntnu.stud.TrainHandling.TrainDepartureComparator;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalTime;
import static org.junit.Assert.assertTrue;

/**
 * JUnit test class for TrainDepartureComparator.
 * This class tests the comparison logic of the TrainDepartureComparator.
 */
public class TrainDepartureComparatorTest {
    private TrainDepartureComparator comparator;

    /**
     * Sets up the testing environment.
     * This method is called before each test method and initializes the comparator.
     */
    @Before
    public void setUp() {
        comparator = new TrainDepartureComparator();
    }

    /**
     * Tests that the comparator correctly identifies an earlier departure time.
     * It verifies that when departure1's time is earlier than departure2's,
     * the comparator returns a negative value.
     */
    @Test
    public void testCompareEarlier() {
        TrainDeparture departure1 = new TrainDeparture(LocalTime.of(8, 30), 1, "L1", "001", "Destination1", null);
        TrainDeparture departure2 = new TrainDeparture(LocalTime.of(9, 30), 2, "L2", "002", "Destination2", null);

        assertTrue("departure1 should be considered earlier than departure2", comparator.compare(departure1, departure2) < 0);
    }


    /**
     * Tests that the comparator correctly identifies a later departure time.
     * It verifies that when departure1's time is later than departure2's,
     * the comparator returns a positive value.
     */
    @Test
    public void testCompareLater() {
        TrainDeparture departure1 = new TrainDeparture(LocalTime.of(10, 30), 1, "L1", "001", "Destination1", null);
        TrainDeparture departure2 = new TrainDeparture(LocalTime.of(9, 30), 2, "L2", "002", "Destination2", null);

        assertTrue("departure1 should be considered later than departure2", comparator.compare(departure1, departure2) > 0);
    }

    /**
     * Tests that the comparator correctly handles equal departure times.
     * It verifies that when departure1's time is equal to departure2's,
     * the comparator returns zero.
     */
    @Test
    public void testCompareEqual() {
        TrainDeparture departure1 = new TrainDeparture(LocalTime.of(11, 30), 1, "L1", "001", "Destination1", null);
        TrainDeparture departure2 = new TrainDeparture(LocalTime.of(11, 30), 2, "L2", "002", "Destination2", null);

        assertTrue("departure1 and departure2 should be considered equal in terms of departure time", comparator.compare(departure1, departure2) == 0);
    }
}
