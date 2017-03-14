package com.cs506.accountable.dto;

/**
 * Created by mberger on 3/13/2017.
 */

public class Purchase {
    private String id;
    private String userId;
    private String accId;
    private String price;
    private String date;
    private String time;
    private String category;
    private String location;
    private String comment;

    public Purchase() {
        this.id = "";
        this.userId = "";
        this.accId = "";
        this.price = "";
        this.date = "";
        this.time = "";
        this.category = "";
        this.location = "";
        this.comment = "";
    }

    public Purchase(String id, String userId, String accId, String price, String date,
                    String time, String category, String location, String comment) {
        this.id = id;
        this.userId = userId;
        this.accId = accId;
        this.price = price;
        this.date = date;
        this.time = time;
        this.category = category;
        this.location = location;
        this.comment = comment;
    }

    public void setId(String id) { this.id = id; }
    public String getId() { return this.id; }

    public void setUserId(String userId) { this.userId = userId; }
    public String getUserId() {  return this.userId; }

    public void setAccId(String accId) {  this.accId = accId; }
    public String getAccId() { return this.accId; }

    public void setPrice(String price) {
        this.price = price;
    }
    public String setPrice() { return this.price; }

    //TODO: date, time, category, location, comment

}