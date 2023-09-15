package com.dakshay.gym.models;

import java.util.StringJoiner;
import java.util.UUID;

public class SlotTransaction extends Base{

    private final String centreName;
    private final String userId;
    private final String slotId;
    private  Long bookedAt;
    private  Long cancelledAt;
    private SlotStatus status;

    public String getCentreName() {
        return centreName;
    }

    public String getUserId() {
        return userId;
    }

    public String getSlotId() {
        return slotId;
    }

    public Long getBookedAt() {
        return bookedAt;
    }

    public Long getCancelledAt() {
        return cancelledAt;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public SlotTransaction(String centreName, String userId, String slotId, Long bookedAt, SlotStatus status) {
        super(UUID.randomUUID().toString());
        this.centreName = centreName;
        this.userId = userId;
        this.slotId = slotId;
        this.bookedAt = bookedAt;
        this.status = status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }

    public void cancelSlot() {
        this.status = SlotStatus.CANCELLED;
        this.cancelledAt = System.currentTimeMillis();
    }


    public void makeSlotBooked() {
        this.status = SlotStatus.BOOKED;
        this.bookedAt = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SlotTransaction.class.getSimpleName() + "[", "]")
                .add("centreName='" + centreName + "'")
                .add("userId='" + userId + "'")
                .add("slotId='" + slotId + "'")
                .add("bookedAt=" + bookedAt)
                .add("cancelledAt=" + cancelledAt)
                .add("status=" + status)
                .add("id='" + getId() + "'")
                .toString();
    }

}
