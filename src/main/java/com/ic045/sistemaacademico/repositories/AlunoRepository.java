package com.ic045.sistemaacademico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ic045.sistemaacademico.domain.models.Aluno;
import com.ic045.sistemaacademico.domain.models.Turma;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    boolean existsByusuarioId(Long id);

    Aluno findByUsuarioId(Long id);
}
