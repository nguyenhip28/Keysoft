package controller;

import DBConnect.DatabaseConnection;
import model.LateFeeRuleModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LateFeeRuleController {

    public boolean insertLateFeeRule(LateFeeRuleModel rule) {
        String sql = "INSERT INTO late_fee_rules (min_days, max_days, fee_per_day) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, rule.getMinDays());

            // Cho phép maxDays NULL nếu trống
            if (rule.getMaxDays() == -1) {
                ps.setNull(2, Types.INTEGER);
            } else {
                ps.setInt(2, rule.getMaxDays());
            }

            ps.setDouble(3, rule.getFeePerDay());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateLateFeeRule(int ruleId, LateFeeRuleModel rule) {
        String sql = "UPDATE late_fee_rules SET min_days = ?, max_days = ?, fee_per_day = ? WHERE rule_id = ?";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, rule.getMinDays());

            if (rule.getMaxDays() == -1) {
                ps.setNull(2, Types.INTEGER);
            } else {
                ps.setInt(2, rule.getMaxDays());
            }

            ps.setDouble(3, rule.getFeePerDay());
            ps.setInt(4, ruleId);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<LateFeeRuleModel> getAllRules() {
        List<LateFeeRuleModel> list = new ArrayList<>();
        String sql = "SELECT * FROM late_fee_rules ORDER BY min_days ASC";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LateFeeRuleModel rule = new LateFeeRuleModel();
                rule.setRuleId(rs.getInt("rule_id"));
                rule.setMinDays(rs.getInt("min_days"));
                int max = rs.getInt("max_days");
                if (rs.wasNull()) {
                    rule.setMaxDays(-1); // đánh dấu không giới hạn
                } else {
                    rule.setMaxDays(max);
                }
                rule.setFeePerDay(rs.getDouble("fee_per_day"));
                list.add(rule);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
}
