package com.example.escuela7708.modelo.basedatos.docente;

import com.example.escuela7708.modelo.basedatos.DatabaseConnection;
import com.example.escuela7708.modelo.clases.docente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class eliminarDocenteBD {

    private docente Docente;

    public eliminarDocenteBD(docente docente) {
        Docente = docente;
    }

    public docente getDocente() {
        return Docente;
    }

    public void setDocente(docente docente) {
        Docente = docente;
    }

    public void eliminarBD() {
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "DELETE FROM docente WHERE (iddocente = ?);";
        try {
            PreparedStatement ps = connectDB.prepareStatement(docenteViewQuery);
            ps.setInt(1, Docente.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
