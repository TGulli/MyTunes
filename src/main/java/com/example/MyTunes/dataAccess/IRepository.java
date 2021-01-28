package com.example.MyTunes.dataAccess;

import com.example.MyTunes.model.Artist;
import com.example.MyTunes.model.Country;
import com.example.MyTunes.model.Customer;

import java.util.ArrayList;

public interface IRepository {
    ArrayList<Customer> getAllCustomers();
    ArrayList<String> getAllTracks();
    ArrayList<String> getAllGenres();
    boolean createCustomer(Customer customer);
    boolean updateCustomer(Customer customer, String id);
    ArrayList<Country> getCustomersFromEachCountry();
    ArrayList<String> getHighestEarningCustomers();
    Artist getMostPopularGenreFromSpecificCustomer(String id);
}
