package com.kamiloinc.resmaconviiep.Model;

public class DataEstudiantes {


    String nombre;

    String numDocumento;

    String curso;

    String sede;

    public DataEstudiantes() {
    }

    public DataEstudiantes(String nombre, String numDocumento, String curso, String sede) {
        this.nombre = nombre;
        this.numDocumento = numDocumento;
        this.curso = curso;
        this.sede = sede;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }
}
