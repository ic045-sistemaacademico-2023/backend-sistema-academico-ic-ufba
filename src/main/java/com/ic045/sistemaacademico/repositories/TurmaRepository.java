package com.ic045.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ic045.sistemaacademico.domain.models.Turma;

import java.util.List;
import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
	Optional<Turma> findById(Long id);
    Optional<List<Turma>> findBysemestre(String periodo);
    List<Turma> findAllByAlunosId(Long alunoId);
    List<Turma> findAll();
    List<Turma> findAllByDisciplinaId(Long disciplinaId);
}
