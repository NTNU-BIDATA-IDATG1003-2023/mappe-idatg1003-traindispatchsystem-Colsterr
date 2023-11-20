package edu.ntnu.stud;


/*
public class TrainDepartureRegisterTest {
    private TrainDepartureRegister register;

    @BeforeEach
    public void setup() {
        register = new TrainDepartureRegister();
    }

    @Test
    public void testAddTrainDeparture() {
        TrainDeparture departure1 = new TrainDeparture(12, ,"F3","34", "Gjøvik", "3");
        TrainDeparture departure2 = new TrainDeparture(/ ... /);

        // Positive test: Adding a unique train departure
        register.addTrainDeparture(departure1);
        assertEquals(departure1, register.findTrainByTrainNumber(departure1.getTrainNumber()));

        // Negative test: Attempting to add a train departure with the same train number
        register.addTrainDeparture(departure2);
        assertNull(register.findTrainByTrainNumber(departure2.getTrainNumber()));
    }

    @Test
    public void testFindTrainByTrainNumber() {
        TrainDeparture departure1 = new TrainDeparture( ... );
        TrainDeparture departure2 = new TrainDeparture( ... );

        // Positive test: Finding an existing train departure
        register.addTrainDeparture(departure1);
        assertEquals(departure1, register.findTrainByTrainNumber(departure1.getTrainNumber()));

        // Negative test: Finding a non-existing train departure
        assertNull(register.findTrainByTrainNumber("NonExistentTrainNumber"));
    }

    @Test
    public void testFindDeparturesByDestination() {
        TrainDeparture departure1 = new TrainDeparture(/ ... /);
        TrainDeparture departure2 = new TrainDeparture(/ ... /);

        // Positive test: Finding departures with a specific destination
        register.addTrainDeparture(departure1);
        register.addTrainDeparture(departure2);
        assertEquals(2, register.findDeparturesByDestination(departure1.getDestination()).size());

        // Negative test: Finding departures with a non-existing destination
        assertEquals(0, register.findDeparturesByDestination("NonExistentDestination").size());
    }

    @Test
    public void testRemoveDeparturesBeforeTime() {
        TrainDeparture departure1 = new TrainDeparture();
        TrainDeparture departure2 = new TrainDeparture(/ ... /);

        // Positive test: Removing departures before a given time
        register.addTrainDeparture(departure1);
        register.addTrainDeparture(departure2);
        register.removeDeparturesBeforeTime("12:00");
        assertEquals(1, register.getSortedDepartures().size());

        // Negative test: Attempting to remove departures before a time that doesn't match any
        register.removeDeparturesBeforeTime("08:00");
        assertEquals(1, register.getSortedDepartures().size());
    }
}
*/
