package Controller;

import Model.PatientModel;
import DBConnect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatientController {

    public List<PatientModel> getPatientsByPage(int page, int pageSize) throws SQLException {
        List<PatientModel> patients = new ArrayList<>();
        int offset = (page - 1) * pageSize;

        try (Connection conn = DatabaseConnection.getJDBConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM patients LIMIT " + pageSize + " OFFSET " + offset)) {

            while (rs.next()) {
                patients.add(new PatientModel(
                        rs.getString("patient_code"),
                        rs.getString("full_name"),
                        rs.getString("date_of_birth"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("email")
                ));
            }
        }
        return patients;
    }

    public List<PatientModel> searchPatients(String keyword) throws SQLException {
        List<PatientModel> patients = new ArrayList<>();
        String query = "SELECT * FROM patients WHERE patient_code = ? OR full_name LIKE ? OR phone_number = ?";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, keyword);
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, keyword);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    patients.add(new PatientModel(
                            rs.getString("patient_code"),
                            rs.getString("full_name"),
                            rs.getString("date_of_birth"),
                            rs.getString("gender"),
                            rs.getString("address"),
                            rs.getString("phone_number"),
                            rs.getString("email")
                    ));
                }
            }
        }
        return patients;
    }

    public boolean deletePatient(String patientCode) throws SQLException {
        try (Connection conn = DatabaseConnection.getJDBConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stAppt = conn.prepareStatement("DELETE FROM appointments WHERE patient_code = ?"); PreparedStatement stPat = conn.prepareStatement("DELETE FROM patients WHERE patient_code = ?")) {

                stAppt.setString(1, patientCode);
                stAppt.executeUpdate();

                stPat.setString(1, patientCode);
                int rows = stPat.executeUpdate();

                conn.commit();
                return rows > 0;
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        }
    }

    public PatientModel getPatientByCode(String patientCode) throws SQLException {
        String query = "SELECT * FROM patients WHERE patient_code = ?";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, patientCode);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new PatientModel(
                            rs.getString("patient_code"),
                            rs.getString("full_name"),
                            rs.getString("date_of_birth"),
                            rs.getString("gender"),
                            rs.getString("address"),
                            rs.getString("phone_number"),
                            rs.getString("email")
                    );
                }
            }
        }
        return null;
    }

    public boolean updatePatient(PatientModel patient) throws SQLException {
        String query = "UPDATE patients SET full_name=?, date_of_birth=?, gender=?, "
                + "address=?, phone_number=?, email=? WHERE patient_code=?";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, patient.getFullName());
            pstmt.setString(2, patient.getDateOfBirth());
            pstmt.setString(3, patient.getGender());
            pstmt.setString(4, patient.getAddress());
            pstmt.setString(5, patient.getPhoneNumber());
            pstmt.setString(6, patient.getEmail());
            pstmt.setString(7, patient.getPatientCode());

            return pstmt.executeUpdate() > 0;
        }
    }
}
