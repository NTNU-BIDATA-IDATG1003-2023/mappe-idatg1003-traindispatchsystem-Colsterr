package edu.ntnu.stud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import edu.ntnu.stud.Clock.TimeUpdate;
import org.junit.Before;
import org.junit.Test;

/**
 * The TimeUpdateTest class contains unit tests for the TimeUpdate class.
 * It tests the functionality of updating the current time based on user input.
 */
public class TimeUpdateTest {

    private TimeUpdate timeUpdate;
    private LocalTime initialTime;

    /**
     * Sets up the environment for each test.
     * Initializes a TimeUpdate object with a predefined initial time.
     */
    @Before
    public void setUp() {

        initialTime = LocalTime.of(12, 0);
        timeUpdate = new TimeUpdate(initialTime);
    }

    /**
     * Tests the updateCurrentTime method of TimeUpdate with valid input.
     * Verifies that the current time is correctly updated.
     */
    @Test
    public void testUpdateCurrentTime() {
        String input = "13:30\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);


        Scanner scanner = new Scanner(System.in);


        timeUpdate.updateCurrentTime(scanner);


        assertEquals("13:30:00", timeUpdate.currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    /**
     * Tests the updateCurrentTime method of TimeUpdate with invalid input.
     * Verifies that an exception is thrown in case of invalid time format.
     */
    @Test
    public void testUpdateCurrentTimeWithInvalidInput() {
        String input = "invalid-time\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);


        try {
            timeUpdate.updateCurrentTime(scanner);
            fail("Expected DateTimeParseException, but no exception was thrown.");
        } catch (Exception e) {

            assertNotNull(e);
        }
    }
}
