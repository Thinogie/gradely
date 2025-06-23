package command_line_interface;

import model.Student;
import service.ResultsAnalyzer;
import service.ResultsFileWriter;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static service.ResultsAnalyzer.averageMarks;
import static service.ResultsAnalyzer.sortByMarks;

/**
 * The CLI_Components class provides methods for interacting with the user
 * through a command-line interface. It includes menus for analyzing student
 * data, exporting reports, and prompting for user input.
 */
public class CLI_Components {

    public static int numTopStudents;

    /**
     * Displays the analysis menu and handles user input for various analysis options.
     * Allows users to sort, view, and export student data.
     *
     * @param students A list of Student objects to analyze.
     * @param scanner  A Scanner object for reading user input.
     */
    public static void runAnalysisMenu(List<Student> students, Scanner scanner) {
        while (true) {
            CLI_Components.printAnalysisMenu();
            System.out.print(CLI_Styling.YELLOW + "💡 Select an analysis option: " + CLI_Styling.RESET);
            String analysisChoice = scanner.nextLine().trim();

            switch (analysisChoice) {
                case "1":
                    // Sort students by name and display the results
                    ResultsAnalyzer.sortByName(students);
                    System.out.println(CLI_Styling.RESULTS_HEADING);
                    ResultsAnalyzer.displayResultsTable(students);
                    break;
                case "2":
                    // Sort students by marks and display the results
                    sortByMarks(students);
                    System.out.println(CLI_Styling.RESULTS_HEADING);
                    ResultsAnalyzer.displayResultsTable(students);
                    break;
                case "3":
                    // Display a detailed results analysis report
                    ResultsAnalyzer.resultsReport(students);
                    break;
                case "4":
                    // Display top students based on user input
                    System.out.println("Enter the number of top students to display\n" +
                            "(ex: Enter 5 to display top 5 students): ");
                    numTopStudents = scanner.nextInt();
                    ResultsAnalyzer.displayTopStudents(students, numTopStudents);
                    scanner.nextLine();
                    break;

                case "5":
                    // Display detailed information for all students
                    ResultsAnalyzer.displayStudentDetails(students);
                    break;
                case "0":
                    // Exit the program
                    System.out.println(CLI_Styling.YELLOW + "Exiting the program. Goodbye! 👋" + CLI_Styling.RESET);
                    System.exit(0);
                    break;
                default:
                    // Handle invalid input
                    System.out.println(CLI_Styling.RED + "Invalid option. Please select a valid analysis option." + CLI_Styling.RESET);
                    continue;
            }

            // Prompt the user for the next action
            System.out.println(CLI_Styling.YELLOW + "💡 What would you like to do next?\n");
            System.out.println("1. Continue with analysis\n" + "2. Export above document to a text file\n" + "0. Exit program" + CLI_Styling.RESET);
            String nextAction = scanner.nextLine().trim();

            switch (nextAction) {
                case "1":
                    // Continue: just loop again
                    break;
                case "2":
                    // Export the current analysis to a text file
                    try {
                        PrintStream originalOut = System.out;
                        System.out.println();
                        String filename = "analysis_report_" + System.currentTimeMillis() + ".txt";
                        PrintStream fileOut = new PrintStream(filename);
                        System.setOut(fileOut);
                        String heading;
                        switch (analysisChoice) {
                            case "1":
                                heading = "Student Results sorted by Students Names";
                                ResultsFileWriter.printResultsTableToFile(students, fileOut, heading);
                                break;
                            case "2":
                                heading = "Student Results sorted from Highest to Lowest Marks";
                                ResultsFileWriter.printResultsTableToFile(students, fileOut, heading);
                                break;
                            case "3":
                                heading = "Student Results Analysis Report";
                                ResultsFileWriter.printResultsAnalysisToFile(students, fileOut, ResultsAnalyzer.topStudent, averageMarks, heading);
                                break;
                            case "4":
                                heading = "Top " + numTopStudents + " Performers Report";
                                ResultsFileWriter.printTopStudentsToFile(students, fileOut, numTopStudents, heading);
                                break;
                            case "5":
                                heading = "Student Details";
                                ResultsFileWriter.printStudentInfoToFile(students, fileOut, heading);
                                break;
                            default:
                                System.out.println(CLI_Styling.RED + "Invalid Choice. No document exported." + CLI_Styling.RESET);
                                break;
                        }
                        fileOut.close();
                        System.setOut(originalOut);
                        System.out.println(CLI_Styling.GREEN + "Document exported to " + filename + ".\n" + CLI_Styling.RESET);
                    } catch (Exception e) {
                        System.out.println(CLI_Styling.RED + "Failed to export document.\n" + CLI_Styling.RESET);
                    }
                    System.out.println(CLI_Styling.YELLOW + "💡 Navigating back to analysis menu." + CLI_Styling.RESET);
                    break;
                case "0":
                    // Exit the program
                    System.out.println(CLI_Styling.YELLOW + "Exiting the program. Goodbye! 👋" + CLI_Styling.RESET);
                    System.exit(0);
                    break;
                default:
                    // Handle invalid input
                    System.out.println(CLI_Styling.RED + "Invalid option. Navigating back to analysis menu." + CLI_Styling.RESET);
            }
        }
    }

    /**
     * Prints instructions for using the application.
     */
    public static void printInstructions() {
        System.out.println(CLI_Styling.WELCOME_BANNER + "\n" + CLI_Styling.YELLOW + "💡 Instructions: \n");
        System.out.println(
                "* Select options from the menus by entering the corresponding number.\n" +
                        "* Ensure that the student details are valid.\n" +
                        "* Provide a valid email address.\n" +
                        "* You can either add students manually or read from a file.\n" + CLI_Styling.RESET);
    }

    /**
     * Prints the main menu for the application.
     */
    public static void printMainMenu() {
        System.out.println(CLI_Styling.BLUE + CLI_Styling.MENU_BORDER + CLI_Styling.RESET + "\n" + """
                1. Add student data manually
                2. Import student data from a text file
                0. Exit Program""" + "\n" + CLI_Styling.BLUE + CLI_Styling.DOUBLE_BORDER_SHORT + CLI_Styling.RESET + "\n");
    }

    /**
     * Prints the analysis menu for the application.
     */
    static void printAnalysisMenu() {
        System.out.println(CLI_Styling.BLUE + CLI_Styling.ANALYSIS_BORDER + CLI_Styling.RESET + "\n" + """
                1. Sort students by name alphabetically
                2. Sort students by marks\s
                3. View results analysis report
                4. View top students
                5. Print student details
                0. Exit Program""" + "\n" + CLI_Styling.BLUE + CLI_Styling.DOUBLE_BORDER_SHORT + CLI_Styling.RESET + "\n");
    }

    /**
     * Prompts the user to enter a student's name.
     * Ensures the name is at least 3 characters long.
     *
     * @param scanner A Scanner object for reading user input.
     * @return A valid student name.
     */
    public static String promptForName(Scanner scanner) {
        while (true) {
            System.out.print("Enter student name: " + CLI_Styling.RESET);
            String name = scanner.nextLine().trim();
            if (name.length() >= 3) return name;
            System.out.println(CLI_Styling.RED + "Name must be at least 3 characters long.\n" + CLI_Styling.RESET);
        }
    }

    /**
     * Prompts the user to enter a student's email address.
     * Ensures the email contains an "@" symbol.
     *
     * @param scanner A Scanner object for reading user input.
     * @return A valid email address.
     */
    public static String promptForEmail(Scanner scanner) {
        while (true) {
            System.out.print("Enter email: " + CLI_Styling.RESET);
            String email = scanner.nextLine().trim();
            if (email.contains("@")) return email;
            System.out.println(CLI_Styling.RED + "Invalid email format. Please try again.\n" + CLI_Styling.RESET);
        }
    }

    /**
     * Prompts the user to enter a student's marks.
     * Ensures the marks are an integer between 0 and 100.
     *
     * @param scanner A Scanner object for reading user input.
     * @return A valid integer representing the student's marks.
     */
    public static int promptForMarks(Scanner scanner) {
        while (true) {
            System.out.print("Enter marks: " + CLI_Styling.RESET);
            try {
                int marks = Integer.parseInt(scanner.nextLine().trim());
                if (marks >= 0 && marks <= 100) return marks;
                System.out.println(CLI_Styling.RED + "Marks must be between 0 and 100.\n" + CLI_Styling.RESET);
            } catch (NumberFormatException e) {
                System.out.println(CLI_Styling.RED + "Invalid number. Please enter an integer.\n" + CLI_Styling.RESET);
            }
        }
    }
}