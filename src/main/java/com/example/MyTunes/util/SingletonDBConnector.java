package com.example.MyTunes.util;

import java.sql.*;

/**
 * SingleTon class for handling Database connection
 */

public class SingletonDBConnector {
    private final static String URL = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
    private Connection conn = null;
    private static SingletonDBConnector singletonDBConnector = null;

    private SingletonDBConnector(){
        try {
            conn = DriverManager.getConnection(URL);
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    public static synchronized SingletonDBConnector getInstance() {
        if (singletonDBConnector == null){
            return new SingletonDBConnector();
        }
        return singletonDBConnector;
    }

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
