package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentModel {

    private int appointmentId; // ✅ Thêm dòng này
    private String patientCode;
    private String fullName;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String symptoms;
    private String status;

    // Constructors
    public AppointmentModel() {
    }

    // Constructor đầy đủ (dùng khi SELECT từ DB)
    public AppointmentModel(int appointmentId, String patientCode, String fullName,
            LocalDate appointmentDate, LocalTime appointmentTime, String symptoms) {
        this.appointmentId = appointmentId;
        this.patientCode = patientCode;
        this.fullName = fullName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.symptoms = symptoms;
    }

    public AppointmentModel(String patientCode, LocalDate appointmentDate,
            LocalTime appointmentTime, String symptoms) {
        this.patientCode = patientCode;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.symptoms = symptoms;
    }

    // Getters and Setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
