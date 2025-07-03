package Controller;

import DBConnect.DatabaseConnection;
import model.ProfileModel;

import java.sql.*;
import model.ProfileModel;

public class ProfileController {

    public ProfileModel getProfileByUserCode(String userCode) {
        String sql = "SELECT * FROM profile WHERE user_code = ?";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userCode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new ProfileModel(
                        rs.getString("user_code"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("phone")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateProfile(ProfileModel profile) {
        String sql = "UPDATE profile SET full_name = ?, email = ?, age = ?, gender = ?, address = ?, phone = ? WHERE user_code = ?";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, profile.getFullName());
            ps.setString(2, profile.getEmail());
            ps.setInt(3, profile.getAge());
            ps.setString(4, profile.getGender());
            ps.setString(5, profile.getAddress());
            ps.setString(6, profile.getPhone());
            ps.setString(7, profile.getUserCode());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createProfile(ProfileModel profile) {
        String sql = "INSERT INTO profile(user_code, full_name, email, age, gender, address, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, profile.getUserCode());
            ps.setString(2, profile.getFullName());
            ps.setString(3, profile.getEmail());
            ps.setInt(4, profile.getAge());
            ps.setString(5, profile.getGender());
            ps.setString(6, profile.getAddress());
            ps.setString(7, profile.getPhone());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean updateProfileByUserCode(ProfileModel profile) {
        String sql = "UPDATE profile SET full_name = ?, email = ?, age = ?, gender = ?, address = ?, phone = ? WHERE user_code = ?";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, profile.getFullName());
            stmt.setString(2, profile.getEmail());
            stmt.setInt(3, profile.getAge());
            stmt.setString(4, profile.getGender());
            stmt.setString(5, profile.getAddress());
            stmt.setString(6, profile.getPhone());
            stmt.setString(7, profile.getUserCode());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
