package com.example.MyTunes.controller;


import com.example.MyTunes.dataAccess.IRepository;
import com.example.MyTunes.dataAccess.SQLiteDatabase;
import com.example.MyTunes.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/customers")
public class CustomerController {
    /**
     * CRUD OPERATIONS.
     * Create, Read, Update, Delete
     */

    IRepository db = new SQLiteDatabase();

    //task 1
    @GetMapping
    public ArrayList<Customer> getAllCustomers(){
        return db.getAllCustomers();
    }

    //Task 2
    @PostMapping()
    public boolean createCustomer(@RequestBody Customer customer){
        return db.createCustomer(customer);
    }

    //Task 3
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean updateCustomer(@PathVariable String id, @RequestBody Customer customer){
        return db.updateCustomer(customer, id);
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
