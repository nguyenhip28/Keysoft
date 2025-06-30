package model;

import java.sql.Timestamp;

public class PenaltyModel {

    private int penaltyId;
    private int borrowDetailId;
    private Timestamp calculatedOn;
    private int lateDays;
    private double feeApplied;
    private int ruleId;

    // Getters and setters
    public int getPenaltyId() {
        return penaltyId;
    }

    public void setPenaltyId(int penaltyId) {
        this.penaltyId = penaltyId;
    }

    public int getBorrowDetailId() {
        return borrowDetailId;
    }

    public void setBorrowDetailId(int borrowDetailId) {
        this.borrowDetailId = borrowDetailId;
    }

    public Timestamp getCalculatedOn() {
        return calculatedOn;
    }

    public void setCalculatedOn(Timestamp calculatedOn) {
        this.calculatedOn = calculatedOn;
    }

    public int getLateDays() {
        return lateDays;
    }

    public void setLateDays(int lateDays) {
        this.lateDays = lateDays;
    }

    public double getFeeApplied() {
        return feeApplied;
    }

    public void setFeeApplied(double feeApplied) {
        this.feeApplied = feeApplied;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }
}
