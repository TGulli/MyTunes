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

    private SingletonDBConnector dataBaseConnection = SingletonDBConnector.getInstance();
    private Connection myConnection = null;


    @Override
    public ArrayList<Customer> getAllCustomers() {
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
    public boolean createCustomer() {
        return false;
    }

    @Override
    public boolean updateCustomer() {
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
