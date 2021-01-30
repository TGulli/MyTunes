package com.example.MyTunes.controller;

import com.example.MyTunes.dataAccess.IRepository;
import com.example.MyTunes.dataAccess.SQLiteDatabase;
import com.example.MyTunes.model.Country;
import com.example.MyTunes.model.Customer;
import com.example.MyTunes.model.HighestEarningCostumer;
import com.example.MyTunes.model.PopularGenres;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * REST Controller for exposing the API
 */



@RestController
public class RestCustomerController {

    IRepository db = new SQLiteDatabase();

    //Task 1: Get all customers
    @GetMapping(value = "/api/customers/")
    public ArrayList<Customer> allCustomers(){
        return db.getAllCustomers();
    }

    //Task 2: Create new customer
    @PostMapping(value = "api/customers/create-new-customer")
    public String createNewCustomer(@RequestBody Customer customer){
        boolean updatedSuccessfully = db.createCustomer(customer);
        return updatedSuccessfully ? "Successfully created customer!" : "Couldn't create customer.";
    }

    //Task 3: Update existing customer
    @PostMapping(value = "api/customers/update-existing-customer/{id}")
    public String updateExistingCustomer(@PathVariable("id") String id, @RequestBody Customer customer){
        boolean updatedSuccessfully = db.updateCustomer(customer, id);
        return updatedSuccessfully ? "Updated customer with ID: " + id : "Could not update customer";
    }

    //Task 4: Get sorted list of countries by number of customers
    @GetMapping("api/get-countries")
    public ArrayList<Country> getAllTheCountries(){
        return db.getCustomersFromEachCountry();
    }

    //Task 5: Get highest spending customers
    @GetMapping("api/customers/highest-spenders/")
    public ArrayList<HighestEarningCostumer> getHighestSpenders(){
        return db.getHighestSpendingCustomers();
    }

    //Task 6: Get most popular genres for a given customer.
    @GetMapping("api/customers/popular-genre/{id}")
    public PopularGenres getMostPopularGenre(@PathVariable("id") String id){
        return db.getMostPopularGenreFromSpecificCustomer(id);
    }
}
