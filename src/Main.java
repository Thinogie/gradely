import command_line_interface.CLI_Styling;
import model.Student;
import service.ResultsAnalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        printInstructions();
        System.out.println();
        System.out.println(CLI_Styling.YELLOW + "🎯 Enter Student Records:"+ CLI_Styling.RESET);

        while (true) {
            String name = promptForName(scanner);
            String email = promptForEmail(scanner);
            int marks = promptForMarks(scanner);

            students.add(new Student(name, email, marks));
            System.out.println(CLI_Styling.GREEN + "Student added successfully! ✅ \n" + CLI_Styling.RESET);

            System.out.print(CLI_Styling.YELLOW+"💡 Type '+' to add another student or 'done' to finish: " + CLI_Styling.RESET);
            String choice = scanner.nextLine().trim();
            while (!choice.equalsIgnoreCase("done") && !choice.equals("+")) {
                System.out.print(CLI_Styling.RED + "Invalid input. Type '+' to add another student or 'done' to finish: " + CLI_Styling.RESET);
                choice = scanner.nextLine().trim();
            }
            if (choice.equalsIgnoreCase("done")) break;
        }

        if (students.isEmpty()) {
            System.out.println(CLI_Styling.RED + "⚠️ No students were added to analyze." +CLI_Styling.RESET);
        } else {
            ResultsAnalyzer.resultsReport(students);
        }

        scanner.close();
    }

    private static void printInstructions() {
        System.out.println(CLI_Styling.BLUE + "\n╔══════════════════════════════════════════╗");
        System.out.println("║           Welcome to GRADELY             ║");
        System.out.println("║    Your Student Results Analysis System  ║");
        System.out.println("╚══════════════════════════════════════════╝" + CLI_Styling.RESET);
        System.out.println();
        System.out.println(CLI_Styling.YELLOW+ "💡 Instructions:"+ CLI_Styling.RESET);
        System.out.println("1. Enter student details when prompted.");
        System.out.println("2. Ensure the name is at least 3 characters long.");
        System.out.println("3. Provide a valid email address");
        System.out.println("4. Marks should be an integer between 0 and 100.");
        System.out.println("5. Type '+' to add another student or 'done' to finish.");
    }

    private static String promptForName(Scanner scanner) {
        while (true) {
            System.out.print("Enter student name: " + CLI_Styling.RESET);
            String name = scanner.nextLine().trim();
            if (name.length() >= 3) {
                return name;
            }
            System.out.println(CLI_Styling.RED + "Name must be at least 3 characters long.\n" + CLI_Styling.RESET);
        }
    }

    private static String promptForEmail(Scanner scanner) {
        while (true) {
            System.out.print("Enter email: " + CLI_Styling.RESET);
            String email = scanner.nextLine().trim();
            if (email.contains("@")) {
                return email;
            }
            System.out.println(CLI_Styling.RED + "Invalid email format. Please try again.\n" + CLI_Styling.RESET);
        }
    }

    private static int promptForMarks(Scanner scanner) {
        while (true) {
            System.out.print("Enter marks: " + CLI_Styling.RESET);
            try {
                int marks = Integer.parseInt(scanner.nextLine().trim());
                if (marks >= 0 && marks <= 100) {
                    return marks;
                }
                System.out.println(CLI_Styling.RED + "Marks must be between 0 and 100.\n" + CLI_Styling.RESET);
            } catch (NumberFormatException e) {
                System.out.println(CLI_Styling.RED + "Invalid number. Please enter an integer.\n" +CLI_Styling. RESET);
            }
        }
    }
}