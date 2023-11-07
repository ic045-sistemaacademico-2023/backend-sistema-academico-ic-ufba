package com.ic045.sistemaacademico.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ic045.sistemaacademico.domain.models.OpMatriculaDisciplinaTurma;

@Repository
public interface OpMatriculaDisciplinaTurmaRepository extends JpaRepository<OpMatriculaDisciplinaTurma, Long>{
	@Query("SELECT opmat FROM OpMatriculaDisciplinaTurma opmat WHERE opmat.id = :id AND opmat.disciplina.id = :disciplinaId")
    public OpMatriculaDisciplinaTurma findByIdAndDisciplinaId(@Param("id") Long id, @Param("disciplinaId") Long disciplinaId);

	@Query("SELECT opmat FROM OpMatriculaDisciplinaTurma opmat WHERE opmat.id = :id AND opmat.turma.id = :idTurma")
	public OpMatriculaDisciplinaTurma findByIdAndTurmaId(Long id, Long idTurma);
}
