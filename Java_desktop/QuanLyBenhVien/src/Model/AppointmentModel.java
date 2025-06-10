package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentModel {

    private String patientCode;
    private String fullName;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String symptoms;

    // Thêm field status (không lưu vào database, chỉ để hiển thị)
    private String status;

    // Constructors
    public AppointmentModel() {
    }

    public AppointmentModel(String patientCode, String fullName, LocalDate appointmentDate,
            LocalTime appointmentTime, String symptoms) {
        this.patientCode = patientCode;
        this.fullName = fullName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.symptoms = symptoms;
    }

    // Getters and Setters
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
