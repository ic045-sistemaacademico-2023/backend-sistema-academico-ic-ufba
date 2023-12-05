package com.ic045.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ic045.sistemaacademico.domain.models.SolicitacaoMatricula;

public interface SolicitacaoMatriculaRepository extends JpaRepository<SolicitacaoMatricula, Long> {
    SolicitacaoMatricula findByAlunoId(Long id);

    @Query("SELECT sol FROM SolicitacaoMatricula sol WHERE sol.aluno.id = :alunoId AND sol.oportunidadeMatricula.id = :oportunidadeId")
	SolicitacaoMatricula findByAlunoIdAndOportunidadeMatriculaId(Long alunoId, Long oportunidadeId);
}
