package com.example.MyTunes.controller;

import com.example.MyTunes.dataAccess.IRepository;
import com.example.MyTunes.dataAccess.SQLiteDatabase;
import com.example.MyTunes.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class customerAddController {
    IRepository db = new SQLiteDatabase();

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
}
