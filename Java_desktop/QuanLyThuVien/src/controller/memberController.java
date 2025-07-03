package Controller;

import model.MemberModel;
import DBConnect.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberController {

    // Lấy danh sách tất cả member (không bao gồm admin)
    public List<MemberModel> getAllMembers() {
        List<MemberModel> list = new ArrayList<>();
        String sql = """
            SELECT m.member_id, m.member_code, m.user_id
            FROM member m
            JOIN user u ON m.user_id = u.user_id
            JOIN user_role ur ON u.user_id = ur.user_id
            WHERE ur.role_id != 1
        """;

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new MemberModel(
                        rs.getInt("member_id"),
                        rs.getString("member_code"),
                        rs.getInt("user_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Thêm mới member
    public boolean addMember(MemberModel member) {
        String sql = "INSERT INTO member (member_code, user_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, member.getMemberCode());
            ps.setInt(2, member.getUserId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Tìm member theo user_code
    public MemberModel getMemberByUserCode(String userCode) {
        String sql = """
            SELECT m.member_id, m.member_code, m.user_id
            FROM member m
            JOIN user u ON m.user_id = u.user_id
            WHERE u.user_code = ?
        """;

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userCode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new MemberModel(
                        rs.getInt("member_id"),
                        rs.getString("member_code"),
                        rs.getInt("user_id")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
