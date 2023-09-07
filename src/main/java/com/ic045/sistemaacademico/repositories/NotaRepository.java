package com.ic045.sistemaacademico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ic045.sistemaacademico.domain.models.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    
}
