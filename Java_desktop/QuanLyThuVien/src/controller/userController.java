package Controller;

import DatabaseConnection.DBConnect;
import Model.userModel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class userController {

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    // Đăng ký người dùng mới
    public boolean registerUser(userModel user, String ignoredRole) {
        Connection conn = null;
        PreparedStatement stmtUser = null;
        PreparedStatement stmtRole = null;
        PreparedStatement stmtUserRole = null;

        try {
            conn = DBConnect.getJDBConnection();
            conn.setAutoCommit(false); // Giao dịch

            // 1. Thêm user
            String sqlUser = "INSERT INTO users(user_code, full_name, username, email, age, gender, address, phone, password_hash, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";
            stmtUser = conn.prepareStatement(sqlUser, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtUser.setString(1, user.getUserCode());
            stmtUser.setString(2, user.getFullName());
            stmtUser.setString(3, user.getUsername());
            stmtUser.setString(4, user.getEmail());
            stmtUser.setInt(5, user.getAge());
            stmtUser.setString(6, user.getGender());
            stmtUser.setString(7, user.getAddress());
            stmtUser.setString(8, user.getPhone());
            stmtUser.setString(9, user.getPasswordHash());

            int affectedRows = stmtUser.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insert user failed.");
            }

            // Lấy user_id vừa tạo
            ResultSet rs = stmtUser.getGeneratedKeys();
            int userId = -1;
            if (rs.next()) {
                userId = rs.getInt(1);
            } else {
                throw new SQLException("Không lấy được user_id.");
            }

            int roleId = 2;

            // 3. Thêm vào user_roles
            String sqlUserRole = "INSERT INTO user_roles(user_id, role_id, assigned_at) VALUES (?, ?, NOW())";
            stmtUserRole = conn.prepareStatement(sqlUserRole);
            stmtUserRole.setInt(1, userId);
            stmtUserRole.setInt(2, roleId);
            stmtUserRole.executeUpdate();

            conn.commit(); // Giao dịch thành công
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback(); // Hoàn tác nếu lỗi
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                if (stmtUser != null) {
                    stmtUser.close();
                }
                if (stmtRole != null) {
                    stmtRole.close();
                }
                if (stmtUserRole != null) {
                    stmtUserRole.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public userModel login(String username, String password) {
        String hashedPassword = hashPassword(password); 

        try (Connection conn = DBConnect.getJDBConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new userModel(
                        rs.getString("user_code"),
                        rs.getString("full_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("password_hash")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getRoleIdByUsername(String username) {
        String sql = "SELECT ur.role_id "
                + "FROM users u "
                + "JOIN user_roles ur ON u.user_id = ur.user_id "
                + "WHERE u.username = ?";

        try (Connection conn = DBConnect.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("role_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; 
    }
}
