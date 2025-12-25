import java.util.Scanner;

/**
 * Main.java
 *
 * This is the entry point of the School Gradebook System.
 * It provides a console-based menu for interacting with the system.
 *
 * The Main class demonstrates:
 * - Program flow control
 * - User input handling
 * - Integration of all OOP components
 */
public class Main {

    // Scanner for user input
    private static Scanner scanner = new Scanner(System.in);

    // Reference to the gradebook instance
    private static Gradebook gradebook = Gradebook.getInstance();

    /**
     * Main method - Entry point of the application
     */
    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("   Welcome to " + Gradebook.getSchoolName());
        System.out.println("       School Gradebook System");
        System.out.println("============================================");

        // Initialize some sample data for demonstration
        initializeSampleData();

        // Main menu loop
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    studentMenu();
                    break;
                case 2:
                    teacherMenu();
                    break;
                case 3:
                    courseMenu();
                    break;
                case 4:
                    gradeMenu();
                    break;
                case 5:
                    demonstratePolymorphism();
                    break;
                case 6:
                    displaySystemStatistics();
                    break;
                case 0:
                    running = false;
                    System.out.println("\nThank you for using the School Gradebook System!");
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    /**
     * Display the main menu options
     */
    private static void displayMainMenu() {
        System.out.println("\n============ MAIN MENU ============");
        System.out.println("1. Student Management");
        System.out.println("2. Teacher Management");
        System.out.println("3. Course Management");
        System.out.println("4. Grade Management");
        System.out.println("5. Demonstrate Polymorphism");
        System.out.println("6. System Statistics");
        System.out.println("0. Exit");
        System.out.println("====================================");
    }

    // ==================== STUDENT MENU ====================

    private static void studentMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n======== STUDENT MANAGEMENT ========");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. View Student Details");
            System.out.println("4. View Student Grades");
            System.out.println("0. Back to Main Menu");
            System.out.println("====================================");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    gradebook.listAllStudents();
                    break;
                case 3:
                    viewStudentDetails();
                    break;
                case 4:
                    viewStudentGrades();
                    break;
                case 0:
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addNewStudent() {
        System.out.println("\n--- Add New Student ---");
        String name = getStringInput("Enter student name: ");
        String email = getStringInput("Enter email: ");
        String password = getStringInput("Enter password: ");
        String studentNumber = getStringInput("Enter student number: ");
        int enrollmentYear = getIntInput("Enter enrollment year: ");

        Student student = new Student(name, email, password, studentNumber, enrollmentYear);
        gradebook.addStudent(student);
    }

    private static void viewStudentDetails() {
        String studentNumber = getStringInput("Enter student number: ");
        Student student = gradebook.findStudentByNumber(studentNumber);

        if (student != null) {
            student.displayInfo();
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void viewStudentGrades() {
        String studentNumber = getStringInput("Enter student number: ");
        Student student = gradebook.findStudentByNumber(studentNumber);

        if (student != null) {
            gradebook.displayStudentGrades(student);
            System.out.println("Student GPA: " +
                    String.format("%.2f", gradebook.calculateStudentGPA(student)));
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void isPassing(){
    }

    // ==================== TEACHER MENU ====================

    private static void teacherMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n======== TEACHER MANAGEMENT ========");
            System.out.println("1. Add New Teacher");
            System.out.println("2. View All Teachers");
            System.out.println("3. View Teacher Details");
            System.out.println("4. Assign Teacher to Course");
            System.out.println("0. Back to Main Menu");
            System.out.println("====================================");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addNewTeacher();
                    break;
                case 2:
                    gradebook.listAllTeachers();
                    break;
                case 3:
                    viewTeacherDetails();
                    break;
                case 4:
                    assignTeacherToCourse();
                    break;
                case 0:
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addNewTeacher() {
        System.out.println("\n--- Add New Teacher ---");
        String name = getStringInput("Enter teacher name: ");
        String email = getStringInput("Enter email: ");
        String password = getStringInput("Enter password: ");
        String department = getStringInput("Enter department: ");
        String title = getStringInput("Enter title (e.g., Professor, Dr.): ");

        Teacher teacher = new Teacher(name, email, password, department, title);
        gradebook.addTeacher(teacher);
    }

    private static void viewTeacherDetails() {
        int teacherId = getIntInput("Enter teacher ID: ");
        Teacher teacher = gradebook.findTeacherById(teacherId);

        if (teacher != null) {
            teacher.displayInfo();
        } else {
            System.out.println("Teacher not found.");
        }
    }

    private static void assignTeacherToCourse() {
        gradebook.listAllTeachers();
        int teacherId = getIntInput("Enter teacher ID: ");
        Teacher teacher = gradebook.findTeacherById(teacherId);

        if (teacher == null) {
            System.out.println("Teacher not found.");
            return;
        }

        gradebook.listAllCourses();
        String courseCode = getStringInput("Enter course code: ");
        Course course = gradebook.findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        course.assignTeacher(teacher);
    }

    // ==================== COURSE MENU ====================

    private static void courseMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n======== COURSE MANAGEMENT ========");
            System.out.println("1. Add New Course");
            System.out.println("2. View All Courses");
            System.out.println("3. View Course Details");
            System.out.println("4. Enroll Student in Course");
            System.out.println("5. View Course Grades");
            System.out.println("0. Back to Main Menu");
            System.out.println("====================================");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addNewCourse();
                    break;
                case 2:
                    gradebook.listAllCourses();
                    break;
                case 3:
                    viewCourseDetails();
                    break;
                case 4:
                    enrollStudentInCourse();
                    break;
                case 5:
                    viewCourseGrades();
                    break;
                case 0:
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addNewCourse() {
        System.out.println("\n--- Add New Course ---");
        String courseName = getStringInput("Enter course name: ");
        int credits = getIntInput("Enter credits: ");

        Course course = new Course(courseName, credits);
        gradebook.addCourse(course);
    }

    private static void viewCourseDetails() {
        String courseCode = getStringInput("Enter course code: ");
        Course course = gradebook.findCourseByCode(courseCode);

        if (course != null) {
            course.displayCourseInfo();
            course.listEnrolledStudents();
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void enrollStudentInCourse() {
        gradebook.listAllStudents();
        String studentNumber = getStringInput("Enter student number: ");
        Student student = gradebook.findStudentByNumber(studentNumber);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        gradebook.listAllCourses();
        String courseCode = getStringInput("Enter course code: ");
        Course course = gradebook.findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        course.enrollStudent(student);
    }

    private static void viewCourseGrades() {
        String courseCode = getStringInput("Enter course code: ");
        Course course = gradebook.findCourseByCode(courseCode);

        if (course != null) {
            gradebook.displayCourseGrades(course);
            double avg = gradebook.calculateCourseAverage(course);
            System.out.println("Course Average: " + String.format("%.2f", avg));
        } else {
            System.out.println("Course not found.");
        }
    }

    // ==================== GRADE MENU ====================

    private static void gradeMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n======== GRADE MANAGEMENT ========");
            System.out.println("1. Add Grade");
            System.out.println("2. Update Grade");
            System.out.println("3. View Student Grades");
            System.out.println("4. View Course Grades");
            System.out.println("0. Back to Main Menu");
            System.out.println("===================================");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addGrade();
                    break;
                case 2:
                    updateGrade();
                    break;
                case 3:
                    viewStudentGrades();
                    break;
                case 4:
                    viewCourseGrades();
                    break;
                case 0:
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addGrade() {
        System.out.println("\n--- Add Grade ---");
        gradebook.listAllStudents();
        String studentNumber = getStringInput("Enter student number: ");
        Student student = gradebook.findStudentByNumber(studentNumber);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        gradebook.listAllCourses();
        String courseCode = getStringInput("Enter course code: ");
        Course course = gradebook.findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        double score = getDoubleInput("Enter score (0-100): ");
        gradebook.addGrade(student, course, score);
    }

    private static void updateGrade() {
        System.out.println("\n--- Update Grade ---");
        String studentNumber = getStringInput("Enter student number: ");
        Student student = gradebook.findStudentByNumber(studentNumber);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        String courseCode = getStringInput("Enter course code: ");
        Course course = gradebook.findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        double newScore = getDoubleInput("Enter new score (0-100): ");
        gradebook.updateGrade(student, course, newScore);
    }

    // ==================== POLYMORPHISM DEMONSTRATION ====================

    /**
     * This method demonstrates runtime polymorphism
     * It shows how the same method call (displayInfo) behaves differently
     * based on the actual object type
     */
    private static void demonstratePolymorphism() {
        System.out.println("\n============ POLYMORPHISM DEMONSTRATION ============");
        System.out.println("This demonstrates runtime polymorphism:");
        System.out.println("The same displayUserInfo() method is called,");
        System.out.println("but different displayInfo() implementations are executed");
        System.out.println("based on the actual object type (Student, Teacher, Admin).\n");

        // Create different user types
        Student student = new Student("Demo Student", "demo@student.edu",
                "pass", "DEMO001", 2024);
        Teacher teacher = new Teacher("Demo Teacher", "demo@teacher.edu",
                "pass", "Computer Science", "Professor");
        Admin admin = new Admin("Demo Admin", "demo@admin.edu",
                "pass", "ADM001", "FULL");

        // Call the same method with different object types
        // This is RUNTIME POLYMORPHISM - the JVM decides at runtime
        // which displayInfo() method to call based on the actual object type

        System.out.println("--- Calling displayUserInfo(student) ---");
        gradebook.displayUserInfo(student);

        System.out.println("\n--- Calling displayUserInfo(teacher) ---");
        gradebook.displayUserInfo(teacher);

        System.out.println("\n--- Calling displayUserInfo(admin) ---");
        gradebook.displayUserInfo(admin);

        // Also demonstrate method overloading
        System.out.println("\n--- METHOD OVERLOADING DEMONSTRATION ---");
        System.out.println("Calling getBasicInfo() with different parameters:\n");
        System.out.println("student.getBasicInfo() returns: " + student.getBasicInfo());
        System.out.println("student.getBasicInfo(true) returns: " + student.getBasicInfo(true));

        System.out.println("\n=====================================================");
    }

    // ==================== SYSTEM STATISTICS ====================

    private static void displaySystemStatistics() {
        System.out.println("\n============ SYSTEM STATISTICS ============");
        System.out.println("School Name: " + Gradebook.getSchoolName());
        System.out.println("Total Students: " + gradebook.getStudents().size());
        System.out.println("Total Teachers: " + gradebook.getTeachers().size());
        System.out.println("Total Courses: " + gradebook.getCourses().size());
        System.out.println("Total Grades Given: " + Grade.getTotalGradesGiven());
        System.out.println("Next User ID: " + User.getNextId());
        System.out.println("Next Course Number: " + Course.getNextCourseNumber());
        System.out.println("=============================================");
    }

    // ==================== SAMPLE DATA INITIALIZATION ====================

    /**
     * Initialize sample data for demonstration purposes
     */
    private static void initializeSampleData() {
        System.out.println("\nInitializing sample data...\n");

        // Create teachers
        Teacher teacher1 = new Teacher("John Smith", "john.smith@university.edu",
                "pass123", "Computer Science", "Professor");
        Teacher teacher2 = new Teacher("Emily Johnson", "emily.j@university.edu",
                "pass123", "Mathematics", "Dr.");

        gradebook.addTeacher(teacher1);
        gradebook.addTeacher(teacher2);

        // Create students
        Student student1 = new Student("Alice Brown", "alice.b@student.edu",
                "pass123", "STU001", 2023);
        Student student2 = new Student("Bob Wilson", "bob.w@student.edu",
                "pass123", "STU002", 2023);
        Student student3 = new Student("Carol Davis", "carol.d@student.edu",
                "pass123", "STU003", 2024);

        gradebook.addStudent(student1);
        gradebook.addStudent(student2);
        gradebook.addStudent(student3);

        // Create courses
        Course course1 = new Course("OOP101", "Object-Oriented Programming", 4);
        Course course2 = new Course("MATH201", "Calculus II", 3);
        Course course3 = new Course("CS301", "Data Structures", 4);

        gradebook.addCourse(course1);
        gradebook.addCourse(course2);
        gradebook.addCourse(course3);

        // Assign teachers to courses
        course1.assignTeacher(teacher1);
        course2.assignTeacher(teacher2);
        course3.assignTeacher(teacher1);

        // Enroll students in courses
        course1.enrollStudent(student1);
        course1.enrollStudent(student2);
        course1.enrollStudent(student3);

        course2.enrollStudent(student1);
        course2.enrollStudent(student2);

        course3.enrollStudent(student2);
        course3.enrollStudent(student3);

        // Add some grades
        gradebook.addGrade(student1, course1, 92.5);
        gradebook.addGrade(student2, course1, 85.0);
        gradebook.addGrade(student3, course1, 78.5);

        gradebook.addGrade(student1, course2, 88.0);
        gradebook.addGrade(student2, course2, 75.5);

        gradebook.addGrade(student2, course3, 90.0);
        gradebook.addGrade(student3, course3, 82.0);

        System.out.println("\nSample data initialized successfully!");
        System.out.println("You can now explore the system.\n");
    }

    // ==================== INPUT HELPER METHODS ====================

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            System.out.print(prompt);
        }
        int value = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        return value;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            System.out.print(prompt);
        }
        double value = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        return value;
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}