package com.cs506.accountable.dto;


public class Goal {

    private int userId;

    // 0 = Daily, 1 = Weekly, 2 = Monthly, 3 = Yearly
    private int timePeriod;

    // 0 = $, 1 = % of Income, 2 = % of Savings
    private int unit;

    private double amount;

    public Goal() {
        this.userId = -1;
        this.timePeriod = -1;
        this.unit = -1;
        this.amount = -1.00;
    }

    public Goal(int userId, int timePeriod, int unit, double amount) {
            this.userId = userId;
            this.timePeriod = timePeriod;
            this.unit = unit;
            this.amount = amount;
    }


    public int getUserId() {return this.userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public int getTimePeriod() {return this.timePeriod;}

    public void setTimePeriod(int timePeriod){this.timePeriod = timePeriod;}

    public int getUnit() {return this.unit;}

    public void setUnit(int unit){this.unit = unit;}

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) { this.amount = amount;  }


}
