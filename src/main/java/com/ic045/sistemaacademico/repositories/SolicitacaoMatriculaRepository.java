package com.ic045.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ic045.sistemaacademico.domain.models.SolicitacaoMatricula;

public interface SolicitacaoMatriculaRepository extends JpaRepository<SolicitacaoMatricula, Long> {
}
