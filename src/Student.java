import java.util.ArrayList;

/**
 * Student.java
 *
 * INHERITANCE DEMONSTRATION
 * Student class extends User class - this is an "is-a" relationship
 * A Student IS-A User with additional student-specific properties
 *
 * OOP Concepts demonstrated:
 * - Inheritance: extends User class
 * - Polymorphism: Overrides displayInfo() and getRole() methods
 * - Collections: Uses ArrayList to store enrolled courses
 */
public class Student extends User {

    // Student-specific fields
    private String studentNumber;
    private int enrollmentYear;

    // COLLECTIONS: ArrayList to store courses the student is enrolled in
    private ArrayList<Course> enrolledCourses;

    /**
     * Constructor for Student class
     * Calls parent constructor using super() keyword
     *
     * @param name Student's full name
     * @param email Student's email address
     * @param password Student's password
     * @param studentNumber Unique student number
     * @param enrollmentYear Year of enrollment
     */
    public Student(String name, String email, String password,
                   String studentNumber, int enrollmentYear) {
        // Call parent class constructor
        super(name, email, password);
        this.studentNumber = studentNumber;
        this.enrollmentYear = enrollmentYear;
        this.enrolledCourses = new ArrayList<>();
    }

    // METHOD OVERRIDING: Provides Student-specific implementation
    // The @Override annotation ensures we're actually overriding a parent method
    @Override
    public void displayInfo() {
        System.out.println("========== STUDENT INFORMATION ==========");
        System.out.println("User ID: " + getId());
        System.out.println("Student Number: " + studentNumber);
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Enrollment Year: " + enrollmentYear);
        System.out.println("Enrolled Courses: " + enrolledCourses.size());
        System.out.println("==========================================");
    }

    // METHOD OVERRIDING: Returns "Student" as the role
    @Override
    public String getRole() {
        return "Student";
    }

    // Method to enroll in a course
    public void enrollInCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            System.out.println(getName() + " enrolled in " + course.getCourseName());
        } else {
            System.out.println(getName() + " is already enrolled in " + course.getCourseName());
        }
    }

    // Method to drop a course
    public void dropCourse(Course course) {
        if (enrolledCourses.remove(course)) {
            System.out.println(getName() + " dropped " + course.getCourseName());
        } else {
            System.out.println(getName() + " is not enrolled in " + course.getCourseName());
        }
    }

    // Getter methods for Student-specific fields
    public String getStudentNumber() {
        return studentNumber;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    // Setter methods
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }
}