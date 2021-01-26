package com.example.MyTunes.dataAccess;

import com.example.MyTunes.model.Customer;

import java.util.ArrayList;

public interface IRepository {
    ArrayList<Customer> getAllCustomers();
    boolean createCustomer();
    boolean updateCustomer();
    String getCustomersFromEachCountry();
    String getHighestEarningCustomers();
    String getMostPopularGenreFromSpecificCustomer();
}
