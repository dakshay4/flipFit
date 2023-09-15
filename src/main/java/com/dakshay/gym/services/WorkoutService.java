package com.dakshay.gym.services;

import com.dakshay.gym.models.Workout;
import com.dakshay.gym.repos.WorkoutStorage;

public class WorkoutService {

    private final WorkoutStorage workoutStorage;

    public WorkoutService() {
        this.workoutStorage = new WorkoutStorage();
    }

    public Workout createWorkout(Workout.WorkoutType type){
       return workoutStorage.save(new Workout(type));
    }
}
