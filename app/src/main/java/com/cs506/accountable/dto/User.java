package com.cs506.accountable.dto;

/**
 * Created by tkobl on 3/9/2017.
 */

public class User {

    private long id;
    private int pin_hash;
    private int pin;
    private String salt;
    private String username;
    private int accountID;
    private int jobID;
    private boolean firstTime;

    public User() {
        id = 0;
        pin_hash = 0;
        pin = 0;
        salt = "";
        username = "";
        accountID = 0;
        jobID = 0;
        firstTime = true;
    }

    public User(long ID, int PIN_HASH, int PIN, String SALT, String USERNAME, int ACCOUNTID, int JOBID,
                boolean FIRSTTIME) {
        id = ID;
        pin_hash = PIN_HASH;
        pin = PIN;
        salt = SALT;
        username = USERNAME;
        accountID = ACCOUNTID;
        jobID = JOBID;
        firstTime = FIRSTTIME;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {this.jobID = jobID;}

    public int getPin_hash() {return this.pin_hash;}

    public void setPin_hash(int pin_hash) {this.pin_hash = pin_hash;}

    public int getPin() {return this.pin;}

    public void setPin(int pin) {this.pin = pin;}

    public String getSalt() {return this.salt;}

    public void setSalt(String salt) {this.salt = salt;}

    public boolean getFirstTime() {return this.firstTime;}

    public void setFirstTime(boolean firstTime) {this.firstTime = firstTime;}

}
