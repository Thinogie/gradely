import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        printInstructions();
        System.out.println();
        System.out.println(AnsiColors.YELLOW + "🎯 Enter Student Records:"+ AnsiColors.RESET);

        while (true) {
            String name = promptForName(scanner);
            String email = promptForEmail(scanner);
            int marks = promptForMarks(scanner);

            students.add(new Student(name, email, marks));
            System.out.println(AnsiColors.GREEN + "Student added successfully! ✅ \n" + AnsiColors.RESET);

            System.out.print(AnsiColors.YELLOW+"💡 Type '+' to add another student or 'done' to finish: " + AnsiColors.RESET);
            String choice = scanner.nextLine().trim();
            while (!choice.equalsIgnoreCase("done") && !choice.equals("+")) {
                System.out.print(AnsiColors.RED + "Invalid input. Type '+' to add another student or 'done' to finish: " + AnsiColors.RESET);
                choice = scanner.nextLine().trim();
            }
            if (choice.equalsIgnoreCase("done")) break;
        }

        if (students.isEmpty()) {
            System.out.println(AnsiColors.RED + "⚠️ No students were added to analyze." + AnsiColors.RESET);
        } else {
            ResultsAnalyzer.resultsReport(students);
        }

        scanner.close();
    }

    private static void printInstructions() {
        System.out.println(AnsiColors.BLUE + "\n╔══════════════════════════════════════════╗");
        System.out.println("║           Welcome to GRADELY             ║");
        System.out.println("║    Your Student Results Analysis System  ║");
        System.out.println("╚══════════════════════════════════════════╝" + AnsiColors.RESET);
        System.out.println();
        System.out.println(AnsiColors.YELLOW+ "💡 Instructions:"+ AnsiColors.RESET);
        System.out.println("1. Enter student details when prompted.");
        System.out.println("2. Ensure the name is at least 3 characters long.");
        System.out.println("3. Provide a valid email address");
        System.out.println("4. Marks should be an integer between 0 and 100.");
        System.out.println("5. Type '+' to add another student or 'done' to finish.");
    }

    private static String promptForName(Scanner scanner) {
        while (true) {
            System.out.print("Enter student name: " + AnsiColors.RESET);
            String name = scanner.nextLine().trim();
            if (name.length() >= 3) {
                return name;
            }
            System.out.println(AnsiColors.RED + "Name must be at least 3 characters long.\n" + AnsiColors.RESET);
        }
    }

    private static String promptForEmail(Scanner scanner) {
        while (true) {
            System.out.print("Enter email: " + AnsiColors.RESET);
            String email = scanner.nextLine().trim();
            if (email.contains("@")) {
                return email;
            }
            System.out.println(AnsiColors.RED + "Invalid email format. Please try again.\n" + AnsiColors.RESET);
        }
    }

    private static int promptForMarks(Scanner scanner) {
        while (true) {
            System.out.print("Enter marks: " + AnsiColors.RESET);
            try {
                int marks = Integer.parseInt(scanner.nextLine().trim());
                if (marks >= 0 && marks <= 100) {
                    return marks;
                }
                System.out.println(AnsiColors.RED + "Marks must be between 0 and 100.\n" + AnsiColors.RESET);
            } catch (NumberFormatException e) {
                System.out.println(AnsiColors.RED + "Invalid number. Please enter an integer.\n" +AnsiColors. RESET);
            }
        }
    }
}