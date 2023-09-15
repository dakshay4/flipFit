package com.dakshay.gym.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Centre extends  Base{

    private final String centreName;
    private final String cityName;
    private List<Workout> workouts;
    private final List<Day> closedDays;
    private final List<Slot> slots;
    private final int totalSlots;
    private final int lat;
    private final int lon;

    public String getCentreName() {
        return centreName;
    }

    public String getCityName() {
        return cityName;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public List<Day> getClosedDays() {
        return closedDays;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public int getLat() {
        return lat;
    }

    public int getLon() {
        return lon;
    }

    public Centre(String centreName, String cityName,
                  List<Day> closedDays, int totalSlots, int lat, int lon) {
        super(UUID.randomUUID().toString());
        this.centreName = centreName;
        this.cityName = cityName;
        this.closedDays = closedDays;
        this.totalSlots = totalSlots;
        this.lat = lat;
        this.lon = lon;
        this.workouts = new ArrayList<>();
        this.slots = new ArrayList<>();
    }

    public Centre addWorkouts(Workout workouts) {
        this.workouts.add(workouts);
        return this;
    }
    public Centre addSlot(Slot slot) {
        this.slots.add(slot);
        return this;
    }
}
