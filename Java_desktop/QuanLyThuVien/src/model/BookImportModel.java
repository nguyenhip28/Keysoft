package model;

import java.util.Date;

public class BookImportModel {

    private int importId;
    private Date importDate;
    private int memberId;

    public BookImportModel(int importId, Date importDate, int memberId) {
        this.importId = importId;
        this.importDate = importDate;
        this.memberId = memberId;
    }

    public BookImportModel(Date importDate, int memberId) {
        this.importDate = importDate;
        this.memberId = memberId;
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

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
