package com.example.escuela7708.modelo.basedatos.docenteCurso;

import com.example.escuela7708.modelo.basedatos.DatabaseConnection;
import com.example.escuela7708.modelo.clases.docente_curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class guardarDocenteCurso {
    public static void guardarBD(docente_curso Docente) {
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "INSERT INTO docente_curso (id_materia,id_docente) VALUES (?,?)";
        try {
            PreparedStatement ps = connectDB.prepareStatement(docenteViewQuery);
            ps.setInt(1, Docente.getIdmateria());
            ps.setInt(2, Docente.getIddocente());
            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
