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
        writer.println(heading + "\n"+ CLI_Styling.TABLE_OUTLINE+"\n"+CLI_Styling.RESULTS_TABLE_HEADING+"\n"+CLI_Styling.TABLE_OUTLINE);

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
        writer.println(heading + "\n"+ CLI_Styling.TABLE_OUTLINE+"\n"+CLI_Styling.RESULTS_TABLE_HEADING+"\n"+CLI_Styling.TABLE_OUTLINE);

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
        writer.println(heading + "\n"+ CLI_Styling.DOTTED_BORDER_LONG + "\n"+ CLI_Styling.INFO_TABLE_HEADING + "\n"+ CLI_Styling.DOTTED_BORDER_LONG);

        for (Student student : students) {
            writer.printf("%-25s   %-15s   %-30s%n", student.getName(), student.getStudentId(), student.getEmail());
        }
        writer.println(CLI_Styling.DOTTED_BORDER_LONG);
    }
}
