package com.projeto.gamificacao.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public record ConclusaoCursoRequestDTO(
        @NotBlank String nomeAluno,
        @NotBlank String nomeCurso,
        @DecimalMin("0.0") @DecimalMax("10.0") double mediaFinal
) {}
