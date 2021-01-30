package com.example.MyTunes.dataAccess;

import com.example.MyTunes.model.*;
import com.example.MyTunes.util.SingletonDBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Class for handling the SQLlite Database
 * Imports the IRepository for generic database functions.
 */


public class SQLiteDatabase implements IRepository{

    //Set singleton database to null
    private SingletonDBConnector dataBaseConnection = null;
    private Connection myConnection = null;


    @Override
    public ArrayList<Track> getAllTracks() {
        //Gets instance of Singleton Database
        dataBaseConnection = SingletonDBConnector.getInstance();
        //Open connection
        myConnection = dataBaseConnection.getConn();
        ArrayList<Track> receivedTracks = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    myConnection.prepareStatement("SELECT Track.TrackId, Track.Name FROM Track");

            ResultSet resultSet = preparedStatement.executeQuery();

            //Add all findings to arraylist
            while (resultSet.next()) {
                receivedTracks.add(new Track(resultSet.getInt(1), resultSet.getString(2)));
            }

            return receivedTracks;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }



        return null;
    }

    @Override
    public ArrayList<Track> searchByTrackId(String trackName) {
        dataBaseConnection = SingletonDBConnector.getInstance();
        myConnection = dataBaseConnection.getConn();
        ArrayList<Track> receivedTracks = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    myConnection.prepareStatement("SELECT Track.Name, Artist.Name, Album.Title, Genre.Name FROM Track" +
                            " INNER JOIN Genre ON Track.GenreId = Genre.GenreId" +
                            " INNER JOIN Album ON Track.AlbumId = Album.AlbumId" +
                            " INNER JOIN Artist ON Album.ArtistId = Artist.ArtistId" +
                            " WHERE Track.Name LIKE ?");
            preparedStatement.setString(1, "%" + trackName + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                receivedTracks.add(new Track(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4)));
            }

            return receivedTracks;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            dataBaseConnection.closeConnection();
        }


        return null;
    }

    @Override
    public ArrayList<String> getAllGenres() {
        dataBaseConnection = SingletonDBConnector.getInstance();
        myConnection = dataBaseConnection.getConn();
        ArrayList<String> receivedGenres = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    myConnection.prepareStatement("SELECT Genre.Name FROM Genre");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                receivedGenres.add(resultSet.getString(1));
            }
            return receivedGenres;
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
        ArrayList<Customer> receivedCustomers = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    myConnection.prepareStatement("SELECT * FROM Customer");

            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                receivedCustomers.add(new Customer(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(8), resultSet.getString(9),resultSet.getString(10), resultSet.getString(12)));
            }
            return receivedCustomers;
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
    public ArrayList<HighestEarningCostumer> getHighestSpendingCustomers() {
        dataBaseConnection = SingletonDBConnector.getInstance();
        myConnection = dataBaseConnection.getConn();

        try {
            // Prepare Statement
            PreparedStatement preparedStatement =
            myConnection.prepareStatement("SELECT Customer.FirstName, Customer.LastName, SUM(Invoice.Total) AS totalSum" +
                    " FROM Invoice INNER JOIN Customer ON Customer.CustomerId = Invoice.CustomerId " +
                    "GROUP BY Invoice.customerId ORDER BY totalSum DESC");

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
    public PopularGenres getMostPopularGenreFromSpecificCustomer(String id) {
        dataBaseConnection = SingletonDBConnector.getInstance();
        myConnection = dataBaseConnection.getConn();

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

            //Map for keeping Song and Song numbers
            HashMap<String, String> genreAndSongs = new HashMap<>();
            // Process Results
            String firstName = "";
            String lastName = "";
            while (resultSet.next()){
                firstName = resultSet.getString(1);
                lastName = resultSet.getString(2);
                genreAndSongs.put(resultSet.getString(3), resultSet.getString(4));
            }
            System.out.println(genreAndSongs);

            return new PopularGenres(firstName,lastName, genreAndSongs);

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}
