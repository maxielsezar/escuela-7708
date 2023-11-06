package com.example.escuela7708.controladores;

import com.example.escuela7708.modelo.basedatos.docente.listaDocente;
import com.example.escuela7708.modelo.basedatos.docenteDias.guardarDocenteDia;
import com.example.escuela7708.modelo.clases.docente;
import com.example.escuela7708.modelo.clases.docente_dia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class GuardarDocenteUemController implements Initializable {

    @FXML
    public TextField t_docente;
    @FXML
    public ListView list;
    @FXML
    public DatePicker dia;

    int iduem;
    FilteredList<String> filteredData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<docente> Docentes = listaDocente.getDocenteList();
        ObservableList<String> docenteList = FXCollections.observableArrayList();
        for (docente docente : Docentes) {
            String item = docente.getNombre() + " " + docente.getApellido();
            docenteList.add(item);
        }

        filteredData = new FilteredList<>(docenteList, p -> true);
        list.setItems(filteredData);
        t_docente.textProperty().addListener((prop, old, text) -> {
            filteredData.setPredicate(docente -> {
                list.setVisible(true);
                if (text == null || text.isEmpty()) return true;
                return docente.toLowerCase().contains(text.toLowerCase());
            });

        });

    }

    @FXML
    private void seleccion() {
        int nombre = list.getSelectionModel().getSelectedIndex();
        t_docente.setText((String) list.getItems().get(nombre));
        list.setVisible(false);
    }

    @FXML
    private void guardar() {
        int idDocente = getIdDocente();
        Date dias = Date.valueOf(dia.getValue());
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Guardar");
        alerta.setHeaderText("¿desea guardar los datos?");
        //alerta.setContentText("Por favor, corrige los errores");
        alerta.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                docente_dia Docente = new docente_dia( idDocente,iduem, dias.toLocalDate());
                guardarDocenteDia.guardarBD(Docente);
            }
        });

        Stage stage = (Stage) t_docente.getScene().getWindow();
        stage.close();
    }

    public int getIdDocente(){
        ObservableList<docente> Docentes = listaDocente.getDocenteList();
        for (docente docente:Docentes){
            if ( t_docente.getText().contains(docente.getNombre()) &&  t_docente.getText().contains(docente.getApellido())){
                return docente.getId();
            }
        }
        return 0;
    }
}
