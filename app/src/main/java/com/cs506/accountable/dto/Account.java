package com.cs506.accountable.dto;

/**
 * Created by tkobl on 3/14/2017.
 */

public class Account {

    private int accountId;
    private int userId;
    private String accountName;
    private double balance;
    private double startBalance;

    public Account() {
        this.accountId = -1;
        this.userId = -1;
        this.accountName = "";
        this.balance = -1.00;
        this.startBalance = -1.00;
    }

    public Account(int accountId, int userId, String accountName, Double balance, Double startBalance) {
        this.accountId = accountId;
        this.userId = userId;
        this.accountName = accountName;
        this.balance = balance;
        this.startBalance = startBalance;
    }

    public int getAccountId() {return accountId;}

    public void setAccountId(int accountId) {this.accountId = accountId;}

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public String getAccountName() {return accountName;}

    public void setAccountName(String accountName) {this.accountName = accountName;}

    public double getBalance() {return balance;}

    public void setBalance(double balance) {this.balance = balance;}

    public double getStartBalance() {return startBalance;}

    public void setStartBalance(double startBalance) {this.startBalance = startBalance;}

}
