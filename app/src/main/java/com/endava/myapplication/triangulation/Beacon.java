package com.endava.myapplication.triangulation;

public interface Beacon<T extends Position> {
    T getPosition();
}
