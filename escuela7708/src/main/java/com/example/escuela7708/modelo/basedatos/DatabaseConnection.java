package com.example.escuela7708.modelo.basedatos;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private Connection databaseLink;

    public Connection getConnection(){
        String databaseName ="escuela";
        String databaseUser ="root";
        String databasePassword ="opce08";
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
