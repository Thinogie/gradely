package model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * The Student class represents a student with a name, email, unique ID, and marks.
 * It ensures that each student has a unique ID and provides methods to access
 * the student's details.
 */
public class Student {
    // Fields to store student information
    private final String name; // Student's name
    private final String studentId; // Unique student ID
    private final String email; // Student's email address
    private final int marks; // Marks obtained by the student

    // Static Set to keep track of all generated student IDs to ensure uniqueness
    private static final Set<String> generatedIds = new HashSet<>();

    /**
     * Constructs a Student object with the specified name, email, and marks.
     * A unique student ID is automatically generated.
     *
     * @param name  The name of the student.
     * @param email The email address of the student.
     * @param marks The marks obtained by the student.
     */
    public Student(String name, String email, int marks) {
        this.name = name; // Assign the name
        this.email = email; // Assign the email
        this.marks = marks; // Assign the marks
        this.studentId = generateUniqueStudentId(); // Generate a unique student ID
    }

    /**
     * Generates a unique student ID in the format "SXXXX", where XXXX is a
     * random 4-digit number. Ensures that the ID is not already in use.
     *
     * @return A unique student ID.
     */
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

    /**
     * Gets the name of the student.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email address of the student.
     *
     * @return The student's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the marks obtained by the student.
     *
     * @return The student's marks.
     */
    public int getMarks() {
        return marks;
    }

    /**
     * Gets the unique ID of the student.
     *
     * @return The student's unique ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns a string representation of the Student object, including
     * the name, email, student ID, and marks.
     *
     * @return A string representation of the student.
     */
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