package com.example.MyTunes.controller;


import com.example.MyTunes.dataAccess.IRepository;
import com.example.MyTunes.dataAccess.SQLiteDatabase;
import com.example.MyTunes.model.PopularGenres;
import com.example.MyTunes.model.Customer;
import com.example.MyTunes.model.RandomLists;
import com.example.MyTunes.model.Track;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static com.example.MyTunes.util.Extractor.extractCustomerById;
import static com.example.MyTunes.util.Extractor.extractRandomElements;

/**
 * Controller used internally with Thymeleaf
 */


@Controller
public class CustomerController {

    //Interface instantiation for dependency inversion
    IRepository db = new SQLiteDatabase();

    //Home page random lists: Retrieves and extracts 5 random elements from customers, tracks and genres.
    @GetMapping()
    public String getRandomElements(Model model){
        //POJO object for holding 3 ArrayLists containing the random objects.
        RandomLists randomLists = extractRandomElements(db.getAllCustomers(), db.getAllTracks(), db.getAllGenres());
        model.addAttribute("customers", randomLists.getCustomers());
        model.addAttribute("tracks", randomLists.getTracks());
        model.addAttribute("genres", randomLists.getGenres());
        return "home";
    }

    //Task 1: Retreives all customers
    @GetMapping("customers")
    public String getAllCustomers(Model model){
        model.addAttribute("customers", db.getAllCustomers());
        return "view-all-customers";
    }

    //Task 2: Creates an empty object for filling out in HTML form
    @GetMapping("customers/addCustomer")
    public String getCustomer(Model model){
        model.addAttribute("customer", new Customer(0, "", "", "", "", "", ""));
        return "addCustomer";
    }

    //Task 2: Retrieves object from HTML form and add it to database
    @PostMapping("customers/addCustomer")
    public String addCustomer(@ModelAttribute Customer customer, BindingResult error, Model model){
        Boolean success = db.createCustomer(customer);
        model.addAttribute("success", success);
        return "addCustomer";
    }


    //Task 3: GetMapping method for importing customer object to fill out html form
    @GetMapping(value = "customers/editCustomer/{id}")
    public String updateCustomer(@PathVariable("id") int id, Model model) {
        Customer myCustomer = extractCustomerById(db.getAllCustomers(), id);
        //Check for parameter injection, ID should be retrieved by get method
        if (myCustomer != null) {
            model.addAttribute("editCustomer", myCustomer);
        }
        return "editCustomer";
    }

    //Task 3: Postmapping method for handling HTML form in editCustomer.html
    @PostMapping("customers/updateCustomer/{id}")
    public String updateCustomer(@ModelAttribute Customer customer, BindingResult error, Model model){
        boolean updatedSuccessfully = db.updateCustomer(customer, String.valueOf(customer.getId()));
        model.addAttribute("success", updatedSuccessfully);
        model.addAttribute("editCustomer", customer);
        return "editCustomer";
    }

    //Task 4: Retrieve sorted list of countries with most customers
    @GetMapping("customers/customer-each-country")
    public String getCustomersFromEachCountry(Model model){
        model.addAttribute("countries", db.getCustomersFromEachCountry());
        return "countryCustomers";
    }

    //Task 5: Get highest earning customers
    @GetMapping("customers/getHighestEarningCustomers")
    public String getHighestEarningCustomers(Model model){
        model.addAttribute("earningCustomers", db.getHighestSpendingCustomers());
        return "highestEarning";
    }

    //Task 6: Retreieve the most popular genres for given artist
    @GetMapping(value = "customers/getMostPopularGenreFromSpecificCustomer/{id}")
    public String getMostPopularGenreFromSpecificCustomer(@PathVariable(name = "id") String id, Model model){
        //POJO object for arist name and genres (name and number of songs)
        PopularGenres popularGenres = db.getMostPopularGenreFromSpecificCustomer(id);
        model.addAttribute("popularGenres", popularGenres);
        return "customerGenre";
    }

    //Search field in NavBar for all pages.
    @GetMapping("customers/searchPage")
    public String searchForTrackById(@RequestParam(value = "searchString") String searchString, Model model){
        ArrayList<Track> tracks = db.searchByTrackId(searchString);
        model.addAttribute("searchedTracks", tracks);
        model.addAttribute("searchString", searchString);

        return "searchPage";
    }
}
