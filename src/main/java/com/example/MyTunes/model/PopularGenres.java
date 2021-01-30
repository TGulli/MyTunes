package com.example.MyTunes.model;

import java.util.HashMap;

/**
 * POJO For PopularGenres
 */


public class PopularGenres {
    private final String firstName;
    private final String lastName;
    private final HashMap<String, String> popularGenres; // key = Genre, id = number of songs

    public PopularGenres(String firstName, String lastName, HashMap<String, String> popularGenres) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.popularGenres = popularGenres;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public HashMap<String, String> getPopularGenres() {
        return popularGenres;
    }


}
