package seedu.address.model.travelplanner;

import seedu.address.commons.core.GuiSettings;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path wanderlustFilePath = Paths.get("data" , "wanderlust.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setWanderlustFilePath(newUserPrefs.getWanderlustFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getWanderlustFilePath() {
        return wanderlustFilePath;
    }

    public void setWanderlustFilePath(Path wanderlustFilePath) {
        requireNonNull(wanderlustFilePath);
        this.wanderlustFilePath = wanderlustFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && wanderlustFilePath.equals(o.wanderlustFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, wanderlustFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + wanderlustFilePath);
        return sb.toString();
    }

}
