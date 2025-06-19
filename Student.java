import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Student {
    // Fields to store student information
    private final String name; // Student's name
    private final String studentId; // Unique student ID
    private final String email; // Student's email address
    private final int marks; // Marks obtained by the student

    // Static Set to keep track of all generated student IDs to ensure uniqueness
    private static final Set<String> generatedIds = new HashSet<>();

    // Constructor to initialize a Student object
    public Student(String name, String email, int marks) {
        this.name = name; // Assign the name
        this.email = email; // Assign the email
        this.marks = marks; // Assign the marks
        this.studentId = generateUniqueStudentId(); // Generate a unique student ID
    }

    // Method to generate a unique student ID
    private String generateUniqueStudentId() {
        Random random = new Random(); // Random object for generating random numbers
        String newId; // Variable to store the new ID
        do {
            int randomDigits = random.nextInt(10000); // Generate a random number between 0 and 9999
            newId = String.format("S%04d", randomDigits); // Format the number with leading zeros and prefix 'S'
        } while (generatedIds.contains(newId)); // Repeat until a unique ID is generated
        generatedIds.add(newId); // Add the new ID to the set of generated IDs
        return newId; // Return the unique ID
    }

    // Getter method for the student's name
    public String getName() {
        return name;
    }

    // Getter method for the student's email
    public String getEmail() {
        return email;
    }

    // Getter method for the student's marks
    public int getMarks() {
        return marks;
    }

    // Getter method for the student's unique ID
    public String getStudentId() {
        return studentId;
    }

    // Override the toString method to provide a string representation of the Student object
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", studentId='" + studentId + '\'' +
                ", marks=" + marks +
                '}';
    }
}