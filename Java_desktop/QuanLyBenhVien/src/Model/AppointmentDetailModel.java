package Model;

public class AppointmentDetailModel {

    private int id;
    private int appointmentId;
    private int prescriptionId;

    // Constructor có id (thường dùng khi lấy từ DB)
    public AppointmentDetailModel(int id, int appointmentId, int prescriptionId) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.prescriptionId = prescriptionId;
    }

    // Constructor không có id (thường dùng khi thêm mới)
    public AppointmentDetailModel(int appointmentId, int prescriptionId) {
        this.appointmentId = appointmentId;
        this.prescriptionId = prescriptionId;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }
}
