package com.example.escuela7708.modelo.clases;

import com.example.escuela7708.modelo.basedatos.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;

public class docente_dia {
    private int docente;
    private int uem;
    private LocalDate dia;
    private String nombre;
    private String apellido;

    public docente_dia() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public docente_dia(int docente, int uem, LocalDate dia) {
        this.docente = docente;
        this.uem = uem;
        this.dia = dia;
    }

    public int getDocente() {
        return docente;
    }

    public void setDocente(int docente) {
        this.docente = docente;
    }

    public int getUem() {
        return uem;
    }

    public void setUem(int uem) {
        this.uem = uem;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }


}
