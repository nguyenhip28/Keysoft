package controller;

import DBConnect.DatabaseConnection;
import java.sql.*;
import java.math.BigDecimal;

public class PenaltyDAO {

    // Kiểm tra đã tồn tại penalty cho borrow_detail chưa
    public static boolean isPenaltyExists(int borrowDetailId) {
        String sql = "SELECT 1 FROM penalties WHERE borrow_detail_id = ?";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, borrowDetailId);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // đã tồn tại
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Thêm penalty mới nếu chưa tồn tại
    public static void insertPenalty(int borrowDetailId, int lateDays, BigDecimal feeApplied, int ruleId) {
        String sql = "INSERT INTO penalties (borrow_detail_id, calculated_on, late_days, fee_applied, rule_id) "
                + "VALUES (?, CURRENT_DATE(), ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, borrowDetailId);
            stmt.setInt(2, lateDays);
            stmt.setBigDecimal(3, feeApplied);
            stmt.setInt(4, ruleId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
