package model;

import java.sql.Timestamp;

public class BorrowModel {

    private int borrowId;
    private int memberId;
    private Timestamp borrowDate;
    private Timestamp expectedReturnDate;

    public BorrowModel() {
    }

    public BorrowModel(int memberId, Timestamp borrowDate, Timestamp expectedReturnDate) {
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.expectedReturnDate = expectedReturnDate;
    }

    public BorrowModel(int borrowId, int memberId, Timestamp borrowDate, Timestamp expectedReturnDate) {
        this.borrowId = borrowId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.expectedReturnDate = expectedReturnDate;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
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
}
