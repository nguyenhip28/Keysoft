package Controller;

import model.memberModel;
import DatabaseConnection.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class memberController {

    // Lấy toàn bộ danh sách hội viên
    public List<memberModel> getAllMembers() {
        List<memberModel> members = new ArrayList<>();
        String sql = "SELECT * FROM members";

        try (Connection conn = DBConnect.getJDBConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                memberModel member = new memberModel(
                        rs.getInt("member_id"),
                        rs.getString("member_code"),
                        rs.getString("full_name"),
                        rs.getString("gender"),
                        rs.getInt("age"),
                        rs.getString("address"),
                        rs.getString("phone")
                );
                members.add(member);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return members;
    }

    // Thêm hội viên mới
    public boolean addMember(memberModel member) {
        String sql = "INSERT INTO members (member_code, full_name, gender, age, address, phone) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnect.getJDBConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, member.getMemberCode());
            pstmt.setString(2, member.getFullName());
            pstmt.setString(3, member.getGender());
            pstmt.setInt(4, member.getAge());
            pstmt.setString(5, member.getAddress());
            pstmt.setString(6, member.getPhone());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Tìm kiếm hội viên theo tên hoặc số điện thoại
    public List<memberModel> searchMembers(String keyword) {
        List<memberModel> members = new ArrayList<>();
        String sql = "SELECT * FROM members WHERE full_name LIKE ? OR phone LIKE ?";

        try (Connection conn = DBConnect.getJDBConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                memberModel member = new memberModel(
                        rs.getInt("member_id"),
                        rs.getString("member_code"),
                        rs.getString("full_name"),
                        rs.getString("gender"),
                        rs.getInt("age"),
                        rs.getString("address"),
                        rs.getString("phone")
                );
                members.add(member);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return members;
    }
}
