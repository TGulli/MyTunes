package com.example.MyTunes;

import com.example.MyTunes.databasehelper.DatabaseRetreiver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyTunesApplication {

	public static void main(String[] args) {
		DatabaseRetreiver.testConnection();
		//SpringApplication.run(MyTunesApplication.class, args);
		//Kommer dette??
	}

}
