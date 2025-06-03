package DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PermissionService {

    public static List<String> getPermissionsByUserCode(String userCode) {
        List<String> permissions = new ArrayList<>();
        try {
            try (Connection conn = DatabaseConnection.getJDBConnection()) {  
                String sql = "SELECT feature_name FROM user_permissions WHERE user_code = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, userCode);
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            permissions.add(rs.getString("feature_name"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            
        }
        return permissions;
    }
}
