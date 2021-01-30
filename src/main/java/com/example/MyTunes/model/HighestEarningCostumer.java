package com.example.MyTunes.model;

/**
 * POJO for HighestEarningCustomer
 */


public class HighestEarningCostumer {
    private final String name;
    private final String earnings;

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
