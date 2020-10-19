package seedu.address.logic.wanderlustlogic.wanderlustcommands;

import static seedu.address.testutil.typicals.TypicalTravelPlans.getTypicalTravelPlanner;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.wanderlustlogic.wanderlustparser.WanderlustFindCommandParser;
import seedu.address.logic.wanderlustlogic.wanderlustparser.exceptions.ParseException;
import seedu.address.model.commons.NameContainsKeywordsPredicate;
import seedu.address.model.travelplanner.Model;
import seedu.address.model.travelplanner.ModelManager;
import seedu.address.model.travelplanner.UserPrefs;

public class FindCommandTest {

    private Model model;

    @BeforeEach
    public void setup() {
        model = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());
        model.setDirectory(0);
    }

    @Test
    public void execute_find_success() {
        try {
            String[] arr = new String[]{"friend"};
            List<String> keywords = Arrays.asList(arr);

            FindCommand expectedCommand = new FindCommand(new NameContainsKeywordsPredicate(keywords), 2);
            Assertions.assertTrue(expectedCommand.equals(new WanderlustFindCommandParser()
                    .parse("find -friend friend")));
        } catch (ParseException pe) {
            System.out.println("Invalid input!");
        }
    }

    @Test
    public void execute_findMissingDescription_failure() {
        List<String> keywords = Arrays.asList("foo", "bar", "baz"); //find activity
        FindCommand validCommand = new FindCommand(new NameContainsKeywordsPredicate(keywords), 0);
        try {
            Assertions.assertFalse(validCommand.equals(new WanderlustFindCommandParser().parse("find -activity")));
        } catch (ParseException e) {
            System.out.println("Invalid input");
        }

    }
}