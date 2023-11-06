package com.example.escuela7708.controladores;

import com.example.escuela7708.Application;
import com.example.escuela7708.modelo.basedatos.docente.eliminarDocenteBD;
import com.example.escuela7708.modelo.basedatos.docente.listaDocente;
import com.example.escuela7708.modelo.basedatos.docente.modificarDocenteBD;
import com.example.escuela7708.modelo.basedatos.docenteCurso.eliminarDocenteCurso;
import com.example.escuela7708.modelo.basedatos.docenteCurso.listaDocenteCurso;
import com.example.escuela7708.modelo.basedatos.docenteCurso.obtenerDocenteCurso;
import com.example.escuela7708.modelo.basedatos.listaUEM;
import com.example.escuela7708.modelo.basedatos.obternerHoras;
import com.example.escuela7708.modelo.clases.docente;
import com.example.escuela7708.modelo.clases.docente_curso;
import com.example.escuela7708.modelo.clases.uem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.*;

public class VistaPrincipal implements Initializable {


    @FXML
    private TableView<tablaDocenteFecha> tablaDias,tablaDias2,tablaDias3,tablaDias4,tablaDias5;

    @FXML
    private TableColumn<tablaDocenteFecha, ComboBox> lunes,lunes2,lunes3,lunes4,lunes5;
    @FXML

    private TableColumn<tablaDocenteFecha, ComboBox> martes,martes2,martes3,martes4,martes5;
    @FXML

    private TableColumn<tablaDocenteFecha, ComboBox> miercoles,miercoles2,miercoles3,miercoles4,miercoles5;
    @FXML

    private TableColumn<tablaDocenteFecha, ComboBox> jueves,jueves2,jueves3,jueves4,jueves5;
    @FXML

    private TableColumn<tablaDocenteFecha, ComboBox> viernes,viernes2,viernes3,viernes4,viernes5;

    int iduem=10;
    @FXML
    public TabPane panel;

    @FXML
    public Label calendario_mes;
    @FXML
    public DatePicker calendario_fecha;
    @FXML
    public ListView list;
    public MenuButton calendario_menu;
    @FXML
    private TableView<docente> tablaDocente;

    @FXML
    private TableColumn<docente, String> nombre,apellido,nacimiento,sexo,cuil,email;

    @FXML
    private TableColumn<docente, Integer> dni;

    public docente docente;
    @FXML
    public TextField t_buscar;
    @FXML
    public TableView<docente_curso> t_cursos1, t_cursos2, t_cursos3, t_cursos4, t_cursos5, t_cursos6;
    @FXML
    public TableColumn<docente_curso, String> c_materia1, c_materia2, c_materia3, c_materia4, c_materia5, c_materia6;
    @FXML
    public TableColumn<docente_curso, String> c_curso1, c_curso2, c_curso3, c_curso4, c_curso5, c_curso6;
    @FXML
    public MenuButton uemMenu;

    @FXML
    public Label localidad;
    @FXML
    public MenuButton menuCurso;
    @FXML
    public Label orientacion;

    @FXML
    private MenuButton menuUEM;


    FilteredList<String> filteredData;

    @FXML
    public Button eliminar;

    private docente_curso docenteCurso;

    ObservableList<docente_curso> docentecursosList= listaDocenteCurso.getDocenteCursosList();


    FilteredList<docente_curso> filteredData1 = new FilteredList<>(docentecursosList, p -> true);
    FilteredList<docente_curso> filteredData2 = new FilteredList<>(docentecursosList, p -> true);
    FilteredList<docente_curso> filteredData3 = new FilteredList<>(docentecursosList, p -> true);
    FilteredList<docente_curso> filteredData4 = new FilteredList<>(docentecursosList, p -> true);
    FilteredList<docente_curso> filteredData5 = new FilteredList<>(docentecursosList, p -> true);
    FilteredList<docente_curso> filteredData6 = new FilteredList<>(docentecursosList, p -> true);
    ObservableList<docente> docentesObservableList = listaDocente.getDocenteList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicioVentanaDocentes();
        inicioVentanaClases();
        iniciarCalendario();
    }

    //VENTANA DOCENTE
    public void inicioVentanaDocentes(){
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        nacimiento.setCellValueFactory(new PropertyValueFactory<>("nacimiento"));
        sexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        dni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        cuil.setCellValueFactory(new PropertyValueFactory<>("cuil"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        FilteredList<docente> filteredData = new FilteredList<>(docentesObservableList, p -> true);
        tablaDocente.setItems(filteredData);

        t_buscar.textProperty().addListener((prop, old, text) -> {
            filteredData.setPredicate(docente -> {
                if (text == null || text.isEmpty()) return true;

                String name = docente.getNombre().toLowerCase();
                String apellido = docente.getApellido().toLowerCase();
                return name.contains(text.toLowerCase()) || apellido.contains(text.toLowerCase());
            });
        });
    }
    @FXML
    public void ventanaGuardar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("guarda-docente-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Guardar");
        stage.initModality(Modality.APPLICATION_MODAL);
        guardarDocenteController controlador = fxmlLoader.getController();
        controlador.docenteGuardar(docentesObservableList);
        stage.setScene(scene);
        stage.show();
    }

    public void eliminarRegistro() {
        eliminarDocenteBD eliminar = new eliminarDocenteBD(docente);
        eliminar.eliminarBD();
        docentesObservableList.remove(docente);
        tablaDocente.refresh();
    }

    @FXML
    public void eliminar() {
        if (docente==null){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("no se pudo eliminar");
            alerta.setHeaderText("Seleccione un docente para eliminar");
            alerta.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    eliminarRegistro();
                }
            });
        }else {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("eliminar");
            alerta.setHeaderText("desea eliminar los datos");
            alerta.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    eliminarRegistro();
                }
            });
        }
    }

    @FXML
    public void seleccionTabla() {
        if (!tablaDocente.getSelectionModel().isEmpty()) {
            docente = tablaDocente.getSelectionModel().getSelectedItem();
        }
    }

    @FXML
    private void modificar() throws IOException {
        if (docente==null){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Seleccione un docente");
            alerta.setHeaderText("Seleccione un docente para modificar");
            alerta.showAndWait();
        }else {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("guarda-docente-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            guardarDocenteController controlador = fxmlLoader.getController();
            controlador.setDocente(docente);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Modificar Docente");
            stage.setScene(scene);
            stage.showAndWait();
            tablaDocente.refresh();
        }
    }

    public void abrirVentanaDocenteMaterias() throws IOException {
        if(tablaDocente.getSelectionModel().getSelectedItem()==null){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Seleccione un docente");
            alerta.setHeaderText("Por favor seleccione un docente");
            alerta.showAndWait();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("docentes_materias.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            int id_docente = tablaDocente.getSelectionModel().getSelectedItem().getId();
            docente_curso seleccion = obtenerDocenteCurso.getDocenteCurso(id_docente);
            if(seleccion==null) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.initModality(Modality.APPLICATION_MODAL);
                alerta.setTitle("Docente sin materias");
                alerta.setHeaderText("el docente no tiene materias");
                alerta.showAndWait();
            }else {
                DocentesMateriasController controlador = fxmlLoader.getController();
                controlador.setDocente(id_docente);
                stage.setTitle("Materias");
                stage.setScene(scene);
                stage.showAndWait();
            }
        }
    }

    //VENTANA CLASES
    public void inicioVentanaClases() {

        c_materia1.setCellValueFactory(new PropertyValueFactory<>("materia"));
        c_curso1.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        c_materia2.setCellValueFactory(new PropertyValueFactory<>("materia"));
        c_curso2.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        c_materia3.setCellValueFactory(new PropertyValueFactory<>("materia"));
        c_curso3.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        c_materia4.setCellValueFactory(new PropertyValueFactory<>("materia"));
        c_curso4.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        c_materia5.setCellValueFactory(new PropertyValueFactory<>("materia"));
        c_curso5.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        c_materia6.setCellValueFactory(new PropertyValueFactory<>("materia"));
        c_curso6.setCellValueFactory(new PropertyValueFactory<>("apellido"));


        List<uem> lista = listaUEM.Lista();
        uemMenu.getItems().removeAll();

        for (uem uem : lista) {
            MenuItem item = new MenuItem(String.valueOf(uem.getNum()));
            item.setOnAction(a -> {
                uemMenu.setText(item.getText());
            });
            uemMenu.getItems().add(item);
        }

        uemMenu.textProperty().addListener((prop, old, text) ->
                filteredData1.setPredicate(curso -> curso.getUEM() == Integer.parseInt(uemMenu.getText()) && curso.getCurso() == 1));
        t_cursos1.setItems(filteredData1);
        uemMenu.textProperty().addListener((prop, old, text) ->
                filteredData2.setPredicate(curso -> curso.getUEM() == Integer.parseInt(uemMenu.getText()) && curso.getCurso() == 2));
        t_cursos2.setItems(filteredData2);

        uemMenu.textProperty().addListener((prop, old, text) ->
                filteredData3.setPredicate(curso -> curso.getUEM() == Integer.parseInt(uemMenu.getText()) && curso.getCurso() == 3));
        t_cursos3.setItems(filteredData3);

        uemMenu.textProperty().addListener((prop, old, text) ->
                filteredData4.setPredicate(curso -> curso.getUEM() == Integer.parseInt(uemMenu.getText()) && curso.getCurso() == 4));
        t_cursos4.setItems(filteredData4);

        uemMenu.textProperty().addListener((prop, old, text) ->
                filteredData5.setPredicate(curso -> curso.getUEM() == Integer.parseInt(uemMenu.getText()) && curso.getCurso() == 5));
        t_cursos5.setItems(filteredData5);

        uemMenu.textProperty().addListener((prop, old, text) ->
                filteredData6.setPredicate(curso -> curso.getUEM() == Integer.parseInt(uemMenu.getText()) && curso.getCurso() == 6));
        t_cursos6.setItems(filteredData6);

        uemMenu.setText(uemMenu.getItems().get(0).getText());

    }

    public void abrirVentanaAgregar() throws IOException {
        if (docenteCurso == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Seleccione un docente");
            alerta.setHeaderText("Por favor seleccione un docente");
            alerta.showAndWait();
        } else if (docenteCurso.getIddocente() != 0) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Esta materia ya tiene un docente");
            alerta.setHeaderText("Por favor seleccione una materia sin docente");
            alerta.showAndWait();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("agregar_docente_curso.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            AgregarDocenteCursoController controlador = fxmlLoader.getController();
            controlador.setListaMateriales(docenteCurso);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Agregar Docente");
            stage.setScene(scene);
            stage.showAndWait();
            docente_curso docente =controlador.getDocente();
            if(docente!=null){
                docenteCurso.setApellido(docente.getApellido());
            }
            actualizar();
        }
    }

    public void seleccion() {
        if (t_cursos1.isFocused()) {
            docenteCurso=t_cursos1.getSelectionModel().getSelectedItem();
        } else if (t_cursos2.isFocused()) {
            docenteCurso=t_cursos2.getSelectionModel().getSelectedItem();
        } else if (t_cursos3.isFocused()) {
            docenteCurso=t_cursos3.getSelectionModel().getSelectedItem();
        } else if (t_cursos4.isFocused()) {
            docenteCurso=t_cursos4.getSelectionModel().getSelectedItem();
        } else if (t_cursos5.isFocused()) {
            docenteCurso=t_cursos5.getSelectionModel().getSelectedItem();
        } else if (t_cursos6.isFocused()) {
            docenteCurso=t_cursos6.getSelectionModel().getSelectedItem();
        }
    }

    public void eliminiar() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Eliminar");
        alerta.setHeaderText("Quitar docente de " + docenteCurso.getMateria());
        alerta.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                eliminarDocenteCurso.eliminarMateriaBD(docenteCurso);
                docenteCurso.setIddocente(0);
                docenteCurso.setNombre(null);
                docenteCurso.setApellido(null);
                actualizar();
            }
        });
    }

    public void actualizar(){
        t_cursos1.refresh();
        t_cursos2.refresh();
        t_cursos3.refresh();
        t_cursos4.refresh();
        t_cursos5.refresh();
        t_cursos6.refresh();
    }

    public void abrirVentanaAgregarDocente(ActionEvent actionEvent) {
    }

    public void abrirVentanaDocenteUEM(ActionEvent actionEvent) {
    }


    public void iniciarCalendario(){

        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {

                super.updateItem(item, empty);

                this.setDisable(false);
                this.setBackground(null);
                this.setTextFill(Color.BLACK);

                DayOfWeek dayweek = item.getDayOfWeek();
                if (dayweek == DayOfWeek.SATURDAY || dayweek == DayOfWeek.SUNDAY) {
                    this.setTextFill(Color.RED);
                    this.setDisable(true);
                }
            }
        };


        calendario_fecha.setValue(LocalDate.now());

        calendario_fecha.valueProperty().addListener((ov, oldValue, newValue) -> {
            calendario_fecha.setValue(newValue);
            LocalDate date = calendario_fecha.getValue();
            String nombre = date.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
            calendario_mes.setText(nombre.toUpperCase());
            cargarCalendario(date.withDayOfMonth(1));
        });

        LocalDate date = calendario_fecha.getValue();
        String nombre = date.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        calendario_mes.setText(nombre.toUpperCase());
        cargarCalendario(LocalDate.now().withDayOfMonth(1));

        List<uem> lista = listaUEM.Lista();

        calendario_menu.getItems().removeAll();
        for (uem uem : lista) {
            MenuItem item = new MenuItem("UEM " + uem.getNum() + " " + uem.getLocalidad());
            item.setOnAction(a->{
                iduem=uem.getNum();
                calendario_menu.setText(item.getText());
                cargarTablas();
            });
            calendario_menu.getItems().add(item);
        }
        calendario_menu.setText(calendario_menu.getItems().get(0).getText());

        cargarTablas();

        lunes.setCellValueFactory(new PropertyValueFactory<>("docenteLunes"));
        martes.setCellValueFactory(new PropertyValueFactory<>("docenteMartes"));
        miercoles.setCellValueFactory(new PropertyValueFactory<>("docenteMiercoles"));
        jueves.setCellValueFactory(new PropertyValueFactory<>("docenteJueves"));
        viernes.setCellValueFactory(new PropertyValueFactory<>("docenteViernes"));

        lunes2.setCellValueFactory(new PropertyValueFactory<>("docenteLunes"));
        martes2.setCellValueFactory(new PropertyValueFactory<>("docenteMartes"));
        miercoles2.setCellValueFactory(new PropertyValueFactory<>("docenteMiercoles"));
        jueves2.setCellValueFactory(new PropertyValueFactory<>("docenteJueves"));
        viernes2.setCellValueFactory(new PropertyValueFactory<>("docenteViernes"));

        lunes3.setCellValueFactory(new PropertyValueFactory<>("docenteLunes"));
        martes3.setCellValueFactory(new PropertyValueFactory<>("docenteMartes"));
        miercoles3.setCellValueFactory(new PropertyValueFactory<>("docenteMiercoles"));
        jueves3.setCellValueFactory(new PropertyValueFactory<>("docenteJueves"));
        viernes3.setCellValueFactory(new PropertyValueFactory<>("docenteViernes"));

        lunes4.setCellValueFactory(new PropertyValueFactory<>("docenteLunes"));
        martes4.setCellValueFactory(new PropertyValueFactory<>("docenteMartes"));
        miercoles4.setCellValueFactory(new PropertyValueFactory<>("docenteMiercoles"));
        jueves4.setCellValueFactory(new PropertyValueFactory<>("docenteJueves"));
        viernes4.setCellValueFactory(new PropertyValueFactory<>("docenteViernes"));

        lunes5.setCellValueFactory(new PropertyValueFactory<>("docenteLunes"));
        martes5.setCellValueFactory(new PropertyValueFactory<>("docenteMartes"));
        miercoles5.setCellValueFactory(new PropertyValueFactory<>("docenteMiercoles"));
        jueves5.setCellValueFactory(new PropertyValueFactory<>("docenteJueves"));
        viernes5.setCellValueFactory(new PropertyValueFactory<>("docenteViernes"));

    }

    public void cargarTablas(){

        int semana = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);

        ObservableList<tablaDocenteFecha> tablaFecha= FXCollections.observableArrayList();
        for (int i=0;i<6;i++){
            tablaFecha.add(new tablaDocenteFecha(iduem,semana));
        }

        ObservableList<tablaDocenteFecha> tablaFecha2= FXCollections.observableArrayList();
        for (int i=0;i<6;i++){
            tablaFecha2.add(new tablaDocenteFecha(iduem,semana));
        }

        ObservableList<tablaDocenteFecha> tablaFecha3= FXCollections.observableArrayList();
        for (int i=0;i<6;i++){
            tablaFecha3.add(new tablaDocenteFecha(iduem,semana));
        }

        ObservableList<tablaDocenteFecha> tablaFecha4= FXCollections.observableArrayList();
        for (int i=0;i<6;i++){
            tablaFecha4.add(new tablaDocenteFecha(iduem,semana));
        }

        ObservableList<tablaDocenteFecha> tablaFecha5= FXCollections.observableArrayList();
        for (int i=0;i<6;i++){
            tablaFecha5.add(new tablaDocenteFecha(iduem,semana));
        }

        tablaDias.setItems(tablaFecha);
        tablaDias2.setItems(tablaFecha2);
        tablaDias3.setItems(tablaFecha3);
        tablaDias4.setItems(tablaFecha4);
        tablaDias5.setItems(tablaFecha5);
    }

    public void cargarCalendario(LocalDate dia1){
        if (dia1.getDayOfWeek().getValue()==7){
            dia1=dia1.plusDays(2);
        }
        if (dia1.getDayOfWeek().getValue()==6){
            dia1=dia1.plusDays(1);
        }
        cargarDias(dia1);
        cargarDias2(dia1.plusDays(7));
        cargarDias3(dia1.plusDays(14));
        cargarDias4(dia1.plusDays(21));
        cargarDias5(dia1.plusDays(28));
    }

    public void cargarDias(LocalDate date) {
        date=date.minusDays(date.getDayOfWeek().getValue()-1);
        lunes.setText(fechaCalendario(date));
        date=date.plusDays(1);
        martes.setText(fechaCalendario(date));
        date=date.plusDays(1);
        miercoles.setText(fechaCalendario(date));
        date=date.plusDays(1);
        jueves.setText(fechaCalendario(date));
        date=date.plusDays(1);
        viernes.setText(fechaCalendario(date));
    }

    public void cargarDias2(LocalDate date){
        date=date.minusDays(date.getDayOfWeek().getValue()-1);
        lunes2.setText(fechaCalendario(date));
        date=date.plusDays(1);
        martes2.setText(fechaCalendario(date));
        date=date.plusDays(1);
        miercoles2.setText(fechaCalendario(date));
        date=date.plusDays(1);
        jueves2.setText(fechaCalendario(date));
        date=date.plusDays(1);
        viernes2.setText(fechaCalendario(date));
    }
    public void cargarDias3(LocalDate date){
        date=date.minusDays(date.getDayOfWeek().getValue()-1);
        lunes3.setText(fechaCalendario(date));
        date=date.plusDays(1);
        martes3.setText(fechaCalendario(date));
        date=date.plusDays(1);
        miercoles3.setText(fechaCalendario(date));
        date=date.plusDays(1);
        jueves3.setText(fechaCalendario(date));
        date=date.plusDays(1);
        viernes3.setText(fechaCalendario(date));
    }
    public void cargarDias4(LocalDate date){
        date=date.minusDays(date.getDayOfWeek().getValue()-1);
        lunes4.setText(fechaCalendario(date));
        date=date.plusDays(1);
        martes4.setText(fechaCalendario(date));
        date=date.plusDays(1);
        miercoles4.setText(fechaCalendario(date));
        date=date.plusDays(1);
        jueves4.setText(fechaCalendario(date));
        date=date.plusDays(1);
        viernes4.setText(fechaCalendario(date));
    }
    public void cargarDias5(LocalDate date){
        date=date.minusDays(date.getDayOfWeek().getValue()-1);
        lunes5.setText(fechaCalendario(date));
        date=date.plusDays(1);
        martes5.setText(fechaCalendario(date));
        date=date.plusDays(1);
        miercoles5.setText(fechaCalendario(date));
        date=date.plusDays(1);
        jueves5.setText(fechaCalendario(date));
        date=date.plusDays(1);
        viernes5.setText(fechaCalendario(date));
    }

    public String fechaCalendario(LocalDate date){
        return date.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es", "ES"))+ " " + date.getDayOfMonth();
    }

    public int getEUM() {
        calendario_menu.getText();
        List<uem> lista = listaUEM.Lista();
        for (uem uem : lista) {
            if (Objects.equals(calendario_menu.getText(), "EUM " + uem.getNum() + " " + uem.getLocalidad())){
                iduem = uem.getNum();
            }
        }
        return iduem;
    }

}
