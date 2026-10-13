package com.projeto.gamificacao.controller;

import com.projeto.gamificacao.dto.ConclusaoCursoRequestDTO;
import com.projeto.gamificacao.dto.ConclusaoCursoResponseDTO;
import com.projeto.gamificacao.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alunos")
@CrossOrigin(origins = "*")
@Tag(name = "Alunos")
public class AlunoRestController {
    private final AlunoService service;

    public AlunoRestController(AlunoService service) {
        this.service = service;
    }

    @PostMapping("/conclusao")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Processar conclusao de curso e liberar recompensa")
    public ConclusaoCursoResponseDTO processarConclusao(
            @Valid @RequestBody ConclusaoCursoRequestDTO dto) {
        return service.processarConclusao(dto);
    }
}
