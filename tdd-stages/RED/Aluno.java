package com.projeto.gamificacao.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String ultimoCurso;
    private double ultimaMedia;
    private int cursosLiberados;

    protected Aluno() {}
    public Aluno(String nome) { this.nome = nome; }

    public void processarConclusao(Curso curso) {
        this.ultimoCurso = curso.getNome();
        this.ultimaMedia = curso.getMediaFinal();
        this.cursosLiberados = -1; // proposital: os 3 testes devem falhar
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getUltimoCurso() { return ultimoCurso; }
    public double getUltimaMedia() { return ultimaMedia; }
    public int getCursosLiberados() { return cursosLiberados; }
}
