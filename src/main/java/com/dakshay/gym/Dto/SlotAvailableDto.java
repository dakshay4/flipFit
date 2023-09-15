package com.dakshay.gym.Dto;

import com.dakshay.gym.models.Base;
import com.dakshay.gym.models.Workout;

public class SlotAvailableDto extends Base {

    private final String centreName;
    private final Workout workout;

    private final String startTime;
    private final int availableSeats;

    public SlotAvailableDto(String id, String centreName, Workout workout, String startTime, int availableSeats) {
        super(id);
        this.centreName = centreName;
        this.workout = workout;
        this.startTime = startTime;
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "SlotAvailableDto{" +
                "centreName='" + centreName + '\'' +
                ", workout=" + workout +
                ", startTime='" + startTime + '\'' +
                ", availableSeats=" + availableSeats +
                ", id='" + getId() + '\'' +
                "} " + super.toString();
    }
}
