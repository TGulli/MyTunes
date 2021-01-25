package com.example.MyTunes.databasehelper;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseRetreiver {

    private final static String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    private static Connection conn = null;

    public static void testConnection(){
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
    }


}
