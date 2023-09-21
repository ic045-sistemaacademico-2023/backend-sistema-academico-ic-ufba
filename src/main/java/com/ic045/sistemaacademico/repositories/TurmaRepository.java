package com.ic045.sistemaacademico.repositories;

import com.ic045.sistemaacademico.domain.models.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ic045.sistemaacademico.domain.models.Turma;

import java.util.List;
import java.util.Set;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    @Query("select t from Turma t where t.alunos = :alunoId")
    Set<Turma> findAllByAluno(@Param("alunoId") Long alunoId);
}
