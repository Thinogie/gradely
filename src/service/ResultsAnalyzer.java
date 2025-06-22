package service;

import command_line_interface.CLI_Styling;
import model.Student;

import java.util.Comparator;
import java.util.List;

public class ResultsAnalyzer {
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

        sortByName(students);

        displayResultsAnalysis(students, topStudent, averageMarks);
    }

    public static void sortByMarks(List<Student> students) {
        students.sort((s1, s2) -> Integer.compare(s2.getMarks(), s1.getMarks()));
    }

    public static void sortByName(List<Student> students) {
        students.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
    }

    public static void sortById(List<Student> students) {
        students.sort(Comparator.comparing(Student::getStudentId));
    }

    public static void displayTopStudents(List<Student> students, int topN) {
        if (students == null || students.isEmpty()) {
            System.out.println(CLI_Styling.RED + "⚠️ No students to display." + CLI_Styling.RESET);
            return;
        }
        sortByMarks(students);
        System.out.printf(String.format(CLI_Styling.BLUE + "\n%33s\n" + CLI_Styling.DOUBLE_BORDER_SHORT + CLI_Styling.RESET, "🎖️ Top " + topN + " Students:")+"\n");
        System.out.printf("%-25s %-15s %-15s%n", "Name", "Student ID", "Marks");
        System.out.println(CLI_Styling.DOTTED_BORDER_SHORT);

        for (int i = 0; i < Math.min(topN, students.size()); i++) {
            Student student = students.get(i);
            System.out.printf(CLI_Styling.GREEN + "%-25s %-15s %-15d%n" + CLI_Styling.RESET, student.getName(), student.getStudentId(), student.getMarks());
        }
        System.out.println(CLI_Styling.DOTTED_BORDER_SHORT);
    }


    public static void displayStudentDetails(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println(CLI_Styling.RED + "⚠️ No students to display." + CLI_Styling.RESET);
            return;
        }
        sortById(students);
        System.out.printf(String.format(CLI_Styling.BLUE + "\n%50s\n" + CLI_Styling.DOUBLE_BORDER_LONG + CLI_Styling.RESET, "🧑‍🎓 Student Details:")+"\n");
        System.out.printf("%-25s %-15s %-30s%n", "Name", "Student ID", "Email");
        System.out.println(CLI_Styling.DOTTED_BORDER_LONG);

        for (Student student : students) {
            System.out.printf(CLI_Styling.GREEN + "%-25s %-15s %-30s%n" + CLI_Styling.RESET, student.getName(), student.getStudentId(), student.getEmail());
        }
        System.out.println(CLI_Styling.DOTTED_BORDER_LONG);

    }

    private static void displayResultsAnalysis(List<Student> students, Student topStudent, double averageMarks) {
        System.out.println();
        System.out.printf(String.format(CLI_Styling.BLUE + "%37s\n" + CLI_Styling.DOUBLE_BORDER_SHORT + CLI_Styling.RESET + "\n", "🧾 RESULTS ANALYSIS REPORT"));
        System.out.println("🏆 Top Student: " + CLI_Styling.GREEN +
                (topStudent != null ? topStudent.getName() + " (" + topStudent.getMarks() + " marks)" : "N/A") + CLI_Styling.RESET+"\n");
        displayResultsTable(students);
        System.out.println(CLI_Styling.GREEN + "Total Students: " + students.size());
        System.out.printf("Class Average: %.2f%n", averageMarks);
        System.out.println(CLI_Styling.RESET);
    }

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
