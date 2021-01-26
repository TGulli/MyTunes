package com.example.MyTunes.util;

import java.sql.*;

public class SingletonDBConnector {
    private final static String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    private Connection conn = null;
    private static SingletonDBConnector singletonDBConnector = null;

    private SingletonDBConnector(){
        try {
            conn = DriverManager.getConnection(URL);
        }
        catch (SQLException sqlException){
            //TODO: Create logger
            System.out.println(sqlException.getMessage());
        }
    }

    public static SingletonDBConnector getInstance() {
        if (singletonDBConnector == null){
            return new SingletonDBConnector();
        }
        return singletonDBConnector;
    }
    /* MIGHT USE THIS LATER
    public ResultSet preparedStatmentQuery(String input, Connection conn){
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(input);
            return preparedStatement.executeQuery();
        }
        catch (SQLException e){
            System.out.println(e.getMessage() + " genericPreparedStatement method");
        }
        return null;
    }

    public boolean preparedStatementUpdate(String input, Connection conn){
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(input);
            return preparedStatement.executeUpdate() == 1;
        }
        catch (SQLException e){
            System.out.println(e.getMessage() + " preparedStatementUpdate ");
        }
        return false;
    }
     */

    public Connection getConn() {
        return conn;
    }

    public void closeConnection(){
        try {
            conn.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
