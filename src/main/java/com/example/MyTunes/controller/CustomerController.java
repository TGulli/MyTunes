package com.example.MyTunes.controller;


import com.example.MyTunes.dataAccess.IRepository;
import com.example.MyTunes.dataAccess.SQLiteDatabase;
import com.example.MyTunes.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
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

//    //Task 2
//    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
//    public String createCustomer(Model model){
//        Customer customer = new Customer();
//        model.addAttribute("customer", customer);
//        System.out.println("SSRKGsræø=??");
//        return "addCustomer";
//    }
//
//    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
//    public String createCustomer(@ModelAttribute Customer customer, BindingResult error, Model model){
//        Boolean success = db.createCustomer(customer);
//        System.out.println("Status: " + success);
//        model.addAttribute("success", success);
//        System.out.println("Ser her=??");
//        return "addCustomer";
//    }

    //Task 3
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean updateCustomer(@PathVariable String id, @RequestBody Customer customer){
        return db.updateCustomer(customer, id);
    }

    //Task 4
    @GetMapping("/customer-each-country")
    public String getCustomersFromEachCountry(){
        return db.getCustomersFromEachCountry();
    }

    //Task 5
    @GetMapping("/getHighestEarningCustomers")
    public String getHighestEarningCustomers(){
        return db.getHighestEarningCustomers();
    }

    //Task 6
    @GetMapping(value = "/getMostPopularGenreFromSpecificCustomer/{id}")
    public String getMostPopularGenreFromSpecificCustomer(@PathVariable(name = "id") String id){
        System.out.println("ID: " + id);
        return db.getMostPopularGenreFromSpecificCustomer(id);
    }
}
