package com.example.escuela7708.modelo.basedatos.docente;

import com.example.escuela7708.modelo.basedatos.DatabaseConnection;
import com.example.escuela7708.modelo.clases.docente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class modificarDocenteBD {

    public static void modificarBD(docente Docente) {
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();

        String docenteViewQuery = "UPDATE docente SET nombre = ?, apellido = ?, nacimiento = ?, sexo = ?, dni = ?, cuil = ?, email = ? WHERE iddocente = ?";
        try {
            PreparedStatement ps = connectDB.prepareStatement(docenteViewQuery);
            ps.setString(1, Docente.getNombre());
            ps.setString(2, Docente.getApellido());
            ps.setString(3, Docente.getNacimiento());
            ps.setString(4, Docente.getSexo());
            ps.setInt(5, Docente.getDni());
            ps.setString(6, Docente.getCuil());
            ps.setString(7, Docente.getEmail());
            ps.setInt(8, Docente.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
