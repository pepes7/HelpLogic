package com.example.helplogic.model;

public class Exercicios {

    private String id;
    private Boolean favorito;
    private EstadoExercicio estado;
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

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }

    public EstadoExercicio getEstadoExercicio() {
        return estado;
    }

    public void setEstadoExercicio(EstadoExercicio estado) {
        this.estado = estado;
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
