package controller;

import DBConnect.DatabaseConnection;
import model.PenaltyModel;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.LateFeeRuleModel;

public class PenaltyController {

    public boolean insertPenalty(PenaltyModel penalty) {
        String sql = """
            INSERT INTO penalties (borrow_detail_id, calculated_on, late_days, fee_applied, rule_id)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, penalty.getBorrowDetailId());
            ps.setTimestamp(2, penalty.getCalculatedOn());
            ps.setInt(3, penalty.getLateDays());
            ps.setDouble(4, penalty.getFeeApplied());
            ps.setInt(5, penalty.getRuleId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public LateFeeRuleModel getRuleForLateDays(int lateDays) {
        String sql = "SELECT * FROM late_fee_rules WHERE ? BETWEEN min_days AND IFNULL(max_days, 9999)";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, lateDays);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                LateFeeRuleModel rule = new LateFeeRuleModel();
                rule.setRuleId(rs.getInt("rule_id"));
                rule.setMinDays(rs.getInt("min_days"));
                rule.setMaxDays(rs.getInt("max_days"));
                rule.setFeePerDay(rs.getDouble("fee_per_day"));
                return rule;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
