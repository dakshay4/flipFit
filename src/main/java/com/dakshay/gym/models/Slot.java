package com.dakshay.gym.models;

import java.util.UUID;

public class Slot extends Base {

    private final Workout workout;

    private final String startTime;
    private final int totalSeats;
    private final int allowedWaitlists;

    public Workout getWorkout() {
        return workout;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getAllowedWaitlists() {
        return allowedWaitlists;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public Slot(Workout workout, String startTime, int totalSeats, int allowedWaitlists) {
        super(UUID.randomUUID().toString());
        this.workout = workout;
        this.startTime = startTime;
        this.totalSeats = totalSeats;
        this.allowedWaitlists = allowedWaitlists;
    }
}
