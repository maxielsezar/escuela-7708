package com.example.escuela7708.modelo.clases;

import com.example.escuela7708.modelo.basedatos.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class curso {
    private int id_curso;
    private int uem;
    private int curso;
    private String materia;

    public curso(int id_curso, int uem, int curso, String materia) {
        this.id_curso = id_curso;
        this.uem = uem;
        this.curso = curso;
        this.materia = materia;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public int getUem() {
        return uem;
    }

    public void setUem(int uem) {
        this.uem = uem;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }


}
