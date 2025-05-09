package service;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class StudentService {
    private static final String FILE_PATH = "data/bigStudent.csv"; // Đường dẫn tới file lưu trữ dữ liệu sinh viên
    private static final Scanner scanner = new Scanner(System.in); // Scanner để nhận input từ người dùng

    // Constructor: Kiểm tra và tạo file nếu chưa tồn tại
    public StudentService() {
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs(); // Tạo thư mục cha nếu chưa tồn tại
        }
        if (!file.exists()) {
            try {
                file.createNewFile(); // Tạo file mới nếu chưa tồn tại
                System.out.println("File does not exist. New file created: " + file.getName());
            } catch (IOException e) {
                System.out.println("Failed to create file: " + e.getMessage());
            }
        } else {
            System.out.println("Already have file: " + file.getName());
        }
    }

    public void addStudent(String lineData) {
        try {
            int newId = getMaxIdFromFile() + 1; // Lấy ID lớn nhất từ file và tăng thêm 1

            String[] parts = lineData.split(",");
            String newLineData = newId + "," + String.join(",", parts); // Tạo dòng dữ liệu mới với ID tự tăng

            List<String> students = Files.readAllLines(Paths.get(FILE_PATH));
            students.add(newLineData); // Thêm sinh viên vào List
            Files.write(Paths.get(FILE_PATH), students); // Ghi lại tất cả các sinh viên vào file
            System.out.println("Added student successfully with ID: " + newId);

        } catch (IOException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    // Tìm sinh viên theo StudentID
    public String findByStudentID(String studentID) {
        try {
            return Files.lines(Paths.get(FILE_PATH))
                    .filter(line -> {
                        String[] parts = line.split(",");
                        return parts.length > 1 && parts[1].equalsIgnoreCase(studentID);
                    })
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        return null;
    }

    // Xóa sinh viên theo StudentID
    public void deleteByStudentID(String studentID) {
        try {
            List<String> students = Files.readAllLines(Paths.get(FILE_PATH));

            List<String> updatedStudents = students.stream()
                    .filter(line -> {
                        String[] parts = line.split(",");
                        return !parts[1].equalsIgnoreCase(studentID); // Xóa theo StudentID
                    })
                    .collect(Collectors.toList());

            if (students.size() != updatedStudents.size()) {
                System.out.print("Type 'y' to confirm deletion: ");
                String confirm = scanner.nextLine();

                if (confirm.equalsIgnoreCase("y")) {
                    Files.write(Paths.get(FILE_PATH), updatedStudents);
                    System.out.println();
                } else {
                    System.out.println("Cancelled.");
                }
            } else {
                System.out.println("Student not found.");
            }

        } catch (IOException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    public void updateByStudentID(String studentID, String newLineData) {
        try {
            List<String> students = Files.readAllLines(Paths.get(FILE_PATH));

            List<String> updatedStudents = students.stream()
                    .map(line -> {
                        String[] parts = line.split(",");
                        if (parts.length > 1 && parts[1].equalsIgnoreCase(studentID)) {
                            return parts[0] + "," + newLineData; 
                        }
                        return line;
                    })
                    .collect(Collectors.toList());

            Files.write(Paths.get(FILE_PATH), updatedStudents);
            System.out.println("Updated successfully.");

        } catch (IOException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    public void getAll() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    System.out.println("| ID: " + parts[0]
                            + " | StudentID: " + parts[1]
                            + " | Name: " + parts[2]
                            + " | Age: " + parts[3]
                            + " | Address: " + parts[4]
                            + " | Score: " + parts[5]);
                } else {
                    System.out.println("Invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public void displayByPage() {
        try {
            List<String> students = Files.readAllLines(Paths.get(FILE_PATH));
            if (students.isEmpty()) {
                System.out.println("This is a new file, add new student!");
                return;
            }

            final int pageSize = 10; // Số lượng sinh viên trên mỗi trang
            int totalPages = (int) Math.ceil((double) students.size() / pageSize);
            int currentPage = 1;

            while (true) {
                clearScreen();
                System.out.println("=== Student List ===");
                System.out.println("Page " + currentPage + " / " + totalPages);
                int start = (currentPage - 1) * pageSize;
                int end = Math.min(start + pageSize, students.size());

                for (int i = start; i < end; i++) {
                    String line = students.get(i);
                    String[] parts = line.split(",");
                    if (parts.length >= 6) {
                        System.out.println("| ID: " + parts[0]
                                + " | StudentID: " + parts[1]
                                + " | Name: " + parts[2]
                                + " | Age: " + parts[3]
                                + " | Address: " + parts[4]
                                + " | Score: " + parts[5]);
                    } else {
                        System.out.println("Invalid line: " + line);
                    }
                }

                // Điều hướng phân trang
                System.out.print("\nEnter: next | p: previous | q: quit >> ");
                String input = scanner.nextLine().trim().toLowerCase();

                if (input.equals("q"))
                    break;
                else if (input.equals("p")) {
                    if (currentPage > 1)
                        currentPage--;
                } else {
                    if (currentPage < totalPages)
                        currentPage++;
                }
            }

        } catch (IOException e) {
            System.out.println("Error displaying students: " + e.getMessage());
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public int getMaxIdFromFile() {
        int maxId = 0;
        try {
            maxId = Files.lines(Paths.get(FILE_PATH))
                    .map(line -> {
                        String[] parts = line.split(",");
                        return parts.length > 0 ? Integer.parseInt(parts[0]) : 0;
                    })
                    .max(Integer::compare)
                    .orElse(0); // Trả về 0 nếu không tìm thấy ID nào
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading max ID: " + e.getMessage());
        }
        return maxId;
    }
}
