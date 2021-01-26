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
        return null;
    }

    @Override
    public String getHighestEarningCustomers() {
        return null;
    }

    @Override
    public String getMostPopularGenreFromSpecificCustomer() {
        return null;
    }
}
