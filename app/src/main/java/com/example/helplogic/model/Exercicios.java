package com.example.helplogic.model;

public class Exercicios {

    private String id;
    private Boolean favorita;
    private EstadoExercicio estadoExercicio;
    private String enunciado;
    private String titulo;

    public Exercicios(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getFavorita() {
        return favorita;
    }

    public void setFavorita(Boolean favorita) {
        this.favorita = favorita;
    }

    public EstadoExercicio getEstadoExercicio() {
        return estadoExercicio;
    }

    public void setEstadoExercicio(EstadoExercicio estadoExercicio) {
        this.estadoExercicio = estadoExercicio;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
