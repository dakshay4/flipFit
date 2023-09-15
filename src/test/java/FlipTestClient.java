import com.dakshay.gym.Dto.SlotAvailableDto;
import com.dakshay.gym.Dto.UserBookingDTO;
import com.dakshay.gym.models.*;
import com.dakshay.gym.services.CentreService;
import com.dakshay.gym.services.SlotBookingService;

import java.util.*;
import java.util.stream.Collectors;

public class FlipTestClient {

    private final List<Centre> centres;
    private final List<User> users;

    private final SlotBookingService slotBookingService;
    private final CentreService centreService;

    public FlipTestClient(List<Centre> centres, List<User> users, CentreService centreService) {
        this.centres = centres;
        this.users = users;
        this.slotBookingService = new SlotBookingService(centreService);
        this.centreService = centreService;
    }

    public List<SlotAvailableDto> getAvailableSlots(String centreName, String date) {
//        Day day = Day.valueOf(DateUtil.getDayOfWeek(date));
        //Considering Assumption as booking happened for all Days
        return slotBookingService.getAvailableSlots(centreName);
    }

    public List<SlotTransaction> findAll() {
        return slotBookingService.findAll();
    }

    public void bookSlot(String centreName, User user, String slotId) {
        slotBookingService.bookSlot(centreName,user, slotId);
    }

    public List<UserBookingDTO> viewUserBooking(User user, String date) {
        return slotBookingService.getUserBookings(user, date);
    }

    public SlotTransaction cancelSlot(String centreName, User user1, String id) {
        return slotBookingService.cancelSlot(centreName, user1, id);
    }
}
