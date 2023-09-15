package com.dakshay.gym.services;

import com.dakshay.gym.Dto.SlotAvailableDto;
import com.dakshay.gym.Dto.UserBookingDTO;
import com.dakshay.gym.models.*;
import com.dakshay.gym.repos.SlotTransactionRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SlotBookingService {

    private final CentreService centreService ;
    private final SlotTransactionRepo slotTransactionRepo ;


    public SlotBookingService(CentreService centreService) {
        this.centreService = centreService;
        this.slotTransactionRepo = new SlotTransactionRepo();
    }

    public List<SlotTransaction> findAll() {
        return slotTransactionRepo.findAll();
    }

    public List<SlotAvailableDto> getAvailableSlots(String centreName) {
        Centre centre = centreService.getCentreByName(centreName);
        List<SlotTransaction> slotTransactions = slotTransactionRepo.findByCentreAndStatus(centreName, SlotStatus.BOOKED);
        Map<String, Integer> bookedSlotsSeats = new HashMap<>();
        for (SlotTransaction slotTransaction : slotTransactions) {
            String slotId = slotTransaction.getSlotId();
            Integer slotsBooked = bookedSlotsSeats.getOrDefault(slotId, 0);
            slotsBooked ++;
            bookedSlotsSeats.put(slotId, slotsBooked);
        }
        List<SlotAvailableDto> availableSlots = new ArrayList<>();
        centre.getSlots().forEach(slot ->{
            SlotAvailableDto slotAvailableDto = new SlotAvailableDto(
                    slot.getId(),
                    centreName,
                    slot.getWorkout(),
                    slot.getStartTime(),
                    slot.getTotalSeats() - bookedSlotsSeats.getOrDefault(slot.getId(), 0)
            );
            availableSlots.add(slotAvailableDto);
        });

        return availableSlots;
    }

    public void bookSlot(String centreName, User user, String slotId) {
        List<SlotTransaction> bookedSlots = slotTransactionRepo.getBookedSlots(centreName, slotId);
        int totalSlots = centreService.getTotalSlots(centreName, slotId);
        SlotTransaction slotTransaction = null;
        if(totalSlots > bookedSlots.size()) {
            slotTransaction = new SlotTransaction(
                    centreName, user.getId(), slotId,
                    System.currentTimeMillis(),
                    SlotStatus.BOOKED
                    );
        } else {
            slotTransaction = new SlotTransaction(
                    centreName, user.getId(), slotId,
                    System.currentTimeMillis(),
                    SlotStatus.WAITLIST
            );
        }
        slotTransactionRepo.save(slotTransaction);
    }

    public SlotTransaction cancelSlot(String centreName, User user, String slotId){
        SlotTransaction slotTransaction = slotTransactionRepo.findSlot(centreName, user.getId(), slotId);
        if(slotTransaction!=null) {
            slotTransaction.cancelSlot();
        } else { // No Slot Booked to Cancel
        }
        SlotTransaction waitList = slotTransactionRepo.findWaitlistSlot(centreName,slotId);
        if(waitList!=null) {
            waitList.makeSlotBooked();
        }
        return waitList;
    }

    public List<UserBookingDTO> getUserBookings(User user, String date) {
       List<SlotTransaction> userBookings = slotTransactionRepo.findByUserId(user.getId());
       Map<String, SlotTransaction> slotVsSlotTrans =
               userBookings.stream().collect(Collectors.toMap(SlotTransaction::getSlotId, Function.identity()));
       List<Centre> centres = centreService.getAll();
       List<UserBookingDTO> bookingsDto = new ArrayList<>();
        for (Centre centre : centres) {
           String centreName =  centre.getCentreName();
            for (Slot slot : centre.getSlots()) {
                if(slotVsSlotTrans.containsKey(slot.getId())) {
                    UserBookingDTO userBookingDTO = new UserBookingDTO(
                            slot.getId(),
                            centreName,
                            slot.getWorkout().getName(),
                            slot.getStartTime()
                    );
                    bookingsDto.add(userBookingDTO);
                }
            }
        }
        return bookingsDto;
    }
}
