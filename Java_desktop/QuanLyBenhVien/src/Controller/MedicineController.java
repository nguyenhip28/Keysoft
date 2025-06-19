package Controller;

import Model.MedicineModel;
import DBConnect.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineController {

    // Đếm tổng số thuốc
    public int getTotalMedicines() throws SQLException {
        String sql = "SELECT COUNT(*) FROM medicines";
        try (Connection conn = DatabaseConnection.getJDBConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    // Phân trang danh sách thuốc
    public List<MedicineModel> getMedicinesByPage(int page, int pageSize) throws SQLException {
        List<MedicineModel> medicines = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM medicines LIMIT ? OFFSET ?";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pageSize);
            stmt.setInt(2, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    medicines.add(new MedicineModel(
                            rs.getInt("medicine_id"),
                            rs.getString("medicine_name"),
                            rs.getString("unit"),
                            rs.getInt("quantity"),
                            rs.getDouble("price")
                    ));
                }
            }
        }
        return medicines;
    }

    // Thêm thuốc mới
    public boolean addMedicine(MedicineModel medicine) throws SQLException {
        String sql = "INSERT INTO medicines (medicine_name, unit, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, medicine.getMedicineName());
            stmt.setString(2, medicine.getUnit());
            stmt.setInt(3, medicine.getQuantity());
            stmt.setDouble(4, medicine.getPrice());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        medicine.setMedicineId(rs.getInt(1));
                    }
                }
                return true;
            }
        }
        return false;
    }

    // Xóa thuốc theo tên
    public boolean deleteMedicine(String medicineName) throws SQLException {
        String sql = "DELETE FROM medicines WHERE medicine_name = ?";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medicineName);
            return stmt.executeUpdate() > 0;
        }
    }

    // Lấy danh sách thuốc tồn kho dưới ngưỡng
    public List<MedicineModel> getLowStockMedicines(int threshold) throws SQLException {
        List<MedicineModel> medicines = new ArrayList<>();
        String sql = "SELECT * FROM medicines WHERE quantity < ?";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, threshold);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    medicines.add(new MedicineModel(
                            rs.getInt("medicine_id"),
                            rs.getString("medicine_name"),
                            rs.getString("unit"),
                            rs.getInt("quantity"),
                            rs.getDouble("price")
                    ));
                }
            }
        }
        return medicines;
    }

    // Tìm thuốc theo tên
    public MedicineModel getMedicineByName(String medicineName) throws SQLException {
        String sql = "SELECT * FROM medicines WHERE medicine_name = ?";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medicineName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MedicineModel(
                            rs.getInt("medicine_id"),
                            rs.getString("medicine_name"),
                            rs.getString("unit"),
                            rs.getInt("quantity"),
                            rs.getDouble("price")
                    );
                }
            }
        }
        return null;
    }

    // Reset AUTO_INCREMENT
    public void resetAutoIncrement() throws SQLException {
        String sql = "ALTER TABLE medicines AUTO_INCREMENT = 1";
        try (Connection conn = DatabaseConnection.getJDBConnection(); Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
        }
    }

    // Cập nhật thông tin thuốc (nếu cần)
    public boolean updateMedicine(MedicineModel medicine) throws SQLException {
        String sql = "UPDATE medicines SET unit = ?, quantity = ?, price = ? WHERE medicine_name = ?";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medicine.getUnit());
            stmt.setInt(2, medicine.getQuantity());
            stmt.setDouble(3, medicine.getPrice());
            stmt.setString(4, medicine.getMedicineName());

            return stmt.executeUpdate() > 0;
        }
    }
}
