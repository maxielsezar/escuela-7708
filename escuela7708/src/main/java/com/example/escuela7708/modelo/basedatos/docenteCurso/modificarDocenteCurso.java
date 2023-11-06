package com.example.escuela7708.modelo.basedatos.docenteCurso;

import com.example.escuela7708.modelo.basedatos.DatabaseConnection;
import com.example.escuela7708.modelo.clases.curso;
import com.example.escuela7708.modelo.clases.docente_curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class modificarDocenteCurso {

    private docente_curso docenteCurso;

    public docente_curso getDocenteCurso() {
        return docenteCurso;
    }

    public void setDocenteCurso(docente_curso docenteCurso) {
        this.docenteCurso = docenteCurso;
    }

    public modificarDocenteCurso(docente_curso docenteCurso) {
        this.docenteCurso = docenteCurso;
    }

    public void modificarBD() {
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "UPDATE docente_curso SET id_docente = ? WHERE id_materia = ?";
        try {
            PreparedStatement ps = connectDB.prepareStatement(docenteViewQuery);
            ps.setInt(1, docenteCurso.getIddocente());
            ps.setInt(2, docenteCurso.getIdmateria());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
