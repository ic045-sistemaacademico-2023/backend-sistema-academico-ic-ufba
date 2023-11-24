package com.ic045.sistemaacademico.repositories;

import com.ic045.sistemaacademico.domain.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
	Optional<Turma> findById(Long id);
    List<Turma> findAllBySemestre(String nrsemestre);

    @Query("SELECT turma FROM Turma turma INNER JOIN AlunoTurma alunoTurma on turma.id = alunoTurma.turma.id WHERE alunoTurma.aluno.id = :alunoId")
    List<Turma> findAllByAlunosId(Long alunoId);

    List<Turma> findAll();
    List<Turma> findAllByDisciplinaId(Long disciplinaId);
    Optional<Turma> findFirstByOrderByIdDesc();
    
    @Query("SELECT turma FROM Turma turma "
    		+ "INNER JOIN OpMatriculaDisciplinaTurma opMatDiscTur on turma.id = opMatDiscTur.turma.id "
    		+ "INNER JOIN OportunidadeMatricula opMat on opMatDiscTur.oportunidadeMatricula.id  = opMat.id "
    		+ "WHERE opMat.aberta = true"
    		)
	List<Turma> findTurmasDisponiveisMatricula();

    @Query("SELECT turma FROM Turma turma " +
            "INNER JOIN OpMatriculaDisciplinaTurma opMatDiscTur " +
            "ON turma.id = opMatDiscTur.turma.id " +
            "WHERE turma.disciplina.curso.id = :cursoId")
    List<Turma> findTurmasDisponiveisPorCursoId(@Param("cursoId") Long cursoId);
}
