package com.projeto.gamificacao.dto;

public record ConclusaoCursoResponseDTO(
        Long id,
        String nomeAluno,
        String nomeCurso,
        double mediaFinal,
        int cursosLiberados
) {}
