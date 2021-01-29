package com.example.MyTunes.dataAccess;

import com.example.MyTunes.model.*;

import java.util.ArrayList;

public interface IRepository {
    ArrayList<Customer> getAllCustomers();
    ArrayList<Track> getAllTracks();
    ArrayList<Track> searchByTrackId(String trackName);
    ArrayList<String> getAllGenres();
    boolean createCustomer(Customer customer);
    boolean updateCustomer(Customer customer, String id);
    ArrayList<Country> getCustomersFromEachCountry();
    ArrayList<HighestEarningCostumer> getHighestEarningCustomers();
    Artist getMostPopularGenreFromSpecificCustomer(String id);
}
