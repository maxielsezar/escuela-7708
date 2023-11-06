package com.example.escuela7708.controladores;

import com.example.escuela7708.modelo.basedatos.listaUEM;
import com.example.escuela7708.modelo.basedatos.listaDocenteUEM;
import com.example.escuela7708.modelo.clases.docente_uem;
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
import java.util.stream.Stream;

public class DocentesUemController implements Initializable {

    @FXML
    public ListView listaView;
    @FXML
    public ChoiceBox<String> docentes;

    @FXML
    public MenuButton uemCB;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<docente_uem> lista = listaDocenteUEM.getDocenteUEMList();

        docentes.getItems().removeAll();

        ObservableList<String> list = FXCollections.observableArrayList();

        lista.forEach(docenteUem -> list.add(docenteUem.getNombre() + " " + docenteUem.getApellido()));

        Stream<String> listaNueva = list.stream().distinct();

        listaNueva.forEach(docente -> docentes.getItems().add(docente));

        ObservableList<uem> listaUem = listaUEM.Lista();


        for (uem uem : listaUem) {
            MenuItem item = new MenuItem(String.valueOf(uem.getNum()));
            item.setOnAction(a -> {
                listaView.getItems().clear();
                uemCB.setText(item.getText());
                lista.filtered((t) -> {
                    if (t.getUem() == Integer.parseInt(item.getText())) {
                        listaView.getItems().add(t.getNombre()+" "+t.getApellido());
                        return true;
                    }
                    return false;
                });
            });

            uemCB.getItems().add(item);
        }


        docentes.valueProperty().addListener(docente
                -> {
            listaView.getItems().clear();

            String texto = docentes.getValue();
            lista.filtered((t) -> {
                if ((t.getNombre() + " " + t.getApellido()).contains(texto)) {
                    listaView.getItems().add(t.getUem());
                    return true;
                }
                return false;
            });

        });

    }
}
