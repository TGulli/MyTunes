package com.example.MyTunes.dataAccess;

import com.example.MyTunes.model.Artist;
import com.example.MyTunes.model.Country;
import com.example.MyTunes.model.Customer;
import com.example.MyTunes.model.HighestEarningCostumer;
import com.example.MyTunes.util.SingletonDBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SQLiteDatabase implements IRepository{

    private SingletonDBConnector dataBaseConnection = null;
    private Connection myConnection = null;


    @Override
    public ArrayList<String> getAllTracks() {
        dataBaseConnection = SingletonDBConnector.getInstance();
        myConnection = dataBaseConnection.getConn();
        ArrayList<String> recievedTracks = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    myConnection.prepareStatement("SELECT Track.Name FROM Track");

            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results

            while (resultSet.next()) {
                recievedTracks.add(resultSet.getString(1));
            }

            return recievedTracks;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }


        return null;
    }

    @Override
    public ArrayList<String> getAllGenres() {
        dataBaseConnection = SingletonDBConnector.getInstance();
        myConnection = dataBaseConnection.getConn();
        ArrayList<String> recievedGenres = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    myConnection.prepareStatement("SELECT Genre.Name FROM Genre");

            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results

            while (resultSet.next()) {
                recievedGenres.add(resultSet.getString(1));
            }
            return recievedGenres;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }


        return null;
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        dataBaseConnection = SingletonDBConnector.getInstance();
        myConnection = dataBaseConnection.getConn();
        System.out.println("Triggered by postman");
        ArrayList<Customer> recievedCustomers = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    myConnection.prepareStatement("SELECT * FROM Customer");

            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results

            while (resultSet.next()) {
                recievedCustomers.add(new Customer(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5),resultSet.getString(6), resultSet.getString(7)));
            }
            return recievedCustomers;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }


        return null;
    }

    @Override
    public boolean createCustomer(Customer customer) {
        dataBaseConnection = SingletonDBConnector.getInstance();
        myConnection = dataBaseConnection.getConn();
        System.out.println("createCustomer reached");
        System.out.println(customer.toString());


        try {

            PreparedStatement createCustomerStatement =
                    myConnection.prepareStatement("INSERT INTO Customer (FirstName,LastName,Country,PostalCode,Phone,Email)  VALUES  (?,?,?,?,?,?)");
            createCustomerStatement.setString(1, customer.getFirstName());
            createCustomerStatement.setString(2, customer.getLastName());
            createCustomerStatement.setString(3, customer.getCountry());
            createCustomerStatement.setString(4, customer.getPostalCode());
            createCustomerStatement.setString(5, customer.getPhoneNumber());
            createCustomerStatement.setString(6, customer.getEmail());


            return createCustomerStatement.executeUpdate() != 0;

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer, String id) {
        dataBaseConnection = SingletonDBConnector.getInstance();
        myConnection = dataBaseConnection.getConn();
        System.out.println("updateCustomer reached");

        try {
            PreparedStatement createCustomerStatement =
                    myConnection.prepareStatement("UPDATE Customer SET FirstName=?,LastName=?,Country=?,PostalCode=?,Phone=?,Email=? WHERE Customer.CustomerID = ?");
            createCustomerStatement.setString(1, customer.getFirstName());
            createCustomerStatement.setString(2, customer.getLastName());
            createCustomerStatement.setString(3, customer.getCountry());
            createCustomerStatement.setString(4, customer.getPostalCode());
            createCustomerStatement.setString(5, customer.getPhoneNumber());
            createCustomerStatement.setString(6, customer.getEmail());
            createCustomerStatement.setString(7, id);


            return createCustomerStatement.executeUpdate() != 0;

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public ArrayList<Country> getCustomersFromEachCountry() {
        dataBaseConnection = SingletonDBConnector.getInstance();
        myConnection = dataBaseConnection.getConn();
        System.out.println("getCustomersFromEachCountry reached");
        StringBuilder returnString = new StringBuilder();


        try {
            // Prepare Statement
            PreparedStatement preparedStatement =
                    myConnection.prepareStatement("SELECT Country, COUNT(Country) as num FROM Customer GROUP BY Country ORDER BY num DESC");

            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Country> foundCustomers = new ArrayList<>();

            // Process Results
            while (resultSet.next()){
                foundCustomers.add(new Country(resultSet.getString(1), resultSet.getInt(2)));
            }
            return foundCustomers;

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<HighestEarningCostumer> getHighestEarningCustomers() {
        dataBaseConnection = SingletonDBConnector.getInstance();
        myConnection = dataBaseConnection.getConn();
        System.out.println("getCustomersFromEachCountry reached");
        StringBuilder returnString = new StringBuilder();


        try {
            // Prepare Statement
            PreparedStatement preparedStatement =
                    myConnection.prepareStatement("SELECT Customer.Firstname, Customer.Lastname, Invoice.Total FROM Customer, Invoice" +
                            " WHERE Customer.CustomerID = Invoice.CustomerID ORDER BY Invoice.Total DESC");

            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<HighestEarningCostumer> arrayList = new ArrayList<>();
            // Process Results
            while (resultSet.next()){
                arrayList.add(new HighestEarningCostumer(resultSet.getString(1) + " " + resultSet.getString(2), resultSet.getString(3)));
            }
            return arrayList;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Artist getMostPopularGenreFromSpecificCustomer(String id) {
        dataBaseConnection = SingletonDBConnector.getInstance();
        myConnection = dataBaseConnection.getConn();
        System.out.println("getCustomersFromEachCountry reached");
        StringBuilder returnString = new StringBuilder();

        try {
            PreparedStatement preparedStatement =
                    myConnection.prepareStatement("WITH tableName AS (SELECT Customer.FirstName as fn, Customer.LastName as ln, Genre.Name as gn, COUNT(InvoiceLine.Quantity) as tot FROM Customer " +
                            "INNER JOIN Invoice ON Invoice.CustomerId = Customer.CustomerId " +
                            "INNER JOIN InvoiceLine ON Invoice.InvoiceId = InvoiceLine.InvoiceId " +
                            "INNER JOIN Track ON Track.TrackId = InvoiceLine.TrackId " +
                            "INNER JOIN Genre ON Track.GenreId = Genre.GenreId " +
                            "WHERE Customer.CustomerId = ? " +
                            "GROUP BY Genre.Name) " +
                            "SELECT fn, ln, gn, tot FROM tableName " +
                            "GROUP BY gn " +
                            "HAVING tot = (SELECT MAX(tot) FROM tableName) ");

            preparedStatement.setString(1, id);

            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<String> genreAndSongs = new ArrayList<>();
            // Process Results
            String firstName = "";
            String lastName = "";
            while (resultSet.next()){
                firstName = resultSet.getString(1);
                lastName = resultSet.getString(2);
                genreAndSongs.add(resultSet.getString(3) + " number " + resultSet.getString(4));
            }
            System.out.println(genreAndSongs);
            Artist myArtist = new Artist(firstName,lastName, genreAndSongs);

            return myArtist;

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
