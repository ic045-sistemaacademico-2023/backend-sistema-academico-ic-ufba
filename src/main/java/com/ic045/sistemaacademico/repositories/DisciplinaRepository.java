package com.ic045.sistemaacademico.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ic045.sistemaacademico.domain.models.Disciplina;
import com.ic045.sistemaacademico.domain.models.Turma;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    public List<Disciplina> findAllByCursoId(Long id);

    @Query("SELECT t FROM Turma t WHERE t.disciplina.id = :disciplinaId")
    public List<Turma> findAllTurmasByDisciplinaId(@Param("disciplinaId") Long disciplinaId);

    public Optional<Disciplina> findByCodigo(@Param("codigo") String codigo);
    
    Long countByarea(String area);

    List<Disciplina> findAll();
}
