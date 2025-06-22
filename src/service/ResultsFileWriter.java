package service;

import command_line_interface.CLI_Styling;
import model.Student;

import java.io.PrintStream;
import java.util.List;

import static service.ResultsAnalyzer.sortByMarks;

/**
 * The ResultsFileWriter class provides methods for exporting student data
 * and analysis reports to text files. It includes functionality to write
 * results tables, top students, student details, and analysis summaries.
 */
public class ResultsFileWriter {

    /**
     * Writes a table of student results to a file, including their names, IDs, and marks.
     *
     * @param students A list of Student objects to write to the file.
     * @param writer   A PrintStream object for writing to the file.
     * @param heading  A heading to include at the top of the results table.
     */
    public static void printResultsTableToFile(List<Student> students, PrintStream writer, String heading) {
        if (students == null || students.isEmpty()) {
            writer.println("⚠️ No students to display.");
            return;
        }
        writer.println(heading + CLI_Styling.RESULTS_FILE_HEADING);

        for (Student student : students) {
            writer.printf("%-25s   %-14s   %-10d  \n", student.getName(), student.getStudentId(), student.getMarks());
        }
        writer.println(CLI_Styling.TABLE_OUTLINE);
    }

    /**
     * Writes the top N students to a file, sorted by their marks in descending order.
     *
     * @param students A list of Student objects to analyze.
     * @param writer   A PrintStream object for writing to the file.
     * @param topN     The number of top students to include in the file.
     * @param heading  A heading to include at the top of the results table.
     */
    public static void printTopStudentsToFile(List<Student> students, PrintStream writer, int topN, String heading) {
        if (students == null || students.isEmpty()) {
            writer.println("⚠️ No students to display.");
            return;
        }
        // Sort by marks in descending order
        sortByMarks(students);
        writer.println(heading + CLI_Styling.RESULTS_FILE_HEADING);

        for (int i = 0; i < Math.min(topN, students.size()); i++) {
            Student student = students.get(i);
            writer.printf("%-25s   %-15s   %-15d%n", student.getName(), student.getStudentId(), student.getMarks());
        }
        writer.println(CLI_Styling.TABLE_OUTLINE);
    }

    /**
     * Writes detailed student information to a file, including their names, IDs, and email addresses.
     *
     * @param students A list of Student objects to write to the file.
     * @param writer   A PrintStream object for writing to the file.
     * @param heading  A heading to include at the top of the student details section.
     */
    public static void printStudentInfoToFile(List<Student> students, PrintStream writer, String heading) {
        if (students == null || students.isEmpty()) {
            writer.println("⚠️ No students to display.");
            return;
        }
        writer.println(heading + CLI_Styling.INFO_FILE_HEADING);

        for (Student student : students) {
            writer.printf("%-25s   %-15s   %-30s%n", student.getName(), student.getStudentId(), student.getEmail());
        }
        writer.println(CLI_Styling.DOTTED_BORDER_LONG);
    }

    /**
     * Writes a detailed results analysis report to a file, including the top student,
     * class average, total students, and a results table.
     *
     * @param students     A list of Student objects to analyze.
     * @param writer       A PrintStream object for writing to the file.
     * @param topStudent   The student with the highest marks.
     * @param averageMarks The average marks of the class.
     * @param heading      A heading to include at the top of the analysis report.
     */
    public static void printResultsAnalysisToFile(List<Student> students, PrintStream writer, Student topStudent, double averageMarks, String heading) {
        writer.println(heading + "\n");
        writer.println("Top Student: " +
                (topStudent != null ? topStudent.getName() + " (" + topStudent.getMarks() + " marks)" : "N/A"));
        writer.printf("Class Average: %.2f%n", averageMarks);
        writer.println("Total Students: " + students.size());
        printResultsTableToFile(students, writer, "");
        writer.println();
    }
}