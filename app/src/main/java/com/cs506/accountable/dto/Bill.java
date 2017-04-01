package com.cs506.accountable.dto;

/**
 * Created by tkobl on 3/13/2017.
 */

public class Bill {
    private int billId;
    private int userId;
    private int accountId;
    private String billName;
    private double billAmount;
    private String dueDate;
    private int occurrenceRte;

    public Bill() {
        this.billId = -1;
        this.userId = -1;
        this.accountId = -1;
        this.billName = "";
        this.billAmount = -1.00;
        this.dueDate = "";
        this.occurrenceRte = -1;
    }

    public Bill(int billId, int userId, int accountId, String billName, double billAmount, String dueDate, int occurrenceRte) {
        this.billId = billId;
        this.userId = userId;
        this.accountId = accountId;
        this.billName = billName;
        this.billAmount = billAmount;
        this.dueDate = dueDate;
        this.occurrenceRte = occurrenceRte;
    }
    public int getBillId() {return billId;}

    public void setBillId(int billId) {this.billId = billId;}

    public void setBillName(String billName) { this.billName = billName; }

    public String getBillName() { return this.billName; }

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public int getAccountId() {return accountId;}

    public void setAccountId(int accountId) {this.accountId = accountId;}

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public double getBillAmount() {
        return this.billAmount;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setOccurrenceRte(int occurrenceRte) { this.occurrenceRte = occurrenceRte; }

    public int getOccurrenceRte() {
        return this.occurrenceRte;
    }
}
