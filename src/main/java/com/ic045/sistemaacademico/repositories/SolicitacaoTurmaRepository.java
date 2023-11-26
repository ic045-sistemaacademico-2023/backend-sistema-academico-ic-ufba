package com.ic045.sistemaacademico.repositories;

import com.ic045.sistemaacademico.domain.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ic045.sistemaacademico.domain.models.SolicitacaoTurma;

import java.util.List;

public interface SolicitacaoTurmaRepository extends JpaRepository<SolicitacaoTurma, Long> {
    List<SolicitacaoTurma> findAllBySolicitacaoMatriculaId(Long solicitacaoMatriculaId);
    List<SolicitacaoTurma> findByTurmaId(Long idTurma);
    void deleteAllBySolicitacaoMatriculaId(Long id);

    List<SolicitacaoTurma> findAllByAlunoId(Long alunoId);
}
