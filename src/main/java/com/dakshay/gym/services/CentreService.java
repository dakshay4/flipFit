package com.dakshay.gym.services;

import com.dakshay.gym.models.Centre;
import com.dakshay.gym.models.Slot;
import com.dakshay.gym.models.Workout;
import com.dakshay.gym.repos.CentreStorage;

import java.util.List;
import java.util.Map;

public class CentreService {

    private final CentreStorage centreStorage;


    public CentreService() {
        this.centreStorage = new CentreStorage();
    }

    public Centre createCentre(Centre centre) {
        return centreStorage.save(centre);
    }


    public Centre getCentreByName(String centreName) {
        Map<String,Centre> data = centreStorage.getData();
        return data.get(centreName);
    }

    public List<Centre> getAll() {
        return centreStorage.findAll();
    }

    public int getTotalSlots(String centreName, String slotId) {
        Centre centre = centreStorage.getByName(centreName);
        for (Slot slot : centre.getSlots()) {
            if(slot.getId().equals(slotId)) return slot.getTotalSeats();
        }
        return 0;
    }
}
