package seedu.address.model.travelplan;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTravelPlans.AUSTRALIA_TRIP;
import static seedu.address.testutil.TypicalTravelPlans.BOSTON_TRIP;
import static seedu.address.testutil.TypicalTravelPlans.VALID_ACTIVITY_LIST_BOSTON;
import static seedu.address.testutil.TypicalTravelPlans.VALID_END_DATE_BOSTON;
import static seedu.address.testutil.TypicalTravelPlans.VALID_FRIEND_LIST_BOSTON;
import static seedu.address.testutil.TypicalTravelPlans.VALID_NAME_BOSTON;
import static seedu.address.testutil.TypicalTravelPlans.VALID_START_DATE_BOSTON;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.TravelPlanBuilder;

public class TravelPlanTest {

    /**
     * NOT DONE: WAITING FOR AccommodationBuilder, TypicalAccommodations TO BE UPDATED.
     */

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        TravelPlan travelPlan = new TravelPlanBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> travelPlan.getFriendList().remove(0));
        assertThrows(UnsupportedOperationException.class, () -> travelPlan.getAccommodationList().remove(0));
        assertThrows(UnsupportedOperationException.class, () -> travelPlan.getActivityList().remove(0));
    }

    @Test
    public void isSameTravelPlan() {
        // same object -> returns true
        assertTrue(AUSTRALIA_TRIP.isSameTravelPlan(AUSTRALIA_TRIP));

        // null -> returns false
        assertFalse(AUSTRALIA_TRIP.isSameTravelPlan(null));

        // different startDate and endDate -> returns false
        TravelPlan editedAustraliaTrip = new TravelPlanBuilder(AUSTRALIA_TRIP).withStartDate(VALID_START_DATE_BOSTON)
                .withEndDate(VALID_END_DATE_BOSTON).build();
        assertFalse(AUSTRALIA_TRIP.isSameTravelPlan(editedAustraliaTrip));

        // different name -> returns false
        editedAustraliaTrip = new TravelPlanBuilder(AUSTRALIA_TRIP).withName(VALID_NAME_BOSTON).build();
        assertFalse(AUSTRALIA_TRIP.isSameTravelPlan(editedAustraliaTrip));

        // same name, same start date, different attributes -> returns true
        editedAustraliaTrip = new TravelPlanBuilder(AUSTRALIA_TRIP).withEndDate(VALID_END_DATE_BOSTON)
                .withActivityList(VALID_ACTIVITY_LIST_BOSTON).build();
        assertTrue(AUSTRALIA_TRIP.isSameTravelPlan(editedAustraliaTrip));

        // same name, same end date, different attributes -> returns true
        editedAustraliaTrip = new TravelPlanBuilder(AUSTRALIA_TRIP).withEndDate(VALID_START_DATE_BOSTON)
                .withFriendList(VALID_FRIEND_LIST_BOSTON).build();
        assertTrue(AUSTRALIA_TRIP.isSameTravelPlan(editedAustraliaTrip));

        // same name, same start date, same end date, different attributes -> returns true
        editedAustraliaTrip = new TravelPlanBuilder(AUSTRALIA_TRIP).withEndDate(VALID_START_DATE_BOSTON)
                .withFriendList(VALID_FRIEND_LIST_BOSTON)
                .withAccommodationList(VALID_ACCOMMODATION_LIST_BOSTON)
                .withActivityList(VALID_ACTIVITY_LIST_BOSTON).build();
        assertTrue(AUSTRALIA_TRIP.isSameTravelPlan(editedAustraliaTrip));
    }
    
    @Test
    public void equals() {
        // same values -> returns true
        TravelPlan australiaCopy = new TravelPlanBuilder(AUSTRALIA_TRIP).build();
        assertTrue(AUSTRALIA_TRIP.equals(australiaCopy));
        
        // same object -> returns true
        assertTrue(AUSTRALIA_TRIP.equals(AUSTRALIA_TRIP));
        
        // null -> returns false
        assertFalse(AUSTRALIA_TRIP.equals(null));
        
        // different type -> returns false
        assertFalse(AUSTRALIA_TRIP.equals(5));
        
        // different person -> returns false
        assertFalse(AUSTRALIA_TRIP.equals(BOSTON_TRIP));
        
        // different name -> returns false
        TravelPlan editedTravelPlan = new TravelPlanBuilder(AUSTRALIA_TRIP).withName(VALID_NAME_BOSTON).build();
        assertFalse(AUSTRALIA_TRIP.equals(editedTravelPlan));
        
        // different start date -> returns false
        editedTravelPlan = new TravelPlanBuilder(AUSTRALIA_TRIP).withStartDate(VALID_START_DATE_BOSTON).build();
        assertFalse(AUSTRALIA_TRIP.equals(editedTravelPlan));

        // different end date -> returns false
        editedTravelPlan = new TravelPlanBuilder(AUSTRALIA_TRIP).withEndDate(VALID_END_DATE_BOSTON).build();
        assertFalse(AUSTRALIA_TRIP.equals(editedTravelPlan));

        // different accommodation list -> returns false
        editedTravelPlan = new TravelPlanBuilder(AUSTRALIA_TRIP).withAccommodationList(VALID_ACCOMMODATION_LIST_BOSTON)
                .build();
        assertFalse(AUSTRALIA_TRIP.equals(editedTravelPlan));

        // different activity list -> returns false
        editedTravelPlan = new TravelPlanBuilder(AUSTRALIA_TRIP).withActivityList(VALID_ACTIVITY_LIST_BOSTON).build();
        assertFalse(AUSTRALIA_TRIP.equals(editedTravelPlan));

        // different friend list -> returns false
        editedTravelPlan = new TravelPlanBuilder(AUSTRALIA_TRIP).withFriendList(VALID_FRIEND_LIST_BOSTON).build();
        assertFalse(AUSTRALIA_TRIP.equals(editedTravelPlan));
    }

}
