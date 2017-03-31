package com.cs506.accountable.dto;

import java.util.Date;

/**
 * Created by tkobl on 3/13/2017.
 */

public class Bill {
    private int billId;
    private String billName;
    private int userId;
    private int accountId;
    private double billAmount;
    private String dueDate;
    private int occuranceRte;

    public Bill() {
        this.billId = -1;
        this.billName = "";
        this.userId = -1;
        this.accountId = -1;
        this.billAmount = -1.00;
        this.dueDate = "";
        this.occuranceRte = 0;
    }

    public Bill(int billId, String billName, int userId, int accountId, double billAmount, String dueDate, int occuranceRte) {
        this.billId = billId;
        this.billName = billName;
        this.userId = userId;
        this.accountId = accountId;
        this.billAmount = billAmount;
        this.dueDate = dueDate;
        this.occuranceRte = occuranceRte;
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

    public void setOccuranceRte(int occuranceRte) {this.occuranceRte = occuranceRte;}

    public int getOccuranceRte() {
        return this.occuranceRte;
    }
}
