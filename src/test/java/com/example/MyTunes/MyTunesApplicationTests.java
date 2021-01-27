package com.example.MyTunes;

import com.example.MyTunes.dataAccess.IRepository;
import com.example.MyTunes.dataAccess.SQLiteDatabase;
import com.example.MyTunes.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyTunesApplicationTests {
	IRepository db = null;

	@BeforeEach
	public void setup(){
		db = new SQLiteDatabase();
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void checkIfDatabaseWorks(){
		db.createCustomer(new Customer(999999, "hei", "nei", "tjohei", "bay", "buja", "somethign"));
	}

}
