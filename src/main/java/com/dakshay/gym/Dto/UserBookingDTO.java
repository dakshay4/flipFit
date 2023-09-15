package com.dakshay.gym.Dto;

import com.dakshay.gym.models.Base;
import com.dakshay.gym.models.Workout;

import java.util.StringJoiner;

public class UserBookingDTO extends Base {

    private final String centreName;
    private final Workout.WorkoutType workoutType;
    private final String slotTime;

    public UserBookingDTO(String slotid, String centreName, Workout.WorkoutType workoutType, String slotTime) {
        super(slotid);
        this.centreName = centreName;
        this.workoutType = workoutType;
        this.slotTime = slotTime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserBookingDTO.class.getSimpleName() + "[", "]")
                .add("centreName='" + centreName + "'")
                .add("workoutType=" + workoutType)
                .add("slotTime='" + slotTime + "'")
                .add("id='" + getId() + "'")
                .toString();
    }
}
