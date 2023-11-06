package com.example.escuela7708.modelo.basedatos.curso;

import com.example.escuela7708.modelo.basedatos.DatabaseConnection;
import com.example.escuela7708.modelo.clases.curso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class listaCurso {

    public static ObservableList<curso> getCursosList() {
        ObservableList<curso> cursoObservableListList = FXCollections.observableArrayList();
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "SELECT idcurso, materia,uem,curso FROM curso;";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(docenteViewQuery);

            while (queryOutput.next()) {

                int queryID = queryOutput.getInt("idcurso");

                String queryNombre = queryOutput.getString("materia");

                int queryUEM = queryOutput.getInt("uem");

                int queryNacimiento = queryOutput.getInt("curso");


                //poblar la lista
                cursoObservableListList.add(new curso(queryID, queryUEM, queryNacimiento,queryNombre));
            }

            return cursoObservableListList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
