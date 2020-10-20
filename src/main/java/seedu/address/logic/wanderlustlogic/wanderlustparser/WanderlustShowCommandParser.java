package seedu.address.logic.wanderlustlogic.wanderlustparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.wanderlustlogic.wanderlustcommands.ShowCommand;
import seedu.address.logic.wanderlustlogic.wanderlustparser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ShowCommand object
 */
public class WanderlustShowCommandParser implements WanderlustParserInterface<ShowCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ShowCommand.
     * and returns a ShowCommand object for execution.
     * @throws ParseException if the user input does not conform
     * the expected format.
     */
    public ShowCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
        }
        String[] keywords = args.split(" ");
        String travelPlanObjectType = keywords[1].substring(1);
        return new ShowCommand(travelPlanObjectType);

    }
}
