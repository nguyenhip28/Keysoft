package Controller;

import Model.MedicineModel;
import DBConnect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicineController {

    public int getTotalMedicines() throws SQLException {
        try (Connection conn = DatabaseConnection.getJDBConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM medicines")) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public List<MedicineModel> getMedicinesByPage(int page, int pageSize) throws SQLException {
        List<MedicineModel> medicines = new ArrayList<>();
        int offset = (page - 1) * pageSize;

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM medicines LIMIT ? OFFSET ?")) {

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

    public boolean deleteMedicine(String medicineName) throws SQLException {
        String sql = "DELETE FROM medicines WHERE medicine_name = ?";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medicineName);
            return stmt.executeUpdate() > 0;
        }
    }

    public List<MedicineModel> getLowStockMedicines(int threshold) throws SQLException {
        List<MedicineModel> medicines = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM medicines WHERE quantity < ?")) {

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

    public void resetAutoIncrement() throws SQLException {
        try (Connection conn = DatabaseConnection.getJDBConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("ALTER TABLE medicines AUTO_INCREMENT = 1");
        }
    }
}
