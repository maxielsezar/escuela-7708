package com.example.escuela7708.controladores;

import com.example.escuela7708.Application;
import com.example.escuela7708.modelo.basedatos.docenteCurso.eliminarDocenteCurso;
import com.example.escuela7708.modelo.basedatos.docenteCurso.listaDocenteCurso;
import com.example.escuela7708.modelo.basedatos.docenteCurso.obtenerDocenteCurso;
import com.example.escuela7708.modelo.basedatos.obternerHoras;
import com.example.escuela7708.modelo.clases.docente_curso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class DocentesMateriasController implements Initializable {

    @FXML
    public ChoiceBox<String> docentes;
    @FXML
    public TableView<docente_curso> t_cursos;
    @FXML
    public TableColumn<docente_curso, String> c_materia;
    @FXML
    public TableColumn<docente_curso, Integer> c_curso;
    @FXML
    public TableColumn<docente_curso, Integer> UEM;
    @FXML
    public Button agregar, eliminar;
    @FXML
    public Label totalHoras;

    ObservableList<docente_curso> lista = listaDocenteCurso.getDocenteCursosList();

    ObservableList<String> list = FXCollections.observableArrayList();

    public void setDocente(int id) {
        docente_curso seleccion = obtenerDocenteCurso.getDocenteCurso(id);
        if (seleccion != null) {
            totalHoras.setText("Total de materias: " + obternerHoras.getHorasCatedras(id));
            docentes.setValue(seleccion.getNombre() + " " + seleccion.getApellido());
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.initModality(Modality.APPLICATION_MODAL);
            alerta.setTitle("Docente sin materias");
            alerta.setHeaderText("el docente no tiene materias");
            alerta.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        c_materia.setCellValueFactory(new PropertyValueFactory<>("materia"));
        c_curso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        UEM.setCellValueFactory(new PropertyValueFactory<>("UEM"));

        lista.forEach(docenteUem -> {
            if (!Objects.equals(docenteUem.getNombre(), null)) {
                list.add(docenteUem.getNombre() + " " + docenteUem.getApellido());
            }
        });
        Stream<String> listaNueva = list.stream().distinct();

        listaNueva.forEach(docente -> docentes.getItems().add(docente));

        docentes.valueProperty().addListener(docente
                -> {
            lista.forEach(docenteCurso -> {
                        if (docentes.getValue().equals(docenteCurso.getNombre() + " " + docenteCurso.getApellido())) {
                            totalHoras.setText("Total de materias: " + obternerHoras.getHorasCatedras(docenteCurso.getIddocente()));
                        }
                    }
            );
            t_cursos.setItems(
                    lista.filtered((t) -> docentes.getValue().equals(t.getNombre() + " " + t.getApellido())));
            eliminar.setVisible(true);
            agregar.setText("Añadir Materia");
        });

    }

    public void eliminarMateria() {

        docente_curso docente = t_cursos.getSelectionModel().getSelectedItem();
        if (docente == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.initModality(Modality.APPLICATION_MODAL);
            alerta.setTitle("Error");
            alerta.setHeaderText("Seleccione un docente");
            alerta.showAndWait();
        } else {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.initModality(Modality.APPLICATION_MODAL);
            alerta.setTitle("Confirmar");
            alerta.setHeaderText("¿Desea eliminar esta materia del docente?");
            alerta.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    lista.remove(docente);
                    eliminarDocenteCurso.eliminarMateriaBD(docente);
                }
            });
        }
    }
    public void materiasSinDocente() {
        docentes.setValue("docente");
        t_cursos.setItems(lista.filtered((t) -> t.getNombre() == null));
        agregar.setText("Añadir docente");
        eliminar.setVisible(false);
    }

    public void agregarBton() throws IOException {

        docente_curso docente = t_cursos.getSelectionModel().getSelectedItem();

        if (Objects.equals(agregar.getText(), "Añadir Materia")) {

            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("agregar-docente-materia.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Docente UEM");
            stage.setScene(scene);
            stage.showAndWait();

        } else {
            if (docente == null) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.initModality(Modality.APPLICATION_MODAL);
                alerta.setTitle("Error");
                alerta.setHeaderText("Seleccione una materia");
                alerta.showAndWait();
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("agregar-docente-materia.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Docente UEM");
                stage.setScene(scene);
                stage.showAndWait();
            }
        }
    }
}
