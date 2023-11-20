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

public class TimeUpdateTest {

    private TimeUpdate timeUpdate;
    private LocalTime initialTime;

    @Before
    public void setUp() {
        //Set up initial time and TimeUpdate instance for testing
        initialTime = LocalTime.of(12, 0);
        timeUpdate = new TimeUpdate(initialTime);
    }

    @Test
    public void testUpdateCurrentTime() {
        // Prepare test inpt and set it as the System.in
        String input = "13:30\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Create a new scanner to read the test input
        Scanner scanner = new Scanner(System.in);

        // Test the updateCurrentTime method
        timeUpdate.updateCurrentTime(scanner);

        // Verify if the currentTime is updated as expected
        assertEquals("13:30:00", timeUpdate.currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    @Test
    public void testUpdateCurrentTimeWithInvalidInput() {
        // Prepare test input with an invalid time format
        String input = "invalid-time\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Create a new scanner to read the test input
        Scanner scanner = new Scanner(System.in);

        // Test the updateCurrentTime method with invalid input
        try {
            timeUpdate.updateCurrentTime(scanner);
            fail("Expected DateTimeParseException, but no exception was thrown.");
        } catch (Exception e) {
            // Verify that the exception is thrown
            assertNotNull(e);
        }
    }
}
