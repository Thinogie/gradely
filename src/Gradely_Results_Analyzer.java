import command_line_interface.CLI_Components;
import command_line_interface.CLI_Styling;
import model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gradely_Results_Analyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        CLI_Components.printInstructions();
        CLI_Components.printMainMenu();

        boolean running = true;
        while (running) {
            System.out.print(CLI_Styling.YELLOW + "💡 Select an option : " + CLI_Styling.RESET);
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    // Add a student manually
                    do {
                        String name = CLI_Components.promptForName(scanner);
                        String email = CLI_Components.promptForEmail(scanner);
                        int marks = CLI_Components.promptForMarks(scanner);

                        students.add(new Student(name, email, marks));
                        System.out.println(CLI_Styling.GREEN + "Student added successfully! ✅ \n" + CLI_Styling.RESET);

                        System.out.print(CLI_Styling.YELLOW + "💡 Type '+' to add another student or 'done' to finish: " + CLI_Styling.RESET);
                        String choice = scanner.nextLine().trim();
                        while (!choice.equalsIgnoreCase("done") && !choice.equals("+")) {
                            System.out.print(CLI_Styling.RED + "Invalid input. Type '+' to add another student or 'done' to finish: " + CLI_Styling.RESET);
                            choice = scanner.nextLine().trim();
                        }
                        if (choice.equalsIgnoreCase("done")) break;

                    } while (true);
                    // Show analysis menu after adding students
                    CLI_Components.runAnalysisMenu( students, scanner);
                case "2":
                    System.out.print("Enter the file path to read student data: " + CLI_Styling.RESET);
                    String filePath = scanner.nextLine().trim();

                    try {
                        List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(filePath));
                        int successCount = 0;

                        for (String line : lines) {
                            if (line.trim().isEmpty()) continue; // Skip empty lines

                            String[] parts = line.split(",");

                            if (parts.length != 3) {
                                System.out.println(CLI_Styling.RED + "⚠️ Invalid format: " + line + CLI_Styling.RESET);
                                continue;
                            }

                            String name = parts[0].trim();
                            String email = parts[1].trim();
                            String marksStr = parts[2].trim();

                            if (name.length() < 3 || !email.contains("@") || !email.contains(".")) {
                                System.out.println(CLI_Styling.RED + "⚠️ Invalid name/email: " + line + CLI_Styling.RESET);
                                continue;
                            }

                            try {
                                int marks = Integer.parseInt(marksStr);
                                if (marks < 0 || marks > 100) {
                                    System.out.println(CLI_Styling.RED + "⚠️ Marks must be between 0 and 100: " + line + CLI_Styling.RESET);
                                    continue;
                                }

                                students.add(new Student(name, email, marks));
                                successCount++;
                            } catch (NumberFormatException e) {
                                System.out.println(CLI_Styling.RED + "Invalid number for marks: " + line + CLI_Styling.RESET);
                            }
                        }

                        if (successCount > 0) {
                            System.out.println(CLI_Styling.GREEN + "✅ Successfully loaded " + successCount + " students from file!\n" + CLI_Styling.RESET);
                            CLI_Components.runAnalysisMenu(students, scanner);
                        } else {
                            System.out.println(CLI_Styling.RED + "No valid student records found in file." + CLI_Styling.RESET);
                        }

                    } catch (java.io.IOException e) {
                        System.out.println(CLI_Styling.RED + "File not found or unreadable: " + e.getMessage() + CLI_Styling.RESET);
                    }

                    break;

                case "0":
                    System.out.println(CLI_Styling.YELLOW + "\nExiting the program. Goodbye! 👋" + CLI_Styling.RESET);
                    running = false;
                    break;
                default:
                    System.out.println(CLI_Styling.RED + "Invalid option. Please select 1 or 2 to continue." + CLI_Styling.RESET);
            }
        }
        scanner.close();
    }

}