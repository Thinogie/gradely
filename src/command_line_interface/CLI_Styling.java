package command_line_interface;

public class CLI_Styling {
    // ANSI escape codes for console styling
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";

    // === Layout Styles ===
    public static final String WELCOME_BANNER =
            BLUE + "\n╔══════════════════════════════════════════════╗\n" +
                    "║              Welcome to GRADELY              ║\n" +
                    "║      Your Student Results Analysis System    ║\n" +
                    "╚══════════════════════════════════════════════╝\n" + RESET;
    public static final String MENU_BORDER = "═══════════════════ MAIN MENU ══════════════════";
    public static final String ANALYSIS_BORDER = "════════════════════ ANALYZE ═══════════════════";
    public static final String DOUBLE_BORDER_SHORT = "════════════════════════════════════════════════";
    public static final String DOUBLE_BORDER_LONG = "═══════════════════════════════════════════════════════════════════════════";

    public static final String DOTTED_BORDER_SHORT = "------------------------------------------------";
    public static final String DOTTED_BORDER_LONG = "---------------------------------------------------------------------------";

    public static final String TABLE_OUTLINE = "---------------------------------------------------";

    public static final String RESULTS_TABLE_HEADING = String.format("%-27s %-16s %-11s", "Name", "Student ID", "Marks");
    public static final String INFO_TABLE_HEADING = String.format("%-27s %-17s %-11s", "Name", "Student ID", "Email");
    public static final String RESULTS_HEADING = CLI_Styling.BLUE + String.format("\n" + "%35s", "🧾 Student Results Table" + "\n")
            + CLI_Styling.DOUBLE_BORDER_SHORT + CLI_Styling.RESET;

    public static final String RESULTS_FILE_HEADING = "\n" + CLI_Styling.TABLE_OUTLINE + "\n" + CLI_Styling.RESULTS_TABLE_HEADING + "\n" + CLI_Styling.TABLE_OUTLINE ;
    public static final String INFO_FILE_HEADING =  "\n" + CLI_Styling.DOTTED_BORDER_LONG + "\n" + CLI_Styling.INFO_TABLE_HEADING + "\n" + CLI_Styling.DOTTED_BORDER_LONG;

}

