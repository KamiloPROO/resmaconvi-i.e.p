package com.kamiloinc.resmaconviiep.Model;

public class DataSpinnerEstudiante {

    String nombreEstudiante;

    public DataSpinnerEstudiante() {
    }

    public DataSpinnerEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    @Override
    public String toString() {
        return nombreEstudiante;
    }
}
