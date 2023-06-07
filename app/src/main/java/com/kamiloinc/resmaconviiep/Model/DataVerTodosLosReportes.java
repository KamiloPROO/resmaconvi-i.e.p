package com.kamiloinc.resmaconviiep.Model;

public class DataVerTodosLosReportes {


    String  nombreUser,
            imgCorreo ,
            anio,
            tipoFaltaSeleccionado,
            cursoSeleccionado,
            faltaCometida,
            personaReportada,
            compromisoEstudiante;


    public DataVerTodosLosReportes() {
    }

    public DataVerTodosLosReportes(String nombreUser, String imgCorreo, String anio, String tipoFaltaSeleccionado, String cursoSeleccionado, String faltaCometida, String personaReportada, String compromisoEstudiante) {
        this.nombreUser = nombreUser;
        this.imgCorreo = imgCorreo;
        this.anio = anio;
        this.tipoFaltaSeleccionado = tipoFaltaSeleccionado;
        this.cursoSeleccionado = cursoSeleccionado;
        this.faltaCometida = faltaCometida;
        this.personaReportada = personaReportada;
        this.compromisoEstudiante = compromisoEstudiante;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getImgCorreo() {
        return imgCorreo;
    }

    public void setImgCorreo(String imgCorreo) {
        this.imgCorreo = imgCorreo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getTipoFaltaSeleccionado() {
        return tipoFaltaSeleccionado;
    }

    public void setTipoFaltaSeleccionado(String tipoFaltaSeleccionado) {
        this.tipoFaltaSeleccionado = tipoFaltaSeleccionado;
    }

    public String getCursoSeleccionado() {
        return cursoSeleccionado;
    }

    public void setCursoSeleccionado(String cursoSeleccionado) {
        this.cursoSeleccionado = cursoSeleccionado;
    }

    public String getFaltaCometida() {
        return faltaCometida;
    }

    public void setFaltaCometida(String faltaCometida) {
        this.faltaCometida = faltaCometida;
    }

    public String getPersonaReportada() {
        return personaReportada;
    }

    public void setPersonaReportada(String personaReportada) {
        this.personaReportada = personaReportada;
    }

    public String getCompromisoEstudiante() {
        return compromisoEstudiante;
    }

    public void setCompromisoEstudiante(String compromisoEstudiante) {
        this.compromisoEstudiante = compromisoEstudiante;
    }
}
