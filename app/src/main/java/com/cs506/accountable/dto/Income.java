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
    private String payPeriod;
    private double hours;

    public Income() {
        this.incomeId = 0;
        this.userId = 0;
        this.accountId = 0;
        this.incomeName = "";
        this.amount = 0;
        this.payPeriod = "";
        this.hours = 0.00;
    }

    public Income(int incomeId, int userId, int accountId, String incomeName, String payPeriod,
                  double hours) {
        this.incomeId = incomeId;
        this.userId = userId;
        this.accountId = accountId;
        this.incomeName = incomeName;
        this.amount = amount;
        this.payPeriod = payPeriod;
        this.hours = hours;
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

    public double getHours() {return hours;}

    public void setHours(double hours) {this.hours = hours;}
}
