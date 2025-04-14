package controller;

import model.Student;
import service.StudentService;

import java.util.List;
import java.util.Scanner;

public class StudentController {
    private final StudentService studentService = new StudentService();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n=== Manage Student ===");
            System.out.println("1. All Students");
            System.out.println("2. Add New Student");
            System.out.println("3. Find Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student by ID");
            System.out.println("0. Exit");
            System.out.print("CHOOSE: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> displayAll();
                case 2 -> addStudent();
                case 3 -> findStudent();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
            }
        } while (choice != 0);
    }

    private void displayAll() {
        List<Student> list = studentService.getAll();
        for (Student s : list) {
            System.out.println(s);
        }
    }

    private void addStudent() {
        try {
            System.out.print("Enter Student ID: ");
            String studentID = scanner.nextLine();
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Age: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();
            System.out.print("Enter Average Score: ");
            int score = Integer.parseInt(scanner.nextLine());

            Student newStudent = new Student(0, studentID, name, age, address, score);
            studentService.addStudent(newStudent);
            System.out.println("Added successfully!");

        } catch (Exception e) {
            System.out.println(" Error " + e.getMessage());
        }
    }

    private void findStudent() {
        System.out.print("Enter ID to find: ");
        String id = scanner.nextLine();
        Student s = studentService.findById(id);
        if (s != null) {
            System.out.println("Student: " + s);
        } else {
            System.out.println(" Can't find student.");
        }
    }

    private void updateStudent() {
        List<Student> list = studentService.getAll();
        for (Student s : list) {
            System.out.println(s);
        }
        System.out.print("Enter ID to update: ");
        String id = scanner.nextLine();
        Student s = studentService.findById(id);
        if (s != null) {
            try {
                System.out.print("Enter new Student ID: ");
                String studentID = scanner.nextLine();
                System.out.print("Enter new Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter new Age: ");
                int age = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter new Address: ");
                String address = scanner.nextLine();
                System.out.print("Enter new Average Score: ");
                int score = Integer.parseInt(scanner.nextLine());

                Student updated = new Student(s.getId(), studentID, name, age, address, score);
                studentService.updateStudent(id, updated);
                System.out.println("Updated successfully.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private void deleteStudent() {
        List<Student> list = studentService.getAll();
        for (Student s : list) {
            System.out.println(s);
        }
        System.out.print("Enter ID to delete: ");
        String id = scanner.nextLine();
        studentService.deleteById(id);
        System.out.println("Deleted if existed.");
    }
}
