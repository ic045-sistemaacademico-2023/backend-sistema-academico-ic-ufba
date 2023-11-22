package com.ic045.sistemaacademico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ic045.sistemaacademico.domain.models.OportunidadeMatricula;

@Repository
public interface OportunidadeMatriculaRepository extends JpaRepository<OportunidadeMatricula, Long>{

	List<OportunidadeMatricula> findByCoordenadorId(Long id);
    
}
