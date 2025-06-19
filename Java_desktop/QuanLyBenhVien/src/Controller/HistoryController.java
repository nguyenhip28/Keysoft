// Controller/HistoryController.java
package Controller;

import DBConnect.DatabaseConnection;
import Model.HistoryModel;
import java.sql.*;
import java.util.*;

public class HistoryController {

    public List<HistoryModel> getHistoryByPatient(String patientCode) throws SQLException {
        List<HistoryModel> list = new ArrayList<>();
        Connection conn = DatabaseConnection.getJDBConnection();
        String sql = "SELECT a.date, ad.notes, p.details FROM appointment a "
                + "JOIN appointment_detail ad ON a.id = ad.appointment_id "
                + "JOIN prescription p ON ad.prescription_id = p.id "
                + "WHERE a.patient_code = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, patientCode);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            HistoryModel h = new HistoryModel();
            h.setDate(rs.getDate("date"));
            h.setNotes(rs.getString("notes"));
            h.setPrescriptionDetails(rs.getString("details"));
            list.add(h);
        }
        return list;
    }
}
