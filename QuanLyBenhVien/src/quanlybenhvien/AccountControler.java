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

    public static void main(String[] args) {
        Connection conn = getJDBCConnection();

        if (conn != null) {
            System.out.println("Ket noi thanh cong!");
        } else {
            System.out.println("Ket noi that bai!");
        }
    }
}
