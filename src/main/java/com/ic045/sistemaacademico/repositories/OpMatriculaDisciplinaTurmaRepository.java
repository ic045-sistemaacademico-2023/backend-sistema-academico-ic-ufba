package com.ic045.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ic045.sistemaacademico.domain.models.OpMatriculaDisciplinaTurma;

@Repository
public interface OpMatriculaDisciplinaTurmaRepository extends JpaRepository<OpMatriculaDisciplinaTurma, Long>{
    
}
