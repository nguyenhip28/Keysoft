package service;

import model.Student;
import util.FileUtil;
import util.Validate;

import java.io.File;
import java.util.*;

public class StudentService {
    private static final String FILE_PATH = "D:/Keysoft Java/Java_core/mng_student/lib/students.txt";
    private List<Student> students;
    private int nextId = 1;
    private static final Scanner scanner = new Scanner(System.in);

    public StudentService() {
        loadFromFile();
        updateNextId();
    }

    private void loadFromFile() {
        students = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("File does not exist at: " + file.getAbsolutePath());
            return;
        }

        List<String> lines = FileUtil.readFile(FILE_PATH);
        System.out.println("Reading data from file: " + file.getAbsolutePath());
        System.out.println("Number of lines: " + lines.size());

        for (String line : lines) {
            try {
                Student s = Student.parse(line);
                students.add(s);
            } catch (Exception e) {
                System.out.println("Could not parse line: " + line);
                e.printStackTrace();
            }
        }
    }

    private void saveToFile() {
        List<String> lines = new ArrayList<>();
        for (Student s : students) {
            lines.add(s.toFileString());
        }
        FileUtil.writeFile(FILE_PATH, lines);
        System.out.println("Saved " + lines.size() + " students to file.");
    }

    private void updateNextId() {
        for (Student s : students) {
            if (s.getId() >= nextId) {
                nextId = s.getId() + 1;
            }
        }
    }

    public List<Student> getAll() {
        return students;
    }

    public void addStudent(Student student) {
        if (!Validate.isValidAge(student.getAge())) {
            throw new IllegalArgumentException("Invalid age!");
        }
        if (!Validate.isValidScore(student.getAvgScore())) {
            throw new IllegalArgumentException("Average score must be between 0 and 10!");
        }

        student.setId(nextId++);
        students.add(student);
        saveToFile();
        
    }

    public Student findById(String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            for (Student s : students) {
                if (s.getId() == id)
                    return s;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format: " + idStr);
        }
        return null;
    }

    public void deleteById(String idStr) {
        try {
            int id = Integer.parseInt(idStr);
    
            // Find the student by ID
            Student studentToDelete = null;
            for (Student s : students) {
                if (s.getId() == id) {
                    studentToDelete = s;
                    break;
                }
            }
    
            if (studentToDelete == null) {
                System.out.println("No student found with ID: " + id);
                return;
            }
    
            System.out.println("Are you sure you want to delete the following student?");
            System.out.println(studentToDelete);
            System.out.print("Enter 'y' to confirm deletion, or any other key to cancel: ");
    
            String confirm = scanner.nextLine();
    
            if (confirm.equalsIgnoreCase("y")) {
                students.remove(studentToDelete);
                System.out.println("Student with ID " + id + " has been deleted.");
                saveToFile();
            } else {
                System.out.println("Deletion cancelled.");
            }
    
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format: " + idStr);
        }
    }
    

    public void updateStudent(String idStr, Student newStudent) {
        Student existing = findById(idStr);
        if (existing != null) {
            existing.setStudentID(newStudent.getStudentID());
            existing.setStudentName(newStudent.getStudentName());
            existing.setAge(newStudent.getAge());
            existing.setAddress(newStudent.getAddress());
            existing.setAvgScore(newStudent.getAvgScore());
            saveToFile();
            System.out.println("Updated student with ID: " + idStr);
        } else {
            System.out.println("Student not found for update.");
        }
    }
}
