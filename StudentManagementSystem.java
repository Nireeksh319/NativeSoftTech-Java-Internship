import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentManagementSystem {
    private static final Scanner sc = new Scanner(System.in);
    private static final Map<Integer, Student> students = new HashMap<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        try (sc) {
            System.out.println("=== Student Management System ===");
            boolean running = true;
            while (running) {
                printMenu();
                String choice = sc.nextLine().trim();
                switch (choice) {
                    case "1" -> addStudent();
                    case "2" -> updateStudent();
                    case "3" -> removeStudent();
                    case "4" -> viewStudent();
                    case "5" -> listAllStudents();
                    case "6" -> {
                        running = false; System.out.println("Exiting.");
                    }
                    default -> System.out.println("Invalid choice.");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n1) Add Student");
        System.out.println("2) Update Student");
        System.out.println("3) Remove Student");
        System.out.println("4) View Student");
        System.out.println("5) List All Students");
        System.out.println("6) Exit");
        System.out.print("Enter choice: ");
    }

    private static void addStudent() {
        System.out.print("Enter name: ");
        String name = sc.nextLine().trim();
        int age = readInt("Enter age: ");
        System.out.print("Enter grade/stream (e.g., 8th, A, B+): ");
        String grade = sc.nextLine().trim();

        Student s = new Student(nextId++, name, age, grade);
        students.put(s.getId(), s);
        System.out.println("Student added. ID: " + s.getId());
    }

    private static void updateStudent() {
        int id = readInt("Enter student ID to update: ");
        Student s = students.get(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("Updating student: " + s);
        System.out.print("New name (leave empty to keep): ");
        String name = sc.nextLine().trim();
        if (!name.isEmpty()) s.setName(name);
        String ageLine;
        System.out.print("New age (leave empty to keep): ");
        ageLine = sc.nextLine().trim();
        if (!ageLine.isEmpty()) {
            try {
                s.setAge(Integer.parseInt(ageLine));
            } catch (NumberFormatException e) {
                System.out.println("Invalid age input, skipped.");
            }
        }
        System.out.print("New grade (leave empty to keep): ");
        String grade = sc.nextLine().trim();
        if (!grade.isEmpty()) s.setGrade(grade);

        System.out.println("Updated: " + s);
    }

    private static void removeStudent() {
        int id = readInt("Enter student ID to remove: ");
        Student removed = students.remove(id);
        if (removed == null) System.out.println("Student not found.");
        else System.out.println("Removed: " + removed);
    }

    private static void viewStudent() {
        int id = readInt("Enter student ID to view: ");
        Student s = students.get(id);
        if (s == null) System.out.println("Student not found.");
        else System.out.println(s);
    }

    private static void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students added.");
            return;
        }
        System.out.println("All students:");
        for (Student s : students.values()) {
            System.out.println(s);
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    // Student record class
    private static class Student {
        private final int id;
        private String name;
        private int age;
        private String grade;

        public Student(int id, String name, int age, String grade) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.grade = grade;
        }

        public int getId() { return id; }
        public void setName(String name) { this.name = name; }
        public void setAge(int age) { this.age = age; }
        public void setGrade(String grade) { this.grade = grade; }

        @Override
        public String toString() {
            return String.format("ID:%d | Name:%s | Age:%d | Grade:%s", id, name, age, grade);
        }
    }
}

