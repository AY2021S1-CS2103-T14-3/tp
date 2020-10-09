package seedu.address.logic.wanderlustlogic.wanderlustparser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.wanderlustlogic.wanderlustparser.exceptions.ParseException;
import seedu.address.model.activity.Importance;
import seedu.address.model.activity.WanderlustDateTime;
import seedu.address.model.commons.Location;
import seedu.address.model.commons.WanderlustDate;
import seedu.address.model.commons.Name;
import seedu.address.model.commons.Cost;
import seedu.address.model.friend.Passport;
import seedu.address.model.friend.Phone;
import seedu.address.model.friend.Friend;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String location} into a {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedLocation = location.trim();
        if (!Location.isValidLocation(trimmedLocation)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedLocation);
    }

    /**
     * Parses a {@code String Cost} into a {@code Cost}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Cost} is invalid.
     */
    public static Cost parseCost(String cost) throws ParseException {
        requireNonNull(cost);
        String trimmedCost = cost.trim();
        if (!Cost.isValidCost(trimmedCost)) {
            throw new ParseException(Cost.MESSAGE_CONSTRAINTS);
        }
        return new Cost(trimmedCost);
    }

    /**
     * Parses a {@code String startDate} into a {@code WanderlustDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code startDate} is invalid.
     */
    public static WanderlustDate parseStartDate(String startDate) throws ParseException {
        requireNonNull(startDate);
        String trimmedStartDate = startDate.trim();
        if (!WanderlustDate.isValidWanderlustDate(trimmedStartDate)) {
            throw new ParseException(WanderlustDate.MESSAGE_CONSTRAINTS);
        }
        return new WanderlustDate(trimmedStartDate);
    }

    /**
     * Parses a {@code String endDate} into a {@code WanderlustDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code endDate} is invalid.
     */
    public static WanderlustDate parseEndDate(String endDate) throws ParseException {
        requireNonNull(endDate);
        String trimmedEndDate = endDate.trim();
        if (!WanderlustDate.isValidWanderlustDate(trimmedEndDate)) {
            throw new ParseException(WanderlustDate.MESSAGE_CONSTRAINTS);
        }
        return new WanderlustDate(trimmedEndDate);
    }

    /**
     * Parses a {@code String passport} into a {@code Passport}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code passport} is invalid.
     */
    public static Passport parsePassport(String passport) throws ParseException {
        requireNonNull(passport);
        String trimmedPassport = passport.trim();
        if (!Passport.isValidPassport(trimmedPassport)) {
            throw new ParseException(Passport.MESSAGE_CONSTRAINTS);
        }
        return new Passport(trimmedPassport);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String importance} into a {@code Importance}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code importance} is invalid.
     */
    public static Importance parseLevelOfImportance(String importance) throws ParseException {
        requireNonNull(importance);
        String trimmedImportance = importance.trim();
        if (!Importance.isValidImportance(trimmedImportance)) {
            throw new ParseException(Importance.MESSAGE_CONSTRAINTS);
        }
        return new Importance(trimmedImportance);
    }

    /**
     * Parses a {@code String activityDateTime} into a {@code WanderlustDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code activityDateTime} is invalid.
     */
    public static WanderlustDateTime parseActivityDateTime(String activityDateTime) throws ParseException {
        requireNonNull(activityDateTime);
        String trimmedActivityDateTime = activityDateTime.trim();
        if (!WanderlustDateTime.isValidWanderlustDateTime(trimmedActivityDateTime)) {
            throw new ParseException(WanderlustDateTime.MESSAGE_CONSTRAINTS);
        }
        return new WanderlustDateTime(trimmedActivityDateTime);
    }


    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}