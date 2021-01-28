package com.example.MyTunes.model;

import java.util.ArrayList;

public class Artist {
    private String firstName;
    private String lastName;
    private ArrayList<String> popularGenres;

    public Artist(String firstName, String lastName, ArrayList<String> popularGenres) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.popularGenres = popularGenres;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<String> getPopularGenres() {
        return popularGenres;
    }

    public void setPopularGenres(ArrayList<String> popularGenres) {
        this.popularGenres = popularGenres;
    }
}
