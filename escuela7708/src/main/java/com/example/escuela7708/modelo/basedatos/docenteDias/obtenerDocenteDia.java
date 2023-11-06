package com.example.escuela7708.modelo.basedatos.docenteDias;

import com.example.escuela7708.modelo.basedatos.DatabaseConnection;
import com.example.escuela7708.modelo.clases.docente;
import com.example.escuela7708.modelo.clases.docente_dia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class obtenerDocenteDia {
    public static ObservableList<docente_dia> getDocenteList(){
        ObservableList<docente_dia> docenteList = FXCollections.observableArrayList();
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "SELECT id_docente,id_uem,dia,nombre,apellido FROM docente_dia join docente on docente_dia.id_docente=docente.iddocente;";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(docenteViewQuery);
            while (queryOutput.next()) {

                int queryIDdocente = queryOutput.getInt("iddocente");

                int queryIDuem = queryOutput.getInt("id_uem");

                LocalDate queryDia = queryOutput.getDate("dia").toLocalDate();

                String queryNombre = queryOutput.getString("nombre");

                String queryApellido = queryOutput.getString("apellido");

                docente_dia newDocente = new docente_dia(queryIDdocente,queryIDuem,queryDia);
                newDocente.setNombre(queryNombre);
                newDocente.setApellido(queryApellido);
                docenteList.add(newDocente);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return docenteList;
    }
}
