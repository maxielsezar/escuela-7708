package com.example.escuela7708.modelo.basedatos;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private Connection databaseLink;

    public Connection getConnection(){
        String databaseName ="7708";
        String databaseUser ="root";
        String databasePassword ="opce03";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser,databasePassword);
        } catch (Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }

}
