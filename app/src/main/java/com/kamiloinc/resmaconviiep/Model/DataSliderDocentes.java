package com.kamiloinc.resmaconviiep.Model;

public class DataSliderDocentes {

    String nombreDocente;

    public DataSliderDocentes() {
    }

    public DataSliderDocentes(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    @Override
    public String toString() {
        return nombreDocente;
    }
}
