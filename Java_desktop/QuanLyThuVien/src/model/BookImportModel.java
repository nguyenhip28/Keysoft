package model;

import java.util.Date;

public class BookImportModel {

    private int importId;
    private Date importDate;
    private String importerName; // ✅ sửa từ memberId thành importerName

    public BookImportModel() {
    }

    public BookImportModel(int importId, Date importDate, String importerName) {
        this.importId = importId;
        this.importDate = importDate;
        this.importerName = importerName;
    }

    public BookImportModel(Date importDate, String importerName) {
        this.importDate = importDate;
        this.importerName = importerName;
    }

    public int getImportId() {
        return importId;
    }

    public void setImportId(int importId) {
        this.importId = importId;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public String getImporterName() {
        return importerName;
    }

    public void setImporterName(String importerName) {
        this.importerName = importerName;
    }
}
