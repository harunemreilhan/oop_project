# Generics Documentation

## School Gradebook System - Generic Method Implementation

---

## 1. What is Generics?

Generics is a feature in Java that allows writing **type-safe** and **reusable** code. Instead of writing separate methods for different types, we can write a single method that works with any type while maintaining compile-time type checking.

### Key Benefits:

- **Type Safety**: Compile-time type checking prevents ClassCastException at runtime
- **Code Reusability**: One method works for multiple types
- **No Casting Required**: Return type is automatically inferred
- **Cleaner Code**: Eliminates duplicate code for similar operations

---

## 2. Generic Method in This Project

### Location

**File**: `Gradebook.java`

**Lines**: 323-330

### Code

![Screenshot 2025-12-25 at 16.57.22.png](Generics%20Documentation/Screenshot_2025-12-25_at_16.57.22.png)

### Syntax Explanation

| Component | Meaning |
| --- | --- |
| `<T extends User>` | Type parameter T that must be User or its subclass |
| `T` (return type) | Returns the same type as the list elements |
| `ArrayList<T> list` | Accepts a list of any type T |
| `T item` | Each item in the loop is of type T |

---

## 3. Where is it Used?

### Usage 1: Searching Students

**File**: `Main.java`

**Lines**: 169-183
**Method**: `searchStudentByName()`

![Screenshot 2025-12-25 at 16.39.23.png](Generics%20Documentation/Screenshot_2025-12-25_at_16.39.23.png)

**How it works**:
1. `gradebook.getStudents()` returns `ArrayList<Student>`
2. Compiler infers `T = Student`
3. Return type is automatically `Student`
4. No casting needed!

### Usage 2: Searching Teachers

**File**: `Main.java`

**Lines**: 275-289
**Method**: `searchTeacherByName()`

![Screenshot 2025-12-25 at 16.59.12.png](Generics%20Documentation/Screenshot_2025-12-25_at_16.59.12.png)

**How it works**:
1. `gradebook.getTeachers()` returns `ArrayList<Teacher>`
2. Compiler infers `T = Teacher`
3. Return type is automatically `Teacher`
4. No casting needed!

---

## 4. Why Use Generics Here?

### Problem Without Generics

If we didn’t use generics, we would need **separate methods** for each type:

```java
// Method for Students
public Student findStudentByName(ArrayList<Student> list, String name) {
    for (Student item : list) {
        if (item.getName().equalsIgnoreCase(name)) {
            return item;
        }
    }
    return null;
}

// Method for Teachers (duplicate code!)
public Teacher findTeacherByName(ArrayList<Teacher> list, String name) {
    for (Teacher item : list) {
        if (item.getName().equalsIgnoreCase(name)) {
            return item;
        }
    }
    return null;
}

// Method for Admins (more duplicate code!)
public Admin findAdminByName(ArrayList<Admin> list, String name) {
    for (Admin item : list) {
        if (item.getName().equalsIgnoreCase(name)) {
            return item;
        }
    }
    return null;
}
```

**Problems**:
- ❌ Code duplication (DRY violation)
- ❌ Harder to maintain
- ❌ If logic changes, must update all methods

### Solution With Generics

One method handles all types:

```java
public <T extends User> T findByName(ArrayList<T> list, String name) {
    for (T item : list) {
        if (item.getName().equalsIgnoreCase(name)) {
            return item;
        }
    }
    return null;
}
```

**Benefits**:
- ✅ Single method for all User types
- ✅ Type-safe (compile-time checking)
- ✅ No casting required
- ✅ Easy to maintain

---

## 5. The Bounded Type Parameter

### Why `<T extends User>` instead of just `<T>`?

```java
public <T extends User> T findByName(ArrayList<T> list, String name)
           ↑
           └── This is a BOUNDED type parameter
```

**Reason**: We need to call `item.getName()` inside the method. If T could be ANY type (like String or Integer), they wouldn’t have a `getName()` method.

By saying `T extends User`, we guarantee that:
1. T is either User or a subclass of User
2. T definitely has the `getName()` method (inherited from User)
3. Compiler allows `item.getName()` call

```java
// This works because T extends User:
if (item.getName().equalsIgnoreCase(name))  // ✓ getName() exists

// If T was unbounded <T>, this would be an error:
// item.getName()  // ✗ Error: getName() might not exist
```

## 6. Type Inference Example

```java
// When calling with ArrayList<Student>:
Student s = gradebook.findByName(gradebook.getStudents(), "Alice");
//                                        ↑
//                         Returns ArrayList<Student>
//                         Compiler infers: T = Student
//                         Return type: Student

// When calling with ArrayList<Teacher>:
Teacher t = gradebook.findByName(gradebook.getTeachers(), "John");
//                                        ↑
//                         Returns ArrayList<Teacher>
//                         Compiler infers: T = Teacher
//                         Return type: Teacher
```

## 7. Summary

| Aspect | Description |
| --- | --- |
| **What** | Generic method with bounded type parameter |
| **Where** | `Gradebook.findByName()` |
| **Used in** | `searchStudentByName()`, `searchTeacherByName()` |
| **Why** | Code reusability, type safety, no casting |
| **Constraint** | `T extends User` ensures getName() is available |