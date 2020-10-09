package seedu.address.logic.wanderlustlogic.wanderlustcommands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.CommandResult;
import seedu.address.logic.wanderlustlogic.wanderlustcommands.exceptions.CommandException;
import seedu.address.model.activity.Activity;
import seedu.address.model.activity.Importance;
import seedu.address.model.activity.WanderlustDateTime;
import seedu.address.model.commons.Cost;
import seedu.address.model.commons.Location;
import seedu.address.model.commons.Name;
import seedu.address.model.commons.TravelPlanObject;
import seedu.address.model.travelplanner.Model;


/**
 * Edits an existing Activity in the address book and updates the travel plan/wishlist in the current directory
 * Edits the importance, cost, location, startdate, enddate
 */
public class EditActivityCommand extends EditCommand {
    public static final String COMMAND_WORD = "activity";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the travel plan identified by the index number used in the displayed travel planner list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_IMPORTANCE + "IMPORTANCE] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_COST + "COST] "
            + "[" + PREFIX_START + "START_DATE] "
            + "[" + PREFIX_END + "END_DATE] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Hard Rock Hotel"
            + PREFIX_IMPORTANCE + "2"
            + PREFIX_LOCATION + "Sentosa"
            + PREFIX_COST + "SGD500"
            + PREFIX_START + "20 September 2020"
            + PREFIX_END + "30 September 2020";

    public static final String MESSAGE_EDIT_ACTIVITY_SUCCESS = "Edited Activity: %1$s";
    public static final String MESSAGE_DUPLICATE_ACTIVITY = "This activity already exists in Wanderlust.";

    private final Index targetIndex;
    private final EditDescriptor editActivityDescriptor;

    /**
     * Constructor for editactivity command
     */
    public EditActivityCommand(Index targetIndex, EditDescriptor editActivityDescriptor) {
        super(targetIndex);
        this.targetIndex = targetIndex;
        this.editActivityDescriptor = editActivityDescriptor;
    }


    //handling the travelplan activity
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<? extends TravelPlanObject> lastShownList = model.getDirectory().getActivityList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
        }

        Activity activityToEdit = (Activity) lastShownList.get(targetIndex.getZeroBased());
        Activity editedActivity = createEditedActivity(activityToEdit, editActivityDescriptor);

        if (!activityToEdit.isSameActivity(editedActivity) && model.hasActivity(editedActivity)) {
            throw new CommandException(MESSAGE_DUPLICATE_ACTIVITY);
        }

        model.setTravelPlanObject(activityToEdit, editedActivity);
        return new CommandResult(String.format(MESSAGE_EDIT_ACTIVITY_SUCCESS, editedActivity));
    }

    /**
     * Creates and returns a {@code Activity} with the details of {@code activityToEdit}
     * edited with {@code editActivityDescriptor}.
     * Edits the importance, cost, location, startime, end time
     */
    private static Activity createEditedActivity(Activity activityToEdit,
                                                 EditDescriptor editActivityDescriptor) {
        assert activityToEdit != null;

        Name updatedName = editActivityDescriptor.getName().orElse(activityToEdit.getName());

        Location updatedlocation = editActivityDescriptor.getLocation().orElse(activityToEdit.getLocation());
        Cost updatedCost = editActivityDescriptor.getCost().orElse(activityToEdit.getCost());
        Importance updatedLevelOfImportance = editActivityDescriptor.getLevelOfImportance()
                .orElse(activityToEdit.getLevelOfImportance());
        WanderlustDateTime updatedActivityDateTime = editActivityDescriptor.getActivityDateTime()
                .orElse(activityToEdit.getActivityDateTime());

        return new Activity(updatedName, updatedlocation, updatedCost, updatedLevelOfImportance,
                updatedActivityDateTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditCommand // instanceof handles nulls
                && targetIndex.equals(((EditActivityCommand) other).targetIndex)); // state check
    }

}