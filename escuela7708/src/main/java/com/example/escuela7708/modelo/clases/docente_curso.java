package com.example.escuela7708.modelo.clases;

public class docente_curso{

    int UEM;
    int curso;
    String materia;

    String nombre;
    String apellido;

    int iddocente;
    int idmateria;

    public docente_curso(){}

    public docente_curso(int UEM, int curso, String materia,String nombre, String apellido, int iddocente, int idmateria) {
        this.UEM = UEM;
        this.curso = curso;
        this.materia = materia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.iddocente = iddocente;
        this.idmateria = idmateria;
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

    public int getUEM() {
        return UEM;
    }

    public void setUEM(int UEM) {
        this.UEM = UEM;
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
    public int getIddocente() {
        return iddocente;
    }

    public void setIddocente(int iddocente) {
        this.iddocente = iddocente;
    }

    public int getIdmateria() {
        return idmateria;
    }

    public void setIdmateria(int idmateria) {
        this.idmateria = idmateria;
    }
}
