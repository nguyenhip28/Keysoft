package Controller;

import DBConnect.DatabaseConnection;
import Model.AppointmentDetailModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDetailController {

    // Lấy tất cả chi tiết lịch hẹn - đơn thuốc
    public List<AppointmentDetailModel> getAllDetails() throws SQLException {
        List<AppointmentDetailModel> list = new ArrayList<>();
        String sql = "SELECT * FROM appointment_prescriptions";

        try (Connection conn = DatabaseConnection.getJDBConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new AppointmentDetailModel(
                        rs.getInt("id"),
                        rs.getInt("appointment_id"),
                        rs.getInt("prescription_id")
                ));
            }
        }
        return list;
    }

    // Thêm mới chi tiết
    public boolean addDetail(AppointmentDetailModel detail) throws SQLException {
        String sql = "INSERT INTO appointment_prescriptions (appointment_id, prescription_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detail.getAppointmentId());
            stmt.setInt(2, detail.getPrescriptionId());

            return stmt.executeUpdate() > 0;
        }
    }

    // Xóa theo ID
    public boolean deleteDetail(int id) throws SQLException {
        String sql = "DELETE FROM appointment_prescriptions WHERE id = ?";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    // Cập nhật chi tiết
    public boolean updateDetail(AppointmentDetailModel detail) throws SQLException {
        String sql = "UPDATE appointment_prescriptions SET appointment_id = ?, prescription_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detail.getAppointmentId());
            stmt.setInt(2, detail.getPrescriptionId());
            stmt.setInt(3, detail.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    // Lấy chi tiết theo ID
    public AppointmentDetailModel getDetailById(int id) throws SQLException {
        String sql = "SELECT * FROM appointment_prescriptions WHERE id = ?";
        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new AppointmentDetailModel(
                        rs.getInt("id"),
                        rs.getInt("appointment_id"),
                        rs.getInt("prescription_id")
                );
            }
        }
        return null;
    }

    // Lấy chi tiết lịch sử khám theo mã bệnh nhân
    public List<String> getDetailsByPatientCode(String patientCode) throws SQLException {
        List<String> result = new ArrayList<>();

        String sql = "SELECT a.appointment_id, a.appointment_date, "
                + "p.prescription_id, p.notes AS prescription_notes, p.total_amount "
                + "FROM appointments a "
                + "JOIN appointment_prescriptions ap ON a.appointment_id = ap.appointment_id "
                + "JOIN prescriptions p ON ap.prescription_id = p.prescription_id "
                + "WHERE a.patient_code = ? "
                + "ORDER BY a.appointment_date DESC, p.prescription_id";

        try (Connection conn = DatabaseConnection.getJDBConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patientCode);
            ResultSet rs = stmt.executeQuery();

            int lastAppointmentId = -1;
            StringBuilder sb = new StringBuilder();

            while (rs.next()) {
                int appointmentId = rs.getInt("appointment_id");
                String appointmentDate = rs.getDate("appointment_date").toString();
                String prescriptionNotes = rs.getString("prescription_notes");
                double totalAmount = rs.getDouble("total_amount");
                int prescriptionId = rs.getInt("prescription_id");

                if (appointmentId != lastAppointmentId) {
                    if (lastAppointmentId != -1) {
                        sb.append("\n----------------------------\n");
                        result.add(sb.toString());
                        sb = new StringBuilder();
                    }
                    sb.append("Ngày khám: ").append(appointmentDate);
                }

                sb.append("\nGhi chú đơn thuốc: ").append(prescriptionNotes)
                        .append("\nChi tiết thuốc:\n");

                List<String> medicineDetails = getMedicineDetails(prescriptionId, conn);
                for (String med : medicineDetails) {
                    sb.append("- ").append(med).append("\n");
                }

                sb.append("Tổng tiền: ").append(totalAmount).append(" VND\n");

                lastAppointmentId = appointmentId;
            }

            if (sb.length() > 0) {
                sb.append("\n----------------------------\n");
                result.add(sb.toString());
            }
        }

        return result;
    }

    // Lấy danh sách thuốc trong đơn
    private List<String> getMedicineDetails(int prescriptionId, Connection conn) throws SQLException {
        List<String> medicineList = new ArrayList<>();

        String sql = "SELECT m.medicine_name, pd.quantity, pd.dosage, m.price "
                + "FROM prescription_details pd "
                + "JOIN medicines m ON pd.medicine_id = m.medicine_id "
                + "WHERE pd.prescription_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, prescriptionId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String detail = rs.getString("medicine_name")
                        + " | Số lượng: " + rs.getInt("quantity")
                        + " | Giá: " + rs.getDouble("price") + " VND";
                medicineList.add(detail);
            }
        }

        return medicineList;
    }
}
