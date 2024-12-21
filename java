import java.util.ArrayList;
import java.util.Scanner;

// Class representing a Book
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            System.out.println("Book is already borrowed.");
        }
    }

    public void returnBook() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Book [ID=" + id + ", Title=" + title + ", Author=" + author + ", Available=" + isAvailable + "]";
    }
}

// Class representing a Library Member
class Member {
    private int id;
    private String name;
    private ArrayList<Book> borrowedBooks;

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Member [ID=" + id + ", Name=" + name + ", Borrowed Books=" + borrowedBooks.size() + "]";
    }
}

// Class representing the Library
class Library {
    private ArrayList<Book> books;
    private ArrayList<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getId() == memberId) {
                return member;
            }
        }
        return null;
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void listMembers() {
        for (Member member : members) {
            System.out.println(member);
        }
    }
}

// Main class
public class LibraryManagementSystem {
    private static Library library = new Library();

    public static void main(String[] args) {
        populateLibrary();
        runMenu();
    }

    private static void populateLibrary() {
        // Adding sample books
        library.addBook(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"));
        library.addBook(new Book(2, "1984", "George Orwell"));
        library.addBook(new Book(3, "To Kill a Mockingbird", "Harper Lee"));

        // Adding sample members
        library.addMember(new Member(1, "Alice"));
        library.addMember(new Member(2, "Bob"));
    }

    private static void runMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. List Books");
            System.out.println("2. List Members");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    library.listBooks();
                    break;
                case 2:
                    library.listMembers();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void borrowBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();

        Member member = library.findMemberById(memberId);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        System.out.print("Enter Book ID to borrow: ");
        int bookId = scanner.nextInt();

        Book book = library.findBookById(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book);
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book is currently not available.");
        }
    }

    private static void returnBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();

        Member member = library.findMemberById(memberId);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        System.out.print("Enter Book ID to return: ");
        int bookId = scanner.nextInt();

        Book book = library.findBookById(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (member.getBorrowedBooks().contains(book)) {
            book.returnBook();
            member.returnBook(book);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("This member did not borrow the book.");
        }
    }
}





import java.util.*;

// Class representing a Course
class Course {
    private int id;
    private String name;
    private int credits;

    public Course(int id, String name, int credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return "Course ID: " + id + ", Name: " + name + ", Credits: " + credits;
    }
}

// Class representing a Student
class Student {
    private int id;
    private String name;
    private Map<Integer, Double> courseGrades;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.courseGrades = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void enrollCourse(int courseId) {
        if (courseGrades.containsKey(courseId)) {
            System.out.println("Already enrolled in this course.");
        } else {
            courseGrades.put(courseId, -1.0); // -1.0 indicates no grade yet
            System.out.println("Enrolled successfully.");
        }
    }

    public void assignGrade(int courseId, double grade) {
        if (courseGrades.containsKey(courseId)) {
            courseGrades.put(courseId, grade);
            System.out.println("Grade assigned successfully.");
        } else {
            System.out.println("Student not enrolled in this course.");
        }
    }

    public Map<Integer, Double> getCourseGrades() {
        return courseGrades;
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name + ", Courses: " + courseGrades;
    }
}

// Class representing the Student Management System
class StudentManagementSystem {
    private Map<Integer, Student> students;
    private Map<Integer, Course> courses;

    public StudentManagementSystem() {
        students = new HashMap<>();
        courses = new HashMap<>();
    }

    // Add a new course
    public void addCourse(int id, String name, int credits) {
        if (courses.containsKey(id)) {
            System.out.println("Course ID already exists!");
        } else {
            courses.put(id, new Course(id, name, credits));
            System.out.println("Course added successfully.");
        }
    }

    // Add a new student
    public void addStudent(int id, String name) {
        if (students.containsKey(id)) {
            System.out.println("Student ID already exists!");
        } else {
            students.put(id, new Student(id, name));
            System.out.println("Student added successfully.");
        }
    }

    // Enroll a student in a course
    public void enrollStudentInCourse(int studentId, int courseId) {
        if (!students.containsKey(studentId)) {
            System.out.println("Student ID not found!");
            return;
        }
        if (!courses.containsKey(courseId)) {
            System.out.println("Course ID not found!");
            return;
        }

        Student student = students.get(studentId);
        student.enrollCourse(courseId);
    }

    // Assign a grade to a student for a course
    public void assignGrade(int studentId, int courseId, double grade) {
        if (!students.containsKey(studentId)) {
            System.out.println("Student ID not found!");
            return;
        }
        if (!courses.containsKey(courseId)) {
            System.out.println("Course ID not found!");
            return;
        }

        Student student = students.get(studentId);
        student.assignGrade(courseId, grade);
    }

    // Display all students
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            students.values().forEach(System.out::println);
        }
    }

    // Display all courses
    public void displayCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses in the system.");
        } else {
            courses.values().forEach(System.out::println);
        }
    }

    // Display grades for a student
    public void displayStudentGrades(int studentId) {
        if (!students.containsKey(studentId)) {
            System.out.println("Student ID not found!");
        } else {
            Student student = students.get(studentId);
            Map<Integer, Double> grades = student.getCourseGrades();
            System.out.println("Grades for " + student.getName() + ":");
            grades.forEach((courseId, grade) -> {
                String courseName = courses.get(courseId).getName();
                System.out.println("Course: " + courseName + ", Grade: " + (grade == -1.0 ? "Not Assigned" : grade));
            });
        }
    }
}

// Main class
public class StudentSystem {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Course");
            System.out.println("2. Add Student");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Assign Grade");
            System.out.println("5. Display Students");
            System.out.println("6. Display Courses");
            System.out.println("7. Display Student Grades");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Course ID: ");
                    int courseId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Course Name: ");
                    String courseName = scanner.nextLine();
                    System.out.print("Enter Course Credits: ");
                    int credits = scanner.nextInt();
                    sms.addCourse(courseId, courseName, credits);
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.nextLine();
                    sms.addStudent(studentId, studentName);
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextInt();
                    System.out.print("Enter Course ID: ");
                    courseId = scanner.nextInt();
                    sms.enrollStudentInCourse(studentId, courseId);
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextInt();
                    System.out.print("Enter Course ID: ");
                    courseId = scanner.nextInt();
                    System.out.print("Enter Grade: ");
                    double grade = scanner.nextDouble();
                    sms.assignGrade(studentId, courseId, grade);
                    break;
                case 5:
                    sms.displayStudents();
                    break;
                case 6:
                    sms.displayCourses();
                    break;
                case 7:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextInt();
                    sms.displayStudentGrades(studentId);
                    break;
                case 8:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
