package com.example.MyTunes.model;

import java.util.ArrayList;
import java.util.HashMap;

public class PopularGenres {
    private String firstName;
    private String lastName;
    private HashMap<String, String> popularGenres; // key = Genre, id = number of songs

    public PopularGenres(String firstName, String lastName, HashMap<String, String> popularGenres) {
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

    public HashMap<String, String> getPopularGenres() {
        return popularGenres;
    }

    public void setPopularGenres(HashMap<String, String> popularGenres) {
        this.popularGenres = popularGenres;
    }
}
