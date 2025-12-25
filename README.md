# School Gradebook System

# Object-Oriented Programming Term Project

---

## Student Information

| Field | Value |
| --- | --- |
| **Name** | Harun Emre ILHAN |
| **Student ID** | 1008182 |
| **Course** | Object-Oriented Programming |
| **Submission Date** | 24/12/2025 |

---

## Assigned Topic

**School Gradebook System** - A console-based Java application for managing students, teachers, courses, and grades in an educational institution. The system allows administrators to track student enrollment, assign teachers to courses, record grades, and calculate GPAs.

---

## System Architecture

### Overview

The School Gradebook System is designed using object-oriented principles to model a real-world educational institution. The architecture follows a layered approach where the `Gradebook` class serves as the central management hub, coordinating interactions between `User` entities (Students, Teachers, Admins), `Course` objects, and `Grade` records. The system employs the Singleton design pattern for the `Gradebook` class to ensure data consistency across the application, while an abstract `User` class establishes a common interface for all user types through inheritance. Collections from the Java Collections Framework (`ArrayList` and `HashMap`) are used to efficiently store and retrieve objects, enabling dynamic data management. The `Main` class provides a console-based user interface that delegates all business logic to the underlying domain classes, maintaining a clear separation of concerns.

### UML Class Diagram

![README.png](/README.png)

---

## Class Descriptions and Collaboration

### User (Abstract Class)

The `User` class serves as the **abstract base class** for all user types in the system. It defines common attributes (`id`, `name`, `email`, `password`) and behaviors (`login()`, `getBasicInfo()`) shared by all users. The class declares two abstract methods (`displayInfo()` and `getRole()`) that force subclasses to provide their own implementations, enabling **polymorphism**. A static `idCounter` variable ensures each user receives a unique ID automatically.

### Student

`Student` **inherits from** `User` and represents students enrolled in the institution. It adds student-specific attributes (`studentNumber`, `enrollmentYear`) and maintains an `ArrayList<Course>` for tracking course enrollments. The class **overrides** `displayInfo()` to show student-specific information and collaborates with `Course` through an **association** relationship for enrollment management.

### Teacher

`Teacher` **inherits from** `User` and represents faculty members. It includes academic attributes (`department`, `title`) and an `ArrayList<Course>` for courses being taught. The class **overrides** `displayInfo()` and `getRole()` to provide teacher-specific behavior. Teachers are **associated** with courses they teach through the course assignment system.

### Admin

`Admin` **inherits from** `User` and represents administrative users with system access privileges. It adds `adminCode` and `accessLevel` attributes. Like other user types, it **overrides** the abstract methods to provide admin-specific implementations.

### Course

`Course` represents an academic course offered by the institution. It demonstrates **composition** by containing a reference to a `Teacher` (the assigned instructor) and an `ArrayList<Student>` (enrolled students). The class manages the relationships between teachers and students through its enrollment and assignment methods. A static `courseCounter` generates unique course codes.

### Grade

`Grade` represents an academic grade for a student in a specific course. It demonstrates **composition** by holding references to both a `Student` and a `Course`. The class includes a static utility method `calculateLetterGrade()` for converting numerical scores to letter grades. Multiple constructor **overloading** allows creating grades from either numerical scores or letter grades.

### Gradebook

`Gradebook` is the **central management class** implementing the **Singleton pattern** to ensure only one instance exists throughout the application. It uses **Collections** (`ArrayList` for students, teachers, courses; `HashMap` for grades) to store and manage all system data. The class demonstrates **runtime polymorphism** through its `displayUserInfo(User)` method, which accepts any user type and calls the appropriate overridden method. It also includes a **generic method** `findByName<T>()` for type-safe searching.

### Main

`Main` is the application entry point providing a console-based user interface. It has a **dependency** on `Gradebook` for all data operations, delegating business logic to the domain classes while handling user input and menu navigation.

---

## Key Relationships

### Inheritance (IS-A)

- `Student` IS-A `User`
- `Teacher` IS-A `User`
- `Admin` IS-A `User`

All three subclasses inherit common properties and behaviors from the abstract `User` class, promoting code reuse and establishing a clear type hierarchy.

### Composition (HAS-A - Strong Ownership)

- `Course` HAS-A `Teacher` (assigned instructor)
- `Grade` HAS-A `Student` and HAS-A `Course`
- `Gradebook` HAS students, teachers, courses, and grades (manages collections)

These compositions represent strong ownership where the containing class manages the lifecycle of its components.

### Association (Uses)

- `Student` is associated with `Course` (enrollment relationship)
- `Teacher` is associated with `Course` (teaching relationship)

These associations represent relationships where objects collaborate but maintain independent lifecycles.

### Dependency

- `Main` depends on `Gradebook` for all operations
- All classes depend on their respective types for method parameters and return values

Dependencies represent weaker relationships where one class uses another temporarily.

---

## OOP Concepts Implementation Summary

| Concept | Implementation Location |
| --- | --- |
| **Abstract Class** | `User` class with abstract methods `displayInfo()`, `getRole()` |
| **Inheritance** | `Student`, `Teacher`, `Admin` extend `User` |
| **Method Overriding** | `displayInfo()`, `getRole()` in all subclasses |
| **Method Overloading** | `getBasicInfo()`, `Grade()` constructors, `displayGrade()` |
| **Encapsulation** | Private fields with public getters/setters in all classes |
| **Collections** | `ArrayList`, `HashMap` in `Gradebook` and entity classes |
| **Static Variables** | `idCounter`, `courseCounter`, `totalGradesGiven`, `instance` |
| **Static Methods** | `getNextId()`, `calculateLetterGrade()`, `getInstance()` |
| **Generics** | `findByName<T extends User>()` in `Gradebook` |
| **Polymorphism** | `displayUserInfo(User user)` method demonstrating runtime polymorphism |

---

## File Structure

```
OOP_Project/
├── src/
│   ├── User.java          # Abstract base class for all users
│   ├── Student.java       # Student entity (extends User)
│   ├── Teacher.java       # Teacher entity (extends User)
│   ├── Admin.java         # Admin entity (extends User)
│   ├── Course.java        # Course entity with teacher/student associations
│   ├── Grade.java         # Grade entity linking student and course
│   ├── Gradebook.java     # Central management class (Singleton)
│   └── Main.java          # Application entry point with console UI
├── README.md              # This documentation file
└── presentation.md        # Detailed code explanations for defense
```
