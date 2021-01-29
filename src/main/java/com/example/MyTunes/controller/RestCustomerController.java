package com.example.MyTunes.controller;

import com.example.MyTunes.dataAccess.IRepository;
import com.example.MyTunes.dataAccess.SQLiteDatabase;
import com.example.MyTunes.model.Country;
import com.example.MyTunes.model.Customer;
import com.example.MyTunes.model.HighestEarningCostumer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class RestCustomerController {

    IRepository db = new SQLiteDatabase();

    //All customers
    @GetMapping(value = "/api/customers/")
    public ArrayList<Customer> allCustomers(){
        return db.getAllCustomers();
    }

    //Create customers
    @PostMapping(value = "api/customers/create-new-customer")
    public String createNewCustomer(@RequestBody Customer customer){
        boolean updatedSuccessfully = db.createCustomer(customer);
        return updatedSuccessfully ? "Successfully created customer!" : "Couldn't create customer.";
    }

    //Update existing customers
    @PostMapping(value = "api/customers/update-existing-customer/{id}")
    public String updateExistingCustomer(@PathVariable("id") String id, @RequestBody Customer customer){
        boolean updatedSuccessfully = db.updateCustomer(customer, id);
        return updatedSuccessfully ? "Updated customer with ID: " + id : "Could not update customer";
    }

    //Get countries
    @GetMapping("api/get-countries")
    public ArrayList<Country> getAllTheCountries(){
        ArrayList<Country> countries = db.getCustomersFromEachCountry();
        return countries;
    }

    //Big spenders
    @GetMapping("api/customers/highest-spenders/")
    public ArrayList<HighestEarningCostumer> getHighestSpenders(){
        ArrayList<HighestEarningCostumer> spenders = db.getHighestEarningCustomers();
        return spenders;
    }
}
