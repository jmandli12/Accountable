package com.cs506.accountable.dto;

import java.util.Date;

/**
 * Created by tkobl on 3/13/2017.
 */

public class Bill {
    private String billName;
    private int billId;
    private int userId;
    private int accountId;
    private double billAmount;
    private String dueDate;
    private int occuranceRte;

    public Bill() {
        this.billName = "";
        this.billAmount = 0;
        this.dueDate = null;
        this.occuranceRte = 0;
    }

    public Bill(int billId, int userId, int accountId, String billName, double billAmount, String dueDate, int occuranceRte) {
        this.billId = billId;
        this.userId = userId;
        this.accountId = accountId;
        this.billName = billName;
        this.billAmount = billAmount;
        this.dueDate = dueDate;
        this.occuranceRte = occuranceRte;
    }

    public Bill(long l, int i, int i1, String arg, String arg1, int i2, int i3, boolean b) {
      //Created this to fix an error in DataSource.java, the constructor above was invalid
        //Not sure which one we want
    }

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public int getAccountId() {return accountId;}

    public void setAccountId(int accountId) {this.accountId = accountId;}

    public int getBillId() {return billId;}

    public void setBillId(int billId) {this.billId = billId;}

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillName() {
        return this.billName;
    }

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
