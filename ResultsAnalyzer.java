import java.util.List;

public class ResultsAnalyzer {

    // Method to print the results analysis
    private static void printResultsAnalysis(List<Student> students, Student topStudent, double averageMarks) {
        System.out.println();
        System.out.println(AnsiColors.BLUE+"        RESULTS ANALYSIS REPORT");
        System.out.println("══════════════════════════════════════" + AnsiColors.RESET);
        System.out.println();

        System.out.println("🏆 Top Student: " +AnsiColors.GREEN+
                (topStudent != null ? topStudent.getName() + " (" + topStudent.getMarks() + " marks)" : "N/A")+ AnsiColors.RESET);

        System.out.println("\n🎖️ Student Results Table");
        System.out.println("-----------------------------------------------");
        System.out.printf("%-15s %-15s %-10s%n", "Name", "Student ID", "Marks");
        System.out.println("-----------------------------------------------");

        for (Student student : students) {
            System.out.printf(AnsiColors.GREEN +"%-15s %-15s %-10d%n"+AnsiColors.RESET, student.getName(), student.getStudentId(), student.getMarks());
        }

        System.out.println("-----------------------------------------------");
        System.out.println();
        System.out.println(AnsiColors.GREEN+"Total Students: " + students.size());
        System.out.printf("Class Average: %.2f%n", averageMarks);
        System.out.println(AnsiColors.RESET);
    }

    // Updated resultsReport method
    public static void resultsReport(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println(AnsiColors.RED + "⚠️ No students to analyze." + AnsiColors.RESET);
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
}
