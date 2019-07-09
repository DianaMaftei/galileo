package com.endava.myapplication.triangulation;

public interface Position<T extends Position> {
    double getDistanceFrom(T other);
}
