# Online-Course-Management-System
# Oops based System Design
## UML Diagram
<img width="1024" height="1024" alt="ChatGPT Image Jul 22, 2025, 11_19_43 AM" src="https://github.com/user-attachments/assets/787bc78f-adf2-4fc1-8221-476c41de0148" />

## Main.java
```
class User {
    String name;
    String email;


    User(String name, String email) {
        this.name = name;
        this.email = email;
    }


    String getDetails() {
        return name + " - " + email;
    }
}


class Student extends User {
    Course[] enrolledCourses = new Course[10];
    int courseCount = 0;


    Student(String name, String email) {
        super(name, email);
    }


    void enroll(Course course) {
        enrolledCourses[courseCount++] = course;
        course.addStudent(this);
    }


    @Override
    String getDetails() {
        return "Student: " + super.getDetails();
    }
}


class Instructor extends User {
    Course[] createdCourses = new Course[10];
    int courseCount = 0;


    Instructor(String name, String email) {
        super(name, email);
    }


    Course createCourse(String title) {
        Course course = new Course(title, this);
        createdCourses[courseCount++] = course;
        return course;
    }


    void gradeAssignment(Assignment assignment, String grade) {
        assignment.setGrade(grade);
    }


    @Override
    String getDetails() {
        return "Instructor: " + super.getDetails();
    }
}


class Course {
    String title;
    Instructor instructor;
    Student[] students = new Student[10];
    Assignment[] assignments = new Assignment[10];
    int studentCount = 0;
    int assignmentCount = 0;


    Course(String title, Instructor instructor) {
        this.title = title;
        this.instructor = instructor;
    }


    void addStudent(Student student) {
        students[studentCount++] = student;
    }


    void addAssignment(Assignment assignment) {
        assignments[assignmentCount++] = assignment;
    }
}


class Assignment {
    String title;
    Student student;
    String grade;


    Assignment(String title, Student student) {
        this.title = title;
        this.student = student;
        this.grade = null;
    }


    void setGrade(String grade) {
        this.grade = grade;
    }


    String getGrade() {
        return grade;
    }
}


public class Main {
    public static void main(String[] args) {
        Instructor instructor = new Instructor("Dr. Ravi", "ravi@edu.com");
        Course course = instructor.createCourse("OOP in Java");


        Student student1 = new Student("Priya", "priya@student.com");
        Student student2 = new Student("Arun", "arun@student.com");


        student1.enroll(course);
        student2.enroll(course);


        Assignment assignment1 = new Assignment("Project 1", student1);
        Assignment assignment2 = new Assignment("Project 1", student2);


        course.addAssignment(assignment1);
        course.addAssignment(assignment2);


        instructor.gradeAssignment(assignment1, "A");
        instructor.gradeAssignment(assignment2, "B+");


        System.out.println(instructor.getDetails());
        System.out.println(student1.getDetails());
        System.out.println(student2.getDetails());
        System.out.println(student1.name + " received grade: " + assignment1.getGrade());
        System.out.println(student2.name + " received grade: " + assignment2.getGrade());
    }
}
```


## Output
<img width="547" height="169" alt="Screenshot 2025-07-22 133911" src="https://github.com/user-attachments/assets/b8c34e9a-1a9e-484d-a2e3-19769bcf2540" />

## Replit Hosting
https://replit.com/@HarshYazhs/Oops#src/main/java/Main.java

## Design Explanation
1. Abstraction:
 Each class represents a real-world entity – User, Student, Instructor, Course, and Assignment. Only relevant data and methods are exposed.
2. Encapsulation:
 Assignment grading is encapsulated using setGrade() and getGrade(). While most fields are public in the code, they can be made private and accessed via getters/setters for full encapsulation.
3. Inheritance:
 Student and Instructor inherit from the User class and reuse common attributes (name, email) and methods (getDetails()).
4. Polymorphism:
 getDetails() method is overridden in both Student and Instructor classes to show different outputs based on the object type.
## SOLID Principles 
S (Single Responsibility): Each class has a single responsibility (e.g., Course manages assignments and students).

O (Open/Closed): Classes are open for extension but closed for modification (e.g., more roles can be added without altering existing ones).

L (Liskov Substitution): Subclasses can replace the base class (User) without affecting correctness.

I (Interface Segregation): Not implemented yet, but interfaces like Gradable or Enrollable could be introduced.

D (Dependency Inversion): Not directly used, but can be applied with service layers or repositories.
