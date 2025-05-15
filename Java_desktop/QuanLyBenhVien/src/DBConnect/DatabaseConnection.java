package DBConnect;




import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vu Nguyen
 */
public class DatabaseConnection {

    public static Connection getJDBConnection() {

        String url = "jdbc:mysql://localhost:3306";
        String user = "root";
        String password = "123456";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try {
                return DriverManager.getConnection(url, user, password);
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public static void main(String[] args) {
        Connection conn = getJDBConnection();
        if (conn != null) {
            System.out.println("Success");

        } else {
            System.out.println("Fail");
        }
    }

}
