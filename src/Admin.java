/**
 * Admin.java
 *
 * INHERITANCE DEMONSTRATION
 * Admin class extends User class - this is an "is-a" relationship
 * An Admin IS-A User with additional administrative properties
 *
 * OOP Concepts demonstrated:
 * - Inheritance: extends User class
 * - Polymorphism: Overrides displayInfo() and getRole() methods
 */
public class Admin extends User {

    // Admin-specific fields
    private String adminCode;
    private String accessLevel;  // e.g., "FULL", "LIMITED"

    /**
     * Constructor for Admin class
     * Calls parent constructor using super() keyword
     *
     * @param name Admin's full name
     * @param email Admin's email address
     * @param password Admin's password
     * @param adminCode Unique admin code
     * @param accessLevel Admin's access level
     */
    public Admin(String name, String email, String password,
                 String adminCode, String accessLevel) {
        // Call parent class constructor
        super(name, email, password);
        this.adminCode = adminCode;
        this.accessLevel = accessLevel;
    }

    // METHOD OVERRIDING: Provides Admin-specific implementation
    @Override
    public void displayInfo() {
        System.out.println("========== ADMIN INFORMATION ==========");
        System.out.println("User ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Admin Code: " + adminCode);
        System.out.println("Access Level: " + accessLevel);
        System.out.println("========================================");
    }

    // METHOD OVERRIDING: Returns "Admin" as the role
    @Override
    public String getRole() {
        return "Admin";
    }

    // Admin-specific method to check if admin has full access
    public boolean hasFullAccess() {
        return "FULL".equals(accessLevel);
    }

    // Getter methods for Admin-specific fields
    public String getAdminCode() {
        return adminCode;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    // Setter methods
    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}