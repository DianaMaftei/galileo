package com.endava.myapplication.model;

public class Employee {

    private int id;

    private String name;

    private String floor;

    private String seat;

    private String seatBeaconIdentifier;

    private String project;

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getSeatBeaconIdentifier() {
        return seatBeaconIdentifier;
    }

    public void setSeatBeaconIdentifier(String seatBeaconIdentifier) {
        this.seatBeaconIdentifier = seatBeaconIdentifier;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
