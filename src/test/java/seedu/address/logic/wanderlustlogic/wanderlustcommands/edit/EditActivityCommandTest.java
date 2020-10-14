package seedu.address.logic.wanderlustlogic.wanderlustcommands.edit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.DESC_SKI;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.DESC_ZOO;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.VALID_NAME_ZOO;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.edit.EditActivityCommand.MESSAGE_DUPLICATE_ACTIVITY;
import static seedu.address.logic.wanderlustlogic.wanderlustcommands.edit.EditActivityCommand.MESSAGE_EDIT_ACTIVITY_SUCCESS;
import static seedu.address.testutil.typicals.TypicalActivities.ARCHERY;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.typicals.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.typicals.TypicalTravelPlans.getTypicalTravelPlanner;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.edit.builder.EditActivityDescriptorBuilder;
import seedu.address.model.activity.Activity;
import seedu.address.model.travelplanner.Model;
import seedu.address.model.travelplanner.ModelManager;
import seedu.address.model.travelplanner.UserPrefs;
import seedu.address.testutil.builders.ActivityBuilder;

public class EditActivityCommandTest {

    private Model model = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Activity editedActivity = new ActivityBuilder(ARCHERY).build();
        EditDescriptor descriptor = new EditActivityDescriptorBuilder(editedActivity).build();
        EditActivityCommand editActivityCommand = new EditActivityCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_ACTIVITY_SUCCESS, editedActivity);

        ModelManager expectedModel = new ModelManager(model.getTravelPlanner(), new UserPrefs());
        expectedModel.setTravelPlanObject(model.getFilteredActivityList().get(0), editedActivity);


        assertCommandSuccess(editActivityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditActivityCommand editActivityCommand = new EditActivityCommand(INDEX_FIRST, new EditDescriptor());
        Activity editedActivity = model.getFilteredActivityList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(MESSAGE_EDIT_ACTIVITY_SUCCESS, editedActivity);

        ModelManager expectedModel = new ModelManager(model.getTravelPlanner(), new UserPrefs());

        assertCommandSuccess(editActivityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {

        Activity activityInFilteredList = model.getFilteredActivityList().get(INDEX_FIRST.getZeroBased());
        Activity editedActivity = new ActivityBuilder(activityInFilteredList).withName(VALID_NAME_ZOO).build();
        EditActivityCommand editActivityCommand = new EditActivityCommand(INDEX_FIRST,
                new EditActivityDescriptorBuilder().withName(VALID_NAME_ZOO).build());

        String expectedMessage = String.format(MESSAGE_EDIT_ACTIVITY_SUCCESS, editedActivity);

        ModelManager expectedModel = new ModelManager(model.getTravelPlanner(), new UserPrefs());
        expectedModel.setTravelPlanObject(model.getFilteredActivityList().get(0), editedActivity);

        assertCommandSuccess(editActivityCommand, model, expectedMessage, expectedModel);
    }


    //HELPS it passes the test on its own, but when running the class, test fails TODO:resolve test failure
    @Test
    public void execute_duplicateActivityUnfilteredList_failure() {
        Activity firstActivity = model.getFilteredActivityList().get(INDEX_FIRST.getZeroBased());
        EditDescriptor descriptor = new EditActivityDescriptorBuilder(firstActivity).build();
        EditActivityCommand editActivityCommand = new EditActivityCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editActivityCommand, model, MESSAGE_DUPLICATE_ACTIVITY);
    }

    @Test
    public void execute_duplicateActivityFilteredList_failure() {

        // edit Activity in filtered list into a duplicate in address book
        Activity activityInList = model.getFilteredActivityList().get(INDEX_SECOND.getZeroBased());
        EditActivityCommand editActivityCommand = new EditActivityCommand(INDEX_FIRST,
                new EditActivityDescriptorBuilder(activityInList).build());

        assertCommandFailure(editActivityCommand, model, MESSAGE_DUPLICATE_ACTIVITY);
    }

    @Test
    public void execute_invalidActivityIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredActivityList().size() + 1);
        EditDescriptor descriptor = new EditActivityDescriptorBuilder().withName(VALID_NAME_ZOO).build();
        EditActivityCommand editActivityCommand = new EditActivityCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editActivityCommand, model, Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        final EditActivityCommand standardCommand = new EditActivityCommand(INDEX_FIRST, DESC_SKI);

        // same values -> returns true
        EditDescriptor copyDescriptor = new EditDescriptor(DESC_SKI);
        EditActivityCommand commandWithSameValues = new EditActivityCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditActivityCommand(INDEX_SECOND, DESC_SKI)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditActivityCommand(INDEX_FIRST, DESC_ZOO)));
    }
}