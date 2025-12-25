import java.util.ArrayList;

/**
 * Course.java
 *
 * This class represents a course in the school system.
 * It demonstrates composition - a Course HAS-A Teacher and HAS students
 *
 * OOP Concepts demonstrated:
 * - Encapsulation: Private fields with public getters/setters
 * - Composition: Course contains references to Teacher and Students
 * - Collections: Uses ArrayList to store enrolled students
 * - Static members: courseCounter for generating unique course codes
 */
public class Course {

    // ENCAPSULATION: Private fields
    private String courseCode;
    private String courseName;
    private int credits;
    private Teacher teacher;  // COMPOSITION: Course HAS-A Teacher

    // COLLECTIONS: ArrayList to store enrolled students
    private ArrayList<Student> enrolledStudents;

    // STATIC VARIABLE: Counter for generating unique course codes
    private static int courseCounter = 100;

    /**
     * Constructor for Course class
     *
     * @param courseName Name of the course
     * @param credits Number of credits
     */
    public Course(String courseName, int credits) {
        this.courseCode = "CS" + courseCounter++;  // Auto-generate course code
        this.courseName = courseName;
        this.credits = credits;
        this.teacher = null;  // No teacher assigned initially
        this.enrolledStudents = new ArrayList<>();
    }

    // METHOD OVERLOADING: Constructor with course code
    public Course(String courseCode, String courseName, int credits) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.teacher = null;
        this.enrolledStudents = new ArrayList<>();
    }

    // STATIC METHOD: Get the next course number
    public static int getNextCourseNumber() {
        return courseCounter;
    }

    // Method to assign a teacher to this course
    public void assignTeacher(Teacher teacher) {
        this.teacher = teacher;
        teacher.assignCourse(this);  // Add this course to teacher's list
        System.out.println("Teacher " + teacher.getName() + " assigned to " + courseName);
    }

    // Method to enroll a student in this course
    public void enrollStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            student.enrollInCourse(this);  // Add this course to student's list
        } else {
            System.out.println(student.getName() + " is already enrolled in " + courseName);
        }
    }

    // Method to remove a student from this course
    public void removeStudent(Student student) {
        if (enrolledStudents.remove(student)) {
            student.dropCourse(this);
            System.out.println(student.getName() + " removed from " + courseName);
        } else {
            System.out.println(student.getName() + " is not enrolled in " + courseName);
        }
    }

    // Method to display course information
    public void displayCourseInfo() {
        System.out.println("========== COURSE INFORMATION ==========");
        System.out.println("Course Code: " + courseCode);
        System.out.println("Course Name: " + courseName);
        System.out.println("Credits: " + credits);
        if (teacher != null) {
            System.out.println("Teacher: " + teacher.getTitle() + " " + teacher.getName());
        } else {
            System.out.println("Teacher: Not assigned");
        }
        System.out.println("Enrolled Students: " + enrolledStudents.size());
        System.out.println("==========================================");
    }

    // Method to list all enrolled students
    public void listEnrolledStudents() {
        System.out.println("Students enrolled in " + courseName + ":");
        if (enrolledStudents.isEmpty()) {
            System.out.println("  No students enrolled yet.");
        } else {
            for (int i = 0; i < enrolledStudents.size(); i++) {
                Student s = enrolledStudents.get(i);
                System.out.println("  " + (i + 1) + ". " + s.getName() +
                        " (" + s.getStudentNumber() + ")");
            }
        }
    }

    // Getter methods
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    // Setter methods
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}