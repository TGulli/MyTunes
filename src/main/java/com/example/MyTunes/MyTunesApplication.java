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
	 */


	public static void main(String[] args) {
		SpringApplication.run(MyTunesApplication.class, args);
	}



}
