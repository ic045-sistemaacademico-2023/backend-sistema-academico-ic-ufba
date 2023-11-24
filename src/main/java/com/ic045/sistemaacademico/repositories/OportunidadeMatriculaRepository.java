package com.ic045.sistemaacademico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ic045.sistemaacademico.domain.models.OportunidadeMatricula;

@Repository
public interface OportunidadeMatriculaRepository extends JpaRepository<OportunidadeMatricula, Long>{

	List<OportunidadeMatricula> findByCoordenadorId(Long id);

	@Query("SELECT opmat FROM OportunidadeMatricula opmat INNER JOIN CoordenadorDeCurso coord on coord.id = opmat.coordenador.id INNER JOIN Curso c on c.coordenadorDeCurso.id = coord.id WHERE c.id = :id")
	List<OportunidadeMatricula> findByCursoId(Long id);
	
	@Query("SELECT COUNT (opmat.id) FROM OportunidadeMatricula opmat WHERE opmat.coordenador.id = :coordenadorId AND opmat.aberta = true")
	int countAbertaByCoordenadorId(Long coordenadorId);
    
}
