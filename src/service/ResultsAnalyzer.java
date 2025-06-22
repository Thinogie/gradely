package service;

import command_line_interface.CLI_Styling;
import model.Student;

import java.util.Comparator;
import java.util.List;

/**
 * The ResultsAnalyzer class provides methods for analyzing and displaying
 * student results, including sorting, generating reports, and displaying
 * detailed information.
 */
public class ResultsAnalyzer {
    // Static fields to store analysis data
    public static int totalMarks = 0; // Total marks of all students
    public static int highestMarks = Integer.MIN_VALUE; // Highest marks among students
    public static int lowestMarks = Integer.MAX_VALUE; // Lowest marks among students
    public static Student topStudent = null; // The student with the highest marks
    public static double averageMarks = 0; // Average marks of the class

    /**
     * Generates a results analysis report for a list of students.
     * Calculates total, highest, lowest, and average marks, identifies the top student,
     * sorts students by name, and displays the analysis.
     *
     * @param students A list of Student objects to analyze.
     */
    public static void resultsReport(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println(CLI_Styling.RED + "⚠️ No students to analyze." + CLI_Styling.RESET);
            return;
        }

        for (Student student : students) {
            int marks = student.getMarks();
            totalMarks += marks;

            if (marks > highestMarks) {
                highestMarks = marks;
                topStudent = student;
            }
            if (marks < lowestMarks) {
                lowestMarks = marks;
            }
        }

        averageMarks = (double) totalMarks / students.size();

        sortByName(students);

        displayResultsAnalysis(students, topStudent, averageMarks);
    }

    /**
     * Sorts a list of students by their marks in descending order.
     *
     * @param students A list of Student objects to sort.
     */
    public static void sortByMarks(List<Student> students) {
        students.sort((s1, s2) -> Integer.compare(s2.getMarks(), s1.getMarks()));
    }

    /**
     * Sorts a list of students by their names in alphabetical order.
     *
     * @param students A list of Student objects to sort.
     */
    public static void sortByName(List<Student> students) {
        students.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
    }

    /**
     * Sorts a list of students by their unique IDs in ascending order.
     *
     * @param students A list of Student objects to sort.
     */
    public static void sortById(List<Student> students) {
        students.sort(Comparator.comparing(Student::getStudentId));
    }

    /**
     * Displays the top N students based on their marks.
     *
     * @param students A list of Student objects to analyze.
     * @param topN     The number of top students to display.
     */
    public static void displayTopStudents(List<Student> students, int topN) {
        if (students == null || students.isEmpty()) {
            System.out.println(CLI_Styling.RED + "⚠️ No students to display." + CLI_Styling.RESET);
            return;
        }
        sortByMarks(students);
        System.out.printf(String.format(CLI_Styling.BLUE + "\n%33s\n" + CLI_Styling.DOUBLE_BORDER_SHORT + CLI_Styling.RESET, "🎖️ Top " + topN + " Students:") + "\n");
        System.out.printf("%-25s %-15s %-15s%n", "Name", "Student ID", "Marks");
        System.out.println(CLI_Styling.DOTTED_BORDER_SHORT);

        for (int i = 0; i < Math.min(topN, students.size()); i++) {
            Student student = students.get(i);
            System.out.printf(CLI_Styling.GREEN + "%-25s %-15s %-15d%n" + CLI_Styling.RESET, student.getName(), student.getStudentId(), student.getMarks());
        }
        System.out.println(CLI_Styling.DOTTED_BORDER_SHORT);
    }

    /**
     * Displays detailed information for all students, including their names,
     * IDs, and email addresses.
     *
     * @param students A list of Student objects to display.
     */
    public static void displayStudentDetails(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println(CLI_Styling.RED + "⚠️ No students to display." + CLI_Styling.RESET);
            return;
        }
        sortById(students);
        System.out.printf(String.format(CLI_Styling.BLUE + "\n%50s\n" + CLI_Styling.DOUBLE_BORDER_LONG + CLI_Styling.RESET, "🧑‍🎓 Student Details:") + "\n");
        System.out.printf("%-25s %-15s %-30s%n", "Name", "Student ID", "Email");
        System.out.println(CLI_Styling.DOTTED_BORDER_LONG);

        for (Student student : students) {
            System.out.printf(CLI_Styling.GREEN + "%-25s %-15s %-30s%n" + CLI_Styling.RESET, student.getName(), student.getStudentId(), student.getEmail());
        }
        System.out.println(CLI_Styling.DOTTED_BORDER_LONG);
    }

    /**
     * Displays a detailed results analysis report, including the top student,
     * total students, class average, and a results table.
     *
     * @param students     A list of Student objects to analyze.
     * @param topStudent   The student with the highest marks.
     * @param averageMarks The average marks of the class.
     */
    private static void displayResultsAnalysis(List<Student> students, Student topStudent, double averageMarks) {
        System.out.println();
        System.out.printf(String.format(CLI_Styling.BLUE + "%37s\n" + CLI_Styling.DOUBLE_BORDER_SHORT + CLI_Styling.RESET + "\n", "🧾 RESULTS ANALYSIS REPORT") + "\n");
        System.out.println(CLI_Styling.GREEN + "Top Student: " +
                (topStudent != null ? topStudent.getName() + " (" + topStudent.getMarks() + " marks)" : "N/A") + CLI_Styling.RESET);
        System.out.println(CLI_Styling.GREEN + "Total Students: " + students.size());
        System.out.printf("Class Average: %.2f%n", averageMarks);
        System.out.println();
        displayResultsTable(students);
        System.out.println(CLI_Styling.RESET);
    }

    /**
     * Displays a table of student results, including their names, IDs, and marks.
     *
     * @param students A list of Student objects to display.
     */
    public static void displayResultsTable(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println(CLI_Styling.RED + "⚠️ No students to display." + CLI_Styling.RESET);
            return;
        }
        System.out.printf("%-25s %-15s %-15s%n", "Name", "Student ID", "Marks");
        System.out.println(CLI_Styling.DOTTED_BORDER_SHORT);
        for (Student student : students) {
            System.out.printf(CLI_Styling.GREEN + "%-25s %-15s %-15d%n" + CLI_Styling.RESET, student.getName(), student.getStudentId(), student.getMarks());
        }
        System.out.println(CLI_Styling.DOTTED_BORDER_SHORT);
        System.out.println();
    }
}