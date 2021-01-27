package com.example.MyTunes.controller;

import com.example.MyTunes.dataAccess.IRepository;
import com.example.MyTunes.dataAccess.SQLiteDatabase;
import com.example.MyTunes.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class customerAddController {
    IRepository db = new SQLiteDatabase();

    //Task 2
    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
    public String createCustomer(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        System.out.println("SSRKGsræø=??");
        return "addCustomer";
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public String createCustomer(@ModelAttribute Customer customer, BindingResult error, Model model){
        Boolean success = db.createCustomer(customer);
        System.out.println("Status: " + success);
        model.addAttribute("success", success);
        System.out.println("Ser her=??");
        return "addCustomer";
    }
}
