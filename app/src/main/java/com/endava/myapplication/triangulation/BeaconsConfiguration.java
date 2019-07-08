package com.endava.myapplication.triangulation;

public class BeaconsConfiguration {

    public Beacon getBeacon(double... coordinates) {
        return new CartesianBeacon(new CartesianPosition(coordinates[0], coordinates[1]));
    }

    public PositionCalculator getPositionCalculator(double acceptableError) {
        return new CartesianPositionCalculator(acceptableError);
    }
}
