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
    @GetMapping(value = "/editCustomer/{id}")
    public String updateCustomer(@PathVariable("id") int id, Model model) {
        ArrayList<Customer> allCustomers = db.getAllCustomers();
        Customer myCustomer = null;
        for (Customer c : allCustomers){
            if (id == c.getId()){
                myCustomer = c;
            }
        }
        model.addAttribute("editCustomer", myCustomer);
        return "editCustomer";
    }

    @PostMapping("/updateCustomer/{id}")
    public String updateCustomer(@ModelAttribute Customer customer, BindingResult error, Model model){
        System.out.println("REACHED");
        System.out.println(customer);
        boolean updatedSuccessfully = db.updateCustomer(customer, String.valueOf(customer.getId()));
        System.out.println(updatedSuccessfully);
        model.addAttribute("success", updatedSuccessfully);
        model.addAttribute("editCustomer", customer);

        //set to main after a spell
        return "editCustomer";
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
