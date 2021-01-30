package com.example.MyTunes.model;


/**
 * POJO for Country
 */

public class Country {
    private final int numberOfPeople;
    private final String nameOfCountry;

    public Country(String nameOfCountry, int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        this.nameOfCountry = nameOfCountry;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public String getNameOfCountry() {
        return nameOfCountry;
    }
}
