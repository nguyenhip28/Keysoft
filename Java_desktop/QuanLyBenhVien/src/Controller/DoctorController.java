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

public class DoctorController {

    private String userCode;

    public DoctorController(String userCode) {
        this.userCode = userCode;
    }

    public List<AppointmentModel> getAppointmentsByPage(int page, int pageSize) throws SQLException {
        List<AppointmentModel> appointments = new ArrayList<>();
        int offset = (page - 1) * pageSize;

        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            String query = """
                SELECT a.appointment_id, a.patient_code, p.full_name, 
                       a.appointment_date, a.appointment_time, a.symptoms
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
                        int appointmentId = rs.getInt("appointment_id");
                        String patientCode = rs.getString("patient_code");
                        String fullName = rs.getString("full_name");
                        LocalDate date = rs.getDate("appointment_date").toLocalDate();
                        LocalTime time = rs.getTime("appointment_time").toLocalTime();
                        String symptoms = rs.getString("symptoms");

                        AppointmentModel model = new AppointmentModel(appointmentId, patientCode, fullName, date, time, symptoms);
                        appointments.add(model);
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
}
