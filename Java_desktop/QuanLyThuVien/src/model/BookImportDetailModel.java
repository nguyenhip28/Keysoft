package model;

public class BookImportDetailModel {

    private int importDetailId;
    private int importId;
    private int bookId;
    private int quantity;

    public BookImportDetailModel(int importId, int bookId, int quantity) {
        this.importId = importId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public int getImportId() {
        return importId;
    }

    public void setImportId(int importId) {
        this.importId = importId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
