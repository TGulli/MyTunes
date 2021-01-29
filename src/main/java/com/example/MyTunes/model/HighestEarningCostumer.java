package com.example.MyTunes.model;

public class HighestEarningCostumer {
    private String name;
    private String earnings;

    public HighestEarningCostumer(String name, String earnings) {
        this.name = name;
        this.earnings = earnings;
    }

    public String getName() {
        return name;
    }

    public String getEarnings() {
        return earnings;
    }
}
