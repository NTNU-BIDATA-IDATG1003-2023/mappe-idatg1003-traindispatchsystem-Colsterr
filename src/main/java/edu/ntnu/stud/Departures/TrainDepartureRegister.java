package edu.ntnu.stud.Departures;

import edu.ntnu.stud.Departures.TrainDeparture;
import edu.ntnu.stud.Departures.TrainDepartureComparator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TrainDepartureRegister {
    private List<TrainDeparture> departures;

    public TrainDepartureRegister() {
        departures = new ArrayList<>();
    }

    public void addTrainDeparture(TrainDeparture newDeparture) {
        departures.add(newDeparture);
    }

    public TrainDeparture findTrainByTrainNumber(String trainNumber) {
        for (TrainDeparture departure : departures) {
            if (departure.getTrainNumber().equals(trainNumber)) {
                return departure;
            }
        }
        return null;
    }

    public List<TrainDeparture> findDeparturesByDestination(String destination) {
        return departures.stream()
                .filter(departure -> departure.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());
    }

    public void removeDeparturesBeforeTime(String time) {
        Iterator<TrainDeparture> iterator = departures.iterator();
        while (iterator.hasNext()) {
            TrainDeparture departure = iterator.next();
            if (departure.getDepartureTime().toString().compareTo(time) < 0) {
                iterator.remove();
            }
        }
    }

    public List<TrainDeparture> getSortedDepartures() {
        List<TrainDeparture> sortedDepartures = new ArrayList<>(departures);
        sortedDepartures.sort(new TrainDepartureComparator());
        return sortedDepartures;
    }

    public List<TrainDeparture> getDepartures() {
        return departures;
    }

    private boolean containsTrainWithNumber(String trainNumber) {
        return departures.stream().anyMatch(departure -> departure.getTrainNumber().equals(trainNumber));
    }
}
