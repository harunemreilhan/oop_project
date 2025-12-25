/**
 * User.java
 *
 * ABSTRACT CLASS DEMONSTRATION
 * This is an abstract class that serves as the base class for all users in the system.
 * It cannot be instantiated directly - only its subclasses (Student, Teacher, Admin) can be created.
 *
 * OOP Concepts demonstrated:
 * - Abstraction: Defines a template for all user types
 * - Encapsulation: Private fields with public getters/setters
 * - Static members: idCounter for generating unique IDs
 */
public abstract class User {

    // ENCAPSULATION: Private fields - data hiding
    private int id;
    private String name;
    private String email;
    private String password;

    // STATIC VARIABLE: Shared across all User instances
    // Used to generate unique IDs for each user
    private static int idCounter = 1000;

    /**
     * Constructor for User class
     * Automatically assigns a unique ID using static counter
     *
     * @param name User's full name
     * @param email User's email address
     * @param password User's password
     */
    public User(String name, String email, String password) {
        this.id = idCounter++;  // Assign current counter value and increment
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // ABSTRACT METHOD: Must be implemented by all subclasses
    // Each user type will display their information differently
    public abstract void displayInfo();

    // ABSTRACT METHOD: Returns the role of the user
    public abstract String getRole();

    // CONCRETE METHOD: Same login logic for all user types
    // This is a non-abstract method in abstract class
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    // STATIC METHOD: Utility method to get the next ID that will be assigned
    public static int getNextId() {
        return idCounter;
    }

    // ENCAPSULATION: Getter methods - controlled access to private fields
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // ENCAPSULATION: Setter methods - controlled modification of private fields
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // METHOD OVERLOADING: Same method name, different parameters
    // This version displays basic info
    public String getBasicInfo() {
        return "ID: " + id + ", Name: " + name;
    }

    // METHOD OVERLOADING: This version includes email
    public String getBasicInfo(boolean includeEmail) {
        if (includeEmail) {
            return "ID: " + id + ", Name: " + name + ", Email: " + email;
        }
        return getBasicInfo();
    }
}