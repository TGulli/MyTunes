package com.example.MyTunes.model;

public class Country {
    private int numberOfPeople;
    private String nameOfCountry;

    public Country(String nameOfCountry, int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        this.nameOfCountry = nameOfCountry;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getNameOfCountry() {
        return nameOfCountry;
    }

    public void setNameOfCountry(String nameOfCountry) {
        this.nameOfCountry = nameOfCountry;
    }
}
