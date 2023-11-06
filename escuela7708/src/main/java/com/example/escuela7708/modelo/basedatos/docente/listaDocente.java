package com.example.escuela7708.modelo.basedatos.docente;

import com.example.escuela7708.modelo.basedatos.DatabaseConnection;
import com.example.escuela7708.modelo.clases.docente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class listaDocente {
    public static ObservableList<docente> getDocenteList() {
        ObservableList<docente> docenteList = FXCollections.observableArrayList();
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "SELECT iddocente,nombre,apellido,nacimiento,sexo,dni,cuil,email FROM docente";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(docenteViewQuery);

            while (queryOutput.next()) {

                int queryID = queryOutput.getInt("iddocente");

                String queryNombre = queryOutput.getString("nombre");

                String queryApellido = queryOutput.getString("apellido");

                String queryNacimiento = queryOutput.getString("nacimiento");

                String querySexo = queryOutput.getString("sexo");

                int queryDNI = queryOutput.getInt("dni");

                String queryCUIL = queryOutput.getString("cuil");

                String queryEmail = queryOutput.getString("email");

                //poblar la lista
                docenteList.add(new docente(queryID,queryNombre, queryApellido, queryNacimiento, querySexo, queryDNI, queryCUIL, queryEmail));
            }

            return docenteList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
