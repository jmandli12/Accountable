package com.cs506.accountable.dto;

/**
 * Created by mberger on 3/13/2017.
 */

public class Bill {
    private String id;
    private String userId;
    private String accId;
    private String billName;
    private String billAmount;
    private String dueDate;
    private String occurance;

    public Bill() {
        this.id = "";
        this.userId = "";
        this.accId = "";
        this.billName = "";
        this.billAmount = "";
        this.dueDate = "";
    }

    public Bill(String id, String userId, String accId, String billName, String billAmount, String dueDate, String occurance) {
        this.id = id;
        this.userId = userId;
        this.accId = accId;
        this.billName = billName;
        this.billAmount = billAmount;
        this.dueDate = dueDate;
    }

    public void setId(String id) { this.id = id; }
    public String getId() { return this.id; }

    public void setUserId(String userId) { this.userId = userId; }
    public String getUserId() { return this.userId; }

    public void setAccId(String accId) { this.accId = accId; }
    public String getAccId() { return this.accId; }

    public void setBillName(String billName) {
        this.billName = billName;
    }
    public String getBillName() {
        return this.billName;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }
    public String getBillAmount() {
        return this.billAmount;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public String getDueDate() {
        return this.dueDate;
    }

    public void setOccurance(String occurance) {
        this.occurance = occurance;
    }
    public String getOccurance() {
        return this.occurance;
    }
}
