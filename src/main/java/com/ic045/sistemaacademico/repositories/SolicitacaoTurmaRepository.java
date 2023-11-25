package com.ic045.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ic045.sistemaacademico.domain.models.SolicitacaoTurma;

import java.util.List;

public interface SolicitacaoTurmaRepository extends JpaRepository<SolicitacaoTurma, Long> {
    List<SolicitacaoTurma> findAllBySolicitacaoMatriculaId(Long solicitacaoMatriculaId);
}
