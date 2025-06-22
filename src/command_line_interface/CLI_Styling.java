package command_line_interface;

/**
 * The CLI_Styling class provides ANSI escape codes and layout styles
 * for styling the console output in the application. It includes
 * constants for text colors, borders, and formatted headings.
 */
public class CLI_Styling {
    // === ANSI escape codes for console styling ===

    /** Resets the console text style to default. */
    public static final String RESET = "\u001B[0m";

    /** Sets the console text color to green. */
    public static final String GREEN = "\u001B[32m";

    /** Sets the console text color to yellow. */
    public static final String YELLOW = "\u001B[33m";

    /** Sets the console text color to red. */
    public static final String RED = "\u001B[31m";

    /** Sets the console text color to blue. */
    public static final String BLUE = "\u001B[34m";

    // === Layout Styles ===

    /** Welcome banner displayed at the start of the application. */
    public static final String WELCOME_BANNER =
            BLUE + "\n╔══════════════════════════════════════════════╗\n" +
                    "║              Welcome to GRADELY              ║\n" +
                    "║      Your Student Results Analysis System    ║\n" +
                    "╚══════════════════════════════════════════════╝\n" + RESET;

    /** Border for the main menu section. */
    public static final String MENU_BORDER = "═══════════════════ MAIN MENU ══════════════════";

    /** Border for the analysis menu section. */
    public static final String ANALYSIS_BORDER = "════════════════════ ANALYZE ═══════════════════";

    /** Short double border for separating sections. */
    public static final String DOUBLE_BORDER_SHORT = "════════════════════════════════════════════════";

    /** Long double border for separating sections. */
    public static final String DOUBLE_BORDER_LONG = "═══════════════════════════════════════════════════════════════════════════";

    /** Short dotted border for separating sections. */
    public static final String DOTTED_BORDER_SHORT = "------------------------------------------------";

    /** Long dotted border for separating sections. */
    public static final String DOTTED_BORDER_LONG = "---------------------------------------------------------------------------";

    /** Outline for tables in the console output. */
    public static final String TABLE_OUTLINE = "---------------------------------------------------";

    /** Heading format for the results table displayed in the console. */
    public static final String RESULTS_TABLE_HEADING = String.format("%-27s %-16s %-11s", "Name", "Student ID", "Marks");

    /** Heading format for the student information table displayed in the console. */
    public static final String INFO_TABLE_HEADING = String.format("%-27s %-17s %-11s", "Name", "Student ID", "Email");

    /** Styled heading for the results table displayed in the console. */
    public static final String RESULTS_HEADING = CLI_Styling.BLUE + String.format("\n" + "%35s", "🧾 Student Results Table" + "\n")
            + CLI_Styling.DOUBLE_BORDER_SHORT + CLI_Styling.RESET;

    /** Styled heading for the results table written to a file. */
    public static final String RESULTS_FILE_HEADING = "\n" + CLI_Styling.TABLE_OUTLINE + "\n" + CLI_Styling.RESULTS_TABLE_HEADING + "\n" + CLI_Styling.TABLE_OUTLINE;

    /** Styled heading for the student information table written to a file. */
    public static final String INFO_FILE_HEADING =  "\n" + CLI_Styling.DOTTED_BORDER_LONG + "\n" + CLI_Styling.INFO_TABLE_HEADING + "\n" + CLI_Styling.DOTTED_BORDER_LONG;
}