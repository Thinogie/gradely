package command_line_interface;

import model.Student;
import service.ResultsAnalyzer;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class CLI_Components {

    public static void runAnalysisMenu(List<Student> students, Scanner scanner) {
        while (true) {
            CLI_Components.analysisMenu();
            System.out.print(CLI_Styling.YELLOW + "💡 Select an analysis option: " + CLI_Styling.RESET);
            String analysisChoice = scanner.nextLine().trim();

            switch (analysisChoice) {
                case "1":
                    System.out.println(CLI_Styling.GREEN + "Sorted by name." + CLI_Styling.RESET);
                    ResultsAnalyzer.sortByName(students);
                    ResultsAnalyzer.printResultsTable(students);
                    break;
                case "2":
                    System.out.println(CLI_Styling.GREEN + "Sorted by marks." + CLI_Styling.RESET);
                    ResultsAnalyzer.sortByMarks(students);
                    ResultsAnalyzer.printResultsTable(students);
                    break;
                case "3":
                    System.out.println(CLI_Styling.GREEN + "Results analysis report generated." + CLI_Styling.RESET);
                    ResultsAnalyzer.resultsReport(students);
                    break;
                case "4":
                    System.out.println(CLI_Styling.GREEN + "Top students displayed." + CLI_Styling.RESET);
                    ResultsAnalyzer.printTopStudents(students, 3);
                    break;
                case "5":
                    System.out.println(CLI_Styling.GREEN + "Printed Student Details" + CLI_Styling.RESET);
                    ResultsAnalyzer.printStudentDetails(students);
                    break;
                case "0":
                    System.out.println(CLI_Styling.YELLOW + "Exiting the program. Goodbye! 👋" + CLI_Styling.RESET);
                    System.exit(0);
                    break;
                default:
                    System.out.println(CLI_Styling.RED + "Invalid option. Please select a valid analysis option." + CLI_Styling.RESET);
                    continue;
            }

            System.out.println(CLI_Styling.YELLOW + "\n💡 What would you like to do next?\n");
            System.out.println("1. Continue with analysis");
            System.out.println("2. Export above document to a text file");
            System.out.println("0. Exit program" + CLI_Styling.RESET);
            String nextAction = scanner.nextLine().trim();

            switch (nextAction) {
                case "1":
                    // Continue: just loop again
                    break;
                case "2":
                    try {
                        PrintStream originalOut = System.out;
                        String filename = "analysis_report_" + System.currentTimeMillis() + ".txt";
                        PrintStream fileOut = new PrintStream(filename);
                        System.setOut(fileOut);
                        String heading;
                        switch (analysisChoice) {
                            case "1" :
                                heading = "Results sorted by Students Names";
                                printStudentInfoToFile(students, fileOut, heading);
                                break;
                            case "2":
                                heading = "Results sorted from Highest to Lowest Marks";
                                printResultsTableToFile(students, fileOut, heading);
                                break;
                            case "3" : // average and top student not printed in txt file
                                heading = "Results Analysis Report";
                                printResultsTableToFile(students, fileOut, heading);
                                break;
                            case "4" : // all students printed not top performers
                                heading = "Top Performers Report";
                                printResultsTableToFile(students, fileOut, heading);
                                break;
                            default :printStudentInfoToFile(students, fileOut, "Student Details");
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
                    System.out.println(CLI_Styling.YELLOW + "Exiting the program. Goodbye! 👋" + CLI_Styling.RESET);
                    System.exit(0);
                    break;
                default:
                    System.out.println(CLI_Styling.RED + "Invalid option. Navigating back to analysis menu." + CLI_Styling.RESET);
            }
        }
    }
    public static void printInstructions() {
        System.out.println(CLI_Styling.WELCOME_BANNER);
        System.out.println(CLI_Styling.YELLOW + "💡 Instructions:\n" + CLI_Styling.RESET);
        System.out.println("* Select options from the menus by entering the corresponding number.");
        System.out.println("* Ensure that the student details are valid:");
        System.out.println("* Provide a valid email address.");
        System.out.println("* You can either add students manually or read from a file.");
    }

    public static void printMainMenu() {
        System.out.println();
        System.out.println(CLI_Styling.BLUE + CLI_Styling.MENU_BORDER + CLI_Styling.RESET);
        System.out.println("1. Add student data manually");
        System.out.println("2. Import student data from a text file");
        System.out.println("0. Exit Program");
        System.out.println(CLI_Styling.BLUE + CLI_Styling.BOTTOM_BORDER + CLI_Styling.RESET);
    }

    static void analysisMenu() {
        System.out.println();
        System.out.println(CLI_Styling.BLUE + CLI_Styling.ANALYSIS_BORDER + CLI_Styling.RESET);
        System.out.println("1. Sort students by name alphabetically");
        System.out.println("2. Sort students by marks");
        System.out.println("3. View results analysis report");
        System.out.println("4. View top students");
        System.out.println("5. Print student details");
        System.out.println("0. Exit Program");
        System.out.println(CLI_Styling.BLUE + CLI_Styling.BOTTOM_BORDER + CLI_Styling.RESET);
    }
    public static void printStudentInfoToFile(List<Student> students, PrintStream writer, String heading) {
        if (students == null || students.isEmpty()) {
            writer.println("⚠️ No students to display.");
            return;
        }

        writer.println("\n" + heading);
        writer.println(CLI_Styling.DOTTED_BORDER_LONG);
        writer.printf("%-25s %-15s %-30s%n", "Name", "Student ID", "Email");
        writer.println(CLI_Styling.DOTTED_BORDER_LONG);

        for (Student student : students) {
            writer.printf("%-25s %-15s %-30s%n", student.getName(), student.getStudentId(), student.getEmail());
        }
        writer.println(CLI_Styling.DOTTED_BORDER_LONG);
        writer.println();
    }
    public static void printResultsTableToFile(List<Student> students, PrintStream writer, String heading) {
        if (students == null || students.isEmpty()) {
            writer.println("⚠️ No students to display.");
            return;
        }
        writer.println("\n" + heading);
        writer.println(CLI_Styling.DOTTED_BORDER_SHORT);
        writer.printf("%-25s %-15s %-15s%n", "Name", "Student ID", "Marks");
        writer.println(CLI_Styling.DOTTED_BORDER_SHORT);
        for (Student student : students) {
            writer.printf("%-25s %-15s %-15d%n", student.getName(), student.getStudentId(), student.getMarks());
        }
        writer.println(CLI_Styling.DOTTED_BORDER_SHORT);
        writer.println();
    }


    public static String promptForName(Scanner scanner) {
        while (true) {
            System.out.print("Enter student name: " + CLI_Styling.RESET);
            String name = scanner.nextLine().trim();
            if (name.length() >= 3) return name;
            System.out.println(CLI_Styling.RED + "Name must be at least 3 characters long.\n" + CLI_Styling.RESET);
        }
    }

    public static String promptForEmail(Scanner scanner) {
        while (true) {
            System.out.print("Enter email: " + CLI_Styling.RESET);
            String email = scanner.nextLine().trim();
            if (email.contains("@")) return email;
            System.out.println(CLI_Styling.RED + "Invalid email format. Please try again.\n" + CLI_Styling.RESET);
        }
    }

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