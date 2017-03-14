package com.cs506.accountable.dto;

/**
 * Created by tkobl on 3/13/2017.
 */

public class Bill {
    private String billName;
    private String billAmount;
    private String dueDate;
    private String occurance;

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
