package com.cs506.accountable.dto;

/**
 * Created by tkobl on 3/9/2017.
 */

public class User {

    private String id;
    private String pin_hash;
    private String pin;
    private String salt;
    private String username;
    private String accountId;
    private String jobID;
    private String firstTime;

    public User() {
        this.id = "";
        this.pin_hash = "";
        this.pin = "";
        this.salt = "";
        this.username = "";
        this.accountId = "";
        this.jobID = "";
        this.firstTime = "false";
    }

    public User(String id, String pin_hash, String pin, String salt, String username, String accountId, String jobId,
                String firstTime) {
        this.id = id;
        this.pin_hash = pin_hash;
        this.pin = pin;
        this.salt = salt;
        this.username = username;
        this.accountId = accountId;
        this.jobID = jobId;
        this.firstTime = firstTime;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getUsername(){return username;}
    public void setUsername(String username) {this.username = username;}

    public String getAccountID() {return accountId;}
    public void setAccountID(String accountID) {this.accountId = accountID;}

    public String getJobID() {return jobID;}
    public void setJobID(String jobID) {this.jobID = jobID;}

    public String getPin_hash() {return this.pin_hash;}
    public void setPin_hash(String pin_hash) {this.pin_hash = pin_hash;}

    public String getPin() {return this.pin;}
    public void setPin(String pin) {this.pin = pin;}

    public String getSalt() {return this.salt;}
    public void setSalt(String salt) {this.salt = salt;}

    public String getFirstTime() {return this.firstTime;}
    public void setFirstTime(String firstTime) {this.firstTime = firstTime;}

}
