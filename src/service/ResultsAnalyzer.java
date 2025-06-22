package service;

import command_line_interface.CLI_Styling;
import model.Student;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.List;

public class ResultsAnalyzer {
    // Updated resultsReport method
    public static void resultsReport(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println(CLI_Styling.RED + "⚠️ No students to analyze." + CLI_Styling.RESET);
            return;
        }

        int totalMarks = 0;
        int highestMarks = Integer.MIN_VALUE;
        int lowestMarks = Integer.MAX_VALUE;
        Student topStudent = null;

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

        double averageMarks = (double) totalMarks / students.size();

        // Sort alphabetically for consistent report
        sortByName(students);

        // Display the analysis
        printResultsAnalysis(students, topStudent, averageMarks);
    }
    // Sort students by marks (descending)
    public static void sortByMarks(List<Student> students) {
        students.sort((s1, s2) -> Integer.compare(s2.getMarks(), s1.getMarks()));
    }
    // Sort students by name (ascending)
    public static void sortByName(List<Student> students) {
        students.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
    }
    // Sort students by id (ascending)
    public static void sortById(List<Student> students) {
        students.sort(Comparator.comparing(Student::getStudentId));
    }
    //print the top 3 students
    public static void printTopStudents(List<Student> students, int topN) {
        if (students == null || students.isEmpty()) {
            System.out.println(CLI_Styling.RED + "⚠️ No students to display." + CLI_Styling.RESET);
            return;
        }
        // Sort by marks in descending order
        sortByMarks(students);
        System.out.println(CLI_Styling.BLUE+"\n           🎖️ Top " + topN + " Students:");
        System.out.println(CLI_Styling.BOTTOM_BORDER + CLI_Styling.RESET);
        System.out.printf("%-25s %-15s %-15s%n", "Name", "Student ID", "Marks");
        System.out.println(CLI_Styling.DOTTED_BORDER_SHORT);

        for (int i = 0; i < Math.min(topN, students.size()); i++) {
            Student student = students.get(i);
            System.out.printf(CLI_Styling.GREEN +"%-25s %-15s %-15d%n"+ CLI_Styling.RESET, student.getName(), student.getStudentId(), student.getMarks());
        }
        System.out.println(CLI_Styling.DOTTED_BORDER_SHORT);
    }


    public static void printStudentDetails(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println(CLI_Styling.RED + "⚠️ No students to display." + CLI_Styling.RESET);
            return;
        }
        sortById(students);
        System.out.println(CLI_Styling.BLUE+"\n           🧑‍🎓 Student Details:");
        System.out.println(CLI_Styling.BOTTOM_BORDER + CLI_Styling.RESET);
        System.out.printf("%-25s %-15s %-30s%n", "Name", "Student ID", "Email");
        System.out.println(CLI_Styling.DOTTED_BORDER_LONG);

        for (Student student : students) {
            System.out.printf(CLI_Styling.GREEN +"%-25s %-15s %-30s%n"+ CLI_Styling.RESET, student.getName(), student.getStudentId(), student.getEmail());
        }
        System.out.println(CLI_Styling.DOTTED_BORDER_LONG);

    }
    // Method to print the results analysis
    private static void printResultsAnalysis(List<Student> students, Student topStudent, double averageMarks) {
        System.out.println();
        System.out.println(CLI_Styling.BLUE+"        RESULTS ANALYSIS REPORT");
        System.out.println(CLI_Styling.BOTTOM_BORDER + CLI_Styling.RESET);

        System.out.println();

        System.out.println("🏆 Top Student: " + CLI_Styling.GREEN+
                (topStudent != null ? topStudent.getName() + " (" + topStudent.getMarks() + " marks)" : "N/A")+ CLI_Styling.RESET);

        printResultsTable(students);
        System.out.println(CLI_Styling.GREEN+"Total Students: " + students.size());
        System.out.printf("Class Average: %.2f%n", averageMarks);
        System.out.println(CLI_Styling.RESET);
    }


    public static void printResultsTable(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println(CLI_Styling.RED + "⚠️ No students to display." + CLI_Styling.RESET);
            return;
        }

        System.out.println("\n🧾 Student Results Table");
        System.out.println(CLI_Styling.DOTTED_BORDER_SHORT);
        System.out.printf("%-25s %-15s %-15s%n", "Name", "Student ID", "Marks");
        System.out.println(CLI_Styling.DOTTED_BORDER_SHORT);

        for (Student student : students) {
            System.out.printf(CLI_Styling.GREEN +"%-25s %-15s %-15d%n"+ CLI_Styling.RESET, student.getName(), student.getStudentId(), student.getMarks());
        }
        System.out.println(CLI_Styling.DOTTED_BORDER_SHORT);
        System.out.println();
    }


}
