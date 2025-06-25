package Controller;

import model.memberModel;
import DatabaseConnection.DBConnect;
import Model.userModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class memberController {

    // Lấy tất cả member (loại bỏ admin)
    public List<memberModel> getAllMembers() {
        List<memberModel> list = new ArrayList<>();
        String sql = """
            SELECT m.member_id, m.member_code, m.user_id
            FROM members m
            JOIN users u ON m.user_id = u.user_id
            JOIN user_roles ur ON u.user_id = ur.user_id
            WHERE ur.role_id != 1
        """;

        try (Connection conn = DBConnect.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new memberModel(
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
    public boolean addMember(memberModel member) {
        String sql = "INSERT INTO members (member_code, user_id) VALUES (?, ?)";

        try (Connection conn = DBConnect.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, member.getMemberCode());
            ps.setInt(2, member.getUserId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Tìm user chưa là member (không phải admin) theo tên/sđt
    public List<userModel> searchUsersNotInMembers(String keyword) {
        List<userModel> list = new ArrayList<>();
        String sql = """
            SELECT u.* 
            FROM users u
            JOIN user_roles ur ON u.user_id = ur.user_id
            WHERE (u.full_name LIKE ? OR u.phone LIKE ?)
              AND ur.role_id != 1
              AND NOT EXISTS (
                  SELECT 1 FROM members m WHERE m.user_id = u.user_id
              )
        """;

        try (Connection conn = DBConnect.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new userModel(
                        rs.getString("user_code"),
                        rs.getString("full_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("password_hash")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Lấy danh sách user chưa là member (không phải admin)
    public List<userModel> getUsersNotInMembers() {
        List<userModel> list = new ArrayList<>();
        String sql = """
            SELECT u.*
            FROM users u
            JOIN user_roles ur ON u.user_id = ur.user_id
            WHERE ur.role_id != 1
              AND NOT EXISTS (
                  SELECT 1 FROM members m WHERE m.user_id = u.user_id
              )
        """;

        try (Connection conn = DBConnect.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new userModel(
                        rs.getString("user_code"),
                        rs.getString("full_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("password_hash")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Tìm member theo tên/sđt (loại bỏ admin)
    public List<memberModel> searchMembersByNameOrPhone(String keyword) {
        List<memberModel> list = new ArrayList<>();
        String sql = """
            SELECT m.member_id, m.member_code, m.user_id
            FROM members m
            JOIN users u ON m.user_id = u.user_id
            JOIN user_roles ur ON u.user_id = ur.user_id
            WHERE (u.full_name LIKE ? OR u.phone LIKE ?)
              AND ur.role_id != 1
        """;

        try (Connection conn = DBConnect.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new memberModel(
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
}
