package controller;

import service.StudentService;
import java.util.Scanner;

public class StudentController {
    private final StudentService studentService = new StudentService();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        StudentController controller = new StudentController();
        controller.showMenu(); 
    }

    public void showMenu() {
        int choice;
        do {
            clearScreen();
            System.out.println("\n====Manage Student====");
            System.out.println("1. All Students");
            System.out.println("2. Add New Student");
            System.out.println("3. Find Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("0. Exit");
            System.out.print("CHOOSE: ");
            choice = Integer.parseInt(scanner.nextLine());

            clearScreen();

            switch (choice) {
                case 1 -> displayAll();
                case 2 -> addStudent();
                case 3 -> findStudent();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 0 -> System.out.println("Exiting the program...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private void displayAll() {
        studentService.displayByPage();
        pause(); 
        clearScreen();
    }

    private void addStudent() {
        try {
            System.out.print("====Add New Student====\n");
            System.out.print("Enter Student ID: ");
            String studentID = scanner.nextLine();
            if (studentService.findByStudentID(studentID) != null) {
                System.out.println("Student ID already exists. Please use another one.");
                return;
            }

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Age: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();
            System.out.print("Enter Average Score: ");
            int score = Integer.parseInt(scanner.nextLine());

            String lineData = studentID + "," + name + "," + age + "," + address + "," + score;
            studentService.addStudent(lineData);
            System.out.println("Student added successfully!");
            pause(); // Pause trước khi clear
            clearScreen();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findStudent() {
        System.out.print("====Find Student====\n");
        System.out.print("Enter studentID to find: ");
        String key = scanner.nextLine().trim();
        String studentData = studentService.findByStudentID(key);
        if (studentData != null) {
            System.out.println(formatStudent(studentData));
        } else {
            System.out.println("Student not found.");
        }
        pause(); 
        clearScreen();
    }

    private void updateStudent() {
        System.out.print("====Update Student====\n");
        System.out.print("Enter Student ID to update: ");
        String studentID = scanner.nextLine();
        String studentData = studentService.findByStudentID(studentID);
        if (studentData != null) {
            System.out.println(formatStudent(studentData));

            try {
                System.out.print("Enter new Student ID: ");
                String newStudentID = scanner.nextLine();
                System.out.print("Enter new Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter new Age: ");
                int age = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter new Address: ");
                String address = scanner.nextLine();
                System.out.print("Enter new Average Score: ");
                int score = Integer.parseInt(scanner.nextLine());

                String updatedLineData = newStudentID + "," + name + "," + age + "," + address + "," + score;
                studentService.updateByStudentID(studentID, updatedLineData);
                System.out.println("Student updated successfully.");
                pause(); // Pause trước khi clear
                clearScreen();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private void deleteStudent() {
        System.out.print("====Delete Student====\n");
        System.out.print("Enter Student ID to delete: ");
        String studentID = scanner.nextLine();
        String studentData = studentService.findByStudentID(studentID);
        if (studentData != null) {
            System.out.println(formatStudent(studentData));
            studentService.deleteByStudentID(studentID);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
        pause();
        clearScreen();
    }

    private String formatStudent(String line) {
        String[] parts = line.split(",");
        if (parts.length >= 6) {
            return "| id: " + parts[0]
                    + " | StudentID: " + parts[1]
                    + " | Name: " + parts[2]
                    + " | Age: " + parts[3]
                    + " | Address: " + parts[4]
                    + " | Score: " + parts[5];
        } else {
            return line;
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void pause() {
        System.out.println("Press Enter to continue");
        scanner.nextLine(); 
    }
}
