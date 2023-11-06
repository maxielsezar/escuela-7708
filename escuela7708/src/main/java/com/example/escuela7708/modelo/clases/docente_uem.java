package com.example.escuela7708.modelo.clases;

public class docente_uem {
    private int uem,iddocente;
    private String nombre, apellido;

    public docente_uem(int uem, int iddocente, String nombre, String apellido) {
        this.uem = uem;
        this.iddocente = iddocente;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getIddocente() {
        return iddocente;
    }

    public void setIddocente(int iddocente) {
        this.iddocente = iddocente;
    }

    public int getUem() {
        return uem;
    }

    public void setUem(int uem) {
        this.uem = uem;
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
}
