package com.example.escuela7708.modelo.basedatos.docenteCurso;
import com.example.escuela7708.modelo.basedatos.DatabaseConnection;
import com.example.escuela7708.modelo.clases.docente_curso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class listaDocenteCurso {

    public static ObservableList<docente_curso> getDocenteCursosList() {
        ObservableList<docente_curso> cursoObservableList = FXCollections.observableArrayList();
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "select uem,curso,materia,nombre,apellido,id_docente,id_materia from (select uem,id_materia,curso,id_docente,materia from curso left join docente_curso on curso.idcurso=docente_curso.id_materia)as tabla left join docente on tabla.id_docente= docente.iddocente";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(docenteViewQuery);

            while (queryOutput.next()) {

                int queryUEM = queryOutput.getInt("uem");

                int queryIDCurso = queryOutput.getInt("curso");

                String queryMateria = queryOutput.getString("materia");

                String queryNombre = queryOutput.getString("nombre");

                String queryApellido = queryOutput.getString("apellido");

                int queryIDdocente = queryOutput.getInt("id_docente");

                int queryIDmateria = queryOutput.getInt("id_materia");

                //poblar la lista
                cursoObservableList.add(new docente_curso(queryUEM,queryIDCurso,queryMateria,queryNombre,queryApellido,queryIDdocente,queryIDmateria));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cursoObservableList;
    }
}
