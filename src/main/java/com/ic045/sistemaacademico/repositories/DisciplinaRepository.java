package com.ic045.sistemaacademico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ic045.sistemaacademico.domain.models.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    public List<Disciplina> findAllByCursoId(Long id);

    Long countByarea(String area);



}
