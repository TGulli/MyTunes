package com.example.MyTunes.model;

import java.util.ArrayList;

/**
 * POJO for RandomLists
 * Used for the five random customers, tracks and genres shown at the home page
 */


public class RandomLists {
    private final ArrayList<Customer> customers;
    private final ArrayList<Track> tracks;
    private final ArrayList<String> genres;

    public RandomLists(ArrayList<Customer> customers, ArrayList<Track> tracks, ArrayList<String> genres) {
        this.customers = customers;
        this.tracks = tracks;
        this.genres = genres;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }
}

