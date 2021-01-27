package com.example.MyTunes.dataAccess;

import com.example.MyTunes.model.Customer;
import com.example.MyTunes.util.SingletonDBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class SQLiteDatabase implements IRepository{

    private SingletonDBConnector dataBaseConnection = null;
    private Connection myConnection = null;


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
    public String getCustomersFromEachCountry() {
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


            // Process Results
            while (resultSet.next()){
                returnString.append(resultSet.getString(1) + ", ");
                returnString.append(resultSet.getString(2) + "\n");
            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return returnString.toString();
    }

    @Override
    public String getHighestEarningCustomers() {
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


            // Process Results
            while (resultSet.next()){
                returnString.append(resultSet.getString(1) + " ");
                returnString.append(resultSet.getString(2) + ", ");
                returnString.append(resultSet.getString(3) + "\n");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return returnString.toString();
    }

    @Override
    public String getMostPopularGenreFromSpecificCustomer(String id) {
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

            // Process Results
            while (resultSet.next()){
                returnString.append("Name: " + resultSet.getString(1));
                returnString.append(" " + resultSet.getString(2) + " | ");
                returnString.append("Genre: " + resultSet.getString(3) + " | ");
                returnString.append("Number of songs in Genre: " + resultSet.getString(4) + "\n");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return returnString.toString();
    }
}