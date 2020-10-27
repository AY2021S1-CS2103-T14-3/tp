package seedu.address.logic.command;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.ACCOMMODATION_INDEX;
import static seedu.address.logic.parser.ParserUtil.ACTIVITY_INDEX;
import static seedu.address.logic.parser.ParserUtil.FRIEND_INDEX;
import static seedu.address.logic.parser.ParserUtil.INVALID_INDEX;

import seedu.address.commons.core.Messages;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.accommodation.Accommodation;
import seedu.address.model.activity.Activity;
import seedu.address.model.friend.Friend;

/**
 * Navigates and display the specified travel plan object based on user input.
 */
public class ShowCommand extends Command {
    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the respective list of travel plan object type identified by the keyword.\n"
            + "Parameters: travelPlanObjectType\n"
            + "Valid keywords: '-activity', '-accommodation', '-friend'\n"
            + "Example: " + COMMAND_WORD + " -activity";

    public static final String MESSAGE_SHOW_SUCCESS = "show: %1$s";

    private final String travelPlanObjectString;

    private final int travelPlanObjectType;

    /**
     * Constructor for ShowCommand.
     * @param travelPlanObjectType string of the travel plan object type.
     */
    public ShowCommand(String travelPlanObjectType) {
        travelPlanObjectString = travelPlanObjectType;
        switch (travelPlanObjectType) {
        case Activity.TPO_WORD:
            this.travelPlanObjectType = ACTIVITY_INDEX;
            break;
        case Accommodation.TPO_WORD:
            this.travelPlanObjectType = ACCOMMODATION_INDEX;
            break;
        case Friend.TPO_WORD:
            this.travelPlanObjectType = FRIEND_INDEX;
            break;
        default:
            this.travelPlanObjectType = INVALID_INDEX;
            break;
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isTravelPlan = model.isDirectoryTypeTravelPlan();
        boolean isWishlist = !isTravelPlan;

        if (isWishlist && travelPlanObjectType != ACTIVITY_INDEX) {
            throw new CommandException(Messages.MESSAGE_INVALID_SHOW_AT_WISHLIST);
        }

        if (travelPlanObjectType == INVALID_INDEX) {
            throw new CommandException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
        }

        switch (travelPlanObjectString) {
        case Activity.TPO_WORD:
            model.updateFilteredActivityList(Model.PREDICATE_SHOW_ALL);
            break;
        case Accommodation.TPO_WORD:
            model.updateFilteredAccommodationList(Model.PREDICATE_SHOW_ALL);
            break;
        case Friend.TPO_WORD:
            model.updateFilteredFriendList(Model.PREDICATE_SHOW_ALL);
            break;
        default:
            throw new CommandException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
        }

        return new CommandResult(String.format(MESSAGE_SHOW_SUCCESS, travelPlanObjectString), travelPlanObjectType);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowCommand // instanceof handles nulls
                && travelPlanObjectString.equals(((ShowCommand) other).travelPlanObjectString)) // state check
                && travelPlanObjectType == (((ShowCommand) other).travelPlanObjectType);
    }
}
