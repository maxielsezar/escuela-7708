package com.example.escuela7708.modelo.basedatos.docente;

import com.example.escuela7708.modelo.basedatos.DatabaseConnection;
import com.example.escuela7708.modelo.clases.docente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class guardarDocenteBD {
    private docente Docente;

    public guardarDocenteBD(docente docente) {
        Docente = docente;
    }

    public docente getDocente() {
        return Docente;
    }

    public void setDocente(docente docente) {
        Docente = docente;
    }

    public void guardarBD() {
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "INSERT INTO docente (nombre,apellido,nacimiento,sexo,dni,cuil,email) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connectDB.prepareStatement(docenteViewQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, Docente.getNombre());
            ps.setString(2, Docente.getApellido());
            ps.setString(3, Docente.getNacimiento());
            ps.setString(4, Docente.getSexo());
            ps.setInt(5, Docente.getDni());
            ps.setString(6, Docente.getCuil());
            ps.setString(7, Docente.getEmail());

            ResultSet rs = ps.getGeneratedKeys();
            ps.executeUpdate();

            if (rs != null && rs.next()) {
                Docente.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
