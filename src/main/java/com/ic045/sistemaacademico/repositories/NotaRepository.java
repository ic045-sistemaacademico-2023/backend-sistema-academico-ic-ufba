package com.ic045.sistemaacademico.repositories;

import com.ic045.sistemaacademico.domain.models.Aluno;
import com.ic045.sistemaacademico.domain.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ic045.sistemaacademico.domain.models.Nota;

import java.util.Set;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    Set<Nota> findByAlunoAndTurma(Aluno aluno, Turma turma);
    
}
