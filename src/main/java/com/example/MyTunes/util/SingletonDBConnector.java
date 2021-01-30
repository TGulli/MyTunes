package com.example.MyTunes.util;

import java.sql.*;
import java.util.Objects;

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
        return Objects.requireNonNullElseGet(singletonDBConnector, SingletonDBConnector::new);
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
