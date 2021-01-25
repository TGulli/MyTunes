package com.example.MyTunes.databasehelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseRetreiver {

    private final static String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    private static Connection conn = null;

    public static void testConnection(){
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT * FROM Customer");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()){
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
            }
        }

        catch (Exception e){
                System.out.println(e.getMessage());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


}
