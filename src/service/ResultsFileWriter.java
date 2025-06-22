package service;

import command_line_interface.CLI_Styling;
import model.Student;

import java.io.PrintStream;
import java.util.List;

import static service.ResultsAnalyzer.sortByMarks;

public class ResultsFileWriter {
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

    public static void printStudentInfoToFile(List<Student> students, PrintStream writer, String heading) {
        if (students == null || students.isEmpty()) {
            writer.println("⚠️ No students to display.");
            return;
        }
        writer.println(heading +CLI_Styling.INFO_FILE_HEADING);

        for (Student student : students) {
            writer.printf("%-25s   %-15s   %-30s%n", student.getName(), student.getStudentId(), student.getEmail());
        }
        writer.println(CLI_Styling.DOTTED_BORDER_LONG);
    }
    public static void printResultsAnalysisToFile(List<Student> students, PrintStream writer, Student topStudent, double averageMarks, String heading) {
        writer.println(heading+"\n");
        writer.println("Top Student: " +
                (topStudent != null ? topStudent.getName() + " (" + topStudent.getMarks() + " marks)" : "N/A") );
        writer.printf("Class Average: %.2f%n", averageMarks);
        writer.println("Total Students: " + students.size());
        printResultsTableToFile(students, writer, "");
        writer.println();
    }
}
