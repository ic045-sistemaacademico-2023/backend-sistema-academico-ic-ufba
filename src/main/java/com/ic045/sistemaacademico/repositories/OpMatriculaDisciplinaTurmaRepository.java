package com.ic045.sistemaacademico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ic045.sistemaacademico.domain.models.OpMatriculaDisciplinaTurma;

@Repository
public interface OpMatriculaDisciplinaTurmaRepository extends JpaRepository<OpMatriculaDisciplinaTurma, Long>{
	@Query("SELECT opmat FROM OpMatriculaDisciplinaTurma opmat WHERE opmat.id = :id AND opmat.disciplina.id = :disciplinaId")
    public OpMatriculaDisciplinaTurma findByIdAndDisciplinaId(@Param("id") Long id, @Param("disciplinaId") Long disciplinaId);

	@Query("SELECT opmat FROM OpMatriculaDisciplinaTurma opmat " +
			"WHERE opmat.oportunidadeMatricula.id = :idOportunidadeMat AND opmat.turma.id = :idTurma AND opmat.disciplina.id = :idDisciplina")
	public OpMatriculaDisciplinaTurma findByOportunidadeMatriculaIdAndDisciplinaIdAndTurmaId(Long idOportunidadeMat, Long idDisciplina, Long idTurma);
	
	@Query("SELECT opmat FROM OpMatriculaDisciplinaTurma opmat WHERE opmat.oportunidadeMatricula.id = :opMatId")
	public List<OpMatriculaDisciplinaTurma> findByOportunidadeMatriculaId(Long opMatId);
}
