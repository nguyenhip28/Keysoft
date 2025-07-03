package controller;

import DBConnect.DatabaseConnection;
import java.sql.*;
import java.math.BigDecimal;

public class LateFeeRuleDAO {

    // Lấy fee_per_day theo số ngày trễ
    public static BigDecimal getFeePerDay(int lateDays) {
        BigDecimal fee = BigDecimal.ZERO;

        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            String query = "SELECT fee_per_day FROM late_fee_rules WHERE ? BETWEEN min_days AND IFNULL(max_days, 9999)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, lateDays);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    fee = rs.getBigDecimal("fee_per_day");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fee;
    }

    // Lấy rule_id theo số ngày trễ
    public static int getRuleIdByLateDays(int lateDays) {
        int ruleId = -1;

        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            String sql = "SELECT rule_id FROM late_fee_rules WHERE ? BETWEEN min_days AND IFNULL(max_days, 9999)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, lateDays);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    ruleId = rs.getInt("rule_id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ruleId;
    }
}
