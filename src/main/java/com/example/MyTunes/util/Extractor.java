package com.example.MyTunes.util;

import com.example.MyTunes.model.Customer;
import com.example.MyTunes.model.RandomLists;
import com.example.MyTunes.model.Track;

import java.util.ArrayList;
import java.util.Random;

/**
 * Util class for extraction
 */


public class Extractor {

    //Takes three arraylists and selects five random elements from each arraylist. Build a POJO and returns it
    public static RandomLists extractRandomElements(ArrayList<Customer> customers, ArrayList<Track> tracks, ArrayList<String> genres) {
        Random ran = new Random();
        ArrayList<Customer> randomCustomers = new ArrayList<>();
        ArrayList<Track> randomTracks = new ArrayList<>();
        ArrayList<String> randomGenres = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            randomCustomers.add(customers.get(ran.nextInt(customers.size())));
            randomTracks.add(tracks.get(ran.nextInt(tracks.size())));
            randomGenres.add(genres.get(ran.nextInt(genres.size())));
        }

        return new RandomLists(randomCustomers, randomTracks, randomGenres);
    }

    //Searches through ArrayList for element matching key and returns key.
    public static Customer extractCustomerById(ArrayList<Customer> allCustomers, int id){
        for (Customer c : allCustomers){
            if (id == c.getId()){
                return c;
            }
        }
        return null;
    }


}
