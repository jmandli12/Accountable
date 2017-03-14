package com.cs506.accountable.dto;

/**
 * Created by mberger on 3/13/2017.
 */

public class Income {
    private String id;
    private String userId;
    private String accId;
    private String incName;
    private String payPeriod;
    //TODO: last two variables from picture (can't read)

    public Income() {
        this.id = "";
        this.userId = "";
        this.accId = "";
        this.incName = "";
        this.payPeriod = "";
        //TODO: last two variables from picture (can't read)
    }

    public Income(String id, String userId, String accId, String incName, String payPeriod) {
        this.id = id;
        this.userId = userId;
        this.accId = accId;
        this.incName = incName;
        this.payPeriod = payPeriod;
        //TODO: last two variables from picture (can't read)
    }

    public void setId(String id) { this.id = id; }
    public String getId() { return this.id; }

    public void setUserId(String userId) { this.userId = userId; }
    public String getUserId() {  return this.userId; }

    public void setAccId(String accId) {  this.accId = accId; }
    public String getAccId() { return this.accId; }

    public void setIncName(String incName) {
        this.incName = incName;
    }
    public String getIncName() {
        return this.incName;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }
    public String getPayPeriod() {
        return this.payPeriod;
    }

    //TODO: last two variables from picture (can't read)


}