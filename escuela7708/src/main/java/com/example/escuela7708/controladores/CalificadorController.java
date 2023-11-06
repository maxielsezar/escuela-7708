package com.example.escuela7708.controladores;

import com.example.escuela7708.modelo.basedatos.docente.listaDocente;
import com.example.escuela7708.modelo.basedatos.listaUEM;
import com.example.escuela7708.modelo.clases.docente;
import com.example.escuela7708.modelo.clases.uem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CalificadorController implements Initializable {
    @FXML
    public Label localidad;
    @FXML
    public MenuButton menuCurso;
    @FXML
    public Label orientacion;
    @FXML

    public TextField t_anyo;
    @FXML
    private MenuButton menuUEM;
    @FXML
    public ListView list;
    @FXML
    public TextField t_docente;

    FilteredList<String> filteredData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<uem> lista = listaUEM.Lista();

        menuUEM.getItems().removeAll();
        for (uem uem : lista) {
            MenuItem item = new MenuItem(String.valueOf(uem.getNum()));
            item.setOnAction(a -> {
                menuUEM.setText(item.getText());
                localidad.setText(uem.getLocalidad());
                orientacion.setText(uem.getOrientacion());
            });
            menuUEM.getItems().add(item);
        }
        menuUEM.setText(menuUEM.getItems().get(0).getText());

        for (int i = 1; i <= 6; i++) {
            MenuItem item = new MenuItem(String.valueOf(i));
            item.setOnAction(a -> {
                menuCurso.setText(item.getText());
            });
            menuCurso.getItems().add(item);
        }

        ObservableList<docente> Docentes = listaDocente.getDocenteList();
        ObservableList<String> docenteList = FXCollections.observableArrayList();
        for (docente docente : Docentes) {
            String item = docente.getNombre() + " " + docente.getApellido();
            docenteList.add(item);
        }

        filteredData = new FilteredList<>(docenteList, p -> true);
        if (filteredData.isEmpty()) {
            list.setVisible(false);
        }
        list.setItems(filteredData);
        t_docente.textProperty().addListener((prop, old, text) -> {
            filteredData.setPredicate(docente -> {
                list.setVisible(true);
                if (text == null || text.isEmpty()) {
                    list.setVisible(false);
                    return true;
                }
                return docente.toLowerCase().contains(text.toLowerCase());
            });

        });

    }
    public void seleccion() {
        int nombre = list.getSelectionModel().getSelectedIndex();
        t_docente.setText((String) list.getItems().get(nombre));
        list.setVisible(false);
    }
}
