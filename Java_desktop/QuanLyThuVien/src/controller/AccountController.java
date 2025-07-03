package Controller;

import DBConnect.DatabaseConnection;
import model.AccountModel;
import model.ProfileModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class AccountController {

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

    public boolean registerAccount(AccountModel user, ProfileModel profile) {
        try (Connection conn = DatabaseConnection.getJDBConnection()) {

            // 1. Insert vào bảng account (Lưu ý: password_hash đúng tên cột)
            String insertAccountQuery = "INSERT INTO account (user_code, username, password_hash) VALUES (?, ?, ?)";
            PreparedStatement stmtAccount = conn.prepareStatement(insertAccountQuery);
            stmtAccount.setString(1, user.getUserCode());
            stmtAccount.setString(2, user.getUsername());
            stmtAccount.setString(3, user.getPasswordHash());
            int accountResult = stmtAccount.executeUpdate();
            if (accountResult <= 0) {
                return false;
            }

            // 2. Insert vào bảng profile
            String insertProfileQuery = "INSERT INTO profile (user_code, full_name, email, age, gender, address, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmtProfile = conn.prepareStatement(insertProfileQuery);
            stmtProfile.setString(1, profile.getUserCode());
            stmtProfile.setString(2, profile.getFullName());
            stmtProfile.setString(3, profile.getEmail());
            stmtProfile.setInt(4, profile.getAge());
            stmtProfile.setString(5, profile.getGender());
            stmtProfile.setString(6, profile.getAddress());
            stmtProfile.setString(7, profile.getPhone());
            int profileResult = stmtProfile.executeUpdate();
            if (profileResult <= 0) {
                return false;
            }

            // 3. Lấy user_id từ account
            String getUserIdQuery = "SELECT user_id FROM account WHERE user_code = ?";
            PreparedStatement stmtGetId = conn.prepareStatement(getUserIdQuery);
            stmtGetId.setString(1, user.getUserCode());
            ResultSet rs = stmtGetId.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");

                // 4. Gán role mặc định (role_id = 2 là user thường)
                String insertRoleQuery = "INSERT INTO user_role (user_id, role_id) VALUES (?, ?)";
                PreparedStatement stmtRole = conn.prepareStatement(insertRoleQuery);
                stmtRole.setInt(1, userId);
                stmtRole.setInt(2, 2);
                stmtRole.executeUpdate();
            } else {
                return false;
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public AccountModel login(String username, String password) {
        String hashedPassword = hashPassword(password);
        String sql = "SELECT * FROM account WHERE username = ? AND password_hash = ?";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new AccountModel(
                        rs.getString("user_code"),
                        rs.getString("username"),
                        rs.getString("password_hash")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkUsernameExists(String username) {
        String sql = "SELECT 1 FROM account WHERE username = ?";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public String getUserCodeByUsername(String username) {
        String sql = "SELECT user_code FROM account WHERE username = ?";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("user_code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getRoleIdByUsername(String username) {
        String sql = "SELECT ur.role_id FROM account a JOIN user_role ur ON a.user_id = ur.user_id WHERE a.username = ?";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("role_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getUserIdByUserCode(String userCode) {
        String sql = "SELECT user_id FROM account WHERE user_code = ?";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
