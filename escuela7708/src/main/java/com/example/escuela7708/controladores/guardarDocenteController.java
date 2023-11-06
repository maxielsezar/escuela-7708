package com.example.escuela7708.controladores;

import com.example.escuela7708.modelo.basedatos.docente.guardarDocenteBD;
import com.example.escuela7708.modelo.basedatos.docente.modificarDocenteBD;
import com.example.escuela7708.modelo.clases.docente;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class guardarDocenteController implements Initializable {

    @FXML
    public TextField t_nombre;
    @FXML
    public TextField t_apellido;
    @FXML
    public TextField t_dni;

    @FXML
    public DatePicker t_fecha;
    @FXML
    public TextField t_cuil;
    @FXML
    public TextField t_email;
    @FXML
    public MenuButton sexo;
    public Button boton;

    ObservableList<docente> docentesObservableList;

    docente Docente;


    @FXML
    private void guardar(){
        if (Docente==null){
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Guardar");
            alerta.setHeaderText("¿desea guardar los cambios?");
            alerta.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    guardado();
                }
            });
        }else {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Modificar");
            alerta.setHeaderText("¿desea guardar los cambios?");
            alerta.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    modificar();
                }
            });
        }
    }
    private void modificar() {
        Docente.setNombre(t_nombre.getText());
        Docente.setApellido(t_apellido.getText());
        Docente.setCuil(t_cuil.getText());
        Docente.setEmail(t_email.getText());
        Docente.setDni(Integer.parseInt(t_dni.getText()));
        Docente.setSexo(sexo.getText());
        Docente.setNacimiento(t_fecha.getEditor().getText());
        modificarDocenteBD.modificarBD(Docente);
        Stage stage = (Stage) t_nombre.getScene().getWindow();
        stage.close();
    }

    private void guardado() {
        String nombre = t_nombre.getText();
        String apellido = t_apellido.getText();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha = t_fecha.getValue().format(formatoFecha);
        String sex = sexo.getText();
        int dni = Integer.parseInt(t_dni.getText());
        String cuil = t_cuil.getText();
        String email = t_email.getText();
        guardarDocenteBD guardar = new guardarDocenteBD(new docente(1,nombre,apellido, fecha,sex,dni,cuil,email));
        guardar.guardarBD();
        docentesObservableList.add(guardar.getDocente());
        Stage stage = (Stage) t_nombre.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cambiarSexoM(){
        sexo.setText("M");
    }
    @FXML
    private void cambiarSexoF(){
        sexo.setText("F");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        t_fecha.setValue(LocalDate.now());
    }

    public void docenteGuardar(ObservableList<docente> docentesObservableList) {
        this.docentesObservableList=docentesObservableList;
    }

    public void setDocente(docente docente) {
        this.Docente=docente;
        t_nombre.setText(docente.getNombre());
        t_apellido.setText(docente.getApellido());
        t_cuil.setText(docente.getCuil());
        t_dni.setText(String.valueOf(docente.getDni()));
        t_email.setText(docente.getEmail());
        t_fecha.getEditor().setText(docente.getNacimiento());
        boton.setText("MODIFICAR");
    }
}
