package com.example.escuela7708.controladores;

import com.example.escuela7708.modelo.basedatos.listaDocenteUEM;
import com.example.escuela7708.modelo.clases.docente_dia;
import com.example.escuela7708.modelo.clases.docente_uem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.time.LocalDate;

public class tablaDocenteFecha extends docente_dia {
    ComboBox<String> docenteLunes = new ComboBox<>();
    ComboBox<String> docenteMartes = new ComboBox<>();
    ComboBox<String> docenteMiercoles = new ComboBox<>();
    ComboBox<String> docenteJueves = new ComboBox<>();
    ComboBox<String> docenteViernes = new ComboBox<>();

    int uem;
    int semana;

    public int getUem() {
        return uem;
    }

    public void setUem(int uem) {
        this.uem = uem;
    }

    public ObservableList<String> getLista() {
        ObservableList<String> lista= FXCollections.observableArrayList();
        ObservableList<docente_uem>docente = listaDocenteUEM.getDocenteUEMList(uem);
        docente.forEach(docentes -> {
            lista.add(docentes.getNombre()+" "+docentes.getApellido());
        });
        return lista;
    }

    public tablaDocenteFecha(int uem, int semana) {
        super();
        this.uem=uem;
        this.semana=semana;
    }

    public ComboBox<String> getDocenteLunes() {
        docenteLunes.setItems(getLista());
        return docenteLunes;
    }

    public void setDocenteLunes(ComboBox<String> docenteLunes) {
        this.docenteLunes = docenteLunes;
    }

    public ComboBox<String> getDocenteMartes() {
        docenteMartes.setItems(getLista());
        return docenteMartes;
    }

    public void setDocenteMartes(ComboBox<String> docenteMartes) {
        this.docenteMartes = docenteMartes;
    }

    public ComboBox<String> getDocenteMiercoles() {
        docenteMiercoles.setItems(getLista());
        return docenteMiercoles;
    }

    public void setDocenteMiercoles(ComboBox<String> docenteMiercoles) {
        this.docenteMiercoles = docenteMiercoles;
    }

    public ComboBox<String> getDocenteJueves() {
        docenteJueves.setItems(getLista());
        return docenteJueves;
    }

    public void setDocenteJueves(ComboBox<String> docenteJueves) {
        this.docenteJueves = docenteJueves;
    }

    public ComboBox<String> getDocenteViernes() {
        docenteViernes.setItems(getLista());
        return docenteViernes;
    }

    public void setDocenteViernes(ComboBox<String> docenteViernes) {
        this.docenteViernes = docenteViernes;
    }
}
