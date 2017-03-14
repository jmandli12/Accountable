package com.cs506.accountable.dto;

/**
 * Created by mberger on 3/13/2017.
 */

public class Account {
    private String id;
    private String userId;
    private String accId;
    private String balance;

    public Account() {
        this.id = "";
        this.userId = "";
        this.accId = "";
        this.balance = "";
    }

    public Account(String id, String userId, String accId, String balance) {
        this.id = id;
        this.userId = userId;
        this.accId = accId;
        this.balance = balance;
    }

    public void setId(String id) { this.id = id; }
    public String getId() { return this.id; }

    public void setUserId(String userId) { this.userId = userId; }
    public String getUserId() {  return this.userId; }

    public void setAccId(String accId) {  this.accId = accId; }
    public String getAccId() { return this.accId; }

    public void setBalance(String balance) {
        this.balance = balance;
    }
    public String getBalance() {
        return this.balance;
    }

}