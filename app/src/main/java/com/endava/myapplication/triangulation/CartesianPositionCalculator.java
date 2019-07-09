package com.endava.myapplication.triangulation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartesianPositionCalculator implements PositionCalculator<CartesianPosition> {

    private final double acceptableError;

    public CartesianPositionCalculator(double acceptableError) {
        this.acceptableError = acceptableError;
    }

    @Override
    public CartesianPosition getPosition(Map<Beacon<CartesianPosition>, Double> distances) {
        if (distances.size() < 3) {
            throw new IllegalArgumentException("Not enough beacons");
        }
        List<Map.Entry<Beacon<CartesianPosition>, Double>> twoBeacons =
                distances.entrySet().stream().limit(2).collect(Collectors.toList());
        List<CartesianPosition> positionsMatchingDistances = new CartesianTriangle().getPositionsMatchingDistances(
                twoBeacons.get(0).getKey().getPosition(), twoBeacons.get(1).getKey().getPosition(),
                twoBeacons.get(0).getValue(), twoBeacons.get(1).getValue());
        return positionsMatchingDistances.stream().filter(cartesianPosition ->
                distances.entrySet().stream().allMatch(entry ->
                        Math.abs(entry.getKey().getPosition().getDistanceFrom(cartesianPosition)
                                - entry.getValue()) <= acceptableError)).findAny()
                .orElseThrow(() -> new IllegalArgumentException("No matching position"));
    }
}
