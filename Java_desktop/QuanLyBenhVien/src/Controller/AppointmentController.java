package Controller;

import Model.AppointmentModel;
import DBConnect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentController {

    public List<AppointmentModel> getAppointmentsByPage(int page, int pageSize) throws SQLException {
        List<AppointmentModel> appointments = new ArrayList<>();
        int offset = (page - 1) * pageSize;

        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            String query = """
                SELECT a.patient_code, p.full_name, a.appointment_date, a.appointment_time, a.symptoms
                FROM appointments a
                JOIN patients p ON a.patient_code = p.patient_code
                ORDER BY a.appointment_date DESC, a.appointment_time DESC
                LIMIT ? OFFSET ?
                """;

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, pageSize);
                stmt.setInt(2, offset);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        appointments.add(new AppointmentModel(
                                rs.getString("patient_code"),
                                rs.getString("full_name"),
                                rs.getDate("appointment_date").toLocalDate(),
                                rs.getTime("appointment_time").toLocalTime(),
                                rs.getString("symptoms")
                        ));
                    }
                }
            }
        }
        return appointments;
    }

    public int getTotalAppointments() throws SQLException {
        int totalRecords = 0;

        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            String countQuery = "SELECT COUNT(*) FROM appointments";

            try (PreparedStatement countStmt = conn.prepareStatement(countQuery); ResultSet rsCount = countStmt.executeQuery()) {
                if (rsCount.next()) {
                    totalRecords = rsCount.getInt(1);
                }
            }
        }
        return totalRecords;
    }

    public boolean createAppointment(AppointmentModel appointment) throws SQLException {
        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            // 1. Check if patient exists
            String sqlCheckPatient = "SELECT full_name FROM patients WHERE patient_code = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlCheckPatient)) {
                stmt.setString(1, appointment.getPatientCode());
                try (ResultSet rs = stmt.executeQuery()) {
                    if (!rs.next()) {
                        return false; 
                    }
                    appointment.setFullName(rs.getString("full_name"));
                }
            }

            // 2. Create appointment
            String sqlInsert = "INSERT INTO appointments (patient_code, appointment_date, appointment_time, symptoms) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {
                stmt.setString(1, appointment.getPatientCode());
                stmt.setDate(2, java.sql.Date.valueOf(appointment.getAppointmentDate()));
                stmt.setTime(3, java.sql.Time.valueOf(appointment.getAppointmentTime()));
                stmt.setString(4, appointment.getSymptoms());

                return stmt.executeUpdate() > 0;
            }
        }
    }

    public boolean deleteAppointment(String patientCode, LocalDate date, LocalTime time) throws SQLException {
        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            String sqlDelete = "DELETE FROM appointments WHERE patient_code = ? AND appointment_date = ? AND appointment_time = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sqlDelete)) {
                stmt.setString(1, patientCode);
                stmt.setDate(2, java.sql.Date.valueOf(date));
                stmt.setTime(3, java.sql.Time.valueOf(time));

                return stmt.executeUpdate() > 0;
            }
        }
    }
}
