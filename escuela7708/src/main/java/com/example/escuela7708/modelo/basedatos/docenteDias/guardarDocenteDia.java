package com.example.escuela7708.modelo.basedatos.docenteDias;

import com.example.escuela7708.modelo.basedatos.DatabaseConnection;
import com.example.escuela7708.modelo.clases.docente_dia;
import java.sql.*;

public class guardarDocenteDia {
    public static void guardarBD(docente_dia docente) {
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "INSERT INTO docente_dia (id_docente,id_uem,dia) VALUES (?,?,?)";
        try {
            PreparedStatement ps = connectDB.prepareStatement(docenteViewQuery);
            ps.setInt(1, docente.getDocente());
            ps.setInt(2, docente.getUem());
            ps.setDate(3, Date.valueOf(docente.getDia()));
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
