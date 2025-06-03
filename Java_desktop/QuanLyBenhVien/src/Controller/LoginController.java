package controller;

import model.UserModel;
import DBConnect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    public UserModel authenticateUser(String username, String password) {
        UserModel user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getJDBConnection();

            // Select database
            String useDBQuery = "USE hospital_management";
            conn.prepareStatement(useDBQuery).execute();

            // SQL query to get user information with role
            String sql = "SELECT u.user_code, r.role_name FROM users u JOIN roles r ON u.role_id = r.role_id WHERE u.username = ? AND u.password = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {
                user = new UserModel();
                user.setUserCode(rs.getString("user_code"));
                user.setUsername(username);
                user.setPassword(password); // Note: In real application, you shouldn't store plain password
                user.setRoleName(rs.getString("role_name"));
            }

        } catch (SQLException e) {
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }

        return user;
    }
}
