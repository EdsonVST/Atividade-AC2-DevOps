package com.projeto.gamificacao.domain;

public class Curso {
    private final String nome;
    private double mediaFinal;

    public Curso(String nome) {
        this.nome = nome;
    }

    public void concluir(double mediaFinal) {
        this.mediaFinal = mediaFinal;
    }

    public String getNome() {
        return nome;
    }

    public double getMediaFinal() {
        return mediaFinal;
    }
}
