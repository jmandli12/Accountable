package com.cs506.accountable.dto;

/**
 * Created by tkobl on 3/14/2017.
 */

public class Purchase {

    private int purchaseId;
    private int userId;
    private int accountId;
    private double price;
    private String date;
    private String time;
    private String category;
    private String location;
    private String comment;

    public Purchase() {
        this.purchaseId = -1;
        this.userId = -1;
        this.accountId = -1;
        this.price = -1.00;
        this.date = "";
        this.time = "";
        this.category = "";
        this.location = "";
        this.comment = "";
    }

    public Purchase(int purchaseId, int userId, int accountId, double price, String date, String time,
                    String category, String location, String comment) {
        this.purchaseId = purchaseId;
        this.userId = userId;
        this.accountId = accountId;
        this.price = price;
        this.date = date;
        this.time = time;
        this.category = category;
        this.location = location;
        this.comment = comment;
    }

    public int getPurchaseId() {return purchaseId;}

    public void setPurchaseId(int purchaseId) {this.purchaseId = purchaseId;}

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public int getAccountId() {return accountId;}

    public void setAccountId(int accountId) {this.accountId = accountId;}

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {return category;}

    public void setCategory(String category) {this.category = category;}

    public String getLocation() {return location;}

    public void setLocation(String location) {this.location = location;}

    public String getComment() {return comment;}

    public void setComment(String comment) {this.comment = comment;}
}
