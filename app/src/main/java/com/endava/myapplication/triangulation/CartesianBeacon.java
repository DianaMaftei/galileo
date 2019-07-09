package com.endava.myapplication.triangulation;

public class CartesianBeacon implements Beacon<CartesianPosition> {
    private final CartesianPosition position;

    public CartesianBeacon(CartesianPosition position) {
        this.position = position;
    }

    @Override
    public CartesianPosition getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "CartesianBeacon{" + "position=" + position +
                '}';
    }
}
