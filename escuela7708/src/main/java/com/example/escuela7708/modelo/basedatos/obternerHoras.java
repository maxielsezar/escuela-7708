package com.example.escuela7708.modelo.basedatos;

import com.example.escuela7708.modelo.clases.docente_curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class obternerHoras {

    public static int getHorasCatedras(int id) {
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "SELECT count(id_materia) as materia FROM docente_curso where id_docente=?";
        try {
            PreparedStatement statement = connectDB.prepareStatement(docenteViewQuery);
            statement.setInt(1, id);

            ResultSet queryOutput = statement.executeQuery();

            queryOutput.next();

            return queryOutput.getInt("materia");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }

}
