package com.example.MyTunes;

import com.example.MyTunes.dataAccess.DatabaseRetreiver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyTunesApplication {

	/**
	 * TODO:
	 * Refactor and delete unused methods
	 * Fix all the front end stuff
	 * Task 5 should summarize invoice total from all invoices related to arist, not just top.
	 * Task 6 needs a entry method for getting id. view-all-customers could for instance have a "Show most payed genres" or something
	 */


	public static void main(String[] args) {
//		DatabaseRetreiver.task3(new Customer("ERT", "Pedersen", "Norway", "1337", "12345678", "bjon@ped.no"));
//		DatabaseRetreiver.task1();
//		DatabaseRetreiver.task4();
//		DatabaseRetreiver.task5();
//		DatabaseRetreiver.task6("12");

		SpringApplication.run(MyTunesApplication.class, args);
		//Kommer dette??
	}



}
