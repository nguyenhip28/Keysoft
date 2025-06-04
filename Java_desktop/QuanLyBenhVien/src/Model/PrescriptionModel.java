package Model;

import DBConnect.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class PrescriptionModel {

    public int insertPrescription(String patientCode, String notes, double totalAmount) throws SQLException {
        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            String insertSql = "INSERT INTO prescriptions(patient_code, notes, total_amount) VALUES (?, ?, ?)";
            PreparedStatement insertPs = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            insertPs.setString(1, patientCode);
            insertPs.setString(2, notes);
            insertPs.setDouble(3, totalAmount);

            int rows = insertPs.executeUpdate();
            if (rows > 0) {
                ResultSet rs = insertPs.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public void insertPrescriptionDetails(int prescriptionId, List<Map<String, String>> medicines) throws SQLException {
        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            String sql = "INSERT INTO prescription_details(prescription_id, medicine_id, quantity, dosage) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            for (Map<String, String> med : medicines) {
                ps.setInt(1, prescriptionId);
                ps.setInt(2, Integer.parseInt(med.get("medicine_id")));
                ps.setInt(3, 1);
                ps.setString(4, med.get("dosage"));
                ps.addBatch();
            }

            ps.executeBatch();
        }
    }

    public Map<String, String> findMedicineByName(String name) throws SQLException {
        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            String sql = "SELECT * FROM medicines WHERE medicine_name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Map<String, String> med = new HashMap<>();
                med.put("medicine_id", String.valueOf(rs.getInt("medicine_id")));
                med.put("price", rs.getString("price"));
                med.put("unit", rs.getString("unit"));
                return med;
            }
        }
        return null;
    }

    public List<Map<String, String>> getPrescriptionDetails(int prescriptionId) throws SQLException {
        List<Map<String, String>> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            String sql = "SELECT pd.*, m.medicine_name, m.price, m.unit FROM prescription_details pd JOIN medicines m ON pd.medicine_id = m.medicine_id WHERE pd.prescription_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, prescriptionId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, String> item = new HashMap<>();
                item.put("medicine_id", String.valueOf(rs.getInt("medicine_id")));
                item.put("medicine_name", rs.getString("medicine_name"));
                item.put("dosage", rs.getString("dosage"));
                item.put("price", String.valueOf(rs.getDouble("price")));
                item.put("unit", rs.getString("unit"));
                list.add(item);
            }
        }
        return list;
    }

    public Map<String, Object> getLatestPrescriptionByPatient(String patientCode) throws SQLException {
        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            String sql = "SELECT * FROM prescriptions WHERE patient_code = ? ORDER BY prescription_id DESC LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, patientCode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Map<String, Object> result = new HashMap<>();
                result.put("prescription_id", rs.getInt("prescription_id"));
                result.put("notes", rs.getString("notes"));
                result.put("total_amount", rs.getDouble("total_amount"));
                return result;
            }
        }
        return null;
    }

    public List<Map<String, Object>> getAllPrescriptionsWithDetails() throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            String sql = "SELECT * FROM prescriptions ORDER BY prescription_id DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> record = new HashMap<>();
                record.put("prescription_id", rs.getInt("prescription_id"));
                record.put("patient_code", rs.getString("patient_code"));
                record.put("notes", rs.getString("notes"));
                record.put("total_amount", rs.getDouble("total_amount"));

                List<Map<String, String>> details = getPrescriptionDetails(rs.getInt("prescription_id"));
                record.put("details", details);
                result.add(record);
            }
        }
        return result;
    }
}
