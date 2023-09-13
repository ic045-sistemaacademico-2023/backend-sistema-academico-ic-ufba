package com.ic045.sistemaacademico.repositories;

import java.util.List;

import com.ic045.sistemaacademico.domain.dtos.TurmaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ic045.sistemaacademico.domain.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    @Query("SELECT new com.ic045.sistemaacademico.domain.dtos.TurmaDTO(t.id, t.disciplina.nome, t.dias, t.horario) " +
                  "FROM Turma t " +
                  "WHERE t.professor.id = :id")
    List<TurmaDTO> findAllTurmasByProfessorId(Long id);
}
