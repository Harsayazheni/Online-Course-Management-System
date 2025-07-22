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
