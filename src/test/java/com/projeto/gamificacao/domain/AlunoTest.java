package com.projeto.gamificacao.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    @Test
    void deveLiberarTresCursosComMediaOito() {
        // Arrange
        Aluno aluno = new Aluno("Leonardo");
        Curso curso = new Curso("Cozinhar");
        curso.concluir(8.0);

        // Act
        aluno.processarConclusao(curso);

        // Assert
        assertEquals(3, aluno.getCursosLiberados());
        assertEquals("Leonardo", aluno.getNome());
        assertEquals("Cozinhar", aluno.getUltimoCurso());
        assertEquals(8.0, aluno.getUltimaMedia());
        assertEquals("Cozinhar", curso.getNome());
        assertEquals(8.0, curso.getMediaFinal());
        assertNull(aluno.getId());

        // Cobre o construtor exigido pelo JPA sem criar um quarto caso de teste.
        Aluno alunoJPA = new Aluno();
        assertNull(alunoJPA.getId());
        assertNull(alunoJPA.getNome());
        assertNull(alunoJPA.getUltimoCurso());
        assertEquals(0.0, alunoJPA.getUltimaMedia());
        assertEquals(0, alunoJPA.getCursosLiberados());
    }

    @Test
    void naoDeveLiberarCursoComMediaSeisPontoNove() {
        // Arrange
        Aluno aluno = new Aluno("Leonardo");
        Curso curso = new Curso("Cozinhar");
        curso.concluir(6.9);

        // Act
        aluno.processarConclusao(curso);

        // Assert
        assertEquals(0, aluno.getCursosLiberados());
    }

    @Test
    void deveLiberarExatamenteTresCursosComMediaNove() {
        // Arrange
        Aluno aluno = new Aluno("Leonardo");
        Curso curso = new Curso("Cozinhar");
        curso.concluir(9.0);

        // Act
        aluno.processarConclusao(curso);

        // Assert
       assertEquals(3, aluno.getCursosLiberados());
       //assertEquals(4, aluno.getCursosLiberados());
    }
}
