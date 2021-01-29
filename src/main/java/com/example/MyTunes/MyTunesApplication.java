package com.example.MyTunes;

import com.example.MyTunes.dataAccess.DatabaseRetreiver;
import com.example.MyTunes.model.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyTunesApplication {

	/**
	 * TODO:
	 * Refactor and delete unused methods
	 * Make frontend pretty (add, edit)
	 * Needs restcontroller AND controller. Controller works internally with thyme and Restcontroller for exposing the api.
	 * The database methods should probably return JSON objects.
	 */


	public static void main(String[] args) {
//		DatabaseRetreiver.task3(new Customer(0, "bajas", "Norway", "1337", "12345678", "bjon@ped.no", "rere2j3"));
//		DatabaseRetreiver.task1();
//		DatabaseRetreiver.task4();
//		DatabaseRetreiver.task5();
//		DatabaseRetreiver.task6("12");
		//minipush

		SpringApplication.run(MyTunesApplication.class, args);
		//Kommer dette??
	}



}
