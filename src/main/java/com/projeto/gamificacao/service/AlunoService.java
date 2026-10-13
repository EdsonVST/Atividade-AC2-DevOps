package com.projeto.gamificacao.service;

import com.projeto.gamificacao.domain.Aluno;
import com.projeto.gamificacao.domain.Curso;
import com.projeto.gamificacao.dto.ConclusaoCursoRequestDTO;
import com.projeto.gamificacao.dto.ConclusaoCursoResponseDTO;
import com.projeto.gamificacao.repository.AlunoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlunoService {
    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ConclusaoCursoResponseDTO processarConclusao(ConclusaoCursoRequestDTO dto) {
        Curso curso = new Curso(dto.nomeCurso());
        curso.concluir(dto.mediaFinal());

        Aluno aluno = new Aluno(dto.nomeAluno());
        aluno.processarConclusao(curso);

        Aluno salvo = repository.save(aluno);
        return toDTO(salvo);
    }

    private ConclusaoCursoResponseDTO toDTO(Aluno aluno) {
        return new ConclusaoCursoResponseDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getUltimoCurso(),
                aluno.getUltimaMedia(),
                aluno.getCursosLiberados()
        );
    }
}
