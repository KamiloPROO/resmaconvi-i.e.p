package com.kamiloinc.resmaconviiep.Model;

public class DataSpinnerFaltasCometidas {

    String descripcionFata;

    public DataSpinnerFaltasCometidas() {
    }

    public DataSpinnerFaltasCometidas(String descripcionFata) {
        this.descripcionFata = descripcionFata;
    }

    public String getDescripcionFata() {
        return descripcionFata;
    }

    public void setDescripcionFata(String descripcionFata) {
        this.descripcionFata = descripcionFata;
    }

    @Override
    public String toString() {
        return descripcionFata;
    }
}
