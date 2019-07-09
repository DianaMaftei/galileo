package com.endava.myapplication.triangulation;

import java.util.Map;

public interface PositionCalculator<T extends Position> {
    T getPosition(Map<Beacon<T>, Double> distances);
}
