import java.util.ArrayList;

/**
 * Teacher.java
 *
 * INHERITANCE DEMONSTRATION
 * Teacher class extends User class - this is an "is-a" relationship
 * A Teacher IS-A User with additional teacher-specific properties
 *
 * OOP Concepts demonstrated:
 * - Inheritance: extends User class
 * - Polymorphism: Overrides displayInfo() and getRole() methods
 * - Collections: Uses ArrayList to store courses taught
 */
public class Teacher extends User {

    // Teacher-specific fields
    private String department;
    private String title;  // e.g., "Professor", "Assistant Professor"

    // COLLECTIONS: ArrayList to store courses the teacher teaches
    private ArrayList<Course> taughtCourses;

    /**
     * Constructor for Teacher class
     * Calls parent constructor using super() keyword
     *
     * @param name Teacher's full name
     * @param email Teacher's email address
     * @param password Teacher's password
     * @param department Teacher's department
     * @param title Teacher's academic title
     */
    public Teacher(String name, String email, String password,
                   String department, String title) {
        // Call parent class constructor
        super(name, email, password);
        this.department = department;
        this.title = title;
        this.taughtCourses = new ArrayList<>();
    }

    // METHOD OVERRIDING: Provides Teacher-specific implementation
    @Override
    public void displayInfo() {
        System.out.println("========== TEACHER INFORMATION ==========");
        System.out.println("User ID: " + getId());
        System.out.println("Name: " + title + " " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Department: " + department);
        System.out.println("Courses Teaching: " + taughtCourses.size());
        System.out.println("==========================================");
    }

    // METHOD OVERRIDING: Returns "Teacher" as the role
    @Override
    public String getRole() {
        return "Teacher";
    }

    // Method to assign a course to teach
    public void assignCourse(Course course) {
        if (!taughtCourses.contains(course)) {
            taughtCourses.add(course);
            System.out.println(getName() + " is now teaching " + course.getCourseName());
        } else {
            System.out.println(getName() + " is already teaching " + course.getCourseName());
        }
    }

    // Method to remove a course from teaching
    public void removeCourse(Course course) {
        if (taughtCourses.remove(course)) {
            System.out.println(getName() + " is no longer teaching " + course.getCourseName());
        } else {
            System.out.println(getName() + " was not teaching " + course.getCourseName());
        }
    }

    // Getter methods for Teacher-specific fields
    public String getDepartment() {
        return department;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Course> getTaughtCourses() {
        return taughtCourses;
    }

    // Setter methods
    public void setDepartment(String department) {
        this.department = department;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}