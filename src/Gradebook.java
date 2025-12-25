import java.util.ArrayList;
import java.util.HashMap;

/**
 * Gradebook.java
 *
 * This is the main management class for the School Gradebook System.
 * It manages all students, teachers, courses, and grades.
 *
 * OOP Concepts demonstrated:
 * - Collections: ArrayList for students, teachers, courses; HashMap for grades
 * - Encapsulation: Private collections with public methods to access them
 * - Static members: Singleton pattern for single gradebook instance
 * - Generics: Generic method for searching (optional bonus)
 */
public class Gradebook {

    // COLLECTIONS: Using ArrayList and HashMap from Java Collections Framework
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private ArrayList<Course> courses;

    // COLLECTIONS: HashMap - Key is "studentId_courseCode", Value is Grade
    // This allows quick lookup of a specific student's grade in a specific course
    private HashMap<String, Grade> grades;

    // STATIC VARIABLE: Single instance of Gradebook (Singleton pattern)
    private static Gradebook instance = null;

    // STATIC VARIABLE: Name of the school
    private static String schoolName = "OOP University";

    /**
     * Private constructor for Singleton pattern
     * Ensures only one Gradebook instance exists
     */
    private Gradebook() {
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        courses = new ArrayList<>();
        grades = new HashMap<>();
    }

    // STATIC METHOD: Get the single instance of Gradebook
    public static Gradebook getInstance() {
        if (instance == null) {
            instance = new Gradebook();
        }
        return instance;
    }

    // STATIC METHOD: Get school name
    public static String getSchoolName() {
        return schoolName;
    }

    // STATIC METHOD: Set school name
    public static void setSchoolName(String name) {
        schoolName = name;
    }

    // ==================== STUDENT MANAGEMENT ====================

    // Add a new student
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student " + student.getName() + " added successfully.");
    }

    // Find student by ID
    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Find student by student number
    public Student findStudentByNumber(String studentNumber) {
        for (Student student : students) {
            if (student.getStudentNumber().equals(studentNumber)) {
                return student;
            }
        }
        return null;
    }

    // List all students
    public void listAllStudents() {
        System.out.println("\n========== ALL STUDENTS ==========");
        if (students.isEmpty()) {
            System.out.println("No students registered.");
        } else {
            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                System.out.println((i + 1) + ". " + s.getName() +
                        " (" + s.getStudentNumber() + ")"  + " " + "Teacher ID:" + s.getId());
            }
        }
        System.out.println("===================================\n");
    }

    // ==================== TEACHER MANAGEMENT ====================

    // Add a new teacher
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        System.out.println("Teacher " + teacher.getName() + " added successfully.");
    }

    // Find teacher by ID
    public Teacher findTeacherById(int id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }

    // List all teachers
    public void listAllTeachers() {
        System.out.println("\n========== ALL TEACHERS ==========");
        if (teachers.isEmpty()) {
            System.out.println("No teachers registered.");
        } else {
            for (int i = 0; i < teachers.size(); i++) {
                Teacher t = teachers.get(i);
                System.out.println((i + 1) + ". " + t.getTitle() + " " +
                        t.getName() + " (" + t.getDepartment() + ")" + " " + "Teacher ID:" + t.getId());
            }
        }
        System.out.println("===================================\n");
    }

    // ==================== COURSE MANAGEMENT ====================

    // Add a new course
    public void addCourse(Course course) {
        courses.add(course);
        System.out.println("Course " + course.getCourseName() + " added successfully.");
    }

    // Find course by code
    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    // List all courses
    public void listAllCourses() {
        System.out.println("\n========== ALL COURSES ==========");
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            for (int i = 0; i < courses.size(); i++) {
                Course c = courses.get(i);
                String teacherName = (c.getTeacher() != null) ?
                        c.getTeacher().getName() : "Not assigned";
                System.out.println((i + 1) + ". " + c.getCourseCode() + " - " +
                        c.getCourseName() + " (Teacher: " + teacherName + ")");
            }
        }
        System.out.println("==================================\n");
    }

    // ==================== GRADE MANAGEMENT ====================

    // Add a grade for a student in a course
    public void addGrade(Student student, Course course, double score) {
        // Check if student is enrolled in the course
        if (!course.getEnrolledStudents().contains(student)) {
            System.out.println("Error: Student is not enrolled in this course.");
            return;
        }

        // Create unique key for HashMap
        String key = student.getId() + "_" + course.getCourseCode();

        // Check if grade already exists
        if (grades.containsKey(key)) {
            System.out.println("Grade already exists. Use updateGrade() to modify.");
            return;
        }

        // Create and store the grade
        Grade grade = new Grade(student, course, score);
        grades.put(key, grade);
        System.out.println("Grade added: " + student.getName() + " received " +
                grade.getLetterGrade() + " (" + score + ") in " +
                course.getCourseName());
    }

    // Update an existing grade
    public void updateGrade(Student student, Course course, double newScore) {
        String key = student.getId() + "_" + course.getCourseCode();

        if (grades.containsKey(key)) {
            Grade grade = grades.get(key);
            double oldScore = grade.getScore();
            grade.setScore(newScore);
            System.out.println("Grade updated: " + student.getName() + "'s grade in " +
                    course.getCourseName() + " changed from " + oldScore +
                    " to " + newScore);
        } else {
            System.out.println("No existing grade found. Use addGrade() first.");
        }
    }

    // Get a specific grade
    public Grade getGrade(Student student, Course course) {
        String key = student.getId() + "_" + course.getCourseCode();
        return grades.get(key);
    }

    // Display all grades for a student
    public void displayStudentGrades(Student student) {
        System.out.println("\n========== GRADES FOR " + student.getName().toUpperCase() + " ==========");
        boolean hasGrades = false;

        for (Grade grade : grades.values()) {
            if (grade.getStudent().getId() == student.getId()) {
                grade.displayGrade();
                hasGrades = true;
            }
        }

        if (!hasGrades) {
            System.out.println("No grades recorded for this student.");
        }
        System.out.println("================================================\n");
    }

    // Display all grades for a course
    public void displayCourseGrades(Course course) {
        System.out.println("\n========== GRADES FOR " + course.getCourseName().toUpperCase() + " ==========");
        boolean hasGrades = false;

        for (Grade grade : grades.values()) {
            if (grade.getCourse().getCourseCode().equals(course.getCourseCode())) {
                grade.displayGrade();
                hasGrades = true;
            }
        }

        if (!hasGrades) {
            System.out.println("No grades recorded for this course.");
        }
        System.out.println("==================================================\n");
    }

    // Calculate average grade for a course
    public double calculateCourseAverage(Course course) {
        double total = 0;
        int count = 0;

        for (Grade grade : grades.values()) {
            if (grade.getCourse().getCourseCode().equals(course.getCourseCode())) {
                total += grade.getScore();
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }
        return total / count;
    }

    // Calculate GPA for a student
    public double calculateStudentGPA(Student student) {
        double totalPoints = 0;
        int count = 0;

        for (Grade grade : grades.values()) {
            if (grade.getStudent().getId() == student.getId()) {
                // Convert letter grade to GPA points
                String letter = grade.getLetterGrade();
                switch (letter) {
                    case "A": totalPoints += 4.0; break;
                    case "B": totalPoints += 3.0; break;
                    case "C": totalPoints += 2.0; break;
                    case "D": totalPoints += 1.0; break;
                    case "F": totalPoints += 0.0; break;
                }
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }
        return totalPoints / count;
    }

    // ==================== POLYMORPHISM DEMONSTRATION ====================

    // RUNTIME POLYMORPHISM: This method accepts any User type
    // The correct displayInfo() method is called based on actual object type
    public void displayUserInfo(User user) {
        System.out.println("\nDisplaying info using polymorphism:");
        System.out.println("User role: " + user.getRole());
        user.displayInfo();  // Calls the overridden method based on object type
    }

    // ==================== GENERIC METHOD (OPTIONAL BONUS) ====================

    /**
     * GENERICS: Generic method to search in any ArrayList
     * This demonstrates type-safe operations with generics
     *
     * @param <T> The type of elements in the list (must extend User)
     * @param list The ArrayList to search in
     * @param name The name to search for
     * @return The found element or null
     */
    public <T extends User> T findByName(ArrayList<T> list, String name) {
        for (T item : list) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    // Getter methods for collections
    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public HashMap<String, Grade> getGrades() {
        return grades;
    }
}