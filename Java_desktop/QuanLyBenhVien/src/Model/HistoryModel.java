// Model/HistoryModel.java

package Model;

import java.util.Date;

public class HistoryModel {
    private Date date;
    private String notes;
    private String prescriptionDetails;

    // getters & setters
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getPrescriptionDetails() { return prescriptionDetails; }
    public void setPrescriptionDetails(String prescriptionDetails) { this.prescriptionDetails = prescriptionDetails; }
}
