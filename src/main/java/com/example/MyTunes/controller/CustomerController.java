package com.example.MyTunes.controller;


import com.example.MyTunes.dataAccess.IRepository;
import com.example.MyTunes.dataAccess.SQLiteDatabase;
import com.example.MyTunes.model.Artist;
import com.example.MyTunes.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Random;

//@RestController
@Controller
public class CustomerController {
    /**
     * CRUD OPERATIONS.
     * Create, Read, Update, Delete
     */

    IRepository db = new SQLiteDatabase();

    @GetMapping()
    public String getCustomers(Model model){
        ArrayList<Customer> customers = db.getAllCustomers();
        ArrayList<Customer> randomCustomers = new ArrayList<>();
        ArrayList<String> tracks = db.getAllTracks();
        ArrayList<String> randomTracks = new ArrayList<>();
        ArrayList<String> genres = db.getAllGenres();
        ArrayList<String> randomGenres = new ArrayList<>();
        Random ran = new Random();

        for (int i = 0; i < 5; i++) {
            randomCustomers.add(customers.get(ran.nextInt(customers.size())));
            randomTracks.add(tracks.get(ran.nextInt(tracks.size())));
            randomGenres.add(genres.get(ran.nextInt(genres.size())));
        }
        model.addAttribute("customers", randomCustomers);
        model.addAttribute("tracks", randomTracks);
        model.addAttribute("genres", randomGenres);
        return "home";
    }

    //task 1
    @GetMapping("api/customers")
    public String getAllCustomers(Model model){
        model.addAttribute("customers", db.getAllCustomers());
        return "view-all-customers";
    }

    /**
     * Adding works fine, but @Controller won't work in POstman
     * @RestController will work, but screw up the thymeleaf html pointer
     */

    //Task 2
    @GetMapping("api/customers/addCustomer")
    public String createCustomer(Model model){
        model.addAttribute("customer", new Customer(0, "test", "teas", "", "", "", ""));
        return "addCustomer";
    }

    @PostMapping("api/customers/addCustomer")
    public String createCustomer(@ModelAttribute Customer customer, BindingResult error, Model model){
        Boolean success = db.createCustomer(customer);
        System.out.println("Status: " + success);
        model.addAttribute("success", success);
        System.out.println("Ser her=??");
        return "addCustomer";
    }


    //Task 3 FINITO
    @GetMapping(value = "api/customers/editCustomer/{id}")
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

    @PostMapping("api/customers/updateCustomer/{id}")
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
    @GetMapping("api/customers/customer-each-country")
    public String getCustomersFromEachCountry(Model model){
        model.addAttribute("countries", db.getCustomersFromEachCountry());
        return "countryCustomers";
    }

    //Task 5
    @GetMapping("api/customers/getHighestEarningCustomers")
    public String getHighestEarningCustomers(Model model){
        model.addAttribute("earningCustomers", db.getHighestEarningCustomers());
        return "highestEarning";
    }

    //Task 6
    @GetMapping(value = "api/customers/getMostPopularGenreFromSpecificCustomer/{id}")
    public String getMostPopularGenreFromSpecificCustomer(@PathVariable(name = "id") String id, Model model){
        Artist artist = db.getMostPopularGenreFromSpecificCustomer(id);
        model.addAttribute("artist", artist);
        return "customerGenre";
    }
}
