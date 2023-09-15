import com.dakshay.gym.Dto.SlotAvailableDto;
import com.dakshay.gym.models.*;
import com.dakshay.gym.services.CentreService;
import com.dakshay.gym.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FlipFitTest {

    private final CentreService centreService;
    private final UserService userService;


    public FlipFitTest() {
        this.centreService = new CentreService();
        this.userService = new UserService();
    }

    @Test
    public void start() {
        Centre centre1 = getCentre1();
        Centre centre2 = getCentre2();
        centre1.addWorkouts(getWorkOutWeights());
        centre1.addWorkouts(getWorkOutCardio());
        centre1.addWorkouts(getWorkOutYoga());
        centre2.addWorkouts(getWorkOutWeights());
        Slot slot1 = getSlot1();
        Slot slot2 = getSlot2();
        centre1.addSlot(slot1);
        centre2.addSlot(slot2);

        centreService.createCentre(centre1);
        centreService.createCentre(centre2);

        User user1 = userService.save(registerUser1());
        User user2 = userService.save(registerUser2());
        User user3 = userService.save(registerUser3());

        FlipTestClient flipTestClient = new FlipTestClient(
                List.of(centre1, centre2),
                List.of(user1, user2, user3),
                centreService
        );

        flipTestClient.bookSlot("bellandur", user1, slot1.getId());
        flipTestClient.bookSlot("bellandur", user2, slot1.getId());
        flipTestClient.bookSlot("bellandur", user3, slot1.getId());

        List<SlotAvailableDto> slotAvailableDtos1 = flipTestClient.getAvailableSlots("bellandur", "28-05-2021");
        System.out.println(slotAvailableDtos1);
        List<SlotAvailableDto> slotAvailableDtos2 = flipTestClient.getAvailableSlots("Sarjapur", "28-05-2021");
        System.out.println(slotAvailableDtos2);

        System.out.println(flipTestClient.findAll());

        System.out.println("View User Booking " +flipTestClient.viewUserBooking(user1,"22"));

        SlotTransaction transaction = flipTestClient.cancelSlot("bellandur", user1, slot1.getId());

        System.out.println(userService.findById(transaction.getUserId()).getName() + " is Promoted for Slot " + transaction.getCentreName());
        Assertions.assertTrue(transaction.getUserId() == user3.getId());


    }


    private User registerUser1() {
        return new User("Vivek", 16, "Bangalore");
    }

    private User registerUser2() {
        return new User("Pavan", 20, "Bangalore");

    }

    private User registerUser3() {
        return new User("Varun", 22, "Bangalore");

    }


    private Slot getSlot2() {
        return new Slot(getWorkOutYoga(),"8:00", 2,3);
    }

    private Slot getSlot1() {
        return new Slot(getWorkOutWeights(),"6:00", 2,3);
    }

    private Workout getWorkOutYoga() {
        return new Workout(Workout.WorkoutType.YOGA);
    }

    private Workout getWorkOutCardio() {
        return new Workout(Workout.WorkoutType.CARDIO);
    }

    public Centre getCentre1()  {
        return new Centre(
                "bellandur", "bangalore",
                List.of(Day.MONDAY, Day.SUNDAY),
                5,2,5);
    }
    public Centre getCentre2()  {
        return new Centre(
                "Sarjapur", "bangalore",
                List.of(Day.MONDAY, Day.SUNDAY),
                5,3,5);
    }

    private Workout getWorkOutWeights() {
        return new Workout(Workout.WorkoutType.WEIGHTS);
    }


}
