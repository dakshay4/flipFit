package com.dakshay.gym.repos;

import com.dakshay.gym.models.SlotStatus;
import com.dakshay.gym.models.SlotTransaction;

import java.util.*;

public class SlotTransactionRepo extends RepoAbstraction<SlotTransaction, String> {


/*    @Override
    public SlotTransaction save(SlotTransaction slotTransaction) {
        if ( slotTransaction!=null ) data.put(slotTransaction.getId(), slotTransaction);
        return slotTransaction;
    }

    @Override
    public SlotTransaction getById(String id) {
        return data.get(id);
    }

    @Override
    public ArrayList<SlotTransaction> findAll() {
        return new ArrayList<>(data.values());
    }*/

    public List<SlotTransaction> findByCentreAndStatus(String centreName, SlotStatus status) {
        List<SlotTransaction> slotTransactions = new ArrayList<>();
        for (SlotTransaction slotTransaction : getData().values()) {
            if(centreName.equals(slotTransaction.getCentreName())
                && status.equals(slotTransaction.getStatus())) slotTransactions.add(slotTransaction);
        }
        return slotTransactions;
    }

    public SlotTransaction findSlot(String centreName, String userId, String slotId) {
        List<SlotTransaction> slotTransactions = new ArrayList<>();
        for (SlotTransaction slotTransaction : getData().values()) {
            if(centreName.equals(slotTransaction.getCentreName())
                    && userId.equals(slotTransaction.getUserId())
                    && slotId.equals(slotTransaction.getSlotId())
                    && SlotStatus.BOOKED.equals(slotTransaction.getStatus()))
                slotTransactions.add(slotTransaction);
        }
        return slotTransactions.size()>0 ? slotTransactions.get(0) : null;

    }

    public List<SlotTransaction> findByUserId(String userId) {
        List<SlotTransaction> slotTransactions = new ArrayList<>();
        for (SlotTransaction slotTransaction : getData().values()) {
            if(userId.equals(slotTransaction.getUserId())
                    && SlotStatus.BOOKED.equals(slotTransaction.getStatus()))
                slotTransactions.add(slotTransaction);
        }
        return slotTransactions;
    }

    public List<SlotTransaction> getBookedSlots(String centreName, String slotId) {
        List<SlotTransaction> slotTransactions = new ArrayList<>();
        for (SlotTransaction slotTransaction : getData().values()) {
            if(slotId.equals(slotTransaction.getSlotId())
                    && centreName.equals(slotTransaction.getCentreName())
                    && SlotStatus.BOOKED.equals(slotTransaction.getStatus()))
                slotTransactions.add(slotTransaction);
        }
        return slotTransactions;
    }

    public SlotTransaction findWaitlistSlot(String centreName, String slotId) {
        List<SlotTransaction> slotTransactions = new ArrayList<>();
        for (SlotTransaction slotTransaction : getData().values()) {
            if(slotId.equals(slotTransaction.getSlotId())
                    && centreName.equals(slotTransaction.getCentreName())
                    && SlotStatus.WAITLIST.equals(slotTransaction.getStatus()))
                slotTransactions.add(slotTransaction);
        }
        return slotTransactions.size()>0 ? slotTransactions.get(0) : null;
    }
}
