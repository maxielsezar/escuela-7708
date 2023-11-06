package com.example.escuela7708.modelo.basedatos.curso;

import com.example.escuela7708.modelo.basedatos.DatabaseConnection;
import com.example.escuela7708.modelo.clases.curso;
import com.example.escuela7708.modelo.clases.docente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class obtenerCurso {

    public static docente getDocente(int id) {
        docente Docente = null;
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "SELECT iddocente,nombre,apellido,nacimiento,sexo,dni,cuil,email FROM docente where iddocente=?";
        try {
            PreparedStatement statement = connectDB.prepareStatement(docenteViewQuery);
            statement.setInt(1, id);
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
                Docente = new docente(queryID, queryNombre, queryApellido, queryNacimiento, querySexo, queryDNI, queryCUIL, queryEmail);
            }
            return Docente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getIdMateria(int iduem, int idcurso, String nombre) {
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "SELECT idcurso FROM curso where uem=? and curso=? and materia=?";
        try {
            PreparedStatement statement = connectDB.prepareStatement(docenteViewQuery);
            statement.setInt(1, iduem);
            statement.setInt(2, idcurso);
            statement.setString(3, nombre);
            ResultSet queryOutput = statement.executeQuery();
            queryOutput.next();
            return queryOutput.getInt("idcurso");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

}
