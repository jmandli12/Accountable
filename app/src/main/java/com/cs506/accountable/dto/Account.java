package com.cs506.accountable.dto;

/**
 * Created by tkobl on 3/14/2017.
 */

public class Account {

    private int accountId;
    private int userId;
    private String accountName;
    private double balance;

    public Account() {
        this.accountId = 0;
        this.userId = 0;
        this.accountName = "";
        this.balance = 0.00;
    }

    public Account(int accountId, int userId, String accountName, Double balance) {
        this.accountId = accountId;
        this.userId = userId;
        this.accountName = accountName;
        this.balance = balance;
    }

    public int getAccountId() {return accountId;}

    public void setAccountId(int accountId) {this.accountId = accountId;}

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public String getAccountName() {return accountName;}

    public void setAccountName(String accountName) {this.accountName = accountName;}

    public double getBalance() {return balance;}

    public void setBalance(double balance) {this.balance = balance;}

}
