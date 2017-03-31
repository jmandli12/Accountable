package com.cs506.accountable.dto;

/**
 * Created by tkobl on 3/9/2017.
 */

public class User {

    private int id;
    private String username;
    private int pin_hash;
    private int pin;
    private String salt;
    private int firstTime;
    private String budget;
    private int hasPin;

    public User() {
        id = -1;
        pin_hash = -1;
        pin = -1;
        salt = "";
        username = "";
        firstTime = 1;
        budget = "";
        hasPin = 0;
    }

    public User(int ID, String USERNAME, int PIN_HASH, int PIN, String SALT,  /*int ACCOUNTID, int JOBID,*/
                int FIRSTTIME, String budget, int hasPin) {
        id = ID;
        pin_hash = PIN_HASH;
        pin = PIN;
        salt = SALT;
        username = USERNAME;
        firstTime = FIRSTTIME;
        this.budget = budget;
        this.hasPin = hasPin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPin_hash() {return this.pin_hash;}

    public void setPin_hash(int pin_hash) {this.pin_hash = pin_hash;}

    public int getPin() {return this.pin;}

    public void setPin(int pin) {this.pin = pin;}

    public String getSalt() {return this.salt;}

    public void setSalt(String salt) {this.salt = salt;}

    public int getFirstTime() {return this.firstTime;}

    public void setFirstTime(int firstTime) {this.firstTime = firstTime;}

    public String getBudget() { return this.budget;}

    public void setBudget(String budget){this.budget = budget;}

    public int getHasPin() { return this.hasPin;}

    public void setHasPin(int hasPin){this.hasPin = hasPin;}
}
