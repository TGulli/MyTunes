package com.example.MyTunes.databasehelper;

import com.example.MyTunes.model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseRetreiver {

    private final static String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    private static Connection conn = null;

    public static void task1(){
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
                System.out.print(resultSet.getString(1) + ", ");
                System.out.println(resultSet.getString(2));
            }
        }

        catch (Exception e){
                System.out.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
    }

    public static void task2(Customer customer){
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO Customer (FirstName,LastName,Country,PostalCode,Phone,Email)  VALUES  (?,?,?,?,?,?)");
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setString(4, customer.getPostalCode());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setString(6, customer.getEmail());

            // Execute Statement
            int temp = preparedStatement.executeUpdate();
            if (temp > 1){
                System.out.println("Yaaay :D");
            }
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
    }

    public static void task3(Customer customer){
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE Customer SET FirstName=?,LastName=?,Country=?,PostalCode=?,Phone=?,Email=? WHERE Customer.CustomerID = ?");
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setString(4, customer.getPostalCode());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setString(6, customer.getEmail());
            preparedStatement.setString(7, "1"); // TODO

            // Execute Statement
            int temp = preparedStatement.executeUpdate();
            if (temp > 1){
                System.out.println("Yaaay3 :D");
            }
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
    }

    public static void task4(){
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Country, COUNT(Country) as num FROM Customer GROUP BY Country ORDER BY num DESC");

            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();


            // Process Results
            while (resultSet.next()){
                System.out.print(resultSet.getString(1) + ", ");
                System.out.println(resultSet.getString(2));
            }
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
    }

    public static void task5(){
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Customer.Firstname, Invoice.Total FROM Customer, Invoice" +
                            " WHERE Customer.CustomerID = Invoice.CustomerID ORDER BY Invoice.Total DESC");

            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();


            // Process Results
            while (resultSet.next()){
                System.out.print(resultSet.getString(1) + ", ");
                System.out.println(resultSet.getString(2));
            }
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
    }

    public static void task6(String firstName, String lastName){
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            PreparedStatement preparedStatement =
                    conn.prepareStatement("WITH tableName AS (SELECT Customer.FirstName as fn, Customer.LastName as ln, Genre.Name as gn, COUNT(InvoiceLine.Quantity) as tot FROM Customer " +
                            "INNER JOIN Invoice ON Invoice.CustomerId = Customer.CustomerId " +
                            "INNER JOIN InvoiceLine ON Invoice.InvoiceId = InvoiceLine.InvoiceId " +
                            "INNER JOIN Track ON Track.TrackId = InvoiceLine.TrackId " +
                            "INNER JOIN Genre ON Track.GenreId = Genre.GenreId " +
                            "WHERE Customer.FirstName = ? " +
                            "AND Customer.LastName = ?" +
                            "GROUP BY Genre.Name) " +
                            "SELECT fn, ln, gn, tot FROM tableName " +
                            "GROUP BY gn " +
                            "HAVING tot = (SELECT MAX(tot) FROM tableName) ");

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()){
                System.out.print("Name: " + resultSet.getString(1));
                System.out.print(" " + resultSet.getString(2) + " | ");
                System.out.print("Genre: " + resultSet.getString(3) + " | ");
                System.out.println("Number of songs in Genre: " + resultSet.getString(4));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
    }

    private static void closeConnection(){
        try {
            conn.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
