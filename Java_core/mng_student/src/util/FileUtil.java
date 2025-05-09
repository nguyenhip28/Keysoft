package util;

import java.io.*;
import java.util.*;


public class FileUtil {
    public static List<String> readFile(String path) {
        List<String> lines = new ArrayList<>();
        File file = new File(path);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File chưa tồn tại. Đã tạo file mới: " + path);
                }
            } catch (IOException e) {
                System.out.println("Không thể tạo file: " + e.getMessage());
            }
            return lines; // Trả về danh sách rỗng
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null)
                lines.add(line);
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
        return lines;
    }

    public static void writeFile(String path, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            lines.stream()  // Dùng Stream để ghi từng dòng vào file
                .forEach(line -> {
                    try {
                         bw.write(line);  // Ghi dòng vào file
                         bw.newLine();  // Thêm dòng mới
                    } catch (IOException e) {
                        System.out.println("Lỗi ghi dòng: " + e.getMessage());
                    }
                });
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    public static void copyFile(File source, File destination) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
            BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}