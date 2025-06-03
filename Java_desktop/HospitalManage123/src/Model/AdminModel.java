package model;

import DBConnect.DatabaseConnection;
import DBConnect.PermissionService;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AdminModel {
    private final int pageSize = 5;

    public List<String> getPermissions(String userCode) {
        return PermissionService.getPermissionsByUserCode(userCode);
    }

    public int countAppointments() {
        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            String query = "SELECT COUNT(*) FROM appointments";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<String> getAppointmentsByPage(int page) {
        List<String> appointments = new ArrayList<>();
        int offset = (page - 1) * pageSize;

        String query = """
            SELECT a.patient_code, p.full_name, a.appointment_date, a.appointment_time, a.symptoms
            FROM appointments a
            JOIN patients p ON a.patient_code = p.patient_code
            ORDER BY a.appointment_date DESC, a.appointment_time DESC
            LIMIT ? OFFSET ?
        """;

        try (Connection conn = DatabaseConnection.getJDBConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, pageSize);
            stmt.setInt(2, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String info = "Mã BN: " + rs.getString("patient_code") +
                                  "\nBệnh nhân: " + rs.getString("full_name") +
                                  "\nNgày: " + rs.getDate("appointment_date").toLocalDate() +
                                  "\nGiờ: " + rs.getTime("appointment_time").toLocalTime() +
                                  "\nTriệu chứng: " + rs.getString("symptoms") +
                                  "\n------------------------\n";
                    appointments.add(info);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }
}
