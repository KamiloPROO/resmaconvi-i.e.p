package com.kamiloinc.resmaconviiep.Model;

public class DataDocentes {

    String nombre;

    String numDocumento;

    String correo;

    String cargo;

    String sede;

    String especialidad;

    String nivelEnseñanza;

    String numeroTelefono;

    public DataDocentes() {
    }

    public DataDocentes(String nombre, String numDocumento, String correo, String cargo, String sede, String especialidad, String nivelEnseñanza, String numeroTelefono) {
        this.nombre = nombre;
        this.numDocumento = numDocumento;
        this.correo = correo;
        this.cargo = cargo;
        this.sede = sede;
        this.especialidad = especialidad;
        this.nivelEnseñanza = nivelEnseñanza;
        this.numeroTelefono = numeroTelefono;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNivelEnseñanza() {
        return nivelEnseñanza;
    }

    public void setNivelEnseñanza(String nivelEnseñanza) {
        this.nivelEnseñanza = nivelEnseñanza;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }


}
