package service;

import model.Student;
import util.FileUtil;
import util.ValidateUtil;

import java.io.File;
import java.util.*;

public class StudentService {
    private static final String FILE_PATH = "D://Keysoft Java/Java_core/mng_student/src/data/students.txt";
    private List<Student> students;
    private int nextId = 1;

    public StudentService() {
        loadFromFile();
        updateNextId();
    }

    private void loadFromFile() {
        students = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println(" File does not exist at: " + file.getAbsolutePath());
            return;
        }

        List<String> lines = FileUtil.readFile(FILE_PATH);
        System.out.println("Reading data from file: " + file.getAbsolutePath());
        System.out.println("Number of lines read: " + lines.size());

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
        if (!ValidateUtil.isValidAge(student.getAge())) {
            throw new IllegalArgumentException("Invalid age!");
        }
        if (!ValidateUtil.isValidScore(student.getAvgScore())) {
            throw new IllegalArgumentException("Average score must be between 0 and 10!");
        }

        student.setId(nextId++); // Auto-increment ID
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
            boolean removed = students.removeIf(s -> s.getId() == id);
            if (removed) {
                System.out.println("Deleted student with ID: " + id);
                saveToFile();
            } else {
                System.out.println("Student not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format for deletion: " + idStr);
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
