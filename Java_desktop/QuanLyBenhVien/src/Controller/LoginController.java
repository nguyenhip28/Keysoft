package Controller;

import Model.UserModel;
import DBConnect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class LoginController {

    public UserModel authenticateUser(String username, String password) {
        UserModel user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getJDBConnection();

            String useDBQuery = "USE hospital_management";
            conn.prepareStatement(useDBQuery).execute();

            String sql = "SELECT u.user_code, u.password, r.role_name FROM users u JOIN roles r ON u.role_id = r.role_id WHERE u.username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            rs = ps.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");

                if (BCrypt.checkpw(password, storedHash)) {
                    user = new UserModel();
                    user.setUserCode(rs.getString("user_code"));
                    user.setUsername(username);
                    user.setPassword(null); // Không lưu password vào model
                    user.setRoleName(rs.getString("role_name"));
                } else {
                    System.out.println("Sai mật khẩu cho username: " + username);
                }
            } else {
                System.out.println("Không tìm thấy user với username: " + username);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
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
                e.printStackTrace();
            }
        }

        return user;
    }
}
