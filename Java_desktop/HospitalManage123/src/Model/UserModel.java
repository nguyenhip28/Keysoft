package model;

import DBConnect.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserModel {
    public String[] login(String username, String password) {
        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            String sql = "USE hospital_management";
            conn.prepareStatement(sql).execute();

            sql = "SELECT user_code, r.role_name FROM users u JOIN roles r ON u.role_id = r.role_id WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new String[] {
                    rs.getString("user_code"),
                    rs.getString("role_name")
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
