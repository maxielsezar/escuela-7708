package com.example.escuela7708.modelo.clases;

public class calificador {
    private int idCalificador,idUEM,curso;
    private String espacioCurricular;
   private String tutor;
    private int id_docente, id_estudiante;

    public calificador(int idCalificador, int idUEM, int curso, String espacioCurricular, String tutor, int id_docente, int id_estudiante) {
        this.idCalificador = idCalificador;
        this.idUEM = idUEM;
        this.curso = curso;
        this.espacioCurricular = espacioCurricular;
        this.tutor = tutor;
        this.id_docente = id_docente;
        this.id_estudiante = id_estudiante;
    }

    public int getIdCalificador() {
        return idCalificador;
    }

    public void setIdCalificador(int idCalificador) {
        this.idCalificador = idCalificador;
    }

    public int getIdUEM() {
        return idUEM;
    }

    public void setIdUEM(int idUEM) {
        this.idUEM = idUEM;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public String getEspacioCurricular() {
        return espacioCurricular;
    }

    public void setEspacioCurricular(String espacioCurricular) {
        this.espacioCurricular = espacioCurricular;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public int getId_docente() {
        return id_docente;
    }

    public void setId_docente(int id_docente) {
        this.id_docente = id_docente;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }
}
