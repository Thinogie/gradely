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
        public static final String MENU_BORDER     ="═══════════════════ MAIN MENU ══════════════════";
        public static final String ANALYSIS_BORDER ="════════════════════ ANALYZE ═══════════════════";
        public static final String BOTTOM_BORDER = "════════════════════════════════════════════════";

        public static final String DOTTED_BORDER_SHORT = "--------------------------------------------------";
        public static final String DOTTED_BORDER_LONG="------------------------------------------------------------------------";




}

