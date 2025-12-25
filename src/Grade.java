/**
 * Grade.java
 *
 * This class represents a grade entry for a student in a course.
 * It stores the numerical score and calculates the letter grade.
 *
 * OOP Concepts demonstrated:
 * - Encapsulation: Private fields with public getters/setters
 * - Composition: Grade HAS-A Student and HAS-A Course
 * - Static method: Utility method for calculating letter grade
 * - Method overloading: Multiple constructors and display methods
 */
public class Grade {

    // ENCAPSULATION: Private fields
    private Student student;  // COMPOSITION: Grade belongs to a Student
    private Course course;    // COMPOSITION: Grade is for a Course
    private double score;     // Numerical score (0-100)
    private String letterGrade;

    // STATIC VARIABLE: Counter for tracking total grades given
    private static int totalGradesGiven = 0;

    /**
     * Constructor for Grade class
     *
     * @param student The student receiving the grade
     * @param course The course for which grade is given
     * @param score The numerical score (0-100)
     */
    public Grade(Student student, Course course, double score) {
        this.student = student;
        this.course = course;
        setScore(score);  // Use setter to validate and calculate letter grade
        totalGradesGiven++;
    }

    // METHOD OVERLOADING: Constructor with letter grade directly
    public Grade(Student student, Course course, String letterGrade) {
        this.student = student;
        this.course = course;
        this.letterGrade = letterGrade;
        this.score = convertLetterToScore(letterGrade);
        totalGradesGiven++;
    }

    // STATIC METHOD: Utility method to calculate letter grade from score
    public static String calculateLetterGrade(double score) {
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // STATIC METHOD: Get total number of grades given
    public static int getTotalGradesGiven() {
        return totalGradesGiven;
    }

    // Helper method to convert letter grade to approximate score
    private double convertLetterToScore(String letter) {
        switch (letter.toUpperCase()) {
            case "A": return 95.0;
            case "B": return 85.0;
            case "C": return 75.0;
            case "D": return 65.0;
            case "F": return 50.0;
            default: return 0.0;
        }
    }

    // Method to display grade information
    public void displayGrade() {
        System.out.println("Student: " + student.getName() +
                " | Course: " + course.getCourseName() +
                " | Score: " + score +
                " | Grade: " + letterGrade);
    }

    // METHOD OVERLOADING: Display with more details
    public void displayGrade(boolean showDetails) {
        if (showDetails) {
            System.out.println("========== GRADE DETAILS ==========");
            System.out.println("Student Name: " + student.getName());
            System.out.println("Student Number: " + student.getStudentNumber());
            System.out.println("Course: " + course.getCourseName());
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Numerical Score: " + score);
            System.out.println("Letter Grade: " + letterGrade);
            System.out.println("====================================");
        } else {
            displayGrade();
        }
    }

    // Check if the grade is passing (D or above)
    public boolean isPassing() {
        return score >= 60;
    }

    // Getter methods
    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public double getScore() {
        return score;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    // Setter methods
    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    // Setter for score also updates letter grade
    public void setScore(double score) {
        // Validate score range
        if (score < 0) {
            this.score = 0;
        } else if (score > 100) {
            this.score = 100;
        } else {
            this.score = score;
        }
        // Calculate and set letter grade
        this.letterGrade = calculateLetterGrade(this.score);
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }
}

