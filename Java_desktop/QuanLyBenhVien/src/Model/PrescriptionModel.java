package Model;

import java.util.List;

public class PrescriptionModel {

    private int prescriptionId;
    private String patientCode;
    private String notes;
    private double totalAmount;
    private List<MedicineModel> medicines;

    public PrescriptionModel() {
    }

    public PrescriptionModel(int prescriptionId, String patientCode, String notes, double totalAmount, List<MedicineModel> medicines) {
        this.prescriptionId = prescriptionId;
        this.patientCode = patientCode;
        this.notes = notes;
        this.totalAmount = totalAmount;
        this.medicines = medicines;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<MedicineModel> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<MedicineModel> medicines) {
        this.medicines = medicines;
    }

    @Override
    public String toString() {
        return "PrescriptionModel{"
                + "prescriptionId=" + prescriptionId
                + ", patientCode='" + patientCode + '\''
                + ", notes='" + notes + '\''
                + ", totalAmount=" + totalAmount
                + ", medicines=" + medicines
                + '}';
    }
}
