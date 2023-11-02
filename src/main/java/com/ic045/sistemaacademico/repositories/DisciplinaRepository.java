package com.ic045.sistemaacademico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.domain.models.Turma;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    public List<Disciplina> findAllByCursoId(Long id);

    @Query("SELECT t FROM Turma t WHERE t.disciplina.id = :disciplinaId")
    public List<Turma> findAllTurmasByDisciplinaId(@Param("disciplinaId") Long disciplinaId);

    Long countByarea(String area);

    List<Disciplina> findAll();
}
