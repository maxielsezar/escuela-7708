package com.example.escuela7708.controladores;

import com.example.escuela7708.modelo.basedatos.curso.obtenerCurso;
import com.example.escuela7708.modelo.basedatos.docente.listaDocente;
import com.example.escuela7708.modelo.basedatos.docenteCurso.guardarDocenteCurso;
import com.example.escuela7708.modelo.basedatos.docenteCurso.obtenerDocenteCurso;
import com.example.escuela7708.modelo.clases.docente;
import com.example.escuela7708.modelo.clases.docente_curso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarDocenteCursoController implements Initializable {

    @FXML
    public ListView list;
    public TextField t_docente;

    docente_curso docenteCurso = new docente_curso();

    int id;
    public void setListaMateriales(docente_curso docenteCurso) {
        id=obtenerCurso.getIdMateria(docenteCurso.getUEM(),docenteCurso.getCurso(),docenteCurso.getMateria());
    }

    ObservableList<docente> Docentes = listaDocente.getDocenteList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> docenteList = FXCollections.observableArrayList();
        for (docente docente : Docentes) {
            String item = docente.getNombre() + " " + docente.getApellido();
            docenteList.add(item);
        }
        FilteredList<String> filteredData = new FilteredList<>(docenteList, p -> true);
        list.setItems(filteredData);
        t_docente.textProperty().addListener((prop, old, text) -> {
            filteredData.setPredicate(docente -> {
                list.setVisible(true);
                if (text == null || text.isEmpty()) return true;
                return docente.toLowerCase().contains(text.toLowerCase());
            });

        });

    }

    public void guardar() {

        Docentes.forEach(docente -> {
            if (t_docente.getText().contains(docente.getNombre()) && t_docente.getText().contains(docente.getApellido())){
                docenteCurso.setIddocente(docente.getId());
            }
        });
        docenteCurso.setIdmateria(id);
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Guardar");
        alerta.setHeaderText("¿desea guardar los cambios?");
        alerta.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                guardarDocenteCurso.guardarBD(docenteCurso);
                Stage stage = (Stage) t_docente.getScene().getWindow();
                stage.close();
            }
        });

    }

    public void seleccion() {
        t_docente.setText((String) list.getSelectionModel().getSelectedItem());
        list.setVisible(false);
    }

    public docente_curso getDocente() {
        return obtenerDocenteCurso.getDocente(docenteCurso.getIdmateria());
    }
}
