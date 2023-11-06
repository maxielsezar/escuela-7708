package com.example.escuela7708.modelo.basedatos;

import com.example.escuela7708.modelo.clases.docente_curso;
import com.example.escuela7708.modelo.clases.docente_uem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class listaDocenteUEM {
    public static ObservableList<docente_uem> getDocenteUEMList() {
        ObservableList<docente_uem> cursoObservableListList = FXCollections.observableArrayList();
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "select distinct id_docente,uem,nombre,apellido from (select uem,curso,id_docente,materia from curso join docente_curso on curso.idcurso=docente_curso.id_materia)as tabla left join docente on tabla.id_docente= docente.iddocente";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(docenteViewQuery);

            while (queryOutput.next()) {

                int queryUEM = queryOutput.getInt("uem");

                int queryDocente = queryOutput.getInt("id_docente");

                String queryNombre = queryOutput.getString("nombre");

                String queryApellido = queryOutput.getString("apellido");

                //poblar la lista
                cursoObservableListList.add(new docente_uem(queryUEM,queryDocente,queryNombre,queryApellido));
            }

            return cursoObservableListList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cursoObservableListList;
    }

    public static ObservableList<docente_uem> getDocenteUEMList(int id) {
        ObservableList<docente_uem> cursoObservableListList = FXCollections.observableArrayList();
        DatabaseConnection connectionnow = new DatabaseConnection();
        Connection connectDB = connectionnow.getConnection();
        String docenteViewQuery = "select distinct id_docente,uem,nombre,apellido from (select uem,curso,id_docente,materia from curso join docente_curso on curso.idcurso=docente_curso.id_materia)as tabla left join docente on tabla.id_docente= docente.iddocente where uem=?";

        try {

            PreparedStatement statement = connectDB.prepareStatement(docenteViewQuery);
            statement.setInt(1, id);
            ResultSet queryOutput = statement.executeQuery();

            while (queryOutput.next()) {

                int queryUEM = queryOutput.getInt("uem");

                int queryDocente = queryOutput.getInt("id_docente");

                String queryNombre = queryOutput.getString("nombre");

                String queryApellido = queryOutput.getString("apellido");

                cursoObservableListList.add(new docente_uem(queryUEM,queryDocente,queryNombre,queryApellido));
            }

            return cursoObservableListList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cursoObservableListList;
    }
}
