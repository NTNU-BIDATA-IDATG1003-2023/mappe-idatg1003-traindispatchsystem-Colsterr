package edu.ntnu.stud;

import edu.ntnu.stud.TrainDeparture;

import java.util.Comparator;

import java.util.Comparator;
public class TrainDepartureComparator implements Comparator<TrainDeparture> {
    @Override
    public int compare(TrainDeparture departure1, TrainDeparture departure2) {
        return departure1.getDepartureTime().compareTo(departure2.getDepartureTime());
    }
}
