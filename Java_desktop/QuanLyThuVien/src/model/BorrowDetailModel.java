package model;

import java.sql.Timestamp;

public class BorrowDetailModel {

    private int borrowDetailId;
    private int borrowId;
    private int bookId;
    private int quantity;
    private String bookTitle;              // Tên sách (để hiển thị)
    private Timestamp borrowDate;          // Ngày mượn
    private Timestamp expectedReturnDate;  // Ngày dự kiến trả
    private Timestamp actualReturnDate;    // Ngày thực trả
    private String status;                 // "Đang mượn" / "Đã trả"
    private double lateFee;                // Phí trễ hạn
    private double totalFee;               // Tổng phí

    public BorrowDetailModel() {
    }

    public BorrowDetailModel(int borrowDetailId, int borrowId, int bookId, int quantity,
            String bookTitle, Timestamp borrowDate, Timestamp expectedReturnDate,
            Timestamp actualReturnDate, String status, double lateFee, double totalFee) {
        this.borrowDetailId = borrowDetailId;
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.expectedReturnDate = expectedReturnDate;
        this.actualReturnDate = actualReturnDate;
        this.status = status;
        this.lateFee = lateFee;
        this.totalFee = totalFee;
    }

    // Getters and Setters
    public int getBorrowDetailId() {
        return borrowDetailId;
    }

    public void setBorrowDetailId(int borrowDetailId) {
        this.borrowDetailId = borrowDetailId;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
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

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Timestamp getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Timestamp borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Timestamp getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Timestamp expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public Timestamp getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(Timestamp actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getLateFee() {
        return lateFee;
    }

    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }
}
