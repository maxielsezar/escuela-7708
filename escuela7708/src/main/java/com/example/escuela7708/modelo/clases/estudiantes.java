package com.example.escuela7708.modelo.clases;

public class estudiantes {
    private int idestudiante,iduem;
    private int dni;

    public estudiantes(int idestudiante, int iduem, int dni) {
        this.idestudiante = idestudiante;
        this.iduem = iduem;
        this.dni = dni;
    }

    public int getIdestudiante() {
        return idestudiante;
    }

    public void setIdestudiante(int idestudiante) {
        this.idestudiante = idestudiante;
    }

    public int getIduem() {
        return iduem;
    }

    public void setIduem(int iduem) {
        this.iduem = iduem;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
}
