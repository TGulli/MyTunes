package com.example.MyTunes.dataAccess;

import com.example.MyTunes.model.Customer;

import java.util.ArrayList;

public interface IRepository {
    ArrayList<Customer> getAllCustomers();
    boolean createCustomer(Customer customer);
    boolean updateCustomer(Customer customer, String id);
    String getCustomersFromEachCountry();
    String getHighestEarningCustomers();
    String getMostPopularGenreFromSpecificCustomer();
}
