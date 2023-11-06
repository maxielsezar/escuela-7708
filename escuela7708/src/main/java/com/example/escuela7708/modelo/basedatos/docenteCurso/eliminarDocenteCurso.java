package com.example.escuela7708.modelo.basedatos.docenteCurso;

import com.example.escuela7708.modelo.basedatos.DatabaseConnection;
import com.example.escuela7708.modelo.clases.docente;
import com.example.escuela7708.modelo.clases.docente_curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class eliminarDocenteCurso {

    public static void eliminarMateriaBD(docente_curso Docente) {
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "DELETE FROM docente_curso WHERE (id_materia = ?);";
        try {
            PreparedStatement ps = connectDB.prepareStatement(docenteViewQuery);
            ps.setInt(1, Docente.getIdmateria());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void eliminarDocenteBD(docente_curso Docente) {
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "DELETE FROM docente_curso WHERE (id_docente = ?);";
        try {
            PreparedStatement ps = connectDB.prepareStatement(docenteViewQuery);
            ps.setInt(1, Docente.getIddocente());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
