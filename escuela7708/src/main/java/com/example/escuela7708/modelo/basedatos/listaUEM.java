package com.example.escuela7708.modelo.basedatos;

import com.example.escuela7708.modelo.clases.uem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class listaUEM {

    public static ObservableList<uem> Lista(){
        ObservableList<uem> uem = FXCollections.observableArrayList();
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "SELECT iduem, localidad,orientacion FROM uem";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(docenteViewQuery);

            while (queryOutput.next()) {
                int queryNum = queryOutput.getInt("iduem");
                String queryLocalidad = queryOutput.getString("localidad");
                String queryOrientacion = queryOutput.getString("orientacion");
                //poblar la lista
                uem.add(new uem(queryNum, queryLocalidad,queryOrientacion));
            }

            return uem;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return uem;
    }

}
