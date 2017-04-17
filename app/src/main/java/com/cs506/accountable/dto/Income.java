package com.cs506.accountable.dto;

/**
 * Created by tkobl on 3/14/2017.
 */

public class Income {

    private int incomeId;
    private int userId;
    private int accountId;
    private String incomeName;
    private double amount;
    private String date;
    private String payPeriod;

    public Income() {
        this.incomeId = -1;
        this.userId = -1;
        this.accountId = -1;
        this.incomeName = "";
        this.amount = -1.00;
        this.date = "";
        this.payPeriod = "";
    }

    public Income(int incomeId, int userId, int accountId, String incomeName, double amount, String date, String payPeriod) {
        this.incomeId = incomeId;
        this.userId = userId;
        this.accountId = accountId;
        this.incomeName = incomeName;
        this.amount = amount;
        this.date = date;
        this.payPeriod = payPeriod;
    }

    public int getIncomeId() {return incomeId;}

    public void setIncomeId(int incomeId) {this.incomeId = incomeId;}

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public int getAccountId() {return accountId;}

    public void setAccountId(int accountId) {this.accountId = accountId;}

    public String getIncomeName() {return incomeName;}

    public void setIncomeName(String incomeName) {this.incomeName = incomeName;}

    public String getPayPeriod() {return payPeriod;}

    public void setPayPeriod(String payPeriod) {this.payPeriod = payPeriod;}

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) { this.amount = amount;  }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
