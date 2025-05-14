package quanlybenhvien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountControler {

    public static Connection getJDBCConnection() {
        String url = "jdbc:mysql://localhost:3306/user";
        String user = "root";
        String password = "123456";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccountControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean checkLogin(String username, String password, String role) {
        Connection conn = getJDBCConnection();
        if (conn == null)
            return false;
        String sql = "SELECT * FROM accounts WHERE email = ? AND password = ? AND role = ?";
        try (java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            java.sql.ResultSet rs = stmt.executeQuery();
            boolean exists = rs.next();
            rs.close();
            stmt.close();
            conn.close();
            return exists;
        } catch (SQLException ex) {
            Logger.getLogger(AccountControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {
        Connection conn = getJDBCConnection();

        if (conn != null) {
            System.out.println("Ket noi thanh cong!");
        } else {
            System.out.println("Ket noi that bai!");
        }
    }
}
