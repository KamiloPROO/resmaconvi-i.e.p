package com.kamiloinc.resmaconviiep.Model;

public class DataVerTodosLosReportes {


    String  imgCorreo ,
            nombreUser,
            anio,
            personaReportada,
            cursoSeleccionado,
            periodoReporte,
            docenteQRP,
            faltaCometidaN1,
            faltaCometidaN2,
            faltaCometidaN3,
            correctivoEstudiante;


    public DataVerTodosLosReportes() {
    }

    public DataVerTodosLosReportes(String imgCorreo, String nombreUser, String anio, String personaReportada, String cursoSeleccionado, String periodoReporte, String docenteQRP, String faltaCometidaN1, String faltaCometidaN2, String faltaCometidaN3, String correctivoEstudiante) {
        this.imgCorreo = imgCorreo;
        this.nombreUser = nombreUser;
        this.anio = anio;
        this.personaReportada = personaReportada;
        this.cursoSeleccionado = cursoSeleccionado;
        this.periodoReporte = periodoReporte;
        this.docenteQRP = docenteQRP;
        this.faltaCometidaN1 = faltaCometidaN1;
        this.faltaCometidaN2 = faltaCometidaN2;
        this.faltaCometidaN3 = faltaCometidaN3;
        this.correctivoEstudiante = correctivoEstudiante;
    }

    public String getImgCorreo() {
        return imgCorreo;
    }

    public void setImgCorreo(String imgCorreo) {
        this.imgCorreo = imgCorreo;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getPersonaReportada() {
        return personaReportada;
    }

    public void setPersonaReportada(String personaReportada) {
        this.personaReportada = personaReportada;
    }

    public String getCursoSeleccionado() {
        return cursoSeleccionado;
    }

    public void setCursoSeleccionado(String cursoSeleccionado) {
        this.cursoSeleccionado = cursoSeleccionado;
    }

    public String getPeriodoReporte() {
        return periodoReporte;
    }

    public void setPeriodoReporte(String periodoReporte) {
        this.periodoReporte = periodoReporte;
    }

    public String getDocenteQRP() {
        return docenteQRP;
    }

    public void setDocenteQRP(String docenteQRP) {
        this.docenteQRP = docenteQRP;
    }

    public String getFaltaCometidaN1() {
        return faltaCometidaN1;
    }

    public void setFaltaCometidaN1(String faltaCometidaN1) {
        this.faltaCometidaN1 = faltaCometidaN1;
    }

    public String getFaltaCometidaN2() {
        return faltaCometidaN2;
    }

    public void setFaltaCometidaN2(String faltaCometidaN2) {
        this.faltaCometidaN2 = faltaCometidaN2;
    }

    public String getFaltaCometidaN3() {
        return faltaCometidaN3;
    }

    public void setFaltaCometidaN3(String faltaCometidaN3) {
        this.faltaCometidaN3 = faltaCometidaN3;
    }

    public String getCorrectivoEstudiante() {
        return correctivoEstudiante;
    }

    public void setCorrectivoEstudiante(String correctivoEstudiante) {
        this.correctivoEstudiante = correctivoEstudiante;
    }

    @Override
    public String toString() {
        return
                imgCorreo
                +nombreUser
                + anio
                +personaReportada
                + cursoSeleccionado
                + periodoReporte
                + docenteQRP
                + faltaCometidaN1
                + faltaCometidaN2
                + faltaCometidaN3
                + correctivoEstudiante;
    }
}
