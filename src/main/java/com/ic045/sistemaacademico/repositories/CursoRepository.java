package com.ic045.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ic045.sistemaacademico.domain.models.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    
}
