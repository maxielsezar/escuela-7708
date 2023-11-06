package com.example.escuela7708.modelo.clases;

import com.example.escuela7708.modelo.basedatos.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class uem {
    private int num;
    private String localidad;
    private String orientacion;

    public String getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }

    public uem(int num, String localidad, String orientacion) {
        this.num = num;
        this.localidad = localidad;
        this.orientacion=orientacion;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

}
