package com.example.MyTunes.controller;


import com.example.MyTunes.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("api/customers")
public class CustomerController {
    /**
     * CRUD OPERATIONS.
     * Create, Read, Update, Delete
     */

    //task 1
    @GetMapping
    public ArrayList<Customer> getAllCustomers(){
        //all customers from database
        return null;
    }

    //Task 2
    @PostMapping
    public boolean createCustomer(@RequestBody Customer customer){
        return false;
    }

    //Task 3
    @PatchMapping
    public boolean updateCustomer(){
        return false;
    }

    //Task 4
    @GetMapping("/customer-each-country")
    public String getCustomersFromEachCountry(){
        return "halloFRARA";
    }

    //Task 5
    @GetMapping("/getHighestEarningCustomers")
    public String getHighestEarningCustomers(){
        return "hallo";
    }

    //Task 6
    @GetMapping(value = "/getMostPopularGenreFromSpecificCustomer/{id}")
    public String getMostPopularGenreFromSpecificCustomer(@RequestParam(value = "id", defaultValue = "1") String id){
        return "hallo fra task 6";
    }


}
