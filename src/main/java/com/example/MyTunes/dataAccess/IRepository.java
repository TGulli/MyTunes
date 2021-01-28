package com.example.MyTunes.dataAccess;

import com.example.MyTunes.model.Artist;
import com.example.MyTunes.model.Country;
import com.example.MyTunes.model.Customer;
import com.example.MyTunes.model.HighestEarningCostumer;

import java.util.ArrayList;

public interface IRepository {
    ArrayList<Customer> getAllCustomers();
    ArrayList<String> getAllTracks();
    ArrayList<String> getAllGenres();
    boolean createCustomer(Customer customer);
    boolean updateCustomer(Customer customer, String id);
    ArrayList<Country> getCustomersFromEachCountry();
    ArrayList<HighestEarningCostumer> getHighestEarningCustomers();
    Artist getMostPopularGenreFromSpecificCustomer(String id);
}
