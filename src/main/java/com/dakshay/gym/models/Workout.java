package com.dakshay.gym.models;

import java.util.UUID;

public class Workout extends Base{

    private final WorkoutType name;

    public Workout(WorkoutType name) {
        super(UUID.randomUUID().toString());
        this.name = name;
    }

    public WorkoutType getName() {
        return name;
    }

    public enum WorkoutType {
        WEIGHTS,CARDIO,YOGA,SWIMMING
    }

}
