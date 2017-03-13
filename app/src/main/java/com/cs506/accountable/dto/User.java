package com.cs506.accountable.dto;

/**
 * Created by tkobl on 3/9/2017.
 */

public class User {

    private long id;
    private String username;
    private int accountID;
    private int jobID;

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

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return username;
    }
}
