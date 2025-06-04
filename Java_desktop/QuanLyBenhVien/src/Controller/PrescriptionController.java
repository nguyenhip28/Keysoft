package Controller;

import DBConnect.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class PrescriptionController {

    // Tìm thuốc theo tên hoặc ID
    public Map<String, String> searchMedicine(String keyword) throws SQLException {
        Map<String, String> result = new HashMap<>();

        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            String sql = "SELECT * FROM medicines WHERE medicine_name LIKE ? OR medicine_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");

            try {
                ps.setInt(2, Integer.parseInt(keyword));
            } catch (NumberFormatException e) {
                ps.setInt(2, -1); // Giá trị không hợp lệ để tránh khớp ID
            }

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result.put("medicine_id", String.valueOf(rs.getInt("medicine_id")));
                result.put("medicine_name", rs.getString("medicine_name"));
                result.put("price", String.valueOf(rs.getDouble("price")));
                result.put("unit", rs.getString("unit"));
            }
        }

        return result;
    }

    // Lưu đơn thuốc và chi tiết đơn thuốc, trả về prescription_id nếu thành công, -1 nếu lỗi
    public int savePrescription(String patientCode, String notes, double totalAmount, List<Map<String, String>> medicines) throws SQLException {
        int prescriptionId = -1;

        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            conn.setAutoCommit(false); // Bắt đầu transaction

            String insertPrescription = "INSERT INTO prescriptions(patient_code, notes, total_amount) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertPrescription, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, patientCode);
                ps.setString(2, notes);
                ps.setDouble(3, totalAmount);
                int inserted = ps.executeUpdate();

                if (inserted > 0) {
                    ResultSet generatedKeys = ps.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        prescriptionId = generatedKeys.getInt(1);
                    }
                }
            }

            if (prescriptionId != -1) {
                String insertDetails = "INSERT INTO prescription_details(prescription_id, medicine_id, quantity, dosage) VALUES (?, ?, ?, ?)";
                try (PreparedStatement detailPs = conn.prepareStatement(insertDetails)) {
                    for (Map<String, String> med : medicines) {
                        detailPs.setInt(1, prescriptionId);
                        detailPs.setInt(2, Integer.parseInt(med.get("medicine_id")));
                        detailPs.setInt(3, 1); // mặc định số lượng 1
                        detailPs.setString(4, med.get("dosage"));
                        detailPs.addBatch();
                    }
                    detailPs.executeBatch();
                }
                conn.commit();
            } else {
                conn.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return prescriptionId;
    }

    // Lấy đơn thuốc mới nhất theo mã bệnh nhân
    public Map<String, Object> getLatestPrescriptionByPatientCode(String patientCode) throws SQLException {
        Map<String, Object> result = new HashMap<>();

        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            String sql = "SELECT * FROM prescriptions WHERE patient_code = ? ORDER BY prescription_id DESC LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, patientCode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int prescriptionId = rs.getInt("prescription_id");
                result.put("prescription_id", prescriptionId);
                result.put("notes", rs.getString("notes"));
                result.put("total_amount", rs.getDouble("total_amount"));

                // Chi tiết thuốc
                result.put("medicines", getPrescriptionDetails(conn, prescriptionId));
            }
        }

        return result;
    }

    // Lấy danh sách tất cả đơn thuốc và chi tiết
    public List<Map<String, Object>> getAllPrescriptions() throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            String sql = "SELECT * FROM prescriptions ORDER BY prescription_id DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int prescriptionId = rs.getInt("prescription_id");
                Map<String, Object> prescription = new HashMap<>();
                prescription.put("prescription_id", prescriptionId);
                prescription.put("patient_code", rs.getString("patient_code"));
                prescription.put("notes", rs.getString("notes"));
                prescription.put("total_amount", rs.getDouble("total_amount"));
                prescription.put("medicines", getPrescriptionDetails(conn, prescriptionId));
                result.add(prescription);
            }
        }

        return result;
    }

    // Hàm phụ: lấy chi tiết thuốc theo prescription_id
    private List<Map<String, String>> getPrescriptionDetails(Connection conn, int prescriptionId) throws SQLException {
        List<Map<String, String>> medicines = new ArrayList<>();

        String sql = "SELECT pd.*, m.medicine_name, m.price, m.unit FROM prescription_details pd JOIN medicines m ON pd.medicine_id = m.medicine_id WHERE pd.prescription_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, prescriptionId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, String> item = new HashMap<>();
                item.put("medicine_id", String.valueOf(rs.getInt("medicine_id")));
                item.put("medicine_name", rs.getString("medicine_name"));
                item.put("dosage", rs.getString("dosage"));
                item.put("price", String.valueOf(rs.getDouble("price")));
                item.put("unit", rs.getString("unit"));
                medicines.add(item);
            }
        }

        return medicines;
    }
}
