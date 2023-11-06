package com.example.escuela7708.modelo.basedatos.docente;

import com.example.escuela7708.modelo.basedatos.DatabaseConnection;
import com.example.escuela7708.modelo.clases.docente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class obtenerDocente {

    public static docente getDocente(int id) {

        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "SELECT iddocente,nombre,apellido,nacimiento,sexo,dni,cuil,email FROM docente where iddocente=?";
        try {
            PreparedStatement statement = connectDB.prepareStatement(docenteViewQuery);
            statement.setInt(1, id);
            ResultSet queryOutput = statement.executeQuery(docenteViewQuery);

            queryOutput.next();

            int queryID = queryOutput.getInt("iddocente");

            String queryNombre = queryOutput.getString("nombre");

            String queryApellido = queryOutput.getString("apellido");

            String queryNacimiento = queryOutput.getString("nacimiento");

            String querySexo = queryOutput.getString("sexo");

            int queryDNI = queryOutput.getInt("dni");

            String queryCUIL = queryOutput.getString("cuil");

            String queryEmail = queryOutput.getString("email");

            return new docente(queryID, queryNombre, queryApellido, queryNacimiento, querySexo, queryDNI, queryCUIL, queryEmail);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
