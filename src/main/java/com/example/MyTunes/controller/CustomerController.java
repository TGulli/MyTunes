package com.example.MyTunes.controller;


import com.example.MyTunes.dataAccess.IRepository;
import com.example.MyTunes.dataAccess.SQLiteDatabase;
import com.example.MyTunes.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

//@RestController
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
    public String getAllCustomers(Model model){
        model.addAttribute("customers", db.getAllCustomers());
        return "view-all-customers";
    }

    /**
     * Adding works fine, but @Controller won't work in POstman
     * @RestController will work, but screw up the thymeleaf html pointer
     */

    //Task 2
    @GetMapping("/addCustomer")
    public String createCustomer(Model model){
        model.addAttribute("customer", new Customer(0, "test", "teas", "", "", "", ""));
        return "addCustomer";
    }

    @PostMapping("/addCustomer")
    public String createCustomer(@ModelAttribute Customer customer, BindingResult error, Model model){
        Boolean success = db.createCustomer(customer);
        System.out.println("Status: " + success);
        model.addAttribute("success", success);
        System.out.println("Ser her=??");
        return "addCustomer";
    }


    //Task 3
    @GetMapping(value = "/edit-customer/{id}")
    public String updateCustomer(@PathVariable() String id, Model model) {
        System.out.println("ID1: " + id);
        Customer customer = db.getCustomer(id);
        System.out.println("ID2: " + customer.getId());
        model.addAttribute("editCustomer", new Customer(Integer.parseInt(id), "","","","","",""));
        return "edit-customer";
    }

    @PostMapping("update-customer/{id}")
    public String updateCustomer(@PathVariable("id") String id, Customer customer, BindingResult error, Model model){
        System.out.println("sgsrg");
        System.out.println((String)model.getAttribute("customerId"));
        System.out.println((String)model.getAttribute("id"));
        System.out.println(customer.toString());
        boolean updatedSuccessfully = db.updateCustomer(customer, (String)model.getAttribute("customerId"));
        System.out.println(updatedSuccessfully);

        return "edit-customer";
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
