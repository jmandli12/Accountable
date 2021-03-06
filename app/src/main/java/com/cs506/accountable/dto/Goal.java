package com.cs506.accountable.dto;


public class Goal {

    private int goalId;
    private int userId;
    private String name;
    private int timePeriod;
    private int unit;
    private double amount;


    public Goal() {
        this.goalId = -1;
        this.userId = -1;
        this.timePeriod = -1;
        this.unit = -1;
        this.amount = -1.00;
        this.name = "";
    }

    public Goal(int goalId, int userId, String name, int timePeriod, int unit, double amount) {
            this.goalId = goalId;
            this.userId = userId;
            this.timePeriod = timePeriod;
            this.unit = unit;
            this.amount = amount;
            this.name = name;
    }

    public int getGoalId() {return this.goalId;}

    public void setGoalId(int goalId) {this.goalId = goalId;}

    public String getGoalName() {return this.name;}

    public void setGoalName(String name){this.name = name;}

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
