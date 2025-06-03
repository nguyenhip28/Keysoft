package Model;

public class MedicineModel {
    private int medicineId;
    private String medicineName;
    private String unit;
    private int quantity;
    private double price;

    // Constructors
    public MedicineModel() {}

    public MedicineModel(int medicineId, String medicineName, String unit, int quantity, double price) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public int getMedicineId() { return medicineId; }
    public void setMedicineId(int medicineId) { this.medicineId = medicineId; }
    
    public String getMedicineName() { return medicineName; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }
    
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}