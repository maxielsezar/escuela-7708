package com.example.escuela7708.modelo.basedatos.curso;

import com.example.escuela7708.modelo.basedatos.DatabaseConnection;
import com.example.escuela7708.modelo.clases.curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class modificarCurso {
    private curso Curso;

    public modificarCurso(curso curso) {
        Curso = curso;
    }

    public curso getCurso() {
        return Curso;
    }

    public void setCurso(curso curso) {
        Curso = curso;
    }

    public void modificarBD() {
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "UPDATE curso SET materia = ? WHERE idcurso = ?";
        try {
            PreparedStatement ps = connectDB.prepareStatement(docenteViewQuery);
            ps.setString(1, Curso.getMateria());
            ps.setInt(2, Curso.getId_curso());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
